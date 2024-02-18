class Solution {
    
    private Map<int[], Integer> graph = new HashMap<>();
    
    public int[][] merge(int[][] intervals) {
        // i번째 배열의 end 값이 i + 1번째 배열의 start 값보다 크다면 i번째 배열과 i + 1번째 배열은 overlapping 되었다고 판단할 수 있다. 
        // - 이 말이 성립하기 위한 전제조건은 다음과 같다.
        //   - start 값보다 end 값이 커야 한다. 
        //   - i번째 start 값이 i + 1번째 start 값보다 커야 한다.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] curNode = intervals[0];
        graph.put(curNode, curNode[1]);
        
        for (int i = 1; i < intervals.length; i++) {
            if (graph.get(curNode) >= intervals[i][0]) {
                int end = Math.max(graph.get(curNode), intervals[i][1]);
                graph.put(curNode, end);
            } else {
                curNode = intervals[i];
                graph.put(curNode, curNode[1]);
            }
        }
        
        return graph.entrySet().stream()
            .map(entry -> new int[]{entry.getKey()[0], entry.getValue()})
            .toArray(int[][]::new);
    }
}