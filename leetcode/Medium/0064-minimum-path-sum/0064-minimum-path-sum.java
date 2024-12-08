class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        int[][] matrix = new int[n][m];
        matrix[0][0] = grid[0][0];
        
        for (int i = 1; i < m; i++) {
            matrix[0][i] = matrix[0][i - 1] + grid[0][i];
        }
        
        for (int i = 1; i < n; i++) {
            matrix[i][0] = matrix[i - 1][0] + grid[i][0];
        }
        
        for (int row = 1; row < n; row++) {
            for (int col = 1; col < m; col++) {
                matrix[row][col] = Math.min(matrix[row - 1][col], matrix[row][col - 1]) + grid[row][col];
            }
        }
        
        return matrix[n - 1][m - 1];
    }
}