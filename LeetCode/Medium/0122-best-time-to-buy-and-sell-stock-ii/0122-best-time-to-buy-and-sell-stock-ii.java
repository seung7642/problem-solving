class Solution {
    public int maxProfit(int[] prices) {
        int result = 0;
        int previous = Integer.MAX_VALUE;
        
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            
            if (price < previous) {
                previous = price;
            } else if (price > previous) {
                result += (price - previous);
                previous = price;
            }
        }
        
        return result;
    }
}