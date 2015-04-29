package basic;

import basic.*;
public class UnitTest{
    public static void main(String[] args){
	//Test math.Sort
	testMathSort();
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
	double[] t2 = new double[100000000];
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
