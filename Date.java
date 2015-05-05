package basic;

import java.util.*;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.IOException;

import basic.LocalDataExchange;

public class Date{
    static public void main(String[] args){
	SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
	Calendar startDay = new GregorianCalendar(2008,0,1);
	String[] period = new String[2343];
	for(int i = 0; i < 2343; i++){
	    period[i] = sFormat.format(startDay.getTime());
	    startDay.set(Calendar.DATE,startDay.get(Calendar.DATE) + 1);
	}
	try{
	    int type = 2;	    
	    String[] subset = LocalDataExchange.findStringByPath(args[0]);
	    String[] subset_no_header = new String[subset.length-1];
	    if(type == 1){
		for(int i = 0; i < subset.length; i++){
		    String[] tmp = subset[i].split(",");
		    subset[i] = tmp[0];
		}
	    }else{
		for(int i = 1; i < subset.length; i++){//start from 1 to avoid header
		    String[] tmp = subset[i].split(",");
		    System.out.println(tmp[0]);
		    tmp = tmp[0].split("-");		    
		    subset_no_header[i-1] = tmp[0]+tmp[1]+tmp[2];
		}
	    }
	    boolean[] out = Intersect(period,subset_no_header);
	    //	    LocalDataExchange.write(out,args[1]);
	    String[] file = LocalDataExchange.findStringByPath(args[1]);
	    for(int i = 0; i < file.length; i++){
		String[] tmp = file[i].split("\\t");
		file[i] = "";
		//System.out.println("Processing line: " + i );
		for(int j = 0; j < tmp.length; j++){
		    if(out[j]){
			file[i] += tmp[j];
			file[i] += " ";
		    }else{
		    }
		}
	    }
	    LocalDataExchange.write(file,args[2]);
	    file = LocalDataExchange.findStringByPath(args[3]);
	    boolean[] out1 = Intersect(subset_no_header,period);
	    int out1_sum = 0;
	    for(int i = 0; i < out1.length; i++){
		if(out1[i])
		    out1_sum++;
	    }
	    String[] adjclose = new String[out1_sum];
	    int iter = 0;
	    for(int i = 1; i < file.length; i++){//start from 1 to avoid header
		String[] tmp = file[i].split(",");
		if(out1[i-1]){
		    adjclose[iter++] = tmp[6];
		}else{
		}
	    }
	    LocalDataExchange.write(adjclose,args[4]);
	}catch(IOException e){
	    e.printStackTrace();
	    System.out.println("Error happens in basic.Date");
	}
    }
    public static boolean[] Intersect(String[] A, String[] B){
	// B and A is well sorted in the case that their element can be viewed as integers and the integer is ascending.
	int[] a = new int[A.length];
	for(int i = 0; i < A.length; i++)
	    a[i] = Integer.parseInt(A[i]);
	int[] b = new int[B.length];
	for(int i = 0; i < B.length; i++)
	    b[i] = Integer.parseInt(B[i]);
	boolean[] out = math.in(a,b);
	return out;
    }
}
