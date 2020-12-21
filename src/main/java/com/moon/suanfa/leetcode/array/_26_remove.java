package com.moon.suanfa.leetcode.array;

public class  _26_remove {


    public int removeDuplicates(int[] nums) {
        if (nums.length==0) {
            return 0;
        }
        if(nums.length==1){
            return nums.length;
        }
        int j=0;
        for (int i = 1; i < nums.length ; i++) {
            if(nums[j]!=nums[i]){
                nums[++j]=nums[i];
            }
        }
        return j+1;

    }

    public static void main(String[] args) {
        int[] num=new int[]{0,0,1,1,1,2,2,3,3,4};

        _26_remove node=new _26_remove();
        System.out.println(node.removeDuplicates(num));
    }
}
