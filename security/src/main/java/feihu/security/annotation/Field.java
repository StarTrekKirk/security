package feihu.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记实体属性，方便生成实体对应表单的表单项属性
 * @author heihuhu
 * @createdate 2018年2月12日
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Field {

	String name() default "";

	String caption() default "";

	FieldType type() default FieldType.TEXT;

	String[] options() default {};
}
