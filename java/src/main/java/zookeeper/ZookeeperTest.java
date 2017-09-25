package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * Created by zhuchunliu on 2017/3/7.
 */
public class ZookeeperTest {


    /*
        Watcher & Version
        watcher分为两大类：data watches和child watches。getData()和exists()上可以设置data watches，getChildren()上可以设置child watches。
        setData()会触发data watches;
        create()会触发data watches和child watches;
        delete()会触发data watches和child watches.
        如果对一个不存在的节点调用了exists()，并设置了watcher，而在连接断开的情况下create/delete了该znode，则watcher会丢失。
        在server端用一个map来存放watcher，所以相同的watcher在map中只会出现一次，只要watcher被回调一次，它就会被删除----map解释了watcher的一次性。
            比如如果在getData()和exists()上设置的是同一个data watcher，调用setData()会触发data watcher，但是getData()和exists()只有一个会收到通知。
     */
    private void testZoo() throws Exception{
        ZooKeeper zk = new ZooKeeper("localhost:4180", 50000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.err.println("节点："+watchedEvent.getPath()+", "+watchedEvent.getType()+" 事件触发了");
            }
        });

        zk.create("/testRootPath","testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        Stat stat1 = zk.exists("/testRootPath",true);// 此处不用设置watcher，之前已经设置过了
//        zk.getChildren("/testRootPath",true);
//        zk.getData("/testRootPath",true,stat1);
//        Stat stat = zk.exists("/testRootPath",true);
//        Stat stat1 = zk.exists("/testRootPath",false);// 此处不用设置watcher，之前已经设置过了
        zk.setData("/testRootPath","abc".getBytes(),stat1.getVersion());
        zk.exists("/testRootPath",true);
        zk.delete("/testRootPath", -1);


        zk.close();


    }
    public static void main(String[] args) throws Exception {
        new ZookeeperTest().testZoo();
    }
}
