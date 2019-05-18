class DrawLot // Time: O(N^4)
{
    public static boolean possibleLotCombination(int[] k, int m)
    {
        int n = k.length;
        for(int a=0; a < n; a++)
            for(int b=0; b < n; b++)
                for(int c=0; c < n; c++)
                    for(int d=0; d < n; d++)
                    {
                        if(k[a]+k[b]+k[c]+k[d] == m)
                            return true;
                    }
        return false;
    }

    public static boolean possibleLotCombination2(int[] k, int m)
    {
        int n = k.length;
        for(int a=0; a < n; a++)
            for(int b=0; b < n; b++)
                for(int c=0; c < n; c++)
                    for(int d=0; d < n; d++)
                    {
                        if(k[d] == m - k[a] - k[b] - k[c])
                            return true;
                    }
        return false;
    }

    public static boolean possibleLotCombination3(int[] k, int m) 
    { // O(N^3 * log(N))
        int n = k.length;
        for(int a=0; a < n; a++)
            for(int b=0; b < n; b++)
                for(int c=0; c < n; c++)
                        if(java.util.Arrays.binarySearch(k , m - k[a] - k[b] - k[c]) != -1)
                            return true;
        return false;
    }

    public static boolean possibleLotCombination4(int[] k, int m)
    {  // Time: O(N^2 * log(N^2)),  Space: O(N^2)
        int n = k.length;
        int[] kk = new int[k.length * k.length];        
        for(int c=0; c < n; c++) // Time: O(N^2)
            for(int d=0; d < n; d++)
                kk[c*n+d] = k[c]+k[d];
        
        java.util.Arrays.sort(kk); // required for binary search. O(N*log(N))

        for(int a=0; a < n; a++) // O(N^2 * log (N^2) 
            for(int b=0; b < n; b++)
                        if(java.util.Arrays.binarySearch(kk , m - k[a] - k[b]) != -1)
                            return true;
        return false;
    }

    public static void main(String[] args)
    {
        /* 抽籤問題 (Draw Lots)
        在一個裝有n個紙片的袋子中，抽出紙片。每次抽完一張，紙片就放回去。
        如果你重覆抽出4次，4次抽出紙片的和剛好為m的話，你就獲勝。請寫出
        一個程式調查是否在袋子中，重覆抽出4次後的和有可能剛好為m。
        */
        int[] k = new int[] {3, 4, 5 , 6, 7};
        System.out.println(java.util.Arrays.toString(k));
        
        for(int m=10;m<=13;m++)
        {
            System.out.print("4 draws for m = " + m);
            System.out.print(" [1] = " + possibleLotCombination(k, m));
            System.out.print(", [2] = " + possibleLotCombination2(k, m));
            System.out.print(", [3] = " + possibleLotCombination3(k, m));
            System.out.println(", [4] = " + possibleLotCombination4(k, m));
        }
    }
}