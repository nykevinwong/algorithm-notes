import java.io.*;
import java.nio.*;
import java.util.Collections;

class countdone
{
    public static void main(String[] args) throws IOException
    {
        File dir = new File(".");
        File[] filesList = dir.listFiles(new FilenameFilter() { 
                 public boolean accept(File dir, String filename)
                      { return filename.endsWith(".txt"); }
        } );
 //       java.util.Arrays.sort(filesList, Collections.reverseOrder());
        try
        {
            PrintWriter writer = new PrintWriter("README.md", "UTF-8");
            System.out.println("Processing");
            int count =0 ;
            for (File file : filesList) {
                if (file.isFile()) {
                    writer.println("## " + file.getName());


                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line = reader.readLine();
                        while (line != null) {
                            int pos = line.indexOf(".");

                            if(pos > 0 && pos <= 10)
                            {
                                try
                                {
                                    String[] cols = line.split("\\.");
                                    if(cols[0].length() <=5)
                                    {
                                        Integer result = Integer.valueOf(cols[0]);
                                        if(result!=null )
                                        {
                                            writer.println("### " + line);
                                            count++;
                                        }	
                                    }
                                    System.out.print(".");
                                }
                                catch(Exception e)
                                {
                                    System.out.print("-");
                                }

                            }
                            // read next line
                            line = reader.readLine();
                        }
                        reader.close();
                        writer.println("");

                }
            }

            System.out.println("\nDone Processing!!");
            writer.println("Total " + count + " problems are COMPLETED!!");
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("error listing files.");
            e.printStackTrace();
        }

        

    }
}