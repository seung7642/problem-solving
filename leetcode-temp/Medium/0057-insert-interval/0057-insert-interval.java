class Solution {
    
    private Map<int[], Integer> map = new HashMap<>();
    
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 1. newInterval을 intervals에 끼워넣기.
        // 2. merge interval 수행 
        
        if (intervals.length == 0) {
            return new int[][] {
                newInterval
            };
        }
        
        
        int[][] newIntervals = new int[intervals.length + 1][2];
        newIntervals[0] = newInterval;
        for (int i = 0; i < intervals.length; i++) {
            newIntervals[i + 1] = intervals[i];
        }
        Arrays.sort(newIntervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] curNode = newIntervals[0];
        map.put(curNode, curNode[1]);
        for (int i = 1; i < newIntervals.length; i++) {
            if (map.get(curNode) >= newIntervals[i][0]) {
                int end = Math.max(map.get(curNode), newIntervals[i][1]);
                map.put(curNode, end);
            } else {
                curNode = newIntervals[i];
                map.put(curNode, curNode[1]);
            }
        }
        
        int[][] result = map.entrySet().stream()
            .map(entry -> new int[]{entry.getKey()[0], map.get(entry.getKey())})
            .toArray(int[][]::new);
        
        Arrays.sort(result, (a, b) -> Integer.compare(a[0], b[0]));
        return result;
    }
}