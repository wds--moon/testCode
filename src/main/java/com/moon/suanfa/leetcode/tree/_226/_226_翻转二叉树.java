package com.moon.suanfa.leetcode.tree._226;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 树的结构
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class _226_翻转二叉树 {


    /**
     * 反转二叉树
     * 实现方式先反转子树,然后直到根节点
     * 递归实现 时间复杂度O(n) 空间复杂度O(h)
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return root;
        }
        if (root.left != null && root.right != null) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;

        }
        if (root.right != null) {
            invertTree(root.right);
        }

        if (root.left != null) {
            invertTree(root.left);
        }
        return root;
    }


    /**
     * 先交换上层,后续慢慢循环交换下层
     * 这个算法涉及到需要一个队列作为缓冲,严格来说递归底层也是使用栈的方式
     * 时间复杂度O(n) 空间复杂度O(n)
     * @param root
     * @return
     */
    public TreeNode invertTreeQueue(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode index = queue.poll();
            TreeNode tmp =index.left ;
            index.left = index.right;
            index.right = tmp;

            if (index.left != null) {
                queue.offer(index.left);
            }
            if (index.right != null) {
                queue.offer(index.right);
            }

        }
        return root;
    }


}
