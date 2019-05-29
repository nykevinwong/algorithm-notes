import java.util.*;
class PermutationArray 
{
    public static void displayState(int[] curStateValue, int pos)
    {
            for(int j=0;j<= pos;j++) System.out.print(curStateValue[j]);
            System.out.print(" , ");
    }

    //  Permutations without Repetition, Permutations without repeated elements.  (繁: 無重複排列, 簡:去重排列) 
    public static void listAllPermStates(int[] arr, int[] curStateValue, boolean[] used, int[] resCount, int pos)
    {
        if(pos==arr.length) // reach the bottom node of the tree.
            return;

        for(int i=0; i < arr.length;i++)
        {
            if(used[i]) continue; //skip if it's used

           // if(i > 0 && arr[i-1]==arr[i] && !used[i-1]) continue; // deduplication 

            curStateValue[pos]=arr[i]; // current answer node
            resCount[0]++;
            
            // display the current answer node
            displayState(curStateValue, pos);

            used[i] = true;
            listAllPermStates(arr, curStateValue, used, resCount, pos+1);
            used[i] = false; //backtracking
        }
    }

    public static void listBottomPermStates(int[] arr, int[] curStateValue, boolean[] used, int[] resCount, int pos)
    {
        if(pos==arr.length) // reach the bottom node of the tree.
        {
            resCount[0]++;
            displayState(curStateValue, arr.length-1);  // display the current answer node
            return;
        }

        for(int i=0; i < arr.length;i++)
        {
            if(used[i]) continue; //skip if it's used

           // if(i > 0 && arr[i-1]==arr[i] && !used[i-1]) continue; // deduplication 
            curStateValue[pos]=arr[i]; // current answer node

            used[i] = true;
            listBottomPermStates(arr, curStateValue, used, resCount, pos+1);
            used[i] = false; //backtracking
        }
    }

    // specify the depth to reach. depth of the root = 0. depth = level - 1.
    public static void listNthdepthPermStates(int[] arr, int[] curStateValue, boolean[] used, int[] resCount, int pos, int depth)
    {
        if(pos==arr.length) return;  // reach the bottom node of the tree.

        if(depth==0)
        {
            // display the current answer node
            resCount[0]++;
            displayState(curStateValue, pos-1);
            return;
        }

        for(int i=0; i < arr.length;i++)
        {
            if(used[i]) continue; //skip if it's used

           // if(i > 0 && arr[i-1]==arr[i] && !used[i-1]) continue; // deduplication 
            curStateValue[pos]=arr[i]; // current answer node

            used[i] = true;
            listNthdepthPermStates(arr, curStateValue, used, resCount, pos+1, depth-1) ;
            used[i] = false; //backtracking
        }
    }

    

    public static void main(String[] args)
    {        
        int[] arr = new int[] { 1, 3,2};
        int N = arr.length;
        int[] resCount = new int[] {0};
        // sort the array to ensure the answer is in order and it also helps to deduplicate
        java.util.Arrays.sort(arr); 
        System.out.println(java.util.Arrays.toString(arr));

        // if you need an empty set in the result, you add it yourself.
        listAllPermStates( arr, new int[N], new boolean[N], resCount, 0 );
        System.out.println("Total answer count:" + resCount[0]);
        resCount[0] = 0;
        listBottomPermStates( arr, new int[N], new boolean[N], resCount , 0 );
        System.out.println("Total answer count:" + resCount[0]);
        resCount[0] = 0;
        listNthdepthPermStates( arr, new int[N], new boolean[N], resCount , 0, 2 );
        System.out.println("Total answer count:" + resCount[0]);
    }
}

class PermutationList
{

// **** Permutations with Repetition  (繁: 重複排列, 簡:可重排列,有重排列) ********************

    // specify the depth to reach. depth of the root = 0. depth = level - 1.
    public static void listAllRepeatedPermStates(int[] arr, List<Integer> l, int depth)
    {
        if(depth==0 || l.size() == arr.length) // check the size to ensure the function won't crash
        {
            System.out.print(java.util.Arrays.toString(l.toArray()));
            return;
        }

        for(int i=0;i<arr.length;i++)        
        {
            // backtracking. here we assume every permutation is valid
            l.add(arr[i]);
            listAllRepeatedPermStates(arr,l, depth-1);
            l.remove(l.size()-1); // must use this instead of remove(object a) to handle duplication.
        }
    }

    public static void getAllRepeatedPermBottomStates(int[] arr, List<List<Integer>> result, List<Integer> l)
    {
        if(l.size() == arr.length) // list bottom state only
        {
            result.add(new ArrayList<Integer>(l));
            return;
        }

        for(int i=0;i<arr.length;i++)        
        {
            // backtracking. here we assume every permutation is valid
            l.add(arr[i]);
            getAllRepeatedPermBottomStates(arr, result, l);
            l.remove(l.size()-1); // must use this instead of remove(object a) to handle duplication.
        }
    }

// **** Permutations WITHOUT Repetition  (繁: 焦重複排列, 簡:去重排列) ********************
    public static void getAllPermBottomStates(int[] arr, List<List<Integer>> result, List<Integer> l)
    {
        if(l.size() == arr.length) // list bottom state only
        {
            result.add(new ArrayList<Integer>(l));
            return;
        }

        for(int i=0;i<arr.length;i++)        
        {
            // skip if it's a invalid condition (repeated the same element). the statement won't work for duplicate values
            if(l.contains(arr[i])) continue; 
            
            // backtracking. here we assume every permutation is valid
            l.add(arr[i]);
            getAllPermBottomStates(arr, result, l);
            l.remove(l.size()-1); // must use this instead of remove(object a) to handle duplication.
        }
    }

    public static void getAllPermBottomStates2(int[] arr, boolean[] used, List<List<Integer>> result, List<Integer> l)
    {
        if(l.size() == arr.length) // list bottom state only
        {
            result.add(new ArrayList<Integer>(l));
            return;
        }

        for(int i=0;i<arr.length;i++)        
        {
            // skip if it's a invalid condition. work for duplicate values in the array
            if(used[i]) continue; 

            // backtracking. here we assume every permutation is valid
            used[i] = true;            
            l.add(arr[i]);
            getAllPermBottomStates2(arr, used, result, l);
            l.remove(l.size()-1); // must use this instead of remove(object a) to handle duplication.
            used[i] = false;
        }
    }

    public static void getAllPermBottomStates3(int[] arr, boolean[] used, List<List<Integer>> result, List<Integer> l)
    {
        if(l.size() == arr.length) // list bottom state only
        {
            result.add(new ArrayList<Integer>(l));
            return;
        }

        for(int i=0;i<arr.length;i++)        
        {
            // skip if it's a invalid condition. work for duplicate values in the array
            if(used[i]) continue; 

            // skip duplicate states. only works if the array is sorted
            if(i > 0 && arr[i-1]==arr[i] && !used[i-1]) continue;

            // backtracking. here we assume every permutation is valid
            used[i] = true;            
            l.add(arr[i]);
            getAllPermBottomStates3(arr, used, result, l);
            l.remove(l.size()-1); // must use this instead of remove(object a) to handle duplication.
            used[i] = false;
        }
    }

    public static void displayStates(String message, List<List<Integer>> result)
    {
        System.out.print(message);
        for(int i=0;i< result.size();i++)
        {
            List<Integer> l = result.get(i);
            System.out.print(java.util.Arrays.toString(l.toArray()));
        }
        System.out.println("");

    }

    public static void main(String[] args)
    {        
        int[] arr = new int[] { 1, 3,2};
        int N = arr.length;
        int[] resCount = new int[] {0};

        // sort the array to ensure the answer is in order and it also helps to deduplicate
        java.util.Arrays.sort(arr); 
        System.out.println(java.util.Arrays.toString(arr));

        for(int i=0;i<arr.length;i++)
        {
            listAllRepeatedPermStates(arr, new ArrayList<Integer>() , i+1);
            System.out.println();
        }
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        getAllRepeatedPermBottomStates(arr, result ,new ArrayList<Integer>());
        displayStates("getAllRepeatedPermBottomStates:", result);

        result.clear();
        getAllPermBottomStates(arr, result,new ArrayList<Integer>());
        displayStates("getAllPermBottomStates:",result);

        result.clear();
        getAllPermBottomStates2(new int[]{1,2,2}, new boolean[3], result,new ArrayList<Integer>());
        displayStates("getAllPermBottomStates2 (duplicates):",result);

        result.clear();
        getAllPermBottomStates3(new int[]{1,2,2}, new boolean[3], result,new ArrayList<Integer>());
        displayStates("getAllPermBottomStates2 (no duplicate states):",result);
    }

}