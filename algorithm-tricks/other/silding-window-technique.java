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

// substring-with-concatenation-of-all-words
class SubstringWithConcatenationOfAllWords
{
    // O(M*N), this also work for variable-length of vocbaulary word
    public static List<Integer> findSubstring_BruteForce(String s, String[] words )
    {
        List<Integer> res = new ArrayList<Integer>();
        if(s==null || s.length()==0 || words == null || words.length ==0 ) return res;

        HashMap<String, Integer> m = new HashMap<>();
        int wordsLen = 0;

        for(String word: words)
        {
            wordsLen += word.length();
            m.put(word, m.getOrDefault(word, 0)+1);
        }
        
        if(s.length() < wordsLen) return res;

        HashMap<String, Integer> tmp = new HashMap<>();

        for(int i=0; i < s.length() - wordsLen + 1;i++)
        {
            int matched= 0; int start = i;
            tmp.clear();

            int fixedVocabularyLen = words[0].length();            
            while(matched < words.length) 
            {
                String w = s.substring(start, start + fixedVocabularyLen);              
                
                if(!m.containsKey(w)) break; // 目前字詞不能匹配任何在words的字詞
                
                tmp.put(w, tmp.getOrDefault(w,0)+1);
                if(tmp.get(w) > m.get(w)) break; // 超過同一單詞在words中的出現次數 
                
                matched++; //matched
                start += w.length(); // 調整start位置以檢查下一個字詞
                // 如果words中的字詞長度不一，就需要這行及 fixedVocabularyLen.
                if(matched < words.length) fixedVocabularyLen = words[matched].length();
            }
            
            // 匹配數與words字詞數相等，條件符合，加入答案表
            if(matched==words.length) res.add(i);
        }
        
        return res;
    }

    public static void main(String[] args)
    {
        String s ="barfoothefoobarman";
        String[] words = new String[] {"foo","bar"};
        List<Integer> res = findSubstring_BruteForce(s,words);
        System.out.println("30. substring with concatenation of all words (串聯所有單詞的子串)");
        System.out.println("s:" + s + " , words:" + java.util.Arrays.toString(words));
        System.out.println("Brute Force Solution:" + res.toString());
    }
}

class LongestSubstringwithAtMost2DistinctCharacters
{
    public int lengthOfLongestSubstringTwoDistinct(String s) 
    {
        if(s==null || s.length()==0) return 0;
        
        HashMap<Character,Integer> m = new HashMap<>();
        int start = 0 , end = 0;
        int maxStart = 0, maxEnd = Integer.MIN_VALUE;
        int nonZeroCount = 0; // HashMap.size() is the size of keys. not size when keys == 0
        
        while(end < s.length())
        {
            char eChar = s.charAt(end);            
            m.put(eChar, m.getOrDefault(eChar,0)+1); // m[eChar]++
            log("CHAREND", s, start, end, m);
            
            if(m.get(eChar)==1) nonZeroCount++;

            while(nonZeroCount==3)
            {
                char sChar = s.charAt(start);            
                m.put(sChar, m.get(sChar)-1); // m[sChar]--
                if(m.get(sChar)==0) nonZeroCount--;
                start++;
                 log("STARTEND", s, start, end, m);
            }
                
            if(nonZeroCount<=2) // <= because at most 2 means it includes when it's 0 or 1.
            {
                if((end-start) > (maxEnd-maxStart))
                {
                    maxStart = start;
                    maxEnd = end;
                }
                 log("UDATEDMAX:", s, maxStart, maxEnd, m);
            }

            end++;
        }
        
        
        return maxEnd==Integer.MIN_VALUE ? 0: (maxEnd-maxStart+1);
    }

    public void log(String msg, String s, int start, int end, HashMap<Character,Integer> m)
    {
        System.out.println(msg + ": " + s.substring(start,end+1) + " at the interval ("+start+","+end+") , frequency:" + m.toString() );
    }

    public static void main(String[] args)
    {
        LongestSubstringwithAtMost2DistinctCharacters t = new LongestSubstringwithAtMost2DistinctCharacters();
        String s = "eceba";        
        System.out.println("Longest Substring with At Most 2 Distinct Characters");
        int len = t.lengthOfLongestSubstringTwoDistinct("a");
        System.out.println("s: " + "a" + " , ans:" + len);
        len = t.lengthOfLongestSubstringTwoDistinct(s);
        System.out.println("s: " + s + " , ans:" + len);
    }
}
