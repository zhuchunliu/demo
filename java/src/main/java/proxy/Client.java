package proxy;

/**
 * Created by zhuchunliu on 2017/1/6.
 */
public class Client {
    public static void main(String[] args) {
        PayService CGLPay = (PayService) new CGLibProxy().createProxyObject(new PayServiceImpl());
        CGLPay.payMoney(null,null);

        PriceSerivceImpl priceSerivce = (PriceSerivceImpl)new CGLibProxy().createProxyObject(new PriceSerivceImpl());
        priceSerivce.getPayNum(88);

        PayService jdkPay = (PayService)new JdkProxy().newProxy(new PayServiceImpl());
        jdkPay.getPayNum(null);

    }
}
