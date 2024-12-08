class Solution {
    
    private List<List<String>> result = new ArrayList<>();
    private Map<String, List<String>> map = new HashMap<>();
    
    public List<List<String>> groupAnagrams(String[] strs) {
        
        for (int i = 0; i < strs.length; i++) {
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);
            String sortedStr = String.valueOf(charArr);
            
            map.putIfAbsent(sortedStr, new ArrayList<>());
            map.get(sortedStr).add(strs[i]);
        }
        
        return map.values().stream()
            .collect(Collectors.toList());
    } 
}