package com.moon.suanfa.二叉树;

import java.util.Comparator;

public class SuperAVLTree<E> extends SearchBinaryTree<E> {

    public SuperAVLTree() {
        this(null);
    }

    public SuperAVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 进行平衡,那么这个node是最先失去平衡的节点,这个时候就需要判断是RR,ll,RL,LR
     *
     * @param grand
     */
    protected void rebalance(Node<E> grand) {
        /**
         * tallerChild 找到高度最大的子节点
         */
        Node<E> parent = ((AVLTree.AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLTree.AVLNode<E>) parent).tallerChild();
        /**
         * 失衡树在失衡节点的左边
         */
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                //LL 右旋  是失衡节点右旋
                rightRotate(grand);

            } else {
                //LR
                leftRotate(parent);
                rightRotate(grand);
            }

        } else {
            if (node.isLeftChild()) {
                //RL
                rightRotate(parent);
                leftRotate(grand);
            } else {
                //RR
                leftRotate(grand);
            }
        }
    }

    /**
     * 左旋
     *
     * @param grand
     */
    protected void leftRotate(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        /**
         * 处理parent
         */
        parent.left = grand;

        /**
         * 处理grand
         */
        afterRotate(grand, parent, child);

    }

    /**
     * 右旋
     *
     * @param grand
     */
    protected void rightRotate(Node<E> grand) {
        Node<E> parent = grand.left;
        //找到左节点的右节点
        Node<E> child = parent.right;
        grand.left = child;
        /**
         * 处理parent
         */
        parent.right = grand;
        afterRotate(grand, parent, child);


    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        parent.parent = grand.parent;
        /**
         * 如果grand是他父级节点的左右节点,否则grand根节点
         * 处理grand节点
         */
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }
        /**
         * 处理子节点
         */
        if (child != null) {
            child.parent = grand;
        }
        grand.parent = parent;

    }


}
