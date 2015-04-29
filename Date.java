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
	    if(type == 1){
		for(int i = 0; i < subset.length; i++){
		    String[] tmp = subset[i].split(",");
		    subset[i] = tmp[0];
		}
	    }else{
		for(int i = 1; i < subset.length; i++){
		    String[] tmp = subset[i].split(",");
		    System.out.println(tmp[0]);
		    tmp = tmp[0].split("-");
		    
		    subset[i-1] = tmp[0]+tmp[1]+tmp[2];
		}
	    }
	    boolean[] out = Intersect(period,subset);
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
	    for(int i = 0; i < file.length; i++){
		String[] tmp = file[i].split(",");
		file[i] = tmp[6];
	    }
	    LocalDataExchange.write(file,args[4]);
	}catch(IOException e){
	    e.printStackTrace();
	    System.out.println("Error happens in basic.Date");
	}
    }
    public static boolean[] Intersect(String[] A, String[] B){
	// B \not \subset A and vice versa
	// B and A is well sorted in the case that their element can be viewed as integers and the integer is ascending.
	int iter = 0;
	int a0 = Integer.parseInt(A[0]);
	while(Integer.parseInt(B[iter]) < a0){
	    iter++;
	}
	String[] sub = new String[B.length - iter];
	for(int i = 0; i < sub.length; i++){
	    sub[i] = B[i+iter];
	}
	boolean[] out = In(A,sub);
	return out;
    }
	
    public static boolean[] In(String[] A, String[] B){
	// min B > min A
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
