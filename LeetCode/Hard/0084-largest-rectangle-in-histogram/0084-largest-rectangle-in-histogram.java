class Solution {
    
    public int largestRectangleArea(int[] heights) {
//         Deque<Integer> stack = new ArrayDeque<>();
//         stack.push(-1);
//         int length = heights.length;
//         int maxArea = 0;
        
//         for (int i = 0; i < length; i++) {
//             while ((stack.peek() != -1) && (heights[stack.peek()] >= heights[i])) {
//                 int currentHeight = heights[stack.pop()];
//                 int currentWidth = i - stack.peek() - 1;
//                 maxArea = Math.max(maxArea, currentHeight * currentWidth);
//             }
//             stack.push(i);
//         }
        
//         while (stack.peek() != -1) {
//             int currentHeight = heights[stack.pop()];
//             int currentWidth = length - stack.peek() - 1;
//             maxArea = Math.max(maxArea, currentHeight * currentWidth);
//         }
//         return maxArea;
        
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int h = (i == n ? 0 : heights[i]);
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                int tp = stack.pop();
                int width = stack.isEmpty() ? i : i - 1 - stack.peek();
                maxArea = Math.max(maxArea, heights[tp] * width);
                i--;  // 현재 인덱스를 다시 처리하기 위해 감소
            }
        }

        return maxArea;
    }
}