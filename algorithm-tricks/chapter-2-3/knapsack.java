class knapsack
{
    static int[] w = new int[] { 2,1,3,2};
    static int[] v = new int[] { 3,2,4,2};
    static int n = w.length;
    static int W = 5;
    static int[][] dp = new int[n+1][W+1];

    public static int rec_baddp(int i, int j, int sum)
    {
        int res;
        if(i==n) res = sum;
        else if(j < w[i]) res = rec_baddp(i+1, j, sum);
        else  res = java.lang.Math.max( rec_baddp(i+1, j,sum)  ,  rec_baddp(i+1, j-w[i], sum + v[i] )  ) ;
        return res;
    }

    public static int rec0(int i, int j)
    {
        if(i==n) return 0;
        else if(j < w[i]) return rec0(i+1, j);
        else  return java.lang.Math.max( rec0(i+1, j)  ,  rec0(i+1, j-w[i]) + v[i] ) ;
    }

    public static int rec(int i, int j)
    {
        int res;
        if(i==n) res = 0;
        else if(j < w[i]) res = rec(i+1, j);
        else  res = java.lang.Math.max( rec(i+1, j)  ,  rec(i+1, j-w[i]) + v[i] ) ;
        return res;
    }

    public static int recdp(int i, int j)
    {
        if(dp[i][j] > 0)
        return dp[i][j];

        int res;
        if(i==n) res = 0;
        else if(j < w[i]) res = rec(i+1, j);
        else  res = java.lang.Math.max( rec(i+1, j)  ,  rec(i+1, j-w[i]) + v[i] ) ;
        dp[i][j]=res;
        return dp[i][j];
    }

    public static int iterdp(int i1, int j1)
    {
        for(int i=n-1;i>=0;i--)
            for(int j=0;j<=W;j++)
            {
                if(j < w[i])
                    dp[i][j] = dp[i+1][i];
                else 
                    dp[i][j] = java.lang.Math.max( dp[i+1][j], dp[i+1][j-w[i] ] + v[i]);
            }

        return dp[i1][j1];
    }


    public static void main(String[] args)
    {
// 有n個重量與價值分別是 w(i) 及 v(i) 的物品。請從這些物品中選擇總重量不超過 W 的物品，求出價值總和的最大值。
        System.out.println("rec0 Ans:" + rec0(0, W));
        System.out.println("rec Ans:" + rec(0, W));
        System.out.println("recdp Ans:" + recdp(0, W));
        System.out.println("iterdp Ans:" + iterdp(0, W));
    }
} 