package org.wtk.behavior.head;

import org.apache.wicket.Component;
import org.apache.wicket.IClusterable;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.apache.wicket.util.io.Streams;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.util.string.JavascriptStripper;
import org.apache.wicket.util.string.interpolator.PropertyVariableInterpolator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wtk.application.CurrentPageSupport;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Improved version of HeaderContributor: <ul>
 * <li>Naming conventions for default file name.</li>
 * <li>Supports interpolated templates.</li>
 * <li>May hold more than one resource.</li> </ul>
 *
 * @author Yoav Aharoni
 */
public class HeadResource extends AbstractBehavior implements IHeaderContributor {
	private static final MetaDataKey REPLACE_KEY = new MetaDataKey(HashMap.class) {
	};

	private static final Logger log = LoggerFactory.getLogger(HeadResource.class);

	private Class<?> replaceScope;
	private Class<?> scope;
	private List<Resource> resources = new ArrayList<Resource>();

	public HeadResource(Class<?> scope) {
		this.scope = scope;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		// check for replacement
		if (renderReplacement(response)) {
			return;
		}

		// render resources
		final Context context = new RenderContext();
		for (Resource resource : resources) {
			resource.renderHead(response, context);
		}
	}

	@Override
	public void bind(Component component) {
		registerReplacement();
	}

	public HeadResource replaces(Class<?> replaceScope) {
		this.replaceScope = replaceScope;
		return this;
	}

	public HeadResource replaces(HeadResource headResource) {
		return replaces(headResource.scope);
	}

	public HeadResource dependsOn(IHeaderContributor headerContributor) {
		resources.add(0, new DependencyResource(headerContributor));
		return this;
	}

	public HeadResource addCss() {
		resources.add(new CssResource(scope.getSimpleName() + ".css", null));
		return this;
	}

	public HeadResource addCss(String relativePath) {
		resources.add(new CssResource(relativePath, null));
		return this;
	}

	public HeadResource addCss(String relativePath, String media) {
		resources.add(new CssResource(relativePath, media));
		return this;
	}

	public HeadResource addJavaScript() {
		resources.add(new JsResource(scope.getSimpleName() + ".js"));
		return this;
	}

	public HeadResource addJavaScript(String relativePath) {
		resources.add(new JsResource(relativePath));
		return this;
	}

	public HeadResource clearCache() {
		for (Resource resource : resources) {
			resource.clearCache();
		}
		return this;
	}

	public HeadResource setCached(boolean cached) {
		if (cached) {
			getResource().cacheLock = new ReentrantReadWriteLock();
		} else {
			getResource().cacheLock = null;
		}
		return this;
	}

	public HeadResource setIncludeOnce(boolean includeOnce) {
		getResource().includeOnce = includeOnce;
		return this;
	}

	public HeadResource setInline(boolean inline) {
		getResource().inline = inline;
		return this;
	}

	public HeadResource setTemplateModel(Object templateModel) {
		getResource().templateModel = templateModel;
		return this;
	}

	/**
	 * Checks if this resource is replaced by another resource.
	 * If so, renders replacing resource.
	 *
	 * @param response HeaderResponse
	 * @return true if this resource is replaced by another.
	 */
	private boolean renderReplacement(IHeaderResponse response) {
		final Page page = CurrentPageSupport.getCurrentPage();
		if (page != null) {
			final HashMap<Class, HeadResource> map = getReplaceMap(page);
			if (map != null) {
				final HeadResource replaceWith = map.get(scope);
				if (replaceWith != null) {
					replaceWith.renderHead(response);
					return true;
				}
			}
		}
		return false;
	}

	private void registerReplacement() {
		if (replaceScope == null) {
			return;
		}

		final Page page = CurrentPageSupport.getCurrentPage();
		if (page == null) {
			log.warn(String.format("%s resource cannot replace %s resource since current containing page cannot be detected", scope.getName(), replaceScope.getName()));
			return;
		}
		HashMap<Class, HeadResource> map = getReplaceMap(page);
		if (map == null) {
			map = new HashMap<Class, HeadResource>();
			page.setMetaData(REPLACE_KEY, map);
		}
		map.put(replaceScope, this);
	}

	private BaseResource getResource() {
		final Resource resource = resources.get(resources.size() - 1);
		if (resource instanceof BaseResource) {
			return (BaseResource) resource;
		}
		throw new RuntimeException("Setters can only be applied after call to addCSS() or JavaScript()");
	}

	private class RenderContext implements Context {
		@Override
		public Class<?> getScope() {
			return scope;
		}
	}

	private static interface Context extends IClusterable {
		Class<?> getScope();
	}

	private static interface Resource extends IClusterable {
		void renderHead(IHeaderResponse response, Context context);

		void clearCache();
	}

	private static class DependencyResource implements Resource {
		private IHeaderContributor contributor;

		private DependencyResource(IHeaderContributor contributor) {
			this.contributor = contributor;
		}

		@Override
		public void renderHead(IHeaderResponse response, Context context) {
			contributor.renderHead(response);
		}

		@Override
		public void clearCache() {
		}
	}

	private static class CssResource extends BaseResource {
		private String media;

		private CssResource(String path, String media) {
			super(path);
			this.media = media;
		}

		@Override
		protected String prepare(String content, Context context) {
			StringBuilder markup = new StringBuilder();
			markup.append("<style type=\"text/css\"");
			final String id = getId(context);
			if (id != null) {
				markup.append(" id=\"").append(id).append("\"");
			}
			if (media != null) {
				markup.append(" media=\"").append(media).append("\"");
			}
			markup.append(">\n").append(content)
					.append("\n</style>\n");
			return markup.toString();
		}

		@Override
		protected void renderInline(String content, IHeaderResponse response, Context context) {
			response.renderString(content);
		}

		@Override
		protected void renderReference(IHeaderResponse response, Context context) {
			response.renderCSSReference(new CompressedResourceReference(context.getScope(), path), media);
		}
	}

	private static class JsResource extends BaseResource {
		private JsResource(String path) {
			super(path);
		}

		@Override
		protected String prepare(String content, Context context) {
			return JavascriptStripper.stripCommentsAndWhitespace(content);
		}

		@Override
		protected void renderInline(String content, IHeaderResponse response, Context context) {
			response.renderJavascript(content, getId(context));
		}

		@Override
		protected void renderReference(IHeaderResponse response, Context context) {
			response.renderJavascriptReference(new JavascriptResourceReference(context.getScope(), path));
		}
	}

	private static abstract class BaseResource implements Resource {
		protected ReentrantReadWriteLock cacheLock;
		protected String cache;

		protected String path;
		protected Object templateModel;
		protected boolean includeOnce = true;
		protected boolean inline;

		protected BaseResource(String path) {
			this.path = path;
		}

		@Override
		public void renderHead(IHeaderResponse response, Context context) {
			if (!inline && templateModel == null) {
				renderReference(response, context);
				return;
			}
			if (cacheLock != null) {
				renderFromCache(response, context);
				return;
			}

			String content = getContent(context);
			renderInline(content, response, context);
		}

		public void clearCache() {
			if (cacheLock != null) {
				cacheLock.writeLock().lock();
				cache = null;
				cacheLock.writeLock().unlock();
			}
		}

		private String getContent(Context context) {
			String content = readResource(context);
			if (templateModel != null) {
				content = PropertyVariableInterpolator.interpolate(content, templateModel);
			}
			content = prepare(content, context);
			return content;
		}

		private String readResource(Context context) {
			try {
				PackageResource resource = PackageResource.get(context.getScope(), path);
				InputStream inputStream = resource.getResourceStream().getInputStream();
				return Streams.readString(inputStream, "utf-8");
			} catch (ResourceStreamNotFoundException e) {
				throw new RuntimeException(String.format("Can't find resource \"%s.%s\"", context.getScope().getName(), path), e);
			} catch (IOException e) {
				throw new RuntimeException(String.format("Can't read resource \"%s.%s\"", context.getScope().getName(), path), e);
			}
		}

		private void renderFromCache(IHeaderResponse response, Context context) {
			cacheLock.readLock().lock();
			if (cache == null) {
				cacheLock.readLock().unlock();
				cacheLock.writeLock().lock();
				if (cache == null) {
					cache = getContent(context);
				}
				cacheLock.readLock().lock();
				cacheLock.writeLock().unlock();
			}

			renderInline(cache, response, context);
			cacheLock.readLock().unlock();
		}

		protected String getId(Context context) {
			if (includeOnce) {
				final String scope = context.getScope().getName();
				final int dotIndex = scope.lastIndexOf(".");
				if (dotIndex < 0) {
					return path;
				}
				return scope.substring(0, dotIndex + 1) + path;
			}
			return null;
		}

		protected abstract String prepare(String content, Context context);

		protected abstract void renderInline(String content, IHeaderResponse response, Context context);

		protected abstract void renderReference(IHeaderResponse response, Context context);
	}

	@SuppressWarnings({"unchecked"})
	private static HashMap<Class, HeadResource> getReplaceMap(Page page) {
		return (HashMap<Class, HeadResource>) page.getMetaData(REPLACE_KEY);
	}
}
