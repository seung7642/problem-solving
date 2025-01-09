class Solution:
    def canJump(self, nums: List[int]) -> bool:  
        memo = [None] * len(nums)
        memo[-1] = True

        def backtracking(col) -> bool:  
            if memo[col] is not None:
                return memo[col]  

            furthest = min(col + nums[col], len(nums) - 1)
            for next_col in range(col + 1, furthest + 1):
                if backtracking(next_col):
                    memo[col] = True
                    return True

            memo[col] = False
            return False

        return backtracking(0)
        