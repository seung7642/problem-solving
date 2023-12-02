class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        IntStream.range(0, nums.length)
            .forEach(idx -> {
                if (map.containsKey(nums[idx])) {
                    map.get(nums[idx]).add(idx);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(idx);
                    map.put(nums[idx], list);
                }
            });

        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];
            if (map.containsKey(x)) {

                List<Integer> list = map.get(x);
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) != i) {
                        result[0] = i;
                        result[1] = list.get(j);
                        return result;
                    }
                }
                
            }
        }

        return result;
    }
}