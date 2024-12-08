class Solution {
    public int[] plusOne(int[] digits) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digits.length; i++) {
            sb.append(digits[i]);
        }
        
        int idx = sb.length() - 1;
        boolean isPlus = true;
        while (isPlus && idx >= 0) {
            char c = sb.charAt(idx);
            if (c == '9') {
                sb.setCharAt(idx, '0');
                if (idx == 0) {
                    sb.insert(0, '1');
                    break;
                }
            } else {
                c++;
                sb.setCharAt(idx, c);
                isPlus = false;
            }
            
            idx--;
        }
        
        int[] result = new int[sb.length()];
        for (int i = 0; i < sb.length(); i++) {
            result[i] = sb.charAt(i) - '0';
        }
        
        return result;
    }
}