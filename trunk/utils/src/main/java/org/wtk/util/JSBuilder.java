package org.wtk.util;

import net.sf.json.JSONArray;
import org.apache.wicket.util.string.JavascriptStripper;

import java.io.Serializable;

/**
 * @author Yoav Aharoni
 */
public class JSBuilder implements CharSequence, Appendable, Serializable {
	private StringBuilder script = new StringBuilder();

	public JSBuilder() {
	}

	public JSBuilder format(String javascript, Object... parameters) {
		script.append(String.format(javascript, parameters));
		return this;
	}

	public JSBuilder function(String... parameters) {
		script.append("function(");
		list(parameters);
		script.append(") {");
		return this;
	}

	public JSBuilder startScope(String... paramNames) {
		return append('(').function(paramNames).append('\n');
	}

	public JSBuilder endScope(String... paramValues) {
		append("})(");
		list(paramValues);
		return append(");");
	}

	public JSBuilder end() {
		script.append('}');
		return this;
	}

	public JSBuilder call(String functionName, Object... parameters) {
		script.append(functionName).append("(");
		commaList(parameters);
		script.append(")");
		return this;
	}

	public JSBuilder array(Object... items) {
		script.append("[");
		commaList(items);
		script.append("]");
		return this;
	}

	public JSBuilder object(Object object) {
		return commaList(object);
	}

	public JSBuilder commaList(Object... parameters) {
		final JSONArray json = JSONArray.fromObject(parameters);
		script.append(json.join(","));
		return this;
	}

	public JSBuilder clear() {
		script = new StringBuilder();
		return this;
	}

	public JSBuilder compress() {
		final String compressed = JavascriptStripper.stripCommentsAndWhitespace(script.toString());
		script = new StringBuilder(compressed);
		return this;
	}

	@Override
	public JSBuilder append(CharSequence javascript) {
		script.append(javascript);
		return this;
	}

	@Override
	public JSBuilder append(char c) {
		script.append(c);
		return this;
	}

	@Override
	public JSBuilder append(CharSequence javascript, int start, int end) {
		script.append(javascript, start, end);
		return this;
	}

	@Override
	public String toString() {
		return script.toString();
	}

	@Override
	public int length() {
		return script.length();
	}

	@Override
	public char charAt(int index) {
		return script.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return script.subSequence(start, end);
	}

	private void list(String[] parameters) {
		for (int i = 0, parametersLength = parameters.length; i < parametersLength; i++) {
			if (i > 0) {
				script.append(',');
			}
			script.append(parameters[i]);
		}
	}
}
