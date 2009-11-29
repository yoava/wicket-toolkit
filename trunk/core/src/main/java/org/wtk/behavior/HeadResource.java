package org.wtk.behavior;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.AbstractHeaderContributor;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.apache.wicket.util.io.Streams;
import org.apache.wicket.util.string.CssUtils;
import org.apache.wicket.util.string.interpolator.PropertyVariableInterpolator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Improved version of HeaderContributor: <ul>
 * <li>Naming conventions for default file name.</li>
 * <li>Supports interpolated templates.</li>
 * <li>May hold more than one resource.</li> </ul>
 *
 * @author Yoav Aharoni
 */
public class HeadResource extends AbstractHeaderContributor {
	private List<IHeaderContributor> tempList = new ArrayList<IHeaderContributor>();
	private Class<?> scope;
	private IHeaderContributor[] headerContributors;

	protected HeadResource() {
		scope = getClass();
	}

	public HeadResource(Class<?> scope) {
		this.scope = scope;
	}

	public HeadResource dependsOn(HeadResource resourcePackage) {
		tempList.addAll(0, asList(resourcePackage.getHeaderContributors()));
		return this;
	}

	public HeadResource addCss() {
		return addCss(getDefalutCssPath());
	}

	public HeadResource addJavaScript() {
		return addJavaScript(getDefaultJavaScriptPath());
	}

	public HeadResource addCssTemplate() {
		return addCssTemplate(getDefalutCssPath());
	}

	public HeadResource addJavaScriptTemplate() {
		return addJavaScriptTemplate(getDefaultJavaScriptPath());
	}

	public HeadResource addCss(final String path) {
		return addCss(path, null);
	}

	public HeadResource addCss(final String path, final String media) {
		addContributor(new IHeaderContributor() {
			@Override
			public void renderHead(IHeaderResponse response) {
				response.renderCSSReference(new CompressedResourceReference(scope, path), media);
			}
		});
		return this;
	}

	public HeadResource addJavaScript(final String path) {
		addContributor(new IHeaderContributor() {
			@Override
			public void renderHead(IHeaderResponse response) {
				response.renderJavascriptReference(new JavascriptResourceReference(scope, path));
			}
		});
		return this;
	}

	public HeadResource addCssTemplate(final String path) {
		addContributor(new IHeaderContributor() {
			@Override
			public void renderHead(IHeaderResponse response) {
				response.renderString(
						CssUtils.INLINE_OPEN_TAG + readInterpolatedString(path) + CssUtils.INLINE_CLOSE_TAG);
			}
		});
		return this;
	}

	public HeadResource addJavaScriptTemplate(final String path) {
		addContributor(new IHeaderContributor() {
			@Override
			public void renderHead(IHeaderResponse response) {
				response.renderJavascript(readInterpolatedString(path), null);
			}
		});
		return this;
	}

	public String getResourceURL(String path) {
		ResourceReference reference = new ResourceReference(scope, path);
		return RequestCycle.get().urlFor(reference).toString();
	}

	/**
	 * @see AbstractHeaderContributor#getHeaderContributors()
	 */
	@Override
	public final IHeaderContributor[] getHeaderContributors() {
		return createHeaderContributors();
	}

	public static HeadResource forCss(Class scope) {
		return new HeadResource(scope).addCss();
	}

	public static HeadResource forJavaScript(Class scope) {
		return new HeadResource(scope).addJavaScript();
	}

	private String getDefaultJavaScriptPath() {
		return scope.getSimpleName() + ".js";
	}

	private String getDefalutCssPath() {
		return scope.getSimpleName() + ".css";
	}

	private String readInterpolatedString(String path) {
		try {
			PackageResource resource = PackageResource.get(scope, path);
			InputStream inputStream = resource.getResourceStream().getInputStream();
			String script = Streams.readString(inputStream);
			return PropertyVariableInterpolator.interpolate(script, this);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private IHeaderContributor[] createHeaderContributors() {
		if (headerContributors == null) {
			headerContributors = new IHeaderContributor[tempList.size()];
			tempList.toArray(headerContributors);
			tempList = null;
		}
		return headerContributors;
	}

	private void addContributor(IHeaderContributor contributor) {
		if (tempList == null) {
			throw new RuntimeException("Can't add contributors after render phase!");
		}
		tempList.add(contributor);
	}
}
