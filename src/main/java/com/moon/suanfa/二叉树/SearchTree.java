package com.moon.suanfa.二叉树;

import com.moon.suanfa.二叉树.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉树 度为2 标示有二个子节点,度为1 标示只有一个子节点 度为0自己就是子节点
 *
 * @param <E>
 */
public class SearchTree<E> implements BinaryTreeInfo {

    public SearchTree() {
        this(null);
    }

    public SearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> tmp = (Node<E>) node;
        return tmp.value;
    }

    public static class Node<E> {
        private E value;
        private Node<E> left;
        private Node<E> right;
        private Node<E> parent;

        public Node(E value, Node left, Node right, Node parent) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node(E value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(E value, Node parent) {
            this.value = value;
            this.parent = parent;
        }

        /**
         * 判断是否是叶子节点
         *
         * @return
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 判断度是否为2
         *
         * @return
         */
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

    /**
     * 节点大小
     */
    private int size;
    /**
     * 根节点
     */
    private Node<E> root;

    private Comparator<E> comparator;

    /**
     * 如果有接口实现就用接口
     * 否则采用对象的自带比较器
     *
     * @param e1
     * @param e2
     * @return
     */
    public int compareTo(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
    }


    /**
     * 新增节点
     *
     * @param value
     */
    public void add(E value) {
        addNode(value);

    }

    public void addNode(E value) {
        /**
         * 如果root是null说明是根节点
         */

        if (root == null) {
            root = new Node(value, null);
            size++;
            return;
        }
        Node<E> index = root;
        Node<E> parent = null;
        int cmp = 0;
        do {
            parent = index;
            cmp = compareTo(value, index.value);
            if (cmp > 0) {
                index = index.right;
            } else if (cmp < 0) {
                index = index.left;
            } else {
                index.value = value;
                return;
            }
        }
        while (index != null);
        Node node = new Node(value, parent);
        /**
         * 确定数据插入index的左边还右
         */
        if (cmp > 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        size++;
    }

    /**
     * 前序遍历,中序遍历,后续遍历
     */

    public void preOrder() {
        preOrder(root);
    }

    /**
     * 前序遍历 先根节点然后 左,右节点
     *
     * @param node
     */
    private void preOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        preOrder(node.left);
        preOrder(node.right);

    }

    public void middleOrder() {
        middleOrder(root);
    }

    private void middleOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        middleOrder(node.left);
        System.out.println(node.value);
        middleOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value);
    }

    /**
     * 层序遍历,由于是先遍历根,后再是子
     */
    public void levelTraversal() {
        levelTraversal(root);
    }

    public void levelTraversal(Node<E> node) {
        /**
         * 这里需要以一格有序的队列辅助完成迭代,遍历当前节点的时候需要把他的子节点压入队列中
         */
        LinkedBlockingQueue queue = new LinkedBlockingQueue();

        while (node != null) {
            System.out.println(node.value);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            node = (Node<E>) queue.poll();
        }
    }

    /**
     * 前驱节点和后驱节点,比根节点小,比根节点大[中序遍历的时候跟节点前后的一条数据],  这个东西主要用在删除节点的时候用
     */

    public E preDrive(E value) {
        if (root == null) {
            return null;
        }
        Node<E> node = getNode(value);
        if (node != null && node.left == null) {
            return null;
        }
        return getPreDrive(node).value;
    }

    /**
     * 指定节点的前驱节点,前驱都是去当前节点的左子树中去找
     *
     * @param node
     * @return
     */
    private Node<E> getPreDrive(Node<E> node) {
        if (node != null && node.left == null) {
            return null;
        }
        Node<E> index = node.left;
        while (index != null) {
            if (index.right == null) {
                break;
            }
            index = index.right;
        }
        return index;
    }


    /**
     * 删除节点
     * 1. 删除度为0的节点, 就是叶子节点
     * 2. 删除度为1的节点, 让其父节点指向,删除节点的子节点,并且断掉当前节点的子节点链接
     * 3. 删除度为2的节点, 需要找到左子节点的最大节点,替换删除的节点,相当于替换删除节点,并且删除左子树最大节点,当然这个节点一定是子树中节点最大的值,他的度只能是1和0 这种情况重复第一步和第二步
     * 4. 删除的时候size需要减一
     */
    public void remove(E value) {
        Node<E> node = getNode(value);
        if (node == null) {
            return;
        }
        remove(node);
    }

    /**
     * 节点已经找到现在需要删除
     *
     * @param node
     */
    public void remove(Node<E> node) {
        /**
         * 先删除节点度为2,必须先找到前驱节点或者后继节点,然后覆盖删除节点值,最后删除前驱节点
         * 经过这样处理 那么就只剩度为1或者0 的节点,相对来说还需要分开处理
         */
        if (node.hasTwoChildren()) {
            Node<E> pre = getPreDrive(node);
            node.value = pre.value;
            node = pre;
        }
        /**
         * 找到度为1 的节点的子节点
         */
        Node<E> childNode = node.left != null ? node.left : node.right;
        if (childNode != null) {
            /**
             * 找到node节点的父级节点,重新指向删除节点的子节点
             */
            Node parent = node.parent;
            if (parent == null) {
                childNode.parent = null;
                root = childNode;
            } else if (parent.left == childNode) {
                parent.left = childNode;
            } else {
                parent.right = childNode;
            }

        } else if (node.parent == null) {
            //如果删除的是根节点,并且也是叶子节点
            root = null;

        } else {
            /**
             * 如果是删除的叶子节点
             */
            if (node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
        size--;


    }

    private Node<E> getNode(E value) {
        Node<E> index = root;
        do {
            int cmp = compareTo(value, index.value);
            if (cmp > 0) {
                index = index.right;
            } else if (cmp < 0) {
                index = index.left;
            } else {
                return index;
            }
        } while (index != null);
        return null;
    }
}
