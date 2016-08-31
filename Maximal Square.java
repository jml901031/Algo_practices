/*
Given a 2D binary matrix filled with 0's and 1's, 
find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.

*/
public class Solution {
    // dp def: dp[i][j] represents the largest square whose bottom right point is i,j
    // induction rule : dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1],dp[i-1][j-1]) + 1
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0)
        {
            return 0;
        }
        int lenX = matrix.length;
        int lenY = matrix[0].length;
        int max = 0;
        int [][]dp = new int[lenX][lenY];
        for(int i = 0; i < lenX; i++)
        {
            for(int j = 0; j < lenY; j++)
            {
                if(matrix[i][j] == '1')
                {
                    if(i == 0|| j==0)
                    {
                        dp[i][j] = 1;
                    }
                    else
                    {
                        dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i][j-1]))+1;
                    }
                }
                max = Math.max(max,dp[i][j]);
            }
        }
        return max*max;
    }
}