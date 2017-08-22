package proxy;

/**
 * Created by zhuchunliu on 2017/1/3.
 */
public class PayServiceImpl implements PayService{

    public Integer getPayNum(String uid) {
        System.err.println("需要支付100");
        return 100;
    }

    public void payMoney(String uid, Integer num) {
        System.err.println("支付成功");
    }
}
