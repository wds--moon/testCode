package com.moon.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class RegistryCenter {

    public final static String URL = "127.0.0.1:2181";

    static CuratorFramework curatorFramework = null;

    /**
     * 如果是spring这里可以配置为config
     */
    static {
        //初始化zookeeper的连接， 会话超时时间是5s，衰减重试
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(URL).sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000, 3))
//                .namespace("warIII")
                .build();
        curatorFramework.start();
    }

    /**
     * 创建持久节点
     *
     * @param nodePath
     * @param nodeValue
     * @return
     */
    public String createPersistentNode(String nodePath, String nodeValue) {
        try {
            return curatorFramework.create().creatingParentsIfNeeded().forPath(nodePath, nodeValue.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("创建持久节点失败" + e);
        }
    }

    /**
     * 创建有序节点
     *
     * @param nodePath
     * @param nodeValue
     * @return
     */
    public String createSequentialPersistentNode(String nodePath, String nodeValue) {
        try {
            return curatorFramework.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(nodePath, nodeValue.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("创建有序持久节点失败" + e);
        }
    }

    /**
     * 创建临时节点- 没有序列的一般是只能一个获取成功,zk只保存成功的节点
     *
     * @param nodePath
     * @param nodeValue
     * @return
     */
    public String createEphemeralNode(String nodePath, String nodeValue) {
        try {
            return curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(nodePath, nodeValue.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("创建临时节点失败" + e);
        }
    }

    /**
     * 创建临时有序节点-有序列的一般是大家都去获取,但是只有头结点或者序列号最小的才能获取成功,同时除了头节点以后
     *
     * @param nodePath
     * @param nodeValue
     * @return
     */
    public String createSequentialEphemeralNode(String nodePath, String nodeValue) {
        try {
            return curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(nodePath, nodeValue.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("创建有序临时节点失败" + e);
        }
    }

    /**
     * 检查节点是否存在
     *
     * @param nodePath
     * @return
     */
    public boolean checkExists(String nodePath) {
        try {
            Stat stat = curatorFramework.checkExists().forPath(nodePath);
            return stat != null;
        } catch (Exception e) {
            throw new RuntimeException("节点不存在!" + e);
        }
    }

    /**
     * 返回节点的所有子节点
     *
     * @param nodePath
     * @return
     */
    public List<String> getChildrens(String nodePath) {
        try {
            List<String> strings = curatorFramework.getChildren().forPath(nodePath);
            return strings;
        } catch (Exception e) {
            throw new RuntimeException("无法获取:" + nodePath + ":的子节点" + e);
        }
    }

    /**
     * 获取节点数据
     *
     * @param nodePath
     * @return
     */
    public String getData(String nodePath) {
        try {
            return new String(curatorFramework.getData().forPath(nodePath));
        } catch (Exception e) {
            throw new RuntimeException("获取节点失败!" + e);
        }
    }

    /**
     * 设置节点数据
     *
     * @param nodePath
     * @param value
     * @return
     */
    public boolean setData(String nodePath, String value) {
        try {
            Stat stat = curatorFramework.setData().forPath(nodePath, value.getBytes());
            return stat != null;
        } catch (Exception e) {
            throw new RuntimeException("设置节点数据失败!" + e);
        }
    }

    /**
     * 删除某个节点
     *
     * @param nodePath
     * @return
     */
    public void delete(String nodePath) {
        try {
            curatorFramework.delete().guaranteed().forPath(nodePath);
        } catch (Exception e) {
            throw new RuntimeException("删除失败!" + e);
        }
    }

    /**
     * 级联删除
     *
     * @param nodePath
     */
    public void deleteChildrenIfNeeded(String nodePath) {
        try {
            curatorFramework.delete().deletingChildrenIfNeeded().forPath(nodePath);
        } catch (Exception e) {
            throw new RuntimeException("删除并且删除子节点失败!" + e);
        }
    }

    /**
     * 使用NodeCache 作为监听 -> 监听一个节点(新增删除修改) 注意监听不子节点
     *
     * @param nodePath
     * @return
     */
    public NodeCache registerNodeCacheListener(String nodePath) {
        try {
            /**
             * 创建一个缓存节点
             */
            NodeCache nodeCache = new NodeCache(curatorFramework, nodePath);
            //添加节点监听器,这里可以指定线程池
            nodeCache.getListenable().addListener(() -> {
                ChildData childData = nodeCache.getCurrentData();
                if (childData != null) {
                    System.out.println("节点发生变化:" + childData.getPath()+"_"+childData.getStat()+"_"+new String(childData.getData()));
                }
            });
            //启动监听器
            nodeCache.start();
            // 返回
            return nodeCache;
        } catch (Exception e) {
            throw new RuntimeException("监听节点变化异常!" + e);
        }
    }

    /**
     * 监听节点的所有子节点
     *
     * @param nodePath
     * @param listener
     * @return
     */
    public PathChildrenCache registerPathChildListener(String nodePath, PathChildrenCacheListener listener) {

        try {

            PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, nodePath, true);
            /**
             * 新增监听
             */
            pathChildrenCache.getListenable().addListener(listener);

            /**
             * 启动监听
             */
            pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
            //返回
            return pathChildrenCache;

        } catch (Exception e) {
            throw new RuntimeException("监听节点的子节点变化异常!" + e);
        }

    }

    /**
     * 监听当前节点和子节点节点变化,综合PathChildrenCache和NodeCache
     * @param nodePath
     * @param maxDepth 监听最大节点深度
     * @param listener 监控事件的回调接口
     * @return
     */
    public TreeCache registerTreeCacheListener(String nodePath, int maxDepth, TreeCacheListener listener) {
        try {
            //1. 创建一个TreeCache
            TreeCache treeCache = TreeCache.newBuilder(curatorFramework, nodePath)
                    .setCacheData(true)
                    .setMaxDepth(maxDepth)
                    .build();
            //2. 添加目录监听器
            treeCache.getListenable().addListener(listener);
            //3. 启动监听器
            treeCache.start();
            //4. 返回TreeCache
            return treeCache;
        } catch (Exception e) {
            throw new RuntimeException("监听节点的子节点变化异常!" + e);
        }
    }

}