package basic;
import basic.sort;

public class math{
    public static int[] seq(int start, int end, int by){
	int length = (end - start + 1)/by;
	int[] out = new int[length];
	for(int i = 0; i < out.length; i++){
	    out[i] = start + i * by;
	}
	return out;
    }
    public static double[] Sort(double[] array){
	sort res = new sort(array);
	return res.value;
	//descending
    }
    public static boolean[] in(int[] A, int[] B){
	//A and B is sorted in ascending order
	boolean[] out = new boolean[A.length];
	int index1 = 0;
	int index2 = 0;
	while(index1 < A.length & index2 < B.length){
	    int a = A[index1];
	    int b = B[index2];
	    if(a == b){
		out[index1] = true;
		index1 += 1;
		index2 += 1;
	    }else if(a > b){
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
