class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            max = Math.max(max, h * w);
            
            if (height[left] < height[right]) {
                left = left + 1;
            } else if (height[left] > height[right]) {
                right = right - 1;
            } else {
                left = left + 1;
                right = right - 1;
            }
        }
        
        return max;
    }
}