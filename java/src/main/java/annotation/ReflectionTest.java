package annotation;


import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import reflect.China;

/**
 * Created by zhuchunliu on 2017/8/9.
 */
public class ReflectionTest {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("annotation");
        reflections.getSubTypesOf(China.class);

    }
}
