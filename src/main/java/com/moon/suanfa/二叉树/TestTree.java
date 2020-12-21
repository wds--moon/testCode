package com.moon.suanfa.二叉树;

import com.moon.suanfa.二叉树.printer.BinaryTrees;

public class TestTree {
    public static void main(String[] args) {

        SearchTree<Integer> tree = new SearchTree();
        int[] val = new int[]{10, 5, 6, 2, 3, 9, 8, 15, 1};
        for (int i = 0; i < val.length; i++) {
            tree.add(val[i]);
        }
        BinaryTrees.print(tree);
        System.out.println("\n");
        tree.remove(2);
        BinaryTrees.print(tree);
    }
}
