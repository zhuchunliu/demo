package thread.static_test;

import java.io.Serializable;

/**
 * Created by zhuchunliu on 2017/6/5.
 */
public class ThreadTest {

    class A extends Thread{
        private Service service;
        public A(Service service){
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            this.service.methodA();
        }
    }

    class B extends Thread{
        private Service service;
        public B(Service service){
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            Service.methodB();
        }
    }

    class C extends Thread{
        private Service service;
        public C(Service service){
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            this.service.methodC();
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        new ThreadTest().new A(service).start();
        new ThreadTest().new B(service).start();
        new ThreadTest().new C(service).start();
    }
}
