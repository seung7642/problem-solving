class Solution {
    
    private List<List<String>> result = new ArrayList<>();
    private Map<String, List<String>> map = new HashMap<>();
    
    public List<List<String>> groupAnagrams(String[] strs) {
        
        for (int i = 0; i < strs.length; i++) {
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);
            String sortStr = String.valueOf(charArr);
            if (map.containsKey(sortStr)) {
                map.get(sortStr).add(strs[i]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(sortStr, list);
            }
        }
        
        return map.values().stream().collect(Collectors.toList());
    } 
}