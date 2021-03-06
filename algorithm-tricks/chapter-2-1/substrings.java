import java.util.*;
class Substrings
{
    public static void substrings(char[] str, List<String> res, StringBuilder sb, int start, int end)
    {        
        if(start>= str.length || end>=str.length) return; // skip to prevent from app crash.

        for(int i=start;i<=end;i++)
        {
            if(sb.indexOf(str[i]+"") >=0) continue;
            sb.append(str[i]);
            res.add(sb.toString());
            substrings(str, res, sb, i,i+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }


    public static void main(String[] args)
    {
        String given = "abcd";
        char[] str = given.toCharArray();
        List<String> res = new ArrayList<String>();
        System.out.println("given:" + given + "\n ans:");
        substrings(str, res, new StringBuilder(), 0, str.length-1); 

        for(String s: res)
        {
            System.out.println(s);
        }       
    }
}
