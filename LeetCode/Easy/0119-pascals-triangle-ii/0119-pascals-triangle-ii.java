class Solution {
    
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(1));
        
        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> prev = result.get(i - 1);
            LinkedList<Integer> list = new LinkedList<>();
            
            for (int j = 1; j < i; j++) {
                list.add(prev.get(j - 1) + prev.get(j));
            }
            
            list.addFirst(1);
            list.addLast(1);
            result.add(list);
        }
        
        return result.get(rowIndex);
    }
}