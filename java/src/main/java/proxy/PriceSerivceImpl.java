package proxy;

/**
 * Created by zhuchunliu on 2017/1/6.
 */
public class PriceSerivceImpl {
    public Integer getPayNum(Integer price) {
        System.err.println("单价: "+price);
        return 100;
    }
}
