class Solution {
    public boolean search(int[] nums, int target) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int start = Integer.MAX_VALUE;
        int k = 0;
        int duplicatedCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                duplicatedCount++;
                continue;
            }
            if (start > nums[i]) {
                start = nums[i];
                k = i - duplicatedCount;
            }
            list.add(nums[i]);
            set.add(nums[i]);
        }
        
        int len = list.size();
        int left = 0 + k;
        int right = len - 1 + k;
        while (left <= right) {
            int mid = (left + right) / 2;
            int val = list.get(mid % len);
            if (val == target) {
                return true;
            } else if (val < target) {
                left = mid + 1;
            } else if (val > target) {
                right = mid - 1;
            }
        }
        
        return false;
    }
}