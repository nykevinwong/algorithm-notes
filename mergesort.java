import java.util.*; 
class Merge2SortedArray {  // Time: O(m+n) => O(N), Space: O(m+n) => O(N)
   public static void main(String[] args)
      { // Arrays must be sorted before merging.
      int k = 5; // kth number
      int[] arr1 = {23, 47, 81, 95};
      int[] arr2 = {7, 14, 39, 55, 62, 74};
      int[] r = new int[arr1.length + arr2.length]; 
      merge(arr1, 4, arr2, 6, r);
      System.out.println("valid sorted array = " + Arrays.equals(r, new int[] {7,14,23,39,47,55,62,74,81,95} ));
      System.out.println(java.util.Arrays.toString(r));      
      int median = (r.length % 2 != 0) ? r[r.length/2] : ( r[r.length/2-1] + r[r.length/2] ) / 2 ;
      System.out.println(k + "th = " + r[k-1] + ", median = " + median );
      }  // end main()
   //-----------------------------------------------------------
     public static void merge( int[] sorted1, int n,
                               int[] sorted2, int m,
                               int[] r )
      {  // merge two sorted arrays into one
      int i=0, j=0, k=0;
      while(i < n && j < m)  // neither array empty
        r[k++] = ( sorted1[i] < sorted2[j] ) ? sorted1[i++]:  sorted2[j++];  
      while(i < n) r[k++] = sorted1[i++];  // sorted2 is empty, but sorted1 isn't
      while(j < m) r[k++] = sorted2[j++];  // sorted1 is empty, but sorted2 isn't
      }  // end merge()
   }  // end class MergeSortedArray
class MergeCountFindKth {  // Time: O(m+n) => O(N), Space: O(1)
// k-th element or median of Two Sorted Arrays of the same/different sizes
   public static void main(String[] args)
      { // Arrays must be sorted before merging.
      int k = 5; // kth number
      int[] arr1 = {23, 47, 81, 95};
      int[] arr2 = {7, 14, 39, 55, 62, 74};
      // watch out that kth is usualy 1-based value and array is zero-based. k-1 is the index for zero-based array.
      System.out.println("3th smallest = " + findKth(arr1, 4, arr2, 6, k-1) );
      int n = arr1.length + arr2.length;
      long median = (n % 2 != 0) ? findKth(arr1, 4, arr2, 6, n/2) : (findKth(arr1, 4, arr2, 6, n/2 - 1) + findKth(arr1, 4, arr2, 6, n/2)  ) / 2 ;
      System.out.println("median = " + median );
      }  // end main()
   //-----------------------------------------------------------
     public static long findKth( int[] sorted1, int n, int[] sorted2, int m, int pos)
      {  // merge two sorted arrays into one
         int i=0, j=0, k=0; 

         while(i < n && j < m) { // neither array empty
            if( sorted1[i] < sorted2[j] ) {
               if(k==pos) return sorted1[i];
               i++; 
            }
            else {
               if(k==pos) return sorted2[j];
               j++; 
            }
            k++;
         }

         while(i < n) { if(k++==pos) return sorted1[i]; }
         while(j < m) { if(k++==pos) return sorted2[j]; }

         return Long.MIN_VALUE;
      }  // end findKth()
   }  // end class MergeCountFindKth
class MergeSort
   {
   //-----------------------------------------------------------
   public static void mergeSort(long[] arr)           // called by main()
      {                              // provides temp
      long[] temp = new long[arr.length];
      recMergeSort(temp, arr, 0, arr.length-1);
      }
   //-----------------------------------------------------------
   public static void recMergeSort(long[] temp, long[] arr,  int lowerBound,
                                               int upperBound)
      {
      if(lowerBound == upperBound)  return; // if range is 1, no sorting
      else {                                    // find midpoint
         int mid = (lowerBound + upperBound) / 2;
         recMergeSort(temp, arr, lowerBound, mid);  // sort low half
         recMergeSort(temp, arr, mid+1, upperBound);   // sort high half
         merge(temp, arr, lowerBound, mid, upperBound);  // merge them
         }  // end else
      }  // end recMergeSort()
   //-----------------------------------------------------------
   public static void merge(long[] temp, long[] arr, int lowerBound,
                           int mid, int upperBound)
      {
      int j = 0;                             // temp index
      int l = lowerBound;
      int r = mid+1;

      while(l <= mid && r <= upperBound)
          temp[j++] = ( arr[l] < arr[r] ) ? arr[l++]: arr[r++];

      while(l <= mid) temp[j++] = arr[l++];
      while(r <= upperBound) temp[j++] = arr[r++];

      for(j=lowerBound; j<=upperBound; j++)
         arr[j] = temp[j-lowerBound];
      }  // end merge()
   //-----------------------------------------------------------
   public static void main(String[] args)
      {
      long[] arr = new long[] {64,21,33,70,12,85,44,3,99,0,108,36};
      System.out.println(java.util.Arrays.toString(arr));
      mergeSort(arr);
      System.out.println(java.util.Arrays.toString(arr));
      }  // end main()
   }  // end class MergeSortApp
