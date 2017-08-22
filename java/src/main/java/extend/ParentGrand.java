package extend;

/**
 * Created by zhuchunliu on 2017/8/8.
 */
public class ParentGrand {
    public void test(){
        System.err.println("ParentGrand");
    }

    public void init(){
        this.test();
    }
}
