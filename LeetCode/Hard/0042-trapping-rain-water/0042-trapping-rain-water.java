class Solution {
    public int trap(int[] height) {
        int ans = 0;
        int len = height.length;
        for (int i = 1; i < len - 1; i++) {
            int left_max = 0, right_max = 0;
            
            for (int j = i; j >= 0; j--) {
                left_max = Math.max(left_max, height[j]);
            }
            for (int j = i; j < len; j++) {
                right_max = Math.max(right_max, height[j]);
            }
            ans += Math.min(left_max, right_max) - height[i];
        }
        
        return ans;
    }
}