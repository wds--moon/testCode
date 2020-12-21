package com.moon.suanfa.数组;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Solution {

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i <nums.length ; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i],i);
            }
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            if(map.containsKey(target-next.getKey())){
                System.out.println(next);
            }
        }
        return null;
    }

    public static int[] twoSumHashMap(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>(4);
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,4};
        int[] ints = twoSum(nums, 6);
    }
}