class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        int result = 0;
        for (int num : nums) {
            if (set.contains(num - 1)) {
                continue;
            }
            
            int len = 1;
            int tmp = num;
            while (set.contains(tmp + 1)) {
                len++;
                tmp++;
            }
            result = Math.max(result, len);
        }
        
        return result;
    }
}