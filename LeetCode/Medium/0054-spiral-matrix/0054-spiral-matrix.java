enum Direction {
    RIGHT, DOWN, LEFT, UP
}

class Solution {
    
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        Direction direction = Direction.RIGHT;
        int matrixRow = matrix.length;
        int matrixCol = matrix[0].length;
        int row = 0;
        int col = 0;
        int cnt = 1;
        result.add(matrix[0][0]);
        matrix[0][0] = Integer.MAX_VALUE;
        while (cnt < matrixRow * matrixCol) {
            direction = switch (direction) {
                case RIGHT -> {
                    while (col + 1 < matrixCol && matrix[row][col + 1] != Integer.MAX_VALUE) {
                        col++;
                        result.add(matrix[row][col]);
                        matrix[row][col] = Integer.MAX_VALUE;
                        cnt++;
                    }
                    yield Direction.DOWN;
                }
                case DOWN -> {
                    while (row + 1 < matrixRow && matrix[row + 1][col] != Integer.MAX_VALUE) {
                        row++;
                        result.add(matrix[row][col]);
                        matrix[row][col] = Integer.MAX_VALUE;
                        cnt++;
                    }
                    yield Direction.LEFT;
                }
                case LEFT -> {
                    while (col - 1 >= 0 && matrix[row][col - 1] != Integer.MAX_VALUE) {
                        col--;
                        result.add(matrix[row][col]);
                        matrix[row][col] = Integer.MAX_VALUE;
                        cnt++;
                    }
                    yield Direction.UP;
                }
                case UP -> {
                    while (row - 1 >= 0 && matrix[row - 1][col] != Integer.MAX_VALUE) {
                        row--;
                        result.add(matrix[row][col]);
                        matrix[row][col] = Integer.MAX_VALUE;
                        cnt++;
                    }
                    yield Direction.RIGHT;
                }
            };
        }
        
        return result;
    }
    
}