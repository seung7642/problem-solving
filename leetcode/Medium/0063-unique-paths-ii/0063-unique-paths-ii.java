class Solution {
    
    private int r, c;
    private int[][] grid;
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        grid = obstacleGrid;
        r = obstacleGrid.length;
        c = obstacleGrid[0].length;
        if (grid[0][0] == 1) {
            return 0;
        }
        
        grid[0][0] = 1;
        
        // Filling the values for the first column 
        for (int i = 1; i < r; i++) {
            grid[i][0] = (grid[i][0] == 0 && grid[i - 1][0] == 1) ? 1 : 0;
        }
        
        // Filling the values for the first row 
        for (int i = 1; i < c; i++) {
            grid[0][i] = (grid[0][i] == 0 && grid[0][i - 1] == 1) ? 1 : 0;
        }
        
        // Starting from cell(1, 1) fill up the values 
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left. 
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
                } else {
                    grid[i][j] = 0;
                }
            }
        }
        
        return grid[r - 1][c - 1];
    }
}