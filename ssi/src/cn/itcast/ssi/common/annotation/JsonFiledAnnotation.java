package cn.itcast.ssi.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JsonFiledAnnotation {

	String name();

	Class<?> clazz();

	boolean translateIntoJSON() default true;
}
