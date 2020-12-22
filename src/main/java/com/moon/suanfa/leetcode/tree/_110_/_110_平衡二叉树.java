package com.moon.suanfa.leetcode.tree._110_;

public class _110_平衡二叉树 {

    public static void main(String[] args) {

    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        try {
            getHeight(root);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取树的高度
     *
     * @param node
     * @return
     */
    public int getHeight(TreeNode node) throws Exception {
        if (node == null) {
            return 0;
        }
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        if (Math.abs(left - right) <= 1) {
            return Math.max(left, right) + 1;
        }
        throw new Exception("0");
    }

    public int getHeight2(TreeNode node) throws Exception {
        if (node == null) {
            return 0;
        }
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        /**
         * 如果存在Math.abs(left - right)>1的情况说明这个树不平衡,给一个标示,
         */
        if(left==-1||right==-1){
            return -1;
        }
        if (Math.abs(left - right) <= 1) {
            return Math.max(left, right) + 1;
        }
        return -1;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
