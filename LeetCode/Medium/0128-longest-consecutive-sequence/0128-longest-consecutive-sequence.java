class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        Arrays.sort(nums);
        
        int result = 0;
        int len = 1;
        int prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (prev == nums[i]) {
                continue;
            }
            
            if (prev + 1 == nums[i]) {
                len++;
                prev = nums[i];
            } else {
                result = Math.max(result, len);
                len = 1;
                prev = nums[i];
            }
        }
        
        return Math.max(result, len);
    }
}