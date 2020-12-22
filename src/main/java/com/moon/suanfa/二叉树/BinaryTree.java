package com.moon.suanfa.二叉树;

import com.moon.suanfa.二叉树.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉树
 */
public class BinaryTree<E> implements BinaryTreeInfo {

    /**
     * 节点大小
     */
    protected int size;
    /**
     * 根节点
     */
    protected Node<E> root;

    protected Comparator<E> comparator;

    public BinaryTree() {
        this(null);
    }

    public BinaryTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Object string(Object node) {
        return node;
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

    public static class Node<E> {
        protected E value;
        protected Node<E> left;
        protected Node<E> right;
        protected Node<E> parent;

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
         * 判断是否是左子树
         * @return
         */
        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
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
    public Node<E> getPreDrive(Node<E> node) {
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

    public Node<E> getNode(E value) {
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

    /**
     * 如果有接口实现就用接口
     * * 否则采用对象的自带比较器
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
     * 父类不实现
     */
    public Node<E> createNode(E value, Node<E> parent) {
        return new Node<>(value, parent);
    }



}
