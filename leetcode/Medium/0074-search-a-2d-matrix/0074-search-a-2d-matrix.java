class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        
        int n = matrix[0].length;

        int left = 0;
        int right = m * n - 1;
        int pivotIdx, pivotElement;
        
        while (left <= right) {
            pivotIdx = (left + right) / 2;
            pivotElement = matrix[pivotIdx / n][pivotIdx % n];
            
            if (pivotElement < target) {
                left = pivotIdx + 1;
            } else if (pivotElement > target) {
                right = pivotIdx - 1;
            } else {
                return true;
            }
        }
        
        return false;
    }
}