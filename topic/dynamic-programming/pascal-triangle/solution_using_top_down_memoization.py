class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        result = [[None] * (i + 1) for i in range(numRows)]

        def getPascalByRow(row) -> List[int]:
            if row == 0:
                result[row][0] = 1
                return result[row][0]

            if result[row][0] is not None:
                return result[row]

            previous_row = getPascalByRow(row - 1)
            result[row][0] = 1
            result[row][row] = 1
            for i in range(1, row):
                result[row][i] = previous_row[i - 1] + previous_row[i]

            return result[row]

        getPascalByRow(numRows - 1)
        return result
    