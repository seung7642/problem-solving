class Solution {
    
    public int search(int[] nums, int target) {
        // 1. 가장 작은 값의 인덱스를 찾는다. (시작 인덱스)
        int k = 0;
        int len = nums.length;
        int start = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < start) {
                start = nums[i];
                k = i;
            }
        }
        
        // 2. 가장 작은 값의 인덱스를 k라고 했을 때, 0 ~ n-1 번 인덱스를 이진 탐색하는데, 이때 인덱스에 k만큼을 더해준다. 
        int left = 0 + k;
        int right = nums.length - 1 + k;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid % len] == target) {
                return mid % len;
            } else if (nums[mid % len] < target) {
                left = mid + 1;
            } else if (nums[mid % len] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }
}