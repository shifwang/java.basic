package basic;

public class sort{
    public double[] value;
    public int[] valueToPos;
    public sort(double[] array){
	value = array;
	valueToPos = new int[array.length];
	for(int i = 0; i < array.length; i++){
	    valueToPos[i] = i;
	}
	quicksort();
    }
    private void swap(int i, int j){
	double tmp = value[i];
	value[i] = value[j];
	value[j] = tmp;
	int ind = valueToPos[i];
	valueToPos[i] = valueToPos[j];
	valueToPos[j] = ind;				   
    }
    public void quicksort(int start, int stop){
	//descending
	if(start+1 >= stop){
	    return;
	}
	swap(start,(stop+start)/2);
	double pivot = value[start];
	int partition = start+1;
	for(int i = start+1; i < stop; i++){
	    if(value[i] > pivot){
		swap(partition,i);
		partition++;
	    }
	}
	swap(start,partition-1);
	quicksort(start,partition-1);
	quicksort(partition,stop);
    }
    public void quicksort(){
	quicksort(0,value.length);
    }
}
