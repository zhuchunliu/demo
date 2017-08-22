package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhuchunliu on 2017/1/3.
 */
public class JdkProxy implements InvocationHandler{

    private Object targetObject = null;

    public Object newProxy(Object target) {
        this.targetObject = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);

    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return method.invoke(this.targetObject,args);
    }
}
