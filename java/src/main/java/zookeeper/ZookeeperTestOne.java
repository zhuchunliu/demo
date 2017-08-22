package zookeeper;

import org.apache.zookeeper.*;

import java.time.ZoneOffset;

/**
 * Created by zhuchunliu on 2017/3/10.
 */
public class ZookeeperTestOne {





    /**
     * 备注：进程结束，目录自动被删除了
     * @throws Exception
     */
    private void testZook() throws Exception{
        ZooKeeper zk = new ZooKeeper("localhost:4181,localhost:4182,localhost:4183", 1000000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.err.println("节点"+watchedEvent.getPath()+" "+watchedEvent.getType());
            }
        });
        System.err.println(zk.getState().isAlive());
//        zk.create("/wanda","wanda".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//        zk.create("/wanda01","wanda01".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//        zk.create("/wanda02","wanda02".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL);
        // System.err.println(new String(zk.getData("/wanda",false,null)));
        zk.getChildren("/wanda01",true,null);
//        zk.getChildren("/wanda",true);
//        zk.delete("/wanda",-1);



        zk.close();//必须保留，这样回话过期，wanda01删除时候，会触发watcher事件
    }




    public static void main(String[] args) throws Exception {
        new ZookeeperTestOne().testZook();
    }
}
