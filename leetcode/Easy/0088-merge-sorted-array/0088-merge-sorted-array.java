class Solution {
    
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        int idx = 0;
        int zeroIdx = nums1.length - 1;
        while (zeroIdx >= 0 && nums1[zeroIdx] == 0) {
            zeroIdx--;
        }
        zeroIdx++;
        
        for (int num : nums2) {
            while (idx < zeroIdx && nums1[idx] <= num) {
                idx++;
            }
            
            for (int i = zeroIdx; i > idx; i--) {
                nums1[i] = nums1[i - 1];
            }
            
            nums1[idx] = num;
            zeroIdx++;
        }
    }
}