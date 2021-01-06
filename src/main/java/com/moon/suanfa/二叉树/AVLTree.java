package com.moon.suanfa.二叉树;

import java.util.Comparator;

/**
 * 平衡二叉树 是可以继承自搜索二叉树
 * 需要不重写add和remove方法,因为平衡树就是在新增删除后进行平衡操作
 * 对于平衡树来说,他需要一个高度节点属于独有的,那么在创建节点的时候,只能自己实现创建逻辑
 *
 * @param <E>
 */
public class AVLTree<E> extends SuperAVLTree<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }


    public static class AVLNode<E> extends Node<E> {
        /**
         * 初始节点的高度是1
         */
        private int height = 1;

        public AVLNode(E value, Node parent) {
            super(value, parent);
        }

        /**
         * 根据高度计算平衡因子
         *
         * @return
         */
        public int balanceFactor() {
            /**
             * 获取左右子节点的高度,并且比较高度差
             */
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;

            return Math.abs(leftHeight - rightHeight);
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        /**
         * 寻找子节点中高度大的那个
         *
         * @return
         */
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;

            if (leftHeight - rightHeight > 0) {
                return left;
            } else if (leftHeight - rightHeight < 0) {
                return right;
            }
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.value.toString();
            }
            return value + "_p(" + parentString + ")_h(" + height + ")";
        }
    }

    /**
     * 等于true表已经平衡
     *
     * @param node
     * @return
     */
    protected boolean isBalance(Node<E> node) {
        return ((AVLNode<E>) node).balanceFactor() <= 1;

    }

    @Override
    public Node<E> createNode(E value, Node<E> parent) {
        return new AVLNode<>(value, parent);
    }

    /**
     * 模板方法
     * 这里设计到左旋L,右旋R ,影响到平衡的方式只能新增节点的时候导致祖先节点失衡(失衡的意思节点的左高和右高相差绝对值大于1)
     * 这个过程需要新增节点一直向上判断他的父级是否失衡,如果失衡就停止寻找,开始平衡,只要这个子树平衡了整个书就平衡
     * 存在4中情况{
     * 1. 如果失衡节点,失衡节点一直left可以找到新增节点(LL的情况).那么只需要把失衡节点右旋(单旋) .
     * 2. 如果失衡节点,失衡节点一直right可以找到新增节点(RR的情况)
     * 3. 如果失衡节点,失衡节点先left,后right可以找到新增节点(LR),那么需要先左旋再右旋(LR),注意:这里是先操作子树,让其(RR然后在LL)
     * 4. 如果失衡节点,失衡节点先right,后left可以找到新增节点(RL),那么是先右旋再左旋(RL)
     * 5. 后续操作就是指针的变换,1-4是思路,实现上只需要实现左旋右旋方法即可
     * }
     */
    @Override
    public void afterAddNode(Node<E> node) {
        /**
         * 不断向上查找父级节点
         */
        while ((node = node.parent) != null) {
            if (isBalance(node)) {
                //如果是平衡的我们需要更新节点的高度,下一次循环就可以直接使用,这里有个点,如果没有发生失衡,他会一直寻找到根节点,并且更新他的高度
                // .相对来说这里的时间复杂度比较低O(h),相对来说这个代价也可以接受.
                updateHeight(node);
            } else {
                //需要进行平衡操作,结束之后直接退出循环
                rebalance(node);
                break;
            }
        }
    }


    @Override
    public void afterRemoveNode(Node<E> node) {
        /**
         * 不断向上查找父级节点
         */
        while ((node = node.parent) != null) {
            if (isBalance(node)) {
                //如果是平衡的我们需要更新节点的高度,下一次循环就可以直接使用,这里有个点,如果没有发生失衡,他会一直寻找到根节点,并且更新他的高度
                // .相对来说这里的时间复杂度比较低O(h),这个代价也可以接受.
                updateHeight(node);
            } else {
                //需要进行平衡操作,结束之后继续循环,因为删除会导致级联失衡,所以需要全部重新平衡,所以后续引出红黑树了,来改变这一个性质,从而提高效率
                rebalance(node);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        /**
         * 更新节点的高度
         */
        updateHeight(grand);
        updateHeight(parent);
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

}
