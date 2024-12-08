class Solution {
    
    private List<List<String>> result = new ArrayList<>();
    private int n;
    
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        backtrack(n, 0, new LinkedList<>());
        return result;
    }
    
    private void backtrack(int n, int depth, LinkedList<String> board) {
        if (depth == n) {
            result.add(new LinkedList<>(board));
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (isAttacked(board, depth, i)) {
                continue;
            }
            
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < n; k++) {
                if (k == i) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            board.addLast(sb.toString());

            backtrack(n, depth + 1, board);
                
            board.removeLast();
        }
    }
    
    private boolean isAttacked(LinkedList<String> board, int curQueenRow, int curQueenCol) {
        if (curQueenRow == 0) {
            return false;
        }
        
        for (int i = 0; i < curQueenRow; i++) {
            String row = board.get(i);
            for (int j = 0; j < n; j++) {
                char ch = row.charAt(j);
                if (ch == 'Q') {
                    if (verticalAttack(j, curQueenCol)) {
                        return true;
                    }
                    if (diagonalAttack(i, j, curQueenRow, curQueenCol)) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    private boolean verticalAttack(int prevQueenCol, int curQueenCol) {
        if (prevQueenCol == curQueenCol) {
            return true;
        }
        return false;
    }
    
    private boolean diagonalAttack(int prevQueenRow, int prevQueenCol, int curQueenRow, int curQueenCol) {
        int nextRow = prevQueenRow;
        int nextCol = prevQueenCol;
        boolean keep = true;
        
        // 좌측 하단
        while (keep) {
            keep = false;
            nextRow += 1;
            nextCol -= 1;
            if (isValid(nextRow, nextCol)) {
                keep = true;
                if (nextRow == curQueenRow && nextCol == curQueenCol) {
                    return true;
                }
            }
        }
        
        // 우측 하단
        nextRow = prevQueenRow;
        nextCol = prevQueenCol;
        keep = true;
        while (keep) {
            keep = false;
            nextRow += 1;
            nextCol += 1;
            if (isValid(nextRow, nextCol)) {
                keep = true;
                if (nextRow == curQueenRow && nextCol == curQueenCol) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < n && col < n;
    }
}