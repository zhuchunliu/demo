package jdk8;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.sun.javafx.applet.ExperimentalExtensions.get;

/**
 * Created by Darren on 2017/9/24.
 */
public class OptionalDemo {
    public static void main(String[] args) throws Throwable {
        //创建Optional实例，也可以通过方法返回值得到。
        Optional<String> name = Optional.of("jack");

        //创建没有值的Optional实例，例如值为'null'
        Optional empty = Optional.ofNullable(null);

        //isPresent方法用来检查Optional实例是否有值。
        if (name.isPresent()) {
            //调用get()返回Optional值。
            System.out.println(name.get());
        }

        try {
            //在Optional实例上调用get()抛出NoSuchElementException。
            System.out.println(empty.get());
        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
        }

        Optional.of("jack").ifPresent((value)->System.err.println("默认consumer方法"+value));

        //如果有值orElse方法会返回Optional实例，否则返回传入的错误信息。
        System.out.println(empty.orElse("There is no value present!"));
        System.out.println(name.orElse("There is some value!"));

        //orElseGet与orElse类似，区别在于传入的默认值。
        //orElseGet接受lambda表达式生成默认值。
        System.out.println(empty.orElseGet(() -> "Default Value"));
        System.out.println(name.orElseGet(() -> "Default Value"));

        try {
            //orElseThrow与orElse方法类似，区别在于返回值。
            //orElseThrow抛出由传入的lambda表达式/方法生成异常。
            empty.orElseThrow(NullPointerException::new);
        } catch (Throwable ex) {
            System.out.println(ex.getMessage());
        }


        Optional<String> upperName = Optional.ofNullable("jack").map((value) -> null);

        System.err.println(upperName.isPresent()+"===");

        Optional.ofNullable("jack").flatMap((value) -> Optional.of(value.toUpperCase()));
        System.out.println(upperName.orElse("No value found"));//输出SANAULLA

        Optional<String> longName = Optional.ofNullable("jack is man").filter((value) -> value.length() > 6);
        System.out.println(longName.orElse("The name is less than 6 characters"));
    }
}
