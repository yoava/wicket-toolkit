package org.wtk.component.border.support;

/**
 * @author Yoav Aharoni
 */
public interface IBorderSupport<C extends IBorderSupport, T> {
	T getBorder();

	C replaceBorder(T border);

	C removeBorder();

	C wrap(T border);

	C wrapOuterBorder(T border);
}
