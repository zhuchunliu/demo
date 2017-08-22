package annotation;

/**
 * Created by zhuchunliu on 2016/12/27.
 */
@MyTarget
@MyTargetA
public class AnnotationTest {

    @MyTarget
    private String id;

    public void test(@MyTarget String info){

    }
    @MyTarget(arr = {"hello"})
    public void hello(){

    }


}
