class BacktrackingPermutation 
{
    public static void displayState(int[] curStateValue, int pos)
    {
            for(int j=0;j<= pos;j++) System.out.print(curStateValue[j]);
            System.out.print(" , ");
    }

    public static void listAllStates(int[] arr, int[] curStateValue, boolean[] used, int[] resCount, int pos)
    {
        if(pos==arr.length) // reach the bottom node of the tree.
            return;

        for(int i=0; i < arr.length;i++)
        {
            if(used[i]) continue; //skip if it's used

            curStateValue[pos]=arr[i]; // current answer node
            resCount[0]++;
            
            // display the current answer node
            displayState(curStateValue, pos);

            used[i] = true;
            listAllStates(arr, curStateValue, used, resCount, pos+1);
            used[i] = false; //backtracking
        }
    }

    public static void listBottomStates(int[] arr, int[] curStateValue, boolean[] used, int[] resCount, int pos)
    {
        if(pos==arr.length) // reach the bottom node of the tree.
        {
            // display the current answer node
            resCount[0]++;
            displayState(curStateValue, arr.length-1);
            return;
        }

        for(int i=0; i < arr.length;i++)
        {
            if(used[i]) continue; //skip if it's used

            curStateValue[pos]=arr[i]; // current answer node

            used[i] = true;
            listBottomStates(arr, curStateValue, used, resCount, pos+1);
            used[i] = false; //backtracking
        }
    }

    public static void listNthLevelStates(int[] arr, int[] curStateValue, boolean[] used, int[] resCount, int pos, int level)
    {
        if(pos==arr.length) return;  // reach the bottom node of the tree.

        if(level==0)
        {
            // display the current answer node
            resCount[0]++;
            displayState(curStateValue, pos-1);
            return;
        }

        for(int i=0; i < arr.length;i++)
        {
            if(used[i]) continue; //skip if it's used

            curStateValue[pos]=arr[i]; // current answer node

            used[i] = true;
            listNthLevelStates(arr, curStateValue, used, resCount, pos+1, level-1) ;
            used[i] = false; //backtracking
        }
    }

    public static void main(String[] args)
    {
        int[] arr = new int[] {1, 2, 3};
        int[] resCount = new int[] {0};
        System.out.println(java.util.Arrays.toString(arr));
        listAllStates( arr, new int[3], new boolean[3], resCount, 0 );
        System.out.println("Total answer count:" + resCount[0]);
        resCount[0] = 0;
        listBottomStates( arr, new int[3], new boolean[3], resCount , 0 );
        System.out.println("Total answer count:" + resCount[0]);
        resCount[0] = 0;
        listNthLevelStates( arr, new int[3], new boolean[3], resCount , 0, 2 );
        System.out.println("Total answer count:" + resCount[0]);
    }
}