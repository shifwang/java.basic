package basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;


public class HdfsDataExchange{
    public static String DEFAULT_TMP_HOME = "/home/users/wangyu42/tmp";
    public static matrix findMatrixByPath(String path) throws IOException{
	String str = readFile_TEXT(path);
	return matrix.parseMatrix(str);
    }
    public static String[] findStringByPath(String path) throws IOException{
	String str = readFile_TEXT(path);
	return str.split("\n");
    }
    public static String readFile_TEXT(String path) throws IOException, FileNotFoundException{
	FileSystem hdfs = FileSystem.get(new Configuration());
	Path file = new Path(path);
	FSDataInputStream fin = hdfs.open(file);	
	String output =	fin.readUTF();
	fin.close();
	//System.out.println("The output String is: " + output);
	return output;
    }
    public static void writeMatrixToFile(String path,matrix m){
	try{
	    //	    System.out.println("DEBUG: m.nrow() is "+ m.nrow() + "m.ncol is " + m.ncol());
	    FileSystem hdfs = FileSystem.get(new Configuration());
	    Path file = new Path(path);
	    if(hdfs.exists(file)){
		hdfs.delete(file,true);
	    }
	    hdfs.createNewFile(file);	    
	    FSDataOutputStream fout = hdfs.create(file);	    
	    PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(path)), "UTF-8"));
	    fout.writeUTF(m.toString());
	    fout.close();
	    
	}catch(IOException e){
	    System.out.println("Error writting to " + path);
	}
    }    
}
