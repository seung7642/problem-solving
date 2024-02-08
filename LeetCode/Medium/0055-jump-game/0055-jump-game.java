class Solution {
    public boolean canJump(int[] nums) {
        
        int len = nums.length;
        boolean[] check = new boolean[len];
        check[0] = true;
        for (int i = 0; i < len; i++) {
            if (!check[i]) {
                continue;
            }
            
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j >= len) {
                    break;
                }
                check[i + j] = true;
            }
        }
        
        return check[len - 1];
    }
}