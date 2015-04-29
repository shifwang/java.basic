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
    
}
