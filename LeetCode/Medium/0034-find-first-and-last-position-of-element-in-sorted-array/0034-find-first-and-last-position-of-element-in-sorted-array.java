class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = binarySearch(nums, target, true);
        int end = binarySearch(nums, target, false);
        
        return new int[]{start, end};
    }
    
    private int binarySearch(int[] nums, int target, boolean isStart) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                result = mid;
                if (isStart) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return result;
    }
}