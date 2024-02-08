class Solution {
    public int jump(int[] nums) {
        int len = nums.length;
        int[] memo = new int[len];
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[0] = 0;
        
        for (int i = 0; i < len; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j >= len) {
                    break;
                }
                memo[i + j] = Math.min(memo[i + j], memo[i] + 1);
            }
        }
        
        return memo[len - 1];
    }
}