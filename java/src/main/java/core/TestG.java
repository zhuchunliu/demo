package core;

/**
 * Created by zhuchunliu on 2017/6/1.
 */
public class TestG {
    static {
        final int age =1;
    }
    {
         final int dd = 10;
    }
    public static void main(String[] args) {
        b:
//        System.err.println("begin");
        for(int i =0;i<10;i++){
            for(int k =0 ;k<10;k++){
                if(k >=2){
                    break b;
                }
                System.err.println(" k :"+k);
            }
            System.err.println(" i :"+i);
        }
        System.err.println("over");
    }

}
