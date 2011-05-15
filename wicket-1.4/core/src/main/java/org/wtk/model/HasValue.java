package org.wtk.model;

import java.io.Serializable;

/**
 * @author Yoav Aharoni
 */
public interface HasValue<T> extends Serializable {
	T getValue();
}
