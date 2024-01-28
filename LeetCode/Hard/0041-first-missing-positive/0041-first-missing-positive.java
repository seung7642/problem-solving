class Solution {
    public int firstMissingPositive(int[] nums) {
//         int n = nums.length;
        
//         // Base case.
//         int contains = 0;
//         for (int i = 0; i < n; i++) {
//             if (nums[i] == 1) {
//                 contains++;
//                 break;
//             }
//         }
        
//         if (contains == 0) {
//             return 1;
//         }
        
//         // Replace negative numbers, zeros, and numbers larger than n by 1s.
//         // After this convertion nums will contain only positive numbers.
//         for (int i = 0; i < n; i++) {
//             if ((nums[i] <= 0) || (nums[i] > n)) {
//                 nums[i] = 1;
//             }
//         }
        
//         // Use index as a hash key and number sign as a presence detector. 
//         // For example, if nums[1] is negative that means that number `1` is present in the array. 
//         // if nums[2] is positive - number 2 is missing. 
//         for (int i = 0; i < n; i++) {
//             int a = Math.abs(nums[i]);
            
//             // If you meet number a in the array - change the sign of a-th element. 
//             // Be careful with duplicates : do it only once. 
//             if (a == n) {
//                 nums[0] = - Math.abs(nums[0]);
//             } else {
//                 nums[a] = - Math.abs(nums[a]);
//             }
//         }
        
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