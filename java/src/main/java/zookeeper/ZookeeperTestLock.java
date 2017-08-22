package zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by zhuchunliu on 2017/3/10.
 */
public class ZookeeperTestLock {

    private ZooKeeper zk = null;

    public ZookeeperTestLock() throws Exception{
         zk = new ZooKeeper("localhost:4180", 50000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.err.println("节点："+watchedEvent.getPath()+", "+watchedEvent.getType()+" 事件触发了");
            }
        });
    }


    void getLock(){

    }



    public static void main(String[] args) {

    }
}
