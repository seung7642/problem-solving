class Solution {
    
    Set<List<Integer>> result = new HashSet<>();
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        int n = nums.length;
        
        Arrays.sort(nums);
        
        int maxNumberOfSubsets = (int) Math.pow(2, n);
        Set<String> seen = new HashSet<>();
        
        for (int subsetIndex = 0; subsetIndex < maxNumberOfSubsets; subsetIndex++) {
            List<Integer> currentSubset = new ArrayList<>();
            StringBuilder hashcode = new StringBuilder();
            for (int j = 0; j < n; j++) {
                int mask = 1 << j;
                int isSet = mask & subsetIndex;
                if (isSet != 0) {
                    currentSubset.add(nums[j]);
                    hashcode.append(nums[j]).append(",");
                }
            }
            
            if (!seen.contains(hashcode.toString())) {
                seen.add(hashcode.toString());
                subsets.add(currentSubset);
            }
        }
        
        return subsets;
    }
}