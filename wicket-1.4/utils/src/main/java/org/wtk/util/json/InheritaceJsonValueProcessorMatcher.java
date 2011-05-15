package org.wtk.util.json;

import net.sf.json.processors.JsonValueProcessorMatcher;

import java.util.Set;

/**
 * Matches value processor by inheritance.
 *
 * @author Yoav Aharoni
 */
public class InheritaceJsonValueProcessorMatcher extends JsonValueProcessorMatcher {
	private static final JsonValueProcessorMatcher INSTANCE = new InheritaceJsonValueProcessorMatcher();

	@SuppressWarnings({"unchecked"})
	@Override
	public Object getMatch(Class target, Set set) {
		for (Class clazz : (Set<Class>) set) {
			if (clazz.isAssignableFrom(target)) {
				return clazz;
			}
		}
		return null;
	}

	public static JsonValueProcessorMatcher getInstance() {
		return INSTANCE;
	}
}
