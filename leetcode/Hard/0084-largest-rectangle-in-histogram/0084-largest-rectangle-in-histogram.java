class Solution {
    
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                int currentHeight = heights[stack.pop()];
                int currentWeight = i - 1 - stack.peek();
                max = Math.max(max, currentHeight * currentWeight);
            }
            stack.push(i);
        }
        
        while (stack.peek() != -1) {
            int currentHeight = heights[stack.pop()];
            int currentWeight = heights.length - 1 - stack.peek();
            max = Math.max(max, currentHeight * currentWeight);
        }
        
        return max;
    }
}