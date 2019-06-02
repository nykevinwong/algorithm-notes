class test
{

    public void main(String[] args)
    {

    }

    int isMatch(String s, String p)
    {
        if(s==null || p == null) return 0;
        if(s.equals(p)) return 1;

        Int j = 0; // index for s
        char prev = 0;
        for(int i=0;i < p.length;i++)
        {
            
            Char c = p.charAt(i);
            if(c=='.') {
                prev=c;
                j++;
            }
            else if(c=='*')
            {
                    while(j < s.length() &&  (prev== '.'|| s.charAt(j)==prev) ) j++;
            }
            else
            {
                prev = s.charAt(j);
                if(prev== ‘.’ || s.charAt(j)==c) j++;
            }

            if(j >= s.length() ) return 1;


            }

            return 0;
            }

        }

    }

}