import java.util.*;
class MergeSortedArray { 
   public static void main(String[] args)
      { // Arrays must be sorted before merging.
      int[] A = {23, 47, 81, 95};
      int[] B = {7, 14, 39, 55, 62, 74};
      int[] C = new int[A.length+B.length];
      merge(A, A.length, B, B.length, C);
      System.out.println(java.util.Arrays.toString(C));
      System.out.println("valid array ?" + Arrays.equals(C, new int[] {7,14,23,39,47,55,62,74,81,95} ));
      }  // end main()
   //-----------------------------------------------------------
     public static void merge( int[] A, int N1,
                               int[] B, int N2,
                               int[] C )
      {  // merge A and B into C
      int a=0, b=0, c=0;
      // neither array empty
      while(a < N1 && b < N2) C[c++] = ( A[a] < B[b] ) ? A[a++]:  B[b++];  
      while(a < N1) C[c++] = A[a++];  // B is empty,but A isn't
      while(b < N2) C[c++] = B[b++];  // A is empty,but B isn't
      }  // end mergeSorted()
   }  // end class MergeSortedArray

// (1) find median [done, linear time complexity, but not best in the worst case (median of median is better)]
// (2) ducth national flag problem [done for 3 color] [https://algorithmsandme.com/3-way-quicksort/]
// (3) simple 3 way quicksort [done]
// (4) tail call optimization
// (5) iterative quicksort
// (6) median of medians 

// 3-way parition: There is another quicksort optimization which is called as Entropy-optimal sorting or 3-way partition quick sort. 
// This method is useful when inout array contains lot of duplicate values which is very frequent in real world.
// Idea is to divide array in three parts rather than two. Let’s say P be pivot.
// First part contains all numbers which are less than p, second part contains number equal to p and last part contains 
// numbers which are greater than p.
class QuickSort3WayPartition
{
    public long[] arr;

    public QuickSort3WayPartition(int size)
    {
        arr = new long[size];
        for(int i=0;i<arr.length;i++)
            arr[i] = (int)(java.lang.Math.random()*199);
    }

    public void display()
    {
        System.out.println(java.util.Arrays.toString(this.arr));
    }

    public void swap(int index1, int index2)
    {
        long temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public void quicksort()
    {
        quicksort(0, this.arr.length-1);
    }

    public void quicksort(int left, int right)
    {
        if(left >= right)
        return;

        long pivotValue = arr[left];
        int reader = left+1;
        int it = left;
        int gt = right;

        while(reader <= gt)
        {
            if(arr[reader] < pivotValue) swap(reader++,it++);
            else if(arr[reader] == pivotValue) reader++;
            else if(arr[reader] > pivotValue) swap(reader,gt--);
        }
        
       
        quicksort(left, it-1);
        quicksort(gt+1, right);
    }

    public static void main(String[] args)
    {
        QuickSort3WayPartition q = new QuickSort3WayPartition(16);

        q.display();
        q.quicksort();
        q.display();
    }
}

// https://algorithmsandme.com/dutch-national-flag-problem/
class DutchNationalFlag
{
    public static void swap(int[] nums, int a, int b)
    {
        int temp = nums[a];
        nums[a]=nums[b];
        nums[b]=temp;
    }

    public static void solve3Color(int[] nums)
    {
        int lo, reader;
        reader=lo=0;
        int hi = nums.length-1;

        System.out.println("\n3 color:" +java.util.Arrays.toString(nums));
        while(reader<=hi)
        {
            if(nums[reader]==0) swap(nums,reader++,lo++);
            else if(nums[reader]==1) reader++;
            else if(nums[reader]==2) swap(nums,reader,hi--);
        }
        System.out.println(java.util.Arrays.toString(nums));
    }

    public static void solve4Color(int[] nums)
    {
        int lo, reader, mid;
        reader=lo=mid=0;
        int hi = nums.length-1;

        System.out.println("\n4 color:" +java.util.Arrays.toString(nums));
        while(reader<=hi)
        {
            if(nums[reader]==0)  { swap(nums,reader++,lo++); mid++; }
            else if(nums[reader]==1) { reader++; mid++; }
            else if(nums[reader]==2) { swap(nums,reader,mid); }
            else if(nums[reader]==3) swap(nums,reader,hi--);
        }
        System.out.println(java.util.Arrays.toString(nums));
    }

    public static void main(String[] args)
    {
        solve3Color(new int[] {2,0,1,2,1,1,2,2,0,0,0,1,2,0});
// not working        solve4Color(new int[] {2,0,1,3,2,1,3,1,2,2,3,3,0,3,0,3,0,1,2,0,2,1});
    }
}

class QuickSelect
{
    public long[] arr;
    public int count = 0;

    public QuickSelect(int size)
    {
        arr = new long[size];
        for(int i=0;i<arr.length;i++)
            arr[i] = (int)(java.lang.Math.random()*199);
    }

    public void display()
    {
        System.out.println(java.util.Arrays.toString(this.arr));
    }

    public void swap(int index1, int index2)
    {
        long temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public int quickselect(int index)
    {
        return quickselect(0, this.arr.length-1, index);
    }

    public int quickselect(int left, int right, int index)
    {
        count++;
        if(left >= right)
        {
            if(left==right && left==index)
            return index;

            return -1;
        }
        
        long pivotValue = arr[right];
        int pivotIndex = partitionIt(left, right, pivotValue);
        
        if(pivotIndex == index) return pivotIndex;
        else if(index < pivotIndex)
        return quickselect(left, pivotIndex-1,index);
//        else if(index > pivotIndex)

        return quickselect(pivotIndex+1, right, index);
    }
    // Hoare algorithm
    public int partitionIt(int left, int right, long pivot)
    {
        int leftPtr = left-1;
        int rightPtr = right;

        while(true)
        {
            while(leftPtr < right && arr[++leftPtr] < pivot); // find an item larger than pivot on the left side
            while(rightPtr > left && arr[--rightPtr] > pivot);// find an item smaller than pivot on the right side

            if(leftPtr >= rightPtr) // break condition
            break;
            swap(leftPtr,rightPtr);
        }

        swap(leftPtr,right); // place the pivot value into th center
        return leftPtr; // return the location of the dividing index
    }

    public static void main(String[] args)
    {
        int size =  (int)(java.lang.Math.random()*10) + 6; // 10~16
        QuickSelect q = new QuickSelect(size);
        int nth = (int)(java.lang.Math.random()*(size-1)) + 1; // 1~16

        q.display();
        int found = q.quickselect(size-nth);      
        q.display();
        if(found>=0)
        System.out.println( (size-nth+1) + "th smallest or " + nth + "th largest = " + q.arr[found] + ",  number of calls: " + q.count + " , size="+ size);

        size =  (int)(java.lang.Math.random()*3) + 3; // 3~6
        QuickSelect q2 = new QuickSelect(size);
        System.out.println("\nFinding the median: ");
        q2.display();

        long median = -1;
        int medianIndex = q2.quickselect(size/2); 

        if(size%2==1) // odd number
           median = q2.arr[medianIndex];
        else 
        {
         long sum = q2.arr[medianIndex];
         int medianIndex2 = q2.quickselect((size/2)-1);
         sum  = sum + q2.arr[medianIndex2];
         median = sum/2;
        }
        q2.display();

        System.out.println("Median:" + median );

    }
}


class QuickSortMedianof3
{
    public long[] arr;

    public QuickSortMedianof3(int size)
    {
        arr = new long[size];
        for(int i=0;i<arr.length;i++)
            arr[i] = (int)(java.lang.Math.random()*199);
    }

    public void display()
    {
        System.out.println(java.util.Arrays.toString(this.arr));
    }

    public void swap(int index1, int index2)
    {
        long temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public void quicksort()
    {
        quicksort(0, this.arr.length-1);
    }

    public void quicksort(int left, int right)
    {
        int size = right-left+1;
        if(size<=3)
            manualsort(left, right);
        else
        {
        long pivotValue = medianof3(left, right);
        int pivotIndex = partitionIt(left, right, pivotValue);
        quicksort(left, pivotIndex-1);
        quicksort(pivotIndex+1, right);
        }
    }

    public long medianof3(int left, int right)
    {
        int center = (left+right)/2;

        if(arr[left] > arr[center])
            swap(left, center);
        if(arr[left] > arr[right])
            swap(left, right);
        if(arr[center] > arr[right])
            swap(center, right);

        swap(center, right-1);
        return arr[right-1];
    }

    public void manualsort(int left, int right)    
    {
        int size = right-left+1;
        if(size==1) return;
        if(size==2)
        {
            if(arr[left] > arr[right])
            swap(left,right);
        }
        else // if size = 3
        {
            int center = right -1;

            if(arr[left] > arr[center])
                swap(left, center);
            if(arr[left] > arr[right])
                swap(left, right);
            if(arr[center] > arr[right])
                swap(center, right);

        }
    }

    // Hoare algorithm
    public int partitionIt(int left, int right, long pivot)
    {
        int leftPtr = left;
        int rightPtr = right-1;

        while(true)
        {
            while(leftPtr < right && arr[++leftPtr] < pivot); // find an item larger than pivot on the left side
            while(rightPtr > left && arr[--rightPtr] > pivot);// find an item smaller than pivot on the right side

            if(leftPtr >= rightPtr) // break condition
            break;
            swap(leftPtr,rightPtr);
        }

        swap(leftPtr,right-1); // place the pivot value into th center
        return leftPtr; // return the location of the dividing index
    }

    public static void main(String[] args)
    {
        QuickSortMedianof3 q = new QuickSortMedianof3(16);

        q.display();
        q.quicksort();
        q.display();
    }
}

class QuickSort
{
    public long[] arr;

    public QuickSort(int size)
    {
        arr = new long[size];
        for(int i=0;i<arr.length;i++)
            arr[i] = (int)(java.lang.Math.random()*199);
    }

    public void display()
    {
        System.out.println(java.util.Arrays.toString(this.arr));
    }

    public void swap(int index1, int index2)
    {
        long temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public void quicksort()
    {
        quicksort(0, this.arr.length-1);
    }

    public void quicksort(int left, int right)
    {
        if(left >= right)
        return;
        
        long pivotValue = arr[right];
        int pivotIndex = partitionIt(left, right, pivotValue);
        quicksort(left, pivotIndex-1);
        quicksort(pivotIndex+1, right);
    }
    // Hoare algorithm
    public int partitionIt(int left, int right, long pivot)
    {
        int leftPtr = left-1;
        int rightPtr = right;

        while(true)
        {
            while(leftPtr < right && arr[++leftPtr] < pivot); // find an item larger than pivot on the left side
            while(rightPtr > left && arr[--rightPtr] > pivot);// find an item smaller than pivot on the right side

            if(leftPtr >= rightPtr) // break condition
            break;
            swap(leftPtr,rightPtr);
        }

        swap(leftPtr,right); // place the pivot value into th center
        return leftPtr; // return the location of the dividing index
    }

    public static void main(String[] args)
    {
        QuickSort q = new QuickSort(16);

        q.display();
        q.quicksort();
        q.display();
    }
}

class HoarePartition
{
    public long[] arr;

    public HoarePartition(int size)
    {
        arr = new long[size];
        for(int i=0;i<arr.length;i++)
            arr[i] = (int)(java.lang.Math.random()*199);
    }

    public void display()
    {
        System.out.println(java.util.Arrays.toString(this.arr));
    }

    public void swap(int index1, int index2)
    {
        long temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    // Hoare algorithm
    public int partitionIt(int left, int right, long pivot)
    {
        int leftPtr = left-1;
        int rightPtr = right+1;

        while(true)
        {
            while(leftPtr < right && arr[++leftPtr] < pivot); // find an item larger than pivot on the left side
            while(rightPtr > left && arr[--rightPtr] > pivot);// find an item smaller than pivot on the right side

            if(leftPtr >= rightPtr) // break condition
            break;
            swap(leftPtr,rightPtr);
        }

        return leftPtr; // return the location of the dividing index
    }

    public static void main(String[] args)
    {
        HoarePartition par = new HoarePartition(16);

        par.display();
        int pviotindex = par.partitionIt(0, par.arr.length-1, 90);
        par.display();
        System.out.println("dividing index position:" + pviotindex);
    }
}

class LomutoPartition
{
    public long[] arr;

    public LomutoPartition(int size)
    {
        arr = new long[size];
        for(int i=0;i<arr.length;i++)
            arr[i] = (int)(java.lang.Math.random()*199);
    }

    public void display()
    {
        System.out.println(java.util.Arrays.toString(this.arr));
    }

    public void swap(int index1, int index2)
    {
        long temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    // Lomuto algorithm
    public int partitionIt(int left, int right, long pivot)
    {
        int i=0;

        for(int j=0; j < arr.length;j++)
        {
            if(arr[j] < pivot)
                swap(i++,j);
        }        

        return i; // return the location of the dividing index
    }

    public static void main(String[] args)
    {
        LomutoPartition par = new LomutoPartition(16);

        par.display();
        int pviotindex = par.partitionIt(0, par.arr.length-1, 90);
        par.display();
        System.out.println("dividing index position:" + pviotindex);
    }
}

/*
A big limitation of quick sort is that it has O(n2) worst case running time. Some improvements have been suggested as given below:
(1) Cutoff to insertion sort: Switch to insertion sort for arrays with size less than a pre-decided limit. We follow quick sort and partition array. Once, the size of partitioned array goes lower than limit, apply insertion sort on that array. Limit varies from system to system and typically it is between 5 to 15.
(2) Median-of-three partitioning: Use median of a small sample of items taken from the array as the partitioning item. Doing so will give a slightly better partition, but at the cost of computing the median.
*/
