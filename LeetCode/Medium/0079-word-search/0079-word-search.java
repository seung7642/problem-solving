enum Direction {
    UP(0, -1), 
    DOWN(0, 1), 
    LEFT(-1, 0), 
    RIGHT(1, 0);
    
    private final int row;
    private final int col;
    
    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public int getCol() {
        return this.col;
    }
}

class Solution {
    
    char[][] newBoard;
    int n;
    int m;
    String newWord;
    boolean[][] visited;
    
    public boolean exist(char[][] board, String word) {
        newBoard = board;
        n = board.length;
        m = board[0].length;
        newWord = word;
        visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                boolean result = backtrack(i, j, String.valueOf(newBoard[i][j]));
                if (result) {
                    return result;
                }
                visited[i][j] = false;
            }
        }
        return false;
    }
    
    private boolean backtrack(int row, int col, String str) {
        if (str.length() > newWord.length() || !newWord.startsWith(str)) {
            return false;
        }
        if (newWord.equals(str)) {
            return true;
        }
        
        for (Direction dir : Direction.values()) {
            int nextRow = row + dir.getRow();
            int nextCol = col + dir.getCol();
            boolean result = false;
            if (isValid(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                visited[nextRow][nextCol] = true;
                result = backtrack(nextRow, nextCol, str + newBoard[nextRow][nextCol]);
                visited[nextRow][nextCol] = false;
            }
            if (result) {
                return result;
            }
        }
        return false;
    }
    
    private boolean isValid(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < m;
    }
}