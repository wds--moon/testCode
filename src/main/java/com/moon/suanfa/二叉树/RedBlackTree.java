package com.moon.suanfa.二叉树;

/**
 * 红黑树的特性
 * 1. 根节点必须是黑色节点
 * 2. 节点不是红色就是黑色
 * 3. 红色节点不能相连
 * 4. 任意节点到他的子节点的黑色节点个数必须相同
 * 5. 叶子节点都是黑色(这个叶子节点指的是空节点)
 * 注意所有的叶子节点都有null的子节点并且是黑色.有了这个特性可以判断是否是红黑树
 * 实现插入步骤的时候.是先着色后旋转,不满足3条,继续递归直到根节点,前提是叔父节点是红色才是需要着色,否则直接旋转
 *
 * @param <E>
 * @author wendongshan
 */
public class RedBlackTree<E> extends SuperAVLTree<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;


    public static class RedBlackNode<E> extends Node<E> {
        /**
         * 节点颜色 初始的时候是红色
         */
        private boolean color = RED;

        public RedBlackNode(E value, Node parent) {
            super(value, parent);
        }

        @Override
        public String toString() {

            String str = "";
            if (color == RED) {
                str = str + "R_";

            }
            return str + value.toString();
        }

    }

    /**
     * 设置节点颜色
     *
     * @param node
     * @param color
     * @return
     */
    public Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return node;
        }
        ((RedBlackNode<E>) node).color = color;
        return node;
    }

    public Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    public Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    public boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    public boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    public boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RedBlackNode<E>) node).color;
    }

    @Override
    public Node<E> createNode(E value, Node<E> parent) {
        return new RedBlackNode<>(value, parent);
    }

    @Override
    public void afterAddNode(Node<E> node) {
        /**
         * 实现数据添加之后的操作
         * 1. 着色
         * 2. 向上溢出(这个过程可能存在连续溢出),可能会存在新增节点
         */
        /**
         * 如果是根节点,变成黑色,直接返回即可
         */
        Node<E> parent = node.parent;
        if (parent == null) {
            black(node);
            return;
        }
        /**
         * 如果插入节点的父节点是黑色,不用变化
         */
        if (isBlack(parent)) {
            return;
        }
        //叔父
        Node<E> uncle = parent.sibling();

        // 祖父节点
        Node<E> grand = red(parent.parent);

        /**
         * 如果父级节点是红色,由于不满足特点3,需要处理
         * 如果叔父节点是红色那么父节点一定是红色,新插入元素的话就需要变色旋转
         */
        //当叔父也是红色,就需要递归寻找处理,下一次递归的时候叔父就变成了黑色
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            afterAddNode(grand);
            return;

        } else {
            //叔父等于null也是黑色,切记新增节点都只能是叶子节点,也就是说新增节点只能是添加在左边或者右边,也就是说只存在LL LR RL RR,为了平衡还需要旋转.只不过红黑树是判断存在相连红色节点就旋转,avl树是通过高度差来判断的
            //然后进行着色,但是不管哪个方式祖父节点都需要进行着色.由于叔父是黑色,祖父只能是红色了.如果存在旋转操作,还需要先重新着色,当子树操作完成还需要进行父级节点的颜色查找,存在不满足特点3就继续循环直到根节点
            //L
            if (parent.isLeftChild()) {
                //LL
                if (node.isLeftChild()) {
                    black(parent);
                } else {
                    //LR
                    black(node);
                    leftRotate(parent);
                }
                rightRotate(grand);
            } else {
                if (node.isLeftChild()) {
                    //RL
                    black(node);
                    rightRotate(parent);
                } else {
                    //RR
                    black(parent);
                }
                leftRotate(grand);
            }
        }

    }

    /**
     * https://zhuanlan.zhihu.com/p/22800206
     * @param node
     */

    @Override
    public void afterRemoveNode(Node<E> node) {

        //走到这一步说明我们只需要操作度为1和0的就可以了,原因是删除度为2的节点实际上是删除子节点来代替
        /**
         * 删除逻辑,不管是那种树,删除都是最终删除叶子节点.
         */
        //1 删除的如果是红色节点直接删除即可不用处理,因为会影响红黑树特性
        //2 删除度为1的黑色节点,实际是用子节点替换了他,此时叶子节点都是红色的话,现在只需要重新着色即可
        if (isRed(node)) {
            black(node);
            return;
        }
        Node<E> parent = node.parent;
        //只有一个节点
        if (parent == null) {
            return;
        }
        //3 删除黑色的叶子节点,并且他的兄弟节点也是黑色,这个时候由于被删除了,会产生下溢.才能满足红黑树特性
        //删除的叶子节点是左边还是右边
        boolean left = parent.left == node ? true : false;
        Node<E> sibling = left ? parent.right : parent.left;
        //被删除的节点左边,兄弟节点在右边
        if (left) {
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                leftRotate(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemoveNode(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rightRotate(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                leftRotate(parent);
            }

        } else {
            //被删除的在右边,兄弟在左边,
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rightRotate(parent);
                //旋转后,还需要重置兄弟节点
                sibling = parent.left;
            }
            //经过前面的操作兄弟节点变成了黑色.他的子节点都是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                //兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                red(sibling);
                black(parent);
                if (parentBlack) {
                    //父节点下溢需要递归继续操作
                    afterRemoveNode(parent);
                }
            } else {
                //兄弟节点最少有一个红色
                if (isBlack(sibling.left)) {
                    leftRotate(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rightRotate(parent);
            }


        }


    }
}
