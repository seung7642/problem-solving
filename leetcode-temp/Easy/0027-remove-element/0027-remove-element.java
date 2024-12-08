class Solution {
    public int removeElement(int[] nums, int val) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] != val) {
                    nums[i] = nums[j];
                    nums[j] = val;
                    break;
                }
            }
        }
        
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                break;
            }
            result++;
        }
        return result;
    }
}