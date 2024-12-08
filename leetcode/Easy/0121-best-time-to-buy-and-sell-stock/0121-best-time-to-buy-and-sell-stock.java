class Solution {

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            
            if (minPrice > price) {
                minPrice = price;
            } else if (maxProfit < price - minPrice) {
                maxProfit = price - minPrice;
            }
        }
        
        return maxProfit;
    }
}