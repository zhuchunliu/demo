package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * Created by zhuchunliu on 2017/3/7.
 */
public class ZookeeperTest {

    private void testChildren() throws Exception{
        ZooKeeper zk = new ZooKeeper("localhost:4180", 50000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.err.println("节点："+watchedEvent.getPath()+", "+watchedEvent.getType()+" 事件触发了");
            }
        });

        zk.create("/root","root".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.getChildren("/root",true);//监控/root子节点的创建、删除行为
        zk.exists("/root/child",true);//监控/root/child创建行为
        zk.create("/root/child","child".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //【节点：/root/child, NodeCreated 事件触发了】【节点：/root, NodeChildrenChanged 事件触发了】

        zk.exists("/root",true);//设置/root的data行为
        zk.exists("/root/child",true);//需要重新绑定事件，create已经把事件消费掉了
        zk.getChildren("/root",true);//监控/root子节点行为，之前create已经消费掉了
        zk.delete("/root/child", -1);//【节点：/root/child, NodeDeleted 事件触发了】【节点：/root, NodeChildrenChanged 事件触发了】,
        // 不会触发/root数据watcher

        zk.delete("/root", -1);//【节点：/root/child, NodeDeleted 事件触发了】
        zk.close();// 【节点：null, None 事件触发了】

    }

    private void testZoo() throws Exception{
        ZooKeeper zk = new ZooKeeper("localhost:4180", 50000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.err.println("节点："+watchedEvent.getPath()+", "+watchedEvent.getType()+" 事件触发了");
            }
        });

        zk.create("/root","root".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        Stat stat = zk.exists("/root",true);//设置true，使用构造函数设置的watcher
        stat = zk.setData("/root","abc1".getBytes(),stat.getVersion());//触发NodeDataChanged事件，后面setData值没有watcher事件，因为事件已经被触发了
        stat = zk.setData("/root","abc2".getBytes(),stat.getVersion());//收不到触发事件
        zk.getData("/root",true,stat);//如果不重新绑定watcher事件，则下一步delete也不会触发事件
        zk.delete("/root", -1);//触发触发NodeDataChanged事件事件，-1表示删除任何版本的/root
        zk.close();//  在客户端与服务器断开连接的时候，触发None事件

    }
    public static void main(String[] args) throws Exception {
//        new ZookeeperTest().testZoo();
        new ZookeeperTest().testChildren();
    }
}
