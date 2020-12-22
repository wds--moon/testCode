package com.moon.suanfa.二叉树;

import java.util.Comparator;

/**
 * 二叉树 度为2 标示有二个子节点,度为1 标示只有一个子节点 度为0自己就是子节点
 *
 * @param <E>
 */
public class SearchBinaryTree<E> extends BinaryTree<E> {


    public SearchBinaryTree() {
        this(null);
    }

    public SearchBinaryTree(Comparator<E> comparator) {
        super(comparator);
    }


    @Override
    public Node<E> createNode(E value, Node<E> parent) {
        return super.createNode(value, parent);
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
            root = createNode(value, null);
            size++;
            afterAddNode(root);
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
        Node node = createNode(value, parent);
        /**
         * 确定数据插入index的左边还右
         */
        if (cmp > 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        size++;
        afterAddNode(node);
    }

    /**
     * 构造一个模板方法,用于子类实现处理逻辑
     */
    public void afterAddNode(Node<E> node){

    }

    public void afterRemoveNode(Node<E> node){

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
        afterRemoveNode(node);

    }


}
