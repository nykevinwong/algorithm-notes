// practice to create a file copy program
import java.nio.*;
import java.nio.channels.*;
import java.io.*;

class FileCopy
{
    public static void main(String[] args) throws IOException
    {
        File srcFile = new File(args[0]);
        File destFile = new File(args[1]);

        if(!destFile.exists()) {  destFile.createNewFile(); }
        FileChannel srcChannel = null;
        FileChannel destChannel = null;

        try
        {
            srcChannel = new FileInputStream(srcFile).getChannel();
            destChannel = new FileOutputStream(destFile).getChannel();
            destChannel.transferFrom(srcChannel,0, srcChannel.size());
        }
        finally {
            if(srcChannel!=null) srcChannel.close();
            if(destChannel!=null) destChannel.close();
        }
        
    }
}
