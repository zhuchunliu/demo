package proxy;

/**
 * Created by zhuchunliu on 2017/1/3.
 */
public interface PayService {
    Integer getPayNum(String uid);

    void payMoney(String uid ,Integer num);
}
