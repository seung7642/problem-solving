class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        List<List<String>> distributedLines = getDistributedLines(words, maxWidth);
        
        for (int m = 0; m < distributedLines.size(); m++) {
            List<String> list = distributedLines.get(m);
            
            int paddingSize = getPaddingSize(list, maxWidth);

            StringBuffer sb = new StringBuffer();
            if (m == (distributedLines.size() - 1)) {
                sb.append(
                    list.stream()
                        .collect(Collectors.joining(" "))
                );
                int size = maxWidth - sb.length();
                for (int i = 0; i < size; i++) {
                    sb.append(" ");
                }  
            } else {
                int quotient = 0;
                int remainder = 0;
                if (list.size() > 1) {
                    quotient = paddingSize / (list.size() - 1);
                    remainder = paddingSize % (list.size() - 1);
                } else {
                    quotient = paddingSize;
                    remainder = 0;
                }

                for (int i = 0; i < list.size(); i++) {
                    String word = list.get(i);
                    sb.append(word);

                    if (i != 0 && i == (list.size() - 1)) {
                        continue;
                    }

                    for (int j = 0; j < quotient; j++) {
                        sb.append(" ");
                    }
                    if (remainder-- > 0) {
                        sb.append(" ");
                    }
                }
            }
            
            result.add(sb.toString());
        }
        
        return result;
    }
    
    private int getPaddingSize(List<String> list, int maxWidth) {
        int wordLen = list.stream()
                .mapToInt(String::length)
                .sum();
            
        return maxWidth - wordLen;
    }
    
    private List<List<String>> getDistributedLines(String[] words, int maxWidth) {
        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        int currentLen = 0;
        for (String word : words) {
            if (currentLen + list.size() + word.length() <= maxWidth) {
                list.add(word);
                currentLen += word.length();
            } else {
                result.add(list);
                list = new ArrayList<>();
                list.add(word);
                currentLen = word.length();
            }
        }
        if (!list.isEmpty()) {
            result.add(list);
        }
        return result;
    }
}