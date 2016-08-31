/*
Given an array nums, there is a sliding window of size k which is moving from 
the very left of the array to the very right. You can only see the k numbers in the window.
Each time the sliding window moves right by one position.
For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].


Solution 1: brute force: search maximum in everySliding window.
Solution 2: Use Heap: every time keep K silding window in  the  Heap
Solution 3: Use balance binary tree
Solution 4: Deque keep the mono increasing order, (need to remove unvalid number)
*/

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0)
        {
            return new int[0];
        }
        int []maxSliding = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<Integer>();
        for(int i = 0; i < nums.length;i++)
        {
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
            {
                deque.pollLast();
            }
            deque.offerLast(nums[i]);
            if(i >= k-1)
            {
                if(i > k - 1)
                {
                    if(nums[i - k] == deque.peekFirst())
                    {
                        deque.pollFirst();
                    }
                }
                maxSliding[i - k + 1] = deque.peekFirst();
            }
        }
        return maxSliding;
    }
}