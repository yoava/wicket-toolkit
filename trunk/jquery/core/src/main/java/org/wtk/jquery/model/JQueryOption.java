package org.wtk.jquery.model;

import java.lang.annotation.*;

/**
 * @author Yoav Aharoni
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JQueryOption {
	String value() default "";
}
