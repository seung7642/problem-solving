import heapq

class Solution:
    def deleteGreatestValue(self, grid: List[List[int]]) -> int:
        sorted_rows = [sorted(row, reverse=True) for row in grid]

        result = 0
        for col in range(len(grid[0])):
            result += max(row[col] for row in sorted_rows)

        return result
        