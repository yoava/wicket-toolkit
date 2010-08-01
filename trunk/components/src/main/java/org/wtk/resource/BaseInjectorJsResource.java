package org.wtk.resource;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Page;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.markup.html.WebResource;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.response.StringResponse;
import org.apache.wicket.util.resource.AbstractResourceStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.util.string.interpolator.MapVariableInterpolator;
import org.apache.wicket.util.template.PackagedTextTemplate;
import org.apache.xml.serialize.Method;
import org.apache.xml.serialize.OutputFormat;
import org.cyberneko.html.parsers.DOMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.Map;

/**
 * @author Tomer Cohen
 */
public abstract class BaseInjectorJsResource extends WebResource {
	private static final Logger log = LoggerFactory.getLogger(BaseInjectorJsResource.class);

	public BaseInjectorJsResource() {
		setCacheable(false);
	}

	public abstract String getJavaScriptFilename();

	@Override
	public IResourceStream getResourceStream() {
		return new AbstractResourceStream() {
			@Override
			public InputStream getInputStream() throws ResourceStreamNotFoundException {
				try {
					return new ByteArrayInputStream(getScript().getBytes("utf-8"));
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}

			@Override
			public void close() throws IOException {
			}
		};

	}

	@Override
	protected void setHeaders(WebResponse response) {
		response.setHeader("Cache-Control", "no-store, no-cache, max-age=0, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setDateHeader("Date", System.currentTimeMillis());
	}

	private String getScript() {
		String script = new PackagedTextTemplate(BaseInjectorJsResource.class, getJavaScriptFilename()).getString();
		try {
			Map<String, String> params = getScriptParameters();
			return MapVariableInterpolator.interpolate(script, params);
		} catch (Exception e) {
			log.error(String.format("Can't get markup for "), e);
			return "";
		}
	}

	protected abstract Map<String, String> getScriptParameters() throws Exception;

	protected Document getPageDocument(Page page) throws SAXException, IOException {
		String markup = getPageMarkup(page);
		DOMParser parser = new DOMParser();
		parser.parse(new InputSource(new StringReader(markup)));
		return parser.getDocument();
	}

	protected String getPageMarkup(Page page) {
		RequestCycle requestCycle = RequestCycle.get();
		Response orgResponse = requestCycle.getResponse();
		StringResponse response = new StringResponse();
		requestCycle.setResponse(response);
		page.renderPage();
		requestCycle.setResponse(orgResponse);
		return response.toString();
	}

	public void fixRelativeUrls(Document document) throws XPathExpressionException {
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList list = (NodeList) xpath.evaluate("//*[@src]|//*[@href]", document, XPathConstants.NODESET);
		int length = list.getLength();

		for (int i = 0; i < length; i++) {
			Element element = (Element) list.item(i);
			fixRelativeUrl(element);
		}
	}

	protected void fixRelativeUrl(Element element) {
		String src = element.getAttribute("src");
		if (StringUtils.isNotEmpty(src)) {
			element.setAttribute("src", toAbsolutePath(src));
		}

		String href = element.getAttribute("href");
		if (StringUtils.isNotEmpty(href)) {
			element.setAttribute("href", toAbsolutePath(href));
		}
	}

	protected String toAbsolutePath(String path) {
		if (StringUtils.isEmpty(path) || path.startsWith("http")) {
			return path;
		}
		if (path.startsWith("/")) {
			return getServerRoot() + path;
		}
		return RequestUtils.toAbsolutePath(path);
	}

	protected String getServerRoot() {
		final WebRequest webRequest = (WebRequest) RequestCycle.get().getRequest();
		final HttpServletRequest servletRequest = webRequest.getHttpServletRequest();
		final StringBuffer requestURL = servletRequest.getRequestURL();
		final String requestURI = servletRequest.getRequestURI();
		final int end = requestURL.length() - requestURI.length();
		return requestURL.substring(0, end);
	}

	protected String getInnerHtml(String html, String tagName) {
		tagName = tagName.toUpperCase();

		String openTag = "<" + tagName;
		String closeTag = "</" + tagName + ">";
		int beginIndex = html.indexOf(openTag);
		if (beginIndex < 0) {
			return "";
		}
		beginIndex = html.indexOf('>', beginIndex);
		if (beginIndex < 0) {
			return "";
		}
		beginIndex++;

		int endIndex = html.indexOf(closeTag, beginIndex);
		if (endIndex < 0) {
			return "";
		}
		return html.substring(beginIndex, endIndex);
	}

	protected String serializeDocument(Document document) throws IOException, TransformerException {
		// create transformer
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, Method.HTML);
		transformer.setOutputProperty(OutputKeys.MEDIA_TYPE, OutputFormat.whichMediaType(Method.HTML));
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

		// serialize
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source source = new DOMSource(document.getDocumentElement());
		transformer.transform(source, new StreamResult(new OutputStreamWriter(outputStream, "UTF-8")));
		return outputStream.toString("UTF-8");
	}
}