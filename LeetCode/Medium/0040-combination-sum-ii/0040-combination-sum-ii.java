class Solution {
    
    private int[] candidates;
    private Set<String> set = new HashSet<>();
    private List<List<Integer>> result = new LinkedList<>();
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.candidates = candidates;
        Arrays.sort(candidates);
        
        backtrack(0, new LinkedList<>(), target);
        return result;
    }
    
    private void backtrack(int idx, LinkedList<Integer> list, int target) {
        if (target == 0) {
            String str = list.stream()
                .map(v -> String.valueOf(v))
                .collect(Collectors.joining(","));
            
            if (set.contains(str)) {
                return;
            }
            set.add(str);
            result.add(new LinkedList<>(list));
            return;
        }
        if (target < 0) {
            return;
        }
        
        for (int i = idx; i < candidates.length; i++) {
            int val = candidates[i];
            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            target -= val;
            list.addLast(val);
            
            backtrack(i + 1, list, target);
            
            list.removeLast();
            target += val;
        }
    }
}