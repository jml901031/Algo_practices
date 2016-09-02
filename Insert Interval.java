/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].



*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

public class Solution {
  
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
       if(intervals.size() == 0)
       {
           intervals.add(newInterval);
           return intervals;
       }
       int indexOfSmallerStart = binarySearch(intervals,newInterval.start);
       int indexOfSmallerEnd = binarySearch(intervals,newInterval.end);
       List<Interval> headPart = null;
       List<Interval> tailPart = null; 
       if(indexOfSmallerStart == -1)
       {
           if(indexOfSmallerEnd == -1)
           {
               intervals.add(0,newInterval);
               return intervals;
           }
           else
           {
               intervals.get(indexOfSmallerEnd).start = newInterval.start;
               intervals.get(indexOfSmallerEnd).end = Math.max(intervals.get(indexOfSmallerEnd).end,newInterval.end);
               return intervals.subList(indexOfSmallerEnd,intervals.size());
           }
       }
       else
       {
           int firstIntervalStart = intervals.get(indexOfSmallerStart).start;
           int firstIntervalEnd = intervals.get(indexOfSmallerStart).end;
           int secondIntervalStart = intervals.get(indexOfSmallerEnd).start;
           int secondIntervalEnd = intervals.get(indexOfSmallerEnd).end;
           if(newInterval.start > firstIntervalEnd && newInterval.end > secondIntervalEnd)
           {
               headPart= intervals.subList(0,indexOfSmallerStart + 1);
               headPart.add(newInterval);
               tailPart = intervals.subList(indexOfSmallerEnd + 2,intervals.size());
               headPart.addAll(tailPart);
               return headPart;
           }
           else if(newInterval.start <= firstIntervalEnd && newInterval.end <= secondIntervalEnd)
           {
               intervals.get(indexOfSmallerStart).end = secondIntervalEnd;
               headPart = intervals.subList(0,indexOfSmallerStart + 1);
               tailPart = intervals.subList(indexOfSmallerEnd + 1, intervals.size());
               headPart.addAll(tailPart);
               return headPart;
           }
           else if(newInterval.start <= firstIntervalEnd && newInterval.end >= secondIntervalEnd)
           {
               intervals.get(indexOfSmallerStart).end = newInterval.end;
               headPart = intervals.subList(0,indexOfSmallerStart + 1);
              
                tailPart = intervals.subList(indexOfSmallerEnd + 1, intervals.size());
                headPart.addAll(tailPart);
               
               return headPart;
           }
           else
           {
               headPart= intervals.subList(0,indexOfSmallerStart + 1);
               newInterval.end = secondIntervalEnd;
               headPart.add(newInterval);
               tailPart = intervals.subList(indexOfSmallerEnd + 2,intervals.size());
               headPart.addAll(tailPart);
               return headPart;
           }
           
       }
     
    }
    public int binarySearch(List<Interval> intervals,int target)
    {
        int left = 0;
        int right = intervals.size() - 1;
        while(left < right)
        {
            int mid = (right - left + 1)/2 + left;
            if(intervals.get(mid).start <= target)
            {
                left = mid;
            }
            else
            {
                right = mid - 1;
            }
        }
        if(intervals.get(right).start <= target)
        {
            return right;
        }
        return -1;
    }
}