package org.wtk.util.markup;

import org.apache.wicket.Application;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.*;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.UrlResourceStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Yoav Aharoni
 */
public class ExternalMarkupSupport implements ExternalMarkup {
	private static final Logger log = LoggerFactory.getLogger(ExternalMarkupSupport.class);
	private static final IMarkupResourceStreamProvider DEFAULT_STREAM_PROVIDER = new DefaultMarkupResourceStreamProvider();
	private static final IMarkupCacheKeyProvider DEFAULT_CACHE_KEY_PROVIDER = new DefaultMarkupCacheKeyProvider();

	private URL url;
	private String cacheKeyPrefix = "";
	private Class<? extends MarkupContainer> componentClass;

	public ExternalMarkupSupport(Class<? extends MarkupContainer> componentClass) {
		this.componentClass = componentClass;
	}

	public ExternalMarkupSupport(Class<? extends MarkupContainer> componentClass, URL url) {
		this.componentClass = componentClass;
		this.url = url;
	}

	public ExternalMarkupSupport(Class<? extends MarkupContainer> componentClass, String url) {
		this.componentClass = componentClass;
		setUrl(url);
	}

	public ExternalMarkupSupport setUrl(URL url) {
		this.url = url;
		this.cacheKeyPrefix = url.toExternalForm();
		return this;
	}

	public ExternalMarkupSupport setUrl(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("Malformed URL " + url, e);
		}
		return this;
	}

	public URL getURL() {
		return url;
	}

	@Override
	public String getCacheKey(MarkupContainer container, Class containerClass) {
		return cacheKeyPrefix + DEFAULT_CACHE_KEY_PROVIDER.getCacheKey(container, containerClass);
	}

	@Override
	public IResourceStream getMarkupResourceStream(MarkupContainer container, Class containerClass) {
		if (containerClass.equals(componentClass) && isUrlAvailable(url)) {
			return new MarkupResourceStream(new UrlResourceStream(url), new ContainerInfo(container), containerClass);
		}
		return DEFAULT_STREAM_PROVIDER.getMarkupResourceStream(container, containerClass);
	}

	private boolean isUrlAvailable(URL url) {
		if (url != null) {
			try {
				final URLConnection connection = url.openConnection();
				if (connection instanceof HttpURLConnection) {
					HttpURLConnection urlConnection = (HttpURLConnection) connection;
					urlConnection.connect();
					int responseCode = urlConnection.getResponseCode();
					final boolean statusOk = responseCode == HttpServletResponse.SC_OK;
					if (!statusOk) {
						log.warn(String.format("Got status %s while loading \"%s\" URL. Falling back to default markup.", responseCode, url));
					}
					return statusOk;
				} else {
					return true;
				}

			} catch (IOException e) {
				log.warn(String.format("IOException while loading \"%s\" URL. Falling back to default markup.", url), e);
			}
		}
		return false;
	}

	public void clearCache() {
		final IMarkupCache cache = Application.get().getMarkupSettings().getMarkupCache();
		cache.
	}

	public static void clearAllMarkupCache() {
		Application.get().getMarkupSettings().getMarkupCache().clear();
	}
}
