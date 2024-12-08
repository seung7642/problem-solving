class Solution {
    public int firstMissingPositive(int[] nums) {
        int[] numsCopy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(numsCopy);
        
        int idx = 0;
        for (int i = 0; i < numsCopy.length; i++) {
            if (numsCopy[i] <= 0) {
                continue;
            }
            idx = i;
            break;
        }
        
        if (numsCopy[idx] != 1) {
            return 1;
        }
        
        int prev = 1;
        for (int i = idx + 1; i < numsCopy.length; i++) {
            if (numsCopy[i] == prev) {
                continue;
            }
            if (numsCopy[i] != prev + 1) {
                return prev + 1;
            }
            prev = numsCopy[i];
        }
        
        return prev + 1;
    }
}