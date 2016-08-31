/*
After robbing those houses on that street, 
the thief has found himself a new place for his thievery so that he will not get too much attention. 
This time, all houses at this place are arranged in a circle.
 That means the first house is the neighbor of the last one. 
 Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.
*/
public class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if(nums == null || nums.length == 0)
        {
            return 0;
        }
        if(nums.length == 1)
        {
            return nums[0];
        }
        return Math.max(getHouseRobberMoney(Arrays.copyOfRange(nums,0,len - 1)),
        getHouseRobberMoney(Arrays.copyOfRange(nums,1,len)));
    }
    public int getHouseRobberMoney(int []nums)
    {
        if(nums == null || nums.length == 0)
        {
            return 0;
        }
        int len = nums.length;
        int robbed = nums[0];
        int nonRobbed = 0;
        for(int i = 1; i < len; i++)
        {
             int curRobbed = nonRobbed + nums[i];
             int curNonRobbed = Math.max(nonRobbed,robbed);
             robbed = curRobbed;
             nonRobbed = curNonRobbed;
        }
        return Math.max(robbed,nonRobbed);
    }
}