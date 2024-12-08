class Solution {
    
    private List<List<Integer>> result = new LinkedList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        backtrack(nums, 0, new LinkedList<>());
        return result;
    }
    
    private void backtrack(int[] nums, int depth, LinkedList<Integer> list) {
        if (depth == nums.length) {
            result.add(new LinkedList<>(list));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            
            list.addLast(nums[i]);
            
            backtrack(nums, depth + 1, list);
            
            list.removeLast();
        }
    }
}