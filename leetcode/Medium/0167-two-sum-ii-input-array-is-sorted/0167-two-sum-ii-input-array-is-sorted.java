class Solution {
    public int[] twoSum(int[] numbers, int target) {
        
        for (int i = 0; i < numbers.length - 1; i++) {
            int searchFor = target - numbers[i];
            
            // int[] subarray = Arrays.copyOfRange(i + 1, numbers.length);
            int idx = Arrays.binarySearch(numbers, i + 1, numbers.length, searchFor);
            if (idx >= 0 ) {
                return new int[]{i + 1, idx + 1};
            }
        }
        return new int[2];
    }
}