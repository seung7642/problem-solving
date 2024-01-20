class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergedArr = mergeArrays(nums1, nums2);
        
        int len = mergedArr.length;
        if (mergedArr.length % 2 == 0) {
            return (mergedArr[len / 2 - 1] + mergedArr[len / 2]) / 2.0;
        } else {
            return mergedArr[len / 2];
        }
    }
    
    private int[] mergeArrays(int[] arr1, int[] arr2) {
        int[] mergedArray = new int[arr1.length + arr2.length];
        
        System.arraycopy(arr1, 0, mergedArray, 0, arr1.length);
        System.arraycopy(arr2, 0, mergedArray, arr1.length, arr2.length);
        
        Arrays.sort(mergedArray);
        return mergedArray;
    }
}