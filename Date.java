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
	    String[] subset = LocalDataExchange.findStringByPath(args[0]);
	    for(int i = 0; i < subset.length; i++){
		String[] tmp = subset[i].split(",");
		subset[i] = tmp[0];
	    }
	    boolean[] out = In(period,subset);
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
	}catch(IOException e){
	    e.printStackTrace();
	    System.out.println("Error happens in basic.Date");
	}
    }
    public static boolean[] In(String[] A, String[] B){
	// B \subset A
	// A and B are well sorted already.
	//return a list of booleans indicating A's corresponding item is in B.
	boolean[] out = new boolean[A.length];
	int index1 = 0;
	int index2 = 0;
	while(index1 < A.length & index2 < B.length){
	    if(A[index1].equals(B[index2])){
		out[index1] = true;
		index1 += 1;
		index2 += 1;
	    }else{
		out[index1] = false;
		index1 += 1;
	    }
	}
	for(int i = index1+1; i < A.length; i++){
	    out[i] = false;
	}
	return out;
    }
}
