class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int left = j + 1;
                int right = nums.length - 1;
                while (left < nums.length && left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum < target) {
                        left++;
                        while (left < nums.length && nums[left] == nums[left - 1]) {
                            left++;
                        }
                    } else if (sum > target) {
                        right--;
                    } else {
                        String str = String.format("%d,%d,%d,%d", nums[i], nums[j], nums[left], nums[right]);
                        if (!set.contains(str)) {
                            set.add(str);
                            result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        
        return result;
    }
}