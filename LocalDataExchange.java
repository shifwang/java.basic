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
    


public class LocalDataExchange{
    //hdfs:/yq01-bdl-hdfs.dmop.baidu.com:54310/app/bdl/bdl_dm/wangyu/tmp";
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
	File file = new File(path);
	FileInputStream fis = new FileInputStream(file);
	byte[] data =  new byte[(int) file.length()];
	fis.read(data);
	fis.close();
	String output = new String(data,"UTF-8");
	//System.out.println("The output String is: " + output);
	return output;
    }
    public static void write(boolean[] str,String path){
	try{
	    PrintWriter pw = new PrintWriter(path);
	    for(int i = 0; i < str.length; i++)
		if(str[i]){
		    pw.println("1");
		}
		else{
		    pw.println("0");
		}
	    pw.close();
	}catch(IOException e){
	    System.out.println("Error writting to " + path);
	}
    }
    public static void write(String[] str,String path){
	try{
	    PrintWriter pw = new PrintWriter(path);
	    for(int i = 0; i < str.length; i++)
		pw.println(str[i]);
	    pw.close();
	}catch(IOException e){
	    System.out.println("Error writting to " + path);
	}
    }
    public static void writeMatrixToFile(String path,matrix m){
	try{
	    //	    System.out.println("DEBUG: m.nrow() is "+ m.nrow() + "m.ncol is " + m.ncol()); 
	    PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(path)), "UTF-8"));
	    pw.println(m.toString());
	    pw.close();
	    
	}catch(IOException e){
	    System.out.println("Error writting to " + path);
	}
    }
    

}
