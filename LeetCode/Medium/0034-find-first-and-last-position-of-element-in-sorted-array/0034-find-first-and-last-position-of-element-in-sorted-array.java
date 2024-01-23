class Solution {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        
        int start = -1, end = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                start = mid;
                right = mid - 1;
            }
        }
        
        left = 0;
        right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                end = mid;
                left = mid + 1;
            }
        }
        
        return new int[]{start, end};
    }
}