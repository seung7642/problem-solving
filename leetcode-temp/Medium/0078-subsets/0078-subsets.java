class Solution {
    
    List<List<Integer>> result = new ArrayList<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0, new ArrayList<>());
        return result;
    }
    
    private void backtrack(int[] nums, int depth, List<Integer> trace) {
        result.add(trace);
        if (nums.length == depth) {
            return;
        }
        
        for (int i = depth; i < nums.length; i++) {
            List<Integer> list = new ArrayList<>(trace);
            list.add(nums[i]);
            backtrack(nums, i + 1, list);
        }
    }
}