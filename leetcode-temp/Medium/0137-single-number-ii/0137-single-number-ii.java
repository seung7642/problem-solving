class Solution {
    
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                if (map.get(num) == 2) {
                    map.remove(num);
                } else {
                    map.put(num, map.get(num) + 1);
                }
            }
        }
        
        return map.keySet().iterator().next();
    }
}