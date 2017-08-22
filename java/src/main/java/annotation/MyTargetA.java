package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhuchunliu on 2017/8/9.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
public @interface MyTargetA {
    String name() default "jack";
    String[] arr() default {"a","b"};

}
