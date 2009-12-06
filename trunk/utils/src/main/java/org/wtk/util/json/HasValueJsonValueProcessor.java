package org.wtk.util.json;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import org.wtk.model.HasValue;

/**
 * @author Yoav Aharoni
 */
public class HasValueJsonValueProcessor implements JsonValueProcessor {
	private static final HasValueJsonValueProcessor INSTANCE = new HasValueJsonValueProcessor();

	public static HasValueJsonValueProcessor getInstance() {
		return INSTANCE;
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		final HasValue hasValue = (HasValue) value;
		return JSONArray.fromObject(hasValue.getValue());
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		final HasValue hasValue = (HasValue) value;
		return toJSON(hasValue.getValue());
	}

	private Object toJSON(Object o) {
		return JSONArray.fromObject(new Object[]{o}).get(0);
	}
}
