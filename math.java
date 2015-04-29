package basic;

public class math{
    public static int[] seq(int start, int end, int by){
	int length = (end - start + 1)/by;
	int[] out = new int[length];
	for(int i = 0; i < out.length; i++){
	    out[i] = start + i * by;
	}
	return out;
    }
}
