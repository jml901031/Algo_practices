/*
There are a row of n houses, each house can be painted with one of the three colors: red, 
blue or green. The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color red; 
costs[1][2] is the cost of painting house 1 with color green, and so on... 
Find the minimum cost to paint all houses.
*/
public class Solution {
    //dp state: min[i][k]   the cost of ith house paint k color 
    //induction rule min[i][k] = Math.min(min[i-1][q])  q!= k
    public int minCost(int[][] costs) {
        if(costs == null || costs.length == 0)
        {
            return 0;
        }
        int len = costs.length;
        int [][]min = new int[len][3];
        for(int i = 0;i < len;i++)
        {
            if(i == 0)
            {
                min[i][0] = costs[i][0];
                min[i][1] = costs[i][1];
                min[i][2] = costs[i][2];
            }
            else
            {
                min[i][0] = Math.min(min[i-1][1],min[i-1][2]) + costs[i][0];
                min[i][1] = Math.min(min[i-1][0],min[i-1][2]) + costs[i][1];
                min[i][2] = Math.min(min[i-1][1],min[i-1][0]) + costs[i][2];
            }
        }
        return Math.min(Math.min(min[len - 1][0],min[len - 1][1]),min[len - 1][2]);
    }
}