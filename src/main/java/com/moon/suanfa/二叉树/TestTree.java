package com.moon.suanfa.二叉树;

import com.moon.suanfa.二叉树.printer.BinaryTrees;

public class TestTree {
    public static void main(String[] args) {
        removeRedBlack();

    }

    private static void setAVL() {
        AVLTree<Integer> tree = new AVLTree();
        int[] val = new int[]{13,14,15,12,11,17,16,8,9,1};
        for (int i = 0; i < val.length; i++) {
            tree.add(val[i]);
        }
        BinaryTrees.print(tree);
        System.out.println("\n");
        tree.remove(16);
        BinaryTrees.print(tree);
        System.out.println("\n");
        tree.remove(17);
        BinaryTrees.print(tree);

    }

    private static void setSBT() {
        SearchBinaryTree<Integer> tree = new SearchBinaryTree();
        int[] val = new int[]{13,14,15,12,11,17,16,8,9,1};
        for (int i = 0; i < val.length; i++) {
            tree.add(val[i]);
        }
        BinaryTrees.print(tree);
        System.out.println("\n");
//        tree.remove(5);
//        BinaryTrees.print(tree);
    }

    private static void setRedBlack() {
        RedBlackTree<Integer> tree = new RedBlackTree();
        int[] val = new int[]{13,14,15,12,11,17,16,8,9,1};
        for (int i = 0; i < val.length; i++) {
            tree.add(val[i]);
        }
        BinaryTrees.print(tree);
        System.out.println("\n");

    }
    private static void removeRedBlack() {
        RedBlackTree<Integer> tree = new RedBlackTree();
        int[] val = new int[]{13,14,15,12,11,17,16,8,9,1};
        for (int i = 0; i < val.length; i++) {
            tree.add(val[i]);
        }
        BinaryTrees.print(tree);
        System.out.println();
        tree.remove(16);
        BinaryTrees.print(tree);

    }
}
