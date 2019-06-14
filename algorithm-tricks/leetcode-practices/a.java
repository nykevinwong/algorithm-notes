class a
{

    public String longestPalindrome(String s) {
        int[] odd = new int[3];
        int[] even = new int[3];
        int[] max = new int[3];
        
        for (int i = 0; i < s.length() ; i++) {
            expandAroundCenter(s, i, i , odd);
            expandAroundCenter(s, i, i + 1, even);

            if(odd[0] > max[0])
            {
                max[0]=odd[0];
                max[1]=odd[1];
                max[2]=odd[2];
            }
            
            if(even[0] > max[0])
            {
                max[0]=even[0];
                max[1]=even[1];
                max[2]=even[2];
            }

            System.out.print("i:" + i);
            System.out.print(", odd:" + java.util.Arrays.toString(odd));
            System.out.print(", even:" + java.util.Arrays.toString(even));
            System.out.print(", max:" + java.util.Arrays.toString(max));
            System.out.println("");
        }
        
        return s.substring(max[0], max[2]-1);
    }
    
    public void expandAroundCenter(String s,int l, int r, int[] res)
    {
        while(l >= 0 && r < s.length() && s.charAt(l)==s.charAt(r))
        {
//            System.out.print( s.charAt(l) + " = " + s.charAt(r) + ", ");
            l--; r++;
        }
        

        res[0] = (r-1)-(l+1) +1;
        res[1] = l+1;
        res[2] = r-1;
    }

    public  static void main(String[] args)
    {
        a c = new a();
        String res =    c.longestPalindrome("babad");
        System.out.println("babad => " + res);

    }
}