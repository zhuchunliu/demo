package zookeeper;

import org.apache.zookeeper.*;

/**
 * Created by zhuchunliu on 2017/3/7.
 */
public class ZookeeperTest {


    /**
     * 备注：getChildren.watch 可以监控新的一级子目录【添加】事件；
     *      当前目录删除[当前path type:NodeDeleted 根目录没有此type事件]
     *      子目录删除[父类path type:NodeChildrenChanged]
     *      子目录添加[父类path type:NodeChildrenChanged]
     *      备注：父类只会触发一次NodeChildrenChanged事件
     *
     *
     *      getData.watch 可以监控当前目录的数据变化
     *      当前目录的删除[当前path type:NodeDeleted][setData之后，就不会触发delete事件]
     *      子目录删除没有type时间
     *
     * @throws Exception
     */
    private void testZook() throws Exception{
        ZooKeeper zk = new ZooKeeper("localhost:4180", 50000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.err.println("节点："+watchedEvent.getPath()+", "+watchedEvent.getType()+" 事件触发了");
            }
        });
        zk.create("/testRootPath","testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create("/testRootPath/testChildPathOne", "testChildPathOnedDataOne".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zk.create("/testRootPath/testChildPathOne/wanda", "testChildPathOnedDataOneWanda".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);


        // 此处watch：true，只能触发当前目录的变更，添加子目录不会触发
        System.err.println("获取节点数据: " + new String(zk.getData("/testRootPath", true, null)));

        // 此处wath:true，只能针对当前目录添加Add新的子目录触发，其余不触发，子目录其他事件得自己设置
        System.err.println("/testRootPath子目录："+zk.getChildren("/testRootPath", true));

        System.err.println("/testRootPath/testChildPathOne子目录："+zk.getChildren("/testRootPath/testChildPathOne", true));

        //只有此处watch设置为true，下面针对该节点的操作才会有watch事件，且没有目录传递作用，只能管理当前目录
        System.err.println(zk.getChildren("/testRootPath/testChildPathOne/wanda", true));

//        System.err.println("test节点是否存在：" + zk.exists("/test", false));

        //此处会触发watch时间，由于zk.getChildren("/testRootPath",true），watch设置为true了
        zk.create("/testRootPath/testChildPathTwo","testChildPathTwo".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.getData("/testRootPath/testChildPathTwo",true,null);
//        zk.setData("/testRootPath/testChildPathTwo", "two".getBytes(), -1);

        zk.delete("/testRootPath/testChildPathOne/wanda",-1);
        zk.delete("/testRootPath/testChildPathOne",-1);
        zk.delete("/testRootPath/testChildPathTwo", -1);

        zk.delete("/testRootPath",-1);
        zk.close();
    }


    private void testQun() throws Exception{
        ZooKeeper zk = new ZooKeeper("localhost:4180", 50000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.err.println("节点："+watchedEvent.getPath()+", "+watchedEvent.getType()+" 事件触发了");
            }
        });



        zk.create("/wanda","wanda".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zk.create("/wanda/wanda01","wanda01".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//        zk.getChildren("/wanda/wanda01",true);
        zk.getChildren("/wanda",true);
//        zk.getData("/wanda/wanda01",true,null);
//        zk.getData("/wanda",true,null);

        zk.delete("/wanda/wanda01",-1);
//        zk.create("/wanda/wanda02","wanda02".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//        zk.delete("/wanda/wanda02",-1);
        zk.delete("/wanda",-1);
        zk.close();


    }
    public static void main(String[] args) throws Exception {
        new ZookeeperTest().testZook();
//        new ZookeeperTest().testQun();
    }
}
