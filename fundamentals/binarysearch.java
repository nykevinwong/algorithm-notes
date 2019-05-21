class BinarySearchSortedArray {
   public static void main(String[] args)
   {
         int sorted[] = new int[] {10,20,30,50,90,110,200,220,320,340,450};
         System.out.println(java.util.Arrays.toString(sorted));
         System.out.println("50 is found at index " + iterBinarySearch(sorted, 50));
         System.out.println("100 is not found => " + (iterBinarySearch(sorted, 100) == Long.MIN_VALUE) ) ;
         System.out.println("90 is found at index " + recurBinarySearch(sorted, 0, sorted.length-1, 90));
         System.out.println("120 is not found => " + (recurBinarySearch(sorted, 0, sorted.length-1, 120) == Long.MIN_VALUE) );
   }  
   // end main
   public static long iterBinarySearch(int sorted[], int target)
   {
         int l = 0, r = sorted.length-1;
         while(l<=r)
         {  
            int mid = l + (r-l)/2;            
            if(sorted[mid] < target) l = mid+1; // search right side
            else if(sorted[mid] > target) r=mid-1; // search left side
            else return mid;
         }
         return Long.MIN_VALUE; // not found
   } // end iterativeBinarySearch
   public static long recurBinarySearch(int sorted[], int l, int r, int target)
   {
         if(l<=r)
         {         
            int mid = l + (r-l)/2;
            if(sorted[mid] < target) return recurBinarySearch(sorted, mid+1 , r, target); // search right side
            else if(sorted[mid] > target) return recurBinarySearch(sorted, l ,mid-1, target); // search left side
            else return mid;
         }
         return Long.MIN_VALUE; // not found
   } // end iterativeBinarySearch
}


 // http://www.zrzahid.com/kth-smallest-element-in-two-sorted-arrays/
class BinarySearch2SortedArray
{
    public static int findkth(int[] nums1, int[] nums2, int k)
    {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int start = 0 ;
        int end = java.lang.Math.min(n1, k);
        
        while(start <= end)
        {
            int mid1 = (start+end) / 2;
            int mid2 = k - mid1;
            if(mid2 > n2)
            {
                start = mid1+1; continue;
            }

            int l1 = (mid1 == 0) ? Integer.MIN_VALUE  : nums1[mid1-1];
            int l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2-1];
            int r1 = (mid1 == n1) ? Integer.MAX_VALUE : nums1[mid1];
            int r2 = (mid2 == n2) ? Integer.MAX_VALUE : nums2[mid2];

            if(l1 > r2) end = mid1 - 1;                
            else if(l2 > r1) start = mid1 + 1;
            else return java.lang.Math.max(l1, l2);
        }

        return 0;
    }

    public static void main(String[] args)
    {
        int[] nums1 = new int[] { 1, 3, 6, 7, 10};
        int[] nums2 = new int[] { 2, 4, 5, 8, 9};
        int N = nums1.length + nums2.length;
        int k1 = (N-1)/2 + 1; 
        int k2 = (N)/2 + 1; 
        double median = (findkth(nums1, nums2, k1) + findkth(nums1, nums2, k2) ) / 2.0;
        System.out.println("median =" + median);
    }
}
