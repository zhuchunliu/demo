package reflect;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhuchunliu on 2017/1/12.
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception{
        System.err.println(new Person().getClass().getName());
        System.err.println(Person.class.getName());
        System.err.println(Class.forName("reflect.Person").getName());

        Person person = (Person) Class.forName("reflect.Person").newInstance();
        person.setAge(25);
        person.setName("张三");
        System.err.println(person);

        Class<?> obj = Class.forName("reflect.Person");
        Constructor[] cs = obj.getConstructors();
        System.err.println("构造函数 "+cs.length);
        for(Constructor constructor :cs){
            System.err.println(constructor+"  "+ Modifier.toString(constructor.getModifiers()));
            Class<?>[] types = constructor.getParameterTypes();
        }

        Person person1 = (Person)cs[0].newInstance(20);
        Person person2 = (Person)cs[1].newInstance("张三");
        Person person3 = (Person)cs[2].newInstance("张三",20);
        Person person4 = (Person)cs[3].newInstance();
        System.err.println(person1);
        System.err.println(person2);
        System.err.println(person3);
        System.err.println(person4);

        Class<?>[] interfaces = obj.getInterfaces();
        for(Class inter :interfaces){
            System.err.println("接口 "+inter.getName());
        }

        Class<?> parent = obj.getSuperclass();
        System.err.println("父类 "+parent.getName());

        Method[] methods = obj.getMethods();
        for(Method method : methods){
            for(Class exception :method.getExceptionTypes()){
                System.err.println(method.getName()+"  "+exception.getName());
            }
        }

        Field[] fields = obj.getFields();
        for(Field field : fields){
            System.err.println(field.getName());
        }

        Method method1 = obj.getMethod("sayChina");
        method1.invoke(obj.newInstance());

        Method method2 = obj.getMethod("sayHello",String.class,Integer.class);
        method2.invoke(obj.newInstance(),"tom",22);

        Method method3 = obj.getMethod("sayHello",String.class,int.class);
        Object value = method3.invoke(obj.newInstance(),"jack",30);
        System.err.println("返回值"+value);

        Field field = obj.getDeclaredField("age");  //getField只能获取public字段
        Object object = obj.newInstance();
        field.setAccessible(true);//给private变量设置值
        field.set(object,100);
        System.err.println(field.get(object));


        System.err.println(obj.newInstance() == object); // 每次新new的对象都不一样

        int[] arr = {1,2,3,5,7};
        System.err.println(Array.getLength(arr));
        Object arr1 = Array.newInstance(arr.getClass().getComponentType(),arr.length);
        System.arraycopy(arr,0,arr1,0,3);
        int[] arr2 = (int[])arr1;
        System.err.println(arr2+"  ");


        List<String> list = Arrays.asList("jack");
        System.err.println(arr.getClass().getName()+"  "+arr.getClass().getComponentType().getName());
        System.err.println(list.getClass().getName()+"  "+list.getClass().getComponentType());
    }
}
