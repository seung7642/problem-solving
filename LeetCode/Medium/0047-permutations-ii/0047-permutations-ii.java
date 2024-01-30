class Solution {
    
    private List<List<Integer>> result = new LinkedList<>();
    private Set<String> resultSet = new HashSet<>();
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        backtrack(nums, 0, new LinkedList<>(), new HashSet<>());
        return result;
    }
    
    private void backtrack(int[] nums, int depth, LinkedList<Integer> list, Set<String> set) {
        if (depth == nums.length) {
            if (!resultSet.contains(list.toString())) {
                resultSet.add(list.toString());
                result.add(new LinkedList<>(list));
            }
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            String key = i + "," + nums[i];
            if (set.contains(key)) {
                continue;
            }
            
            set.add(key);
            list.addLast(nums[i]);
            
            backtrack(nums, depth + 1, list, set);
            
            list.removeLast();
            set.remove(key);
        }
    }
}