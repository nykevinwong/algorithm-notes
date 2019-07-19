import java.util.*;

class RodCutting
{
    // (1) learn to draw a recursion tree of its recursive function call
    // (2) draw  it subproblem graph to understand it better

    // O(2^n) - recursive top-down approach
    public int cutRod(int[] p, int n)
    {
        int max = Integer.MIN_VALUE;
        if(n==0) return 0;

        for (int i=1;i<=n;i++)
        {
                max = Math.max(max , p[i-1] + cutRod(p, n-i) );         
        }

        return max;
    }

    // O(n^2) - memorization approach
    public int cutRodMemorized(int[] p, int n)
    {
        int[] dp = new int[p.length+1];
        for(int i=0;i<dp.length;i++)
            dp[i] = Integer.MIN_VALUE;
        return cutRodMemorizedAux(p, n, dp);
    }

    private int cutRodMemorizedAux(int[] p, int n, int[] dp)
    {
        int max = Integer.MIN_VALUE;
        
        if(dp[n] >= 0) return dp[n];

        if(n == 0) { dp[n] = 0; return dp[n]; }

        for (int i=1;i<=n;i++)
        {
            max = Math.max(max , p[i-1] + cutRodMemorizedAux(p, n-i, dp) );         
        }

        dp[n] = max;

        return dp[n];
    }

    // O(n^2) bottom-up dynamica programming approach
    public int cutRodBottomUp(int[] p, int n)
    {
        int[] dp = new int[p.length+1];
        dp[0]=0;
        for(int j=1;j<=n;j++)
        {
            int max = Integer.MIN_VALUE;
            for (int i=1;i<=j;i++)
            {
                max = Math.max(max , p[i-1] + dp[j-i] );         
            }
            dp[j] = max;
        }

        return dp[n];
    }

    // get solution also
    public int[][] getCutRodSolution(int[] p, int n)
    {
        int[] dp = new int[p.length+1];
        int[] s = new int[p.length+1];

        dp[0]=0;
        for(int j=1;j<=n;j++)
        {
            int max = Integer.MIN_VALUE;
            for (int i=1;i<=j;i++)
            {
                if(max < p[i-1] + dp[j-i] )
                {
                    max = p[i-1] + dp[j-i];
                    s[j] = i;
                }
            }
            dp[j] = max;
        }

        return new int[][] { dp, s };
    }

    // print out one of best ways to cut in order to obtain optima profit on a given length
    public void printCutRodSolution(int[] prices, int n)
    {
        int[][] ans = this.getCutRodSolution(prices, n);
        int[] revenue = ans[0]; // possible maximum/optimal profit on each given length
        int[] s = ans[1]; // optimal cuts required for each possible maximu profit on each given length

        System.out.print("Cutting a rod of length  "+ n +  " => ");

        while(n > 0)
        {
            System.out.print(" | " + s[n]);
            n=n-s[n];
        }

        System.out.println(" |");
    }

    public static void main(String[] args)
    {
        RodCutting rod = new RodCutting();
    
        int[] prices = new int[] {1,5,8,9,10,17,17,20,24,30};

        for (int i=1;i<=10;i++)
        {
            System.out.println("Possible Max Profit[" + i + "]:" +rod.cutRod(prices, i));
        }

        for (int i=1;i<=10;i++)
        {
            System.out.println("(memorized) Possible Max Profit[" + i + "]:" + rod.cutRodMemorized(prices, i));
        }

        for (int i=1;i<=10;i++)
        {
            System.out.println("(DP Bottom-up) Possible Max Profit[" + i + "]:" + rod.cutRodBottomUp(prices, i));
        }

        for (int i=1;i<=10;i++)
        {
            rod.printCutRodSolution(prices,i);
        }

    }
}

class LongestCommonSubsequence
{
    public int lcs(String x, String y, int i, int j)    
    {
        if(x==null || y==null ) return 0;
        if(i==0||j==0)
            return 0;

        if(x.charAt(i-1)==y.charAt(j-1))
            return lcs(x, y, i-1, j-1) + 1;
        else 
            return Math.max( lcs(x, y, i, j-1), lcs(x , y, i-1, j) );
    }

    public int lcsMemorized(String x, String y, int i, int j)    
    {
        if(x==null || y==null ) return 0;
        int[][] dp = new int[x.length()+1][y.length()+1];
        return lcsMemorizedAux(x, y, i, j, dp);
    }

    public int lcsMemorizedAux(String x, String y, int i, int j, int[][] dp)    
    {
        if(dp[i][j]>0) return dp[i][j];

        if(i == 0 || j == 0)
        {
            return dp[i][j] = 0;
        }

        if(x.charAt(i-1) == y.charAt(j-1))
            dp[i][j] = lcsMemorizedAux(x, y, i-1, j-1, dp) + 1;
        else 
            dp[i][j] = Math.max( lcsMemorizedAux(x, y, i, j-1, dp), lcsMemorizedAux(x, y, i-1, j, dp) );

        return dp[i][j];
    }

    public int lcsForward(String x, String y, int i, int j)    
    {
        if(x==null || y==null ) return 0;
        if(i==x.length() || j==y.length())
            return 0;


        if(x.charAt(i)==y.charAt(j))
        {
            return lcsForward(x,y, i+1,j+1)+1;
        }
        else 
            return Math.max(lcsForward(x,y,i, j+1), lcsForward(x,y,i+1,j));
    }

    public int lcsForwardMemorized(String x, String y, int i, int j)    
    {
        if(x==null || y==null ) return 0;
        int[][] dp = new int[x.length()+1][y.length()+1];
        return lcsForwardMemorizedAux(x, y, i, j, dp);
    }

    public int lcsForwardMemorizedAux(String x, String y, int i, int j, int[][] dp)    
    {
        if(dp[i][j] > 0) return dp[i][j];

        if(i==x.length() || j==y.length())
        {            
            return dp[i][j]=0;
        }


        if(x.charAt(i)==y.charAt(j))
        {
            dp[i][j] = lcsForwardMemorizedAux(x,y, i+1,j+1, dp)+1;
        }
        else 
            dp[i][j] = Math.max(lcsForwardMemorizedAux(x,y,i, j+1, dp), lcsForwardMemorizedAux(x,y,i+1,j,dp) );

        return dp[i][j];
    }

    public int lcsDP(String x, String y)
    {
        if(x==null || y==null ) return 0;

        int[][] dp = new int[x.length()+1][y.length()+1];

        for (int i=0;i<=x.length();i++)
            for (int j=0;j<=y.length();j++)
            {
                if(i==0 || j==0)
                {
                    dp[i][j] = 0;
                }
                else if(x.charAt(i-1)==y.charAt(j-1))
                {
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else
                {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }

        return  dp[x.length()][y.length()];
    }

    public int lcsDPBottomTable(String x, String y)
    {
        if(x==null || y==null ) return 0;

        int[][] dp = new int[x.length()+1][y.length()+1];

        for (int i=x.length(); i >= 0 ; i--)
            for (int j=y.length(); j >= 0 ;j--)
            {
                if(i==x.length() || j==y.length())
                {
                    dp[i][j] = 0;
                }
                else if(x.charAt(i)==y.charAt(j))
                {
                    dp[i][j]=dp[i+1][j+1]+1;
                }
                else
                {
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j+1]);
                }
            }

        return  dp[0][0];
    }


    public static void main(String[] args)
    {
        LongestCommonSubsequence LCS = new LongestCommonSubsequence();
        String x = "ABCBDAB";
        String y = "BDCABA";
        System.out.println("LCS length: " + LCS.lcs(x,y, x.length(), y.length()) );
        System.out.println("LCS length (memorized): " + LCS.lcsMemorized(x, y, x.length(), y.length()) );
        System.out.println("LCS length (forward): " + LCS.lcsForward(x, y, 0, 0) );
        System.out.println("LCS length (forward Memorized): " + LCS.lcsForwardMemorized(x, y, 0, 0) );
        System.out.println("LCS length (DP): " + LCS.lcsDP(x, y) );
        System.out.println("LCS length (DP BottomTable): " + LCS.lcsDPBottomTable(x, y) );

    }
}