package core;

/**
 * Created by zhuchunliu on 2017/6/2.
 */
public class TestL {
    class Car{
        public int age = 10;
        public int year = 2017;
    }

     Car test(){
        Car car = new Car();
        try{
            return car;
        }catch (Exception ex){

        }finally {
            car.age = 100;
        }
        return null;
    }

    public static void main(String[] args) {
        System.err.println( new TestL().test().age);
    }
}
