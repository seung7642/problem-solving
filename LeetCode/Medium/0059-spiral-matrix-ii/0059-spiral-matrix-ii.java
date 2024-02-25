class Solution {
    
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        int row = 0;
        int col = 0;
        int cnt = 1;
        int d = 0;
        while (cnt <= n * n) {
            matrix[row][col] = cnt++;
            
            int nextRow = row + dir[d][0];
            int nextCol = col + dir[d][1];
            if (!isValid(nextRow, nextCol, n) || matrix[nextRow][nextCol] != 0) {
                d = (d + 1) % 4;
            }
            
            row += dir[d][0];
            col += dir[d][1];
        }
        
        return matrix;
    }
    
    private boolean isValid(int row, int col, int n) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }
}