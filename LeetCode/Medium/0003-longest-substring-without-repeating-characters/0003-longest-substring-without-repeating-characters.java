class Solution {
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        Set<Character> set = new TreeSet<>();
        int left, right;
        left = right = 0;
        while (left < s.length() && right < s.length()) {
            char ch = s.charAt(right);
            if (set.contains(ch)) {
                result = Math.max(result, set.size());
                set.clear();
                left++;
                right = left;
            } else {
                set.add(ch);
                right++;
            }
        }
        
        return Math.max(result, set.size());
    }
}