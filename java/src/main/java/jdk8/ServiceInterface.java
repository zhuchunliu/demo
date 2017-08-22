package jdk8;

/**
 * Created by zhuchunliu on 2017/1/17.
 */
public interface ServiceInterface {


    interface demoInterface {

        default String getName() {
            return "jack";
        }

        default int getAge() {
            return 10;
        }

        int getNum();
    }

    class demo implements demoInterface{

        @Override
        public int getNum() {
            return 100;
        }
    }

    public static void main(String[] args) {
        ServiceInterface.demo demo = new ServiceInterface.demo();
        System.err.println(demo.getName());
        System.err.println(demo.getNum());
        System.err.println(demo.getAge());

    }
}
