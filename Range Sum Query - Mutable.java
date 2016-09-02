/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
*/

public class NumArray {
    class IntervalTreeNode
    {
        int start = 0;
        int end = 0;
        int val = 0;
        IntervalTreeNode left;
        IntervalTreeNode right;
        IntervalTreeNode(int start, int end)
        {
            this.start = start;
            this.end = end;
        }
        IntervalTreeNode(int start, int end,int val)
        {
            this.start = start;
            this.end = end;
            this.val = val;
        }
    }
    class IntervalTree
    {
        private IntervalTreeNode root = null;
        private int[] nums = null;
        public IntervalTree(int []nums)
        {
            this.nums = nums;
            root = buildTree(0,nums.length - 1);
        }
        private IntervalTreeNode buildTree(int start, int end)
        {
            if(start > end)
            {
                return null;
            }
            if(start == end)
            {
                return new IntervalTreeNode(start,end,nums[start]);
            }
            IntervalTreeNode root = new IntervalTreeNode(start,end);
            int mid = (end - start) / 2 + start;
            root.left = buildTree(start,mid);
            root.right = buildTree(mid+1,end);
            root.val = root.left.val + root.right.val;
            return root;
        }
        public void update(int index,int val)
        {
            updateVal(root,index,val);
            
        }
        private  void updateVal(IntervalTreeNode root,int index,int val)
        {
            if(root == null)
            {
                return;
            }
            if(root.start == index && root.end == index)
            {
                root.val = val;
                return;
            }
            int mid = (root.end - root.start)/2 + root.start;
            if(index > mid)
            {
                 updateVal(root.right,index,val);
            }
            else
            {
                  updateVal(root.left,index,val);
            }
            root.val = root.right.val + root.left.val;
            
        }
        public int sumRange(int i, int j)
        {
            return sumRangeHelper(root, i, j);
        }
        private int sumRangeHelper(IntervalTreeNode root, int i, int j)
        {
          
            if(i == root.start && root.end == j)
            {
                return root.val;
            }
            int mid = (root.end - root.start)/2 + root.start;
            if(j<=mid)
            {
                return sumRangeHelper(root.left,i,j);
            }
            else if(i > mid)
            {
                return sumRangeHelper(root.right,i,j);
            }
            else
            {
                return sumRangeHelper(root.left,i,mid) + sumRangeHelper(root.right,mid + 1, j);
            }
            
        }
    }
    IntervalTree intervalTree = null;
    public NumArray(int[] nums) {
        intervalTree = new IntervalTree(nums);
    }

    void update(int i, int val) {
        intervalTree.update(i,val);
    }

    public int sumRange(int i, int j) {
        return intervalTree.sumRange(i,j);
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);