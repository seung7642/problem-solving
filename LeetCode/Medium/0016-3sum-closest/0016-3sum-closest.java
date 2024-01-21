class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int gap = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    left++;
                    while (left < nums.length && nums[left] == nums[left - 1]) {
                        left++;
                    }
                } else if (sum > target) {
                    right--;
                } else {
                    return sum;
                }
                
                int diff = Math.abs(target - sum);
                if (gap > diff) {
                    gap = diff;
                    result = sum;
                }
            }
        }
        
        return result;
    }
}