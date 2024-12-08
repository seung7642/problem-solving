class Solution {

    static int EMPTH_VALUE = Integer.MAX_VALUE;
    Map<Integer, Integer> map = new HashMap<>();
    int duplicatedCount = 0;
    
    public int removeDuplicates(int[] nums) {
        // 1. 중복을 제거한다.
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            int count = map.getOrDefault(key, 0);
            if (count == 2) {
                nums[i] = EMPTH_VALUE;
                duplicatedCount++;
            } else {
                map.put(key, count + 1);
            }
        }
        
        // 2. 빈 공간이 생긴 위치가 있다면, 뒤에 있는 값을 앞으로 당긴다.
        for (int i = 0, j = 0; i < nums.length && j < nums.length; i++, j++) {
            int val = nums[i];
            if (val == EMPTH_VALUE) {
                while (j < nums.length - 1 && nums[j] == EMPTH_VALUE) {
                    j++;
                }
                
                nums[i] = nums[j];
                nums[j] = EMPTH_VALUE;
            }
        }
        
        return nums.length - duplicatedCount;
    }
}