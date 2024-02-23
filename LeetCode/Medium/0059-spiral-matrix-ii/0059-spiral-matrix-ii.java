enum Direction {
    RIGHT, DOWN, LEFT, UP;
}

class Solution {
    
    public int[][] generateMatrix(int n) {
    
        int[][] matrix = new int[n][n];
        Direction dir = Direction.RIGHT;
        
        int val = 1;
        int row = 0;
        int col = 0;
        boolean flag = true;
        while (flag) {
            flag = false;
            dir = switch (dir) {
                case RIGHT:
                    while (col < n && matrix[row][col] == 0) {
                        matrix[row][col++] = val++;
                        flag = true;
                    }
                    col--;
                    row++;
                    yield Direction.DOWN;
                case DOWN:
                    while (row < n && matrix[row][col] == 0) {
                        matrix[row++][col] = val++;
                        flag = true;
                    }
                    row--;
                    col--;
                    yield Direction.LEFT;
                case LEFT:
                    while (col >= 0 && matrix[row][col] == 0) {
                        matrix[row][col--] = val++;
                        flag = true;
                    }
                    col++;
                    row--;
                    yield Direction.UP;
                case UP:
                    while (row >= 0 && matrix[row][col] == 0) {
                        matrix[row--][col] = val++;
                        flag = true;
                    }
                    row++;
                    col++;
                    yield Direction.RIGHT;
            };
        }
        
        return matrix;
    }
}