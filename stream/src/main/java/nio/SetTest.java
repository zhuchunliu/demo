package nio;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhuchunliu on 2017/8/16.
 */
public class SetTest {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add(12);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
//            System.out.print(iterator.next());
            iterator.next();
            iterator.remove();
        }
        System.err.print(set.size());
    }
}
