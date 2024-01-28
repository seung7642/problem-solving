class Solution {
    
    private List<List<Integer>> result = new ArrayList<>();
    private int[] candidates;
    private int target;
    private Set<String> set = new HashSet<>();
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;
        
        backtrack(0, target, new LinkedList<>());
        return result;
    }
    
    public void backtrack(int idx, int accumulatedValue, LinkedList<Integer> list) {
        if (accumulatedValue == 0) {
            String str = list.stream()
                .sorted()
                .map(val -> String.valueOf(val))
                .collect(Collectors.joining(","));
            if (set.contains(str)) {
                return;
            }
            
            set.add(str);
            result.add(new LinkedList<>(list));
            return;
        }
        if (accumulatedValue < 0) {
            return;
        }
        
        for (int i = 0; i < candidates.length; i++) {
            int val = candidates[i];
            
            accumulatedValue -= val;
            list.addLast(val);
            
            backtrack(idx + 1, accumulatedValue, list);

            list.removeLast();
            accumulatedValue += val;
        }
    }
}