class DepthFirstSearchStates // Time: O(N^2)
{  // 如果用圖形表示所有深度優先搜尋狀態的節點，此圖將是一個二元樹的圖形。
    public static boolean sumEqualToK(int[] arr, int k, int sum, int i)
    {
        if(i==arr.length) return sum==k;
        if(sumEqualToK(arr, k, sum + arr[i], i+1)) return true;// include arr[i]
        if(sumEqualToK(arr, k, sum , i+1)) return true; // exclude arr[i]
        return false;
    }
    // 如果不剪枝，所有深度優先搜尋狀態的節點數為 N^(2+1) 個，也代表有 N^(2+1) 個狀態。
    // 剪枝: 如果是二元樹，代表有些狀態可在某些狀況下排除。排除不必要狀態的動作就稱為剪枝。
    // 剪枝的目地是降低程式執行時間。
    public static boolean sumEqualToK_cutBranch(int[] arr, int k, int sum, int i)
    {
        if(sum==k) return true; // 可剪枝，已找到答案。題目需求許可，可排除不加入其它狀態的值。
        if(sum > k ) return false; //可剪枝，大於k時，下面的狀態不有符合題目解的可能性。

        if(i==arr.length) return sum==k;
        if(sumEqualToK(arr, k, sum + arr[i], i+1)) return true;// include arr[i]
        if(sumEqualToK(arr, k, sum , i+1)) return true; // exclude arr[i]
        return false;
    }

    public static void main(String[] args)
    {
        /*  提供整數 a[0], a[1], a[2], a[3], ..., a[n]. 請判斷從中選幾個數的和剛好為 k .
            輸入: arr[] = {1,2,4,7}, k = 13. 
            輸出: yes. (13 = 4 + 2 + 7 )             
        */        
        int[] arr = new int[] {1, 2, 4, 7 , 8, 9};
        System.out.println(java.util.Arrays.toString(arr));
        System.out.println("find sum = 13 in the array? " + sumEqualToK(arr, 13, 0, 0));
        System.out.println("find sum = 40 in the array? " + sumEqualToK(arr, 40, 0, 0));
        System.out.println("cutbranch: find sum = 13 in the array? " + sumEqualToK_cutBranch(arr, 13, 0, 0));
        System.out.println("cutbranch: find sum = 40 in the array? " + sumEqualToK_cutBranch(arr, 40, 0, 0));
    }
}