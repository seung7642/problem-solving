"""
Search Insert Position
--------------------
Given a sorted array of distinct integers and a target value,
return the index if the target is found.
If not, return the index where it would be if it were inserted in order.

Example:
    Input: nums = [1,3,5,6], target = 5
    Output: 2

    Input: nums = [1,3,5,6], target = 2
    Output: 1

Time Complexity: O(log n)
Space Complexity: O(1)

Reference:
- https://leetcode.com/problems/search-insert-position/description/

Related Topics:
- Binary Search
- Array
"""

class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        left, right = 0, len(nums) - 1
        while left <= right:
            mid = (left + right) // 2
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                left = mid + 1
            else:
                right = mid - 1

        return left

