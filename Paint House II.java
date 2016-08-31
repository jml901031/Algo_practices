/*
There are a row of n houses, each house can be painted with one of the k colors. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color 0; 
costs[1][2] is the cost of painting house 1 with color 2, 
and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?


*/
public class Solution {
    /*
       Primary solution: O(nk^2)   scan each color to get Minimum cost,
       Optimal solution: maintain one minimum and one second minmum number
    
    */
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0)
        {
            return 0;
        }
        int len = costs.length;
        int minimumCost = Integer.MAX_VALUE;
        int miniCostColor = 0;
        int secondMinimumCost = Integer.MAX_VALUE;
        int secondMiniCostColor = 0;
        for(int i = 0; i < costs[0].length; i++)
        {
            if(minimumCost > costs[0][i])
            {
                secondMinimumCost = minimumCost;
                secondMiniCostColor = miniCostColor;
                minimumCost = costs[0][i];
                miniCostColor = i;
            }
            else if(secondMinimumCost > costs[0][i])
            {
                secondMiniCostColor = i;
                secondMinimumCost = costs[0][i];
            }
        }
        for(int i = 1; i < len; i++)
        {
            int curMinCost = Integer.MAX_VALUE;
            int curMinColor = 0;
            int curSecondMinCost = Integer.MAX_VALUE;
            int curSecondMinColor = 0;
            for(int j = 0; j < costs[0].length; j++) {
                int cost = 0;
                if (j == miniCostColor) {
                    cost += secondMinimumCost + costs[i][j];
                } else {
                    cost += minimumCost + costs[i][j];
                }
                if (cost < curMinCost) {
                    curSecondMinCost = curMinCost;
                    curSecondMinColor = curMinColor;
                    curMinCost = cost;
                    curMinColor = j;
                } else if (cost < curSecondMinCost) {
                    curSecondMinColor = j;
                    curSecondMinCost = cost;

                }
            }
            minimumCost = curMinCost;
            miniCostColor = curMinColor;
            secondMinimumCost = curSecondMinCost;
            secondMiniCostColor = curSecondMinColor;
        }
        return minimumCost;
    }
}