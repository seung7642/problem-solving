class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        row = len(matrix)
        col = len(matrix[0]) if matrix else 0
        
        left = 0
        right = row * col - 1

        while left <= right:
            pivot = (left + right) // 2
            r = pivot // col
            c = pivot % col
            
            if matrix[r][c] < target:
                left = pivot + 1
            elif matrix[r][c] > target:
                right = pivot - 1
            else:
                return True
        
        return False
        