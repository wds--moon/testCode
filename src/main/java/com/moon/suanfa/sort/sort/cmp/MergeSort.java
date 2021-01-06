package com.moon.suanfa.sort.sort.cmp;

import com.moon.suanfa.sort.sort.Sort;

/**
 * 归并排序 不断的把集合拆分为二个子集 直到不能再拆分位置,在进行递归式的是合并排序,从而完成排序
 * 时间复杂度n*log(n) 空间复杂度leftArray=n/2 所以空间复杂度为(n)
 */
public class MergeSort<T extends Comparable<T>> extends Sort<T> {


    private T[] leftArray;

    @Override
    protected void sort() {
        this.leftArray = (T[]) new Comparable[array.length >> 1];
        int end = array.length;
        int begin = 0;
        sort(begin, end);
    }

    /**
     * 递归拆分,然后进行数据合并 这一层时间复杂度log(N)
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        int mid = (begin + end) >> 1;
        //[begin, mid)
        sort(begin, mid);
        //[mid, end)
        sort(mid, end);
        merge(begin, end);
    }

    /**
     * 合并的时候;左边的数组和右边的链接在一起,把左边的数组copy一份当做一个缓存
     * 操作还是操作的原来数组
     7,5,1,1,8,            7,4,6,7,5
     7,5,1,1,8             7,4,6,7,5
     7,5,1,1,8             7,4,6,7,5
     7,5  1,1,8         7,4      6,7,5
     7  5  1  1,8       7  4    6  7, 5
     7  5  1  1  8    7     4  6  7    5


     由于存在循环操作这一层时间复杂度需要n 因为最大的时候leftArray=n/2 所以还是n
     * @param begin
     * @param end
     */
    private void merge(int begin, int end) {
        //二个数组进行排序把数据放到新的数组中
        int mid = (begin + end) >> 1;
        int li = 0;
        //这才是数组长度
        int le = mid - begin;
        int ri = mid;
        int re = end;
        int ai = begin;
        /**
         * 把左边的数组移动过来
         */
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }
        //左边先完成 由于左右二边子集都是有序的,如果说左边先完成,那么不用移动右边的元素直接退出 while 就是干这个事情
        //右边先完成 那么需要把左右的依次copy过来
        while (li < le) {
            /**
             * li<le 判断是否是左边先完成
             * 判断如果<=0 那么会导致先移动ri 违背了原来数组的顺序,也就是失去了稳定性
             */
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }


    }

}
