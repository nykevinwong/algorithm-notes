import java.util.*;

class MinimumWindowSubstring
{
    public static String minimumWindowSubstring(String s, String t)
    {
        int minStart = 0, minEnd = Integer.MAX_VALUE;
        HashMap<Character,Integer> m = new HashMap<>();

        for(int i=0;i < t.length();i++)
        {
            char c = t.charAt(i);
            m.put(c, m.getOrDefault(c,0)+1);  
        }

        int start = 0, end = 0;
        int counter = m.size();
        
        printResult("INITIALIZED:", m, start, end, s, counter);

        while(end < s.length())
        {
            char eChar = s.charAt(end);     
            
            if(m.containsKey(eChar))
            {
                m.put(eChar,m.get(eChar)-1);
                if(m.get(eChar)==0) counter--;
            }

            printResult("ENDCHAR--", m, start, end, s, counter);
            
            while(counter==0) // when the interval contains the valid string
            {
                // shrink the interval
                char sChar = s.charAt(start);     
                if(m.containsKey(sChar))
                {
                    m.put(sChar,m.get(sChar)+1);
                    if(m.get(sChar)==1) counter++;
                }
                
                if( (end-start) < (minEnd-minStart))
                {
                    minStart = start;
                    minEnd = end;
                    printResult("UPDATEMIN",m, minStart, minEnd, s, counter);
                }
                else printResult("STARTCHAR", m, start, end, s, counter);

                start++;
            }

            end++;
        }

        return (minEnd==Integer.MAX_VALUE) ? "":s.substring(minStart, minEnd+1);
    }

    public static void printResult(String state, HashMap<Character, Integer> m, int start, int end, String s, int counter)
    {
        System.out.println(state + ": HashMap=>" + m.toString() + ", current window:" + s.substring(start,end+1) + ", counter:" + counter);

    }

    public static void main(String[] args)
    {
        String s = "ADOBECODEBANC";
        String t = "CDEE";
        System.out.println("result:" + minimumWindowSubstring(s,t));
        System.out.println("S:" + s);
        System.out.println("T:" + t);
    }
}
