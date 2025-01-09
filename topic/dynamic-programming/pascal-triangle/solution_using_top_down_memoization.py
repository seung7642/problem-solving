class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        memo = {}
        def getValue(row, col) -> int:
            if row == col or col == 0:
                return 1

            if (row, col) in memo:
                return memo[(row, col)]

            memo[(row, col)] = getValue(row - 1, col - 1) + getValue(row - 1, col)
            return memo[(row, col)]
                
        return [[getValue(i, j) for j in range(i + 1)] for i in range(numRows)]