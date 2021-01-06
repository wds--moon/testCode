package com.moon.suanfa.leetcode.sort;

public class _88_合并两个有序数组 {
    /**
     * 使用归并排序的合并过程, 由于题目给出nums1可以存放所有元素m+n ,需要额外的空间m
     * [1,2,3,0,0,0]
     * 3
     * [2,5,6]
     * 3
     * [1,2,2,3,0,0]
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        if (n < 1) {
            return;
        }
        int load[] = new int[m];
        for (int i = 0; i < m; i++) {
            load[i] = nums1[i];
        }
        int li = 0;
        int ri = 0;
        int begin = 0;
        while (begin < m + n) {
            //左边未满
            if (li < m) {
                if (ri < n && load[li] > nums2[ri]) {
                    nums1[begin++] = nums2[ri++];
                } else {
                    nums1[begin++] = load[li++];
                }
            } else {//右边未满
                nums1[begin++] = nums2[ri++];
            }
        }
    }

    /**
     * 从后面开始遍历,最大的直接放在nums1[m+n] 的位置
     * <p>
     * * [1,2,3,0,0,0]
     * * 3
     * * [2,5,6]
     * * 3
     * * [1,2,3,0,0,0]
     * * [1,2,3,0,0,6] le=2 re=1
     * * [1,2,3,0,5,6] le=2 re=0
     * * [1,2,2,3,5,6] le=1 re=0
     * * [1,2,2,3,5,6] le=0 re=0
     * * [1,2,2,3,5,6]
     * [0]
     * 0
     * [1]
     * 1
     * 时间复杂度O(n+m) 空间复杂度0
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n < 1) {
            return;
        }
        int le = m - 1;
        int re = n - 1;
        int index = m + n - 1;
        //由于num1 是m+n的集合容量,那么判断的时候只需要先判断re是否满足
        while (re >= 0) {
            if (le >= 0 && nums1[le] > nums2[re]) {
                nums1[index--] = nums1[le--];
            } else {
                nums1[index--] = nums2[re--];
            }
        }

    }


}
