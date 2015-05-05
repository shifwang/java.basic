package basic;

import basic.*;
public class UnitTest{
    public static void main(String[] args){
	//Test math.Sort
	testMathSort();
	//Test math.in
	testMathIn();	
    }
    public static void testMathIn(){
	int[] A = {1,2,3,4,5,10,11,15,200,1000,1002,1005};
	int[] B = {10,11,12,13,15,1000,1001,1004,1005};
	boolean[] out1 = math.in(A,B);
	boolean[] target1 = {false,false,false,false,false,true,true,true,false,true,false,true};
	boolean flag1 = false;
	if(out1.length == target1.length){
	    flag1 = true;
	    for(int i = 0; i < target1.length; i++){
		flag1 = flag1 & (out1[i] == target1[i]);
	    }
	}
	if(flag1){
	    System.out.println("math.in has passed the first case!");
	}else{
	    System.out.println("Test on Math in failed on the first case!");
	    for(int i = 0; i < out1.length; i++)
		System.out.println(out1[i]);
	    return;
	}
	boolean[] out2 = math.in(B,A);
	boolean[] target2 = {true,true,false,false,true,true,false,false,true};
	boolean flag2 = false;
	if(out2.length == target2.length){
	    flag2 = true;
	    for(int i = 0; i < target2.length; i++){
		flag2 = flag2 & (out2[i] == target2[i]);
	    }
	}
	if(flag2){
	    System.out.println("math.in has passed the second case!");
	}else{
	    System.out.println("Test on Math in failed on the second case!");
	    for(int i = 0; i < out2.length; i++)
		System.out.println(out2[i]);
	    return;
	}
	
	
	
    }
    public static void testMathSort(){
	double[] t1 = {9.0,4.0,-0.1,1.3,-99999.0,0};
	double[] values = math.Sort(t1);
	boolean flag1 = false;
	if(values[0] == 9)
	    if(values[1] == 4)
		if(values[2] == 1.3)
		    if(values[3] == 0)
			if(values[4] == -0.1)
			    if(values[5] == -99999){
				flag1 = true;
			    }
	if(flag1){
	    System.out.println("math.Sort has passed the first case!");
	}else{
	    System.out.println("Test on Math Sort failed on the first case!");
	    for(int i = 0; i < values.length; i++)
		System.out.println(values[i]);
	    return;
	}
	double[] t2 = new double[1000];
	for(int i = 0; i < t2.length; i++){
	    t2[i] = i-t2.length;
	}
	double[] v2 = math.Sort(t2);
        boolean flag2 = true;
	for(int i = 0; i < t2.length; i++){
	    if(v2[i] != -i-1){
		flag2 = false;
		break;
	    }
	}
	if(flag2){
	    System.out.println("math.Sort has passed the second case!");
	}else{
	    System.out.println("Test on Math Sort failed on the second case!");
	    return;
	}
	
    }
    
}
