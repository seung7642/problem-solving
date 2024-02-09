class Solution {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] memo = new int[len];
        memo[0] = nums[0];
        int max = memo[0];
        int fromIdx = 0;
        int toIdx = 0;
        for (int i = 1; i < len; i++) {
            if (memo[i - 1] + nums[i] < nums[i]) {
                memo[i] = nums[i];
                fromIdx = i;
                toIdx = i;
            } else {
                memo[i] = memo[i - 1] + nums[i];
                toIdx = i;
            }
            max = Math.max(max, memo[i]);
        }

        return max;
    }
}