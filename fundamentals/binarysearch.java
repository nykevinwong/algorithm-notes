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

class BinarySearch2SortedArray
{ // http://www.zrzahid.com/kth-smallest-element-in-two-sorted-arrays/
}
