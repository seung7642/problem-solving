class Solution:
    def getRow(self, rowIndex: int) -> List[int]:
        memo = {}

        def getPascalValue(row, col) -> int:
            if col == 0 or col == row:
                return 1

            if (row, col) in memo:
                return memo[(row, col)]

            memo[(row, col)] = getPascalValue(row - 1, col - 1) + getPascalValue(row - 1, col)
            return memo[(row, col)]

        return [getPascalValue(rowIndex, i) for i in range(rowIndex + 1)]