// (1) find median [done, linear time complexity, but not best in the worst case (median of median is better)]
// (2) 3 way quicksort/ducth national flag problem
// (3) tail call optimization
// (4) iterative quicksort
// (5) median of medians 

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
