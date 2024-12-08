class Solution {
    public int searchInsert(int[] nums, int target) {
        int result = 0;
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
                result = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
                result = mid + 1;
            } else {
                return mid;
            }
        }
        return result;
    }
}