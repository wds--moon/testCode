package com.moon.zookeeper;

import org.apache.curator.framework.recipes.cache.*;

import java.util.List;

import static org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type.CHILD_ADDED;
import static org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type.CHILD_UPDATED;

public class Demo {
  static   RegistryCenter registryCenter = new RegistryCenter();
    public static void main(String[] args) throws Exception {

        //System.out.println(registryCenter.createPersistentNode("/hum", "0.32"));
//        System.out.println(registryCenter.createPersistentNode("/hum/hum1", "0.32"));
//        System.out.println(registryCenter.createPersistentNode("/hum/hum2", "0.32"));
//        System.out.println(registryCenter.createPersistentNode("/hum/hum3", "0.32"));
        //System.out.println(registryCenter.createPersistentNode("/hum1", "0.32"));
        //System.out.println(registryCenter.checkExists("/hum1"));

       // registryCenter.getChildrens("/hum").forEach(s -> System.out.println(s));
        //System.out.println(registryCenter.setData("/hum", "ls"));
//        NodeCache nodeCache = registryCenter.registerNodeCacheListener("/hum");
//        //节点不存在，set（不触发监听）
//        registryCenter.setData("/hum/hum1","1");
//        //节点存在，delete（触发监听）
//        registryCenter.delete("/hum/hum1");
//        //节点不存在，create（触发监听）
//        registryCenter.createPersistentNode("/hum/hum4","4");
//        //节点存在，set（触发监听）
//        registryCenter.setData("/hum/hum3","3");
//
//        Thread.sleep(20000);
//        //关闭NodeCache
//        nodeCache.close();


        //===========================================================================
        //testPathChildListener();
        testTreeCacheListener();
    }

    public static void testPathChildListener() throws Exception {
        PathChildrenCache pathChildrenCache = registryCenter.registerPathChildListener("/curator", (client, event) -> {
            ChildData childData = event.getData();

            if(childData != null){
                System.out.println("Path: " + childData.getPath());
                System.out.println("Stat:" + childData.getStat());
                System.out.println("Data: "+ new String(childData.getData()));
            }
            while (true){
                setEvent(event, childData);
                Thread.sleep(1000);
            }


        });
        Thread.sleep(10000000);

        //关闭PathChildrenCache
        pathChildrenCache.close();
    }

    private static void setEvent(PathChildrenCacheEvent event, ChildData childData) {
        switch (event.getType()){
            case CHILD_ADDED:
                System.out.println("正在新增子节点：" + childData.getPath());
                //获取子节点
                List<String> list = registryCenter.getChildrens("/curator");
                list.forEach(System.out::println);

                break;
            case CHILD_UPDATED:
                System.out.println("正在更新子节点："  + childData.getPath());
                break;
            case CHILD_REMOVED:
                System.out.println("子节点被删除");
                break;
            case CONNECTION_LOST:
                System.out.println("连接丢失");
                break;
            case CONNECTION_SUSPENDED:
                System.out.println("连接被挂起");
                break;
            case CONNECTION_RECONNECTED:
                System.out.println("恢复连接");
                break;
        }
    }


    public static void testTreeCacheListener() throws Exception {
        TreeCache treeCache = registryCenter.registerTreeCacheListener("/curator", 2, (client, event) -> {
            ChildData childData = event.getData();

            if(childData != null){
                System.out.println("Path: " + childData.getPath());
                System.out.println("Stat:" + childData.getStat());
                System.out.println("Data: "+ new String(childData.getData()));
            }

            switch (event.getType()){
                case NODE_ADDED:
                    System.out.println("正在新增子节点：" + childData.getPath());
                    //获取子节点
                    List<String> list = registryCenter.getChildrens("/curator");
                    list.forEach(System.out::println);

                    break;
                case NODE_UPDATED:
                    System.out.println("正在更新节点："  + childData.getPath());
                    break;
                case NODE_REMOVED:
                    System.out.println("节点被删除：" + childData.getPath());
                    break;
                case CONNECTION_LOST:
                    System.out.println("连接丢失");
                    break;
                case CONNECTION_SUSPENDED:
                    System.out.println("连接被挂起");
                    break;
                case CONNECTION_RECONNECTED:
                    System.out.println("恢复连接");
                    break;
            }
        });
        Thread.sleep(10000000);

        //关闭PathChildrenCache
        treeCache.close();
    }
}
