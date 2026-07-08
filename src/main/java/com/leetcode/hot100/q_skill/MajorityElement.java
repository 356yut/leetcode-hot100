package com.leetcode.hot100.q_skill;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        int res=0;
        int count=0     ;
        for (int num:nums) {
            if(res==num){
                count+=1;
            }else if(count==0) {
                count=1;
                res=num;
            }else{
                count-=1;
            }
        }
        return res;
    }
}
