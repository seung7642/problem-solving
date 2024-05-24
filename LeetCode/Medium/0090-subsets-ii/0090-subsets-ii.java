class Solution {
    
    Set<List<Integer>> result = new HashSet<>();
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtracking(nums, 0, new ArrayList<>());
        return new ArrayList<>(result);
    }
    
    private void backtracking(int[] nums, int depth, List<Integer> list) {
        if (depth > nums.length) {
            return;
        }
        
        result.add(new ArrayList<>(list));
        for (int i = depth; i < nums.length; i++) {
            list.add(nums[i]);
            
            backtracking(nums, i + 1, list);
            
            list.remove(list.size() - 1);
        }
    }
}