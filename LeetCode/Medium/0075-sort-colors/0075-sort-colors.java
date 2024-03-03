class Solution {
    
    // Dutch National Flag problem solution. 
    public void sortColors(int[] nums) {
        int p0 = 0;
        int curr = 0;
        
        int p2 = nums.length - 1;
        
        int tmp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                // Swap p0-th and curr-th elements.
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            } else if (nums[curr] == 2) {
                // Swap k-th and curr-th elements.
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            } else {
                curr++;
            }
        }
    }
}