package org.wtk.jquery.ui.util;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.wicket.util.lang.PropertyResolver;
import org.wtk.jquery.model.JQueryOption;
import org.wtk.model.HasValue;
import org.wtk.util.json.HasValueJsonValueProcessor;
import org.wtk.util.json.InheritaceJsonValueProcessorMatcher;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 * @author Yoav Aharoni
 */
public class JQueryOptionSerializer<T> {
	private Collection<Option> optionsProperties = new ArrayList<Option>();

	public JQueryOptionSerializer(Class<T> optionClass) {
		final PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(optionClass);
		for (PropertyDescriptor descriptor : descriptors) {
			final Method readMethod = descriptor.getReadMethod();
			if (readMethod != null) {
				final JQueryOption annotation = readMethod.getAnnotation(JQueryOption.class);
				if (annotation != null) {
					final String name = annotation.name();
					final String propName = descriptor.getName();
					final Option option = isEmpty(name) ? new Option(propName) : new Option(propName, name);
					optionsProperties.add(option);
				}
			}
		}
	}

	public Map<String, Object> toMap(T options) {
		final Map<String, Object> map = new HashMap<String, Object>(optionsProperties.size());
		for (Option option : optionsProperties) {
			final Object value = PropertyResolver.getValue(option.property, options);
			map.put(option.optionName, value);
		}
		return map;
	}

	public JSONObject toJSON(T options) {
		final Map<String, Object> map = toMap(options);
		final JsonConfig config = new JsonConfig();
		config.setJsonValueProcessorMatcher(InheritaceJsonValueProcessorMatcher.getInstance());
		config.registerJsonValueProcessor(HasValue.class, HasValueJsonValueProcessor.getInstance());
		return JSONObject.fromObject(map, config);
	}

	private static class Option {
		private String optionName;
		private String property;

		private Option(String property) {
			this.property = property;
			this.optionName = property;
		}

		private Option(String property, String optionName) {
			this.property = property;
			this.optionName = optionName;
		}
	}

}
