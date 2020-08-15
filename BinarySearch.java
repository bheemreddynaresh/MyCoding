Binary serach template:
/*
Leet code link: https://leetcode.com/problems/split-array-largest-sum/

Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.

**************************************************
Check the Comments Thoroughlly
**************************************************

*/
class Solution {
    public boolean enough(int[] nums,long large, int m){
        long count = 0;
        long sum = 0;
        for(int each: nums){
            if(sum + each <= large){
                sum += each;
                continue;
            }
            count++;
            if(count>m) return false;
            sum = each;
            if(each > large) return false;
        }
        if(sum>0 && sum <= large) count++;
        return count <= m? true:false;
    }
    public int splitArray(int[] nums, int m) {
        long start = nums[0];
        long end = 0;
        for(long each: nums){
            end += each;
            start = start>each?each:start;
        }
        
        //end = end!= Integer.MAX_VALUE?end+1:Integer.MAX_VALUE; // because end have to included
        if(m == 1) return (int)end;
        end = end+1;
        long ret = -1;
        while(start < end){
            long mid = start + (end-start)/2;
            //System.out.println(mid);
            if(enough(nums,mid,m)){
                //System.out.println("true");
                ret = mid;
                // Had you put here end = mid-1, this will fail for 2 values
                //probably start would have been mid-1 at some point in recursion
                //So the start < end cond will fail and obviously you are not verifying for mid-1 condtions
                //Also if you thought of changing the condition (start<=end) you will enter in a loop
                //so be very careful while designing this;
                end = mid;
            }
            else{
               // System.out.println("false");
                start = mid+1;
            }
        }
        return (int)ret;
    }
}