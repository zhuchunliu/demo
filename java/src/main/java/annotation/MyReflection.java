package annotation;


import java.lang.reflect.Method;

/**
 * Created by zhuchunliu on 2016/12/27.
 */
public class MyReflection {

    public static void main(String[] args) {
        if(AnnotationTest.class.isAnnotationPresent(MyTargetA.class)){
            System.err.println("=====");
        }
        Method[] methods = AnnotationTest.class.getMethods();
        for(Method method :methods){
            if(method.isAnnotationPresent(MyTarget.class)){
                System.err.println(method.getName()+" 注解");
                MyTarget target = method.getDeclaredAnnotation(MyTarget.class);
                System.out.println(target.arr()[0]+" == "+target.name());
            }else{
//                System.out.println(method.getName());
            }
        }



        System.err.println(AnnotationTest.class.isAnnotation());
        System.err.println(MyTarget.class.isAnnotation());
    }
}
