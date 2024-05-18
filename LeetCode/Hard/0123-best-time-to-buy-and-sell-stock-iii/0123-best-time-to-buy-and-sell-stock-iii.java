class Pair {
    public int min;
    public int max;
    
    public Pair(int min, int max) {
        this.min = min;
        this.max = max;
    }
    
    public int diff() {
        return max - min;
    }
}

class Solution {
    
    public int maxProfit(int[] prices) {
        // 문제 요구사항 : 서로 겹치지 않으면서 고점과 저점의 차이의 합이 큰 두 개의 구간을 찾아라.
        // 피벗을 기준으로 좌/우 측의 고점과 저점의 차이가 가장 큰 구간을 구하는 것.
        
        Map<Integer, Integer> leftMap = new HashMap<>();
        Map<Integer, Integer> rightMap = new HashMap<>();

        int min = Integer.MAX_VALUE;
        int maxPrice = 0;
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            
            if (price < min) {
                min = price;
            } else if (maxPrice < price - min) {
                maxPrice = price - min;
            }
            
            leftMap.put(i, maxPrice);
        }
        
        maxPrice = 0;
        int max = Integer.MIN_VALUE;
        for (int i = prices.length - 1; i >= 0; i--) {
            int price = prices[i];
            
            if (price > max) {
                max = price;
            } else if (maxPrice < max - price) {
                maxPrice = max - price;
            }
            
            rightMap.put(i, maxPrice);
        }
        
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            result = Math.max(result, leftMap.get(i) + rightMap.get(i));
        }
        
        return result;
    }
}