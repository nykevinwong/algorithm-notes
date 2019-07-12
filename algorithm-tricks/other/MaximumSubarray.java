import java.util.*;

class MaximumSubarraySum
{
    public int[] maxCrossingSum(int[] arr, int l, int m, int r)
    {
        int sum = 0;
        int leftMax = Integer.MIN_VALUE;
        int leftMaxIndex = -1;
        for(int i=m;i>=l;i--)
        {
            sum+=arr[i];
            if(sum > leftMax)
            {
                leftMax = sum;
                leftMaxIndex = i;
            }
        }

        int rightSum  = 0;
        int rightMax = Integer.MIN_VALUE;
        int rightMaxIndex = -1;
        for(int i=m+1;i<=r;i++)
        {
            rightSum+=arr[i];
            if(rightSum > rightMax)
            {
                rightMax = rightSum;
                rightMaxIndex = i;
            }
        }

        return new int[]{leftMaxIndex, rightMaxIndex, leftMax+rightMax};
    }

    public int MaxSubarraySum_DivideAndConquer(int[] arr, int l, int r, int[] res)
    {
        if(l==r) // base case
            return arr[l];
        
        int m = l+(r-l)/2;
        
        System.out.println("ENTER: checking interval(" + l+ ","+ r+ ")");

        int leftMax = MaxSubarraySum_DivideAndConquer(arr, l, m, res);
        int rightMax = MaxSubarraySum_DivideAndConquer(arr, m+1,r, res);
        int[] crossRes = maxCrossingSum(arr, l, m, r);
        int crossMax = crossRes[2];

        System.out.println("EXIT: checking interval(" + l+ ","+ r+ ")");


        if(leftMax <= crossMax && rightMax <= crossMax) // crossmax is maximum
        {
            res[0] = crossRes[0];
            res[1] = crossRes[1];
            res[2] = crossRes[2];
            return crossMax;
        }

        if(leftMax <= rightMax && crossMax <= rightMax) // right is maximum
        {
            res[0] = m+1;
            res[1] = r;
            res[2] = rightMax;
            return rightMax;
        }

        if(rightMax <= leftMax && crossMax <= leftMax) // right is maximum
        {
            res[0] = l;
            res[1] = m;
            res[2] = leftMax;            
            return leftMax;
        }

        return -1; // impossible to reach
    }

    public int[] MaxSubarraySum_BruteForce(int[] arr)
    {
        int maxSum = Integer.MIN_VALUE;
        int[] res = new int[]{-1,-1, Integer.MIN_VALUE};

        for(int i=0;i<arr.length;i++)
        {
            for(int j=i;j<arr.length;j++)
            {
                int sum = 0;

                System.out.print("sum interval("+i+","+j+") : ");
                for(int k=i;k<=j;k++)
                {
                    sum+=arr[k];
                    System.out.print(arr[k]+ ",");
                }

                if(maxSum < sum)
                {
                    maxSum = sum;
                    res[0]=i;
                    res[1]=j;
                    res[2] = maxSum;
                }

                System.out.println(" = " + sum);

            }
        }

        return res;
    }

    public int[] MaxSubarraySum_KadaneAlgorithm(int[] arr)
    {
        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;
        int leftIndex = -1;
        int rightIndex = -1;


        for(int i=0;i<arr.length;i++)
        {
            maxEndingHere += arr[i];
            
            // or use maxEndingHere = Math.max(maxEndingHere, 0);
            if(maxEndingHere< maxEndingHere + arr[i]) 
            {
                maxEndingHere = 0;
                leftIndex = i+1;
            }

            // or use maxSoFar = Math.max(maxSoFar, maxEndingHere);
            if(maxSoFar < maxEndingHere)
            {
                maxSoFar = maxEndingHere;
                rightIndex = i;
            }
        }

        return new int[] {leftIndex, rightIndex, maxSoFar};
    }

    public static void main(String[] args)
    {
        int[] arr2 = new int[]{-2, -5, -6, -2, -3, -1, -5, -6};
        int[] arr = new int[]{-2, -5, 6, -2, -3, 1, 5, -6};
        MaximumSubarraySum s = new MaximumSubarraySum();

        System.out.println("----BRUTE FORCE-- O(N^2)--");
        int[] res =s.MaxSubarraySum_BruteForce(arr);
        System.out.println("arr[0-" + (arr.length-1) + "]: " + java.util.Arrays.toString(arr));
        System.out.println("maximum subarray interavl:("+res[0]+","+res[1]+") , maxSum:" + res[2]);


        System.out.println("----DIVIDE AND CONQUER---O(nlogn)-");
        s.MaxSubarraySum_DivideAndConquer(arr, 0, arr.length-1 ,res);
        System.out.println("arr[0-" + (arr.length-1) + "]: " + java.util.Arrays.toString(arr));
        System.out.println("maximum subarray interavl:("+res[0]+","+res[1]+") , maxSum:" + res[2]);

        System.out.println("----Kadane Algorithm---O(N)-");
        res = s.MaxSubarraySum_KadaneAlgorithm(arr);
        System.out.println("arr[0-" + (arr.length-1) + "]: " + java.util.Arrays.toString(arr));
        System.out.println("maximum subarray interavl:("+res[0]+","+res[1]+") , maxSum:" + res[2]);
        System.out.println("*Kadane algorithm can't deal with an array filled with all negative values");
    }
}