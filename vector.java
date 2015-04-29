package basic;
//import basic.matrix;
import java.lang.Math;

public class vector extends matrix{
    public vector(int n){
	this.numRow = n;
	this.numCol = 1;
	this.data = new double[this.numRow][1];
	for(int i = 0; i < numRow; i++){
	    this.data[i][0] = 0;	    
	}	
    }    
    public void assign(vector vec, int[] ind){
	this.numRow = vec.nrow();
	this.numCol = 1;
	this.data = null;
	this.data = new double[numRow][numCol];
	for(int i = 0; i < numRow; i++)
	    this.data[i][0] = 0;
	for(int i = 0; i < ind.length; i++){
	    data[ind[i]][0] = vec.data[ind[i]][0];
	}
    }
    public vector(matrix m){
	this.numRow = m.nrow();
	this.numCol = 1;
	this.data = new double[this.numRow][this.numCol];
	for(int i = 0; i < numRow; i++){
	    this.data[i][0] = m.data[i][0];
	}
    }
    public vector(int [] a){
	this.numRow = a.length;
	this.numCol = 1;
	this.data = new double[this.numRow][this.numCol];
	for(int i = 0; i < numRow; i++){
	    this.data[i][0] = a[i];
	}	
    }
    public vector(double [] a){
	this.numRow = a.length;
	this.numCol = 1;
	this.data = new double[this.numRow][this.numCol];
	for(int i = 0; i < numCol; i++){
	    this.data[i][0] = a[i];
	}	
    }
    public vector add(double constant){
	vector v = new vector(super.add(constant));
	return v;
    }
    public vector add(vector m){
	vector v = new vector(super.add(m));
	return v;
    }
    
    public static matrix sort(int[] array){
	//System.out.println(array[1]);
	vector vec = new vector(array);
	//System.out.println(vec.data[1][0]);
	return vec.sort();
    }	
    public matrix sort(){
	double[] tmp = new double[this.nrow()];
	for(int i = 0; i < this.nrow(); i++){
	    tmp[i] = this.data[i][0];
	}
	double slotDoub;
	int slotInt;
	int[] ind = new int[this.nrow()];
	for(int i = 0; i < ind.length; i++){
	    ind[i] = i;
	}
	for(int i = 0; i < tmp.length; i++){
	    for(int j = i+1; j < tmp.length; j++){
		if(tmp[i] < tmp[j]){
		    slotDoub = tmp[i];tmp[i] = tmp[j];tmp[j] = slotDoub;
		    slotInt = ind[i];ind[i] = ind[j];ind[j] = slotInt;
		}
	    }
	}
	matrix out = new matrix(this.nrow(),2);
	for(int i = 0; i < out.nrow(); i++){
	    out.data[i][0] = ind[i];
	    out.data[i][1] = tmp[i];
	}
	return out;
    }

    public vector abs(){
	vector out = new vector(this);
	for(int i = 0; i < out.nrow(); i++){
	    for(int j = 0; j < out.ncol(); j++){
		out.data[i][j] = Math.abs(out.data[i][j]);
	    }
	}
	return out;
    }
    public vector minus(matrix a){
	vector out = new vector(this.numRow);
	for(int i = 0; i < this.numRow; i++){
	    out.data[i][0] = this.data[i][0] - a.data[i][0];
	}
	return out;
    }
    public static boolean same(int[] a, int[] b){
	// give a ?= b
	if(a.length != b.length){
	    return false;
	}else{
	    matrix sort_a = sort(a);
	    //System.out.println(a[1]);//DEBUG
	    matrix sort_b = sort(b);
	    //System.out.println(b[1]);//DEBUG
	    for(int i = 0; i < sort_a.nrow(); i++){
		if( sort_a.data[i][1] != sort_b.data[i][1]){
		    return false;
		}
	    }
	    return true;
	}	
    }

    public vector subvector(int[] indList){
	vector out = new vector(indList.length);
	for(int i = 0; i < indList.length; i++){
	    out.data[i][0] = this.data[indList[i]][0];
	}
	return out;
    }
    public vector subvector(double[] indList){
	vector out = new vector(indList.length);
	for(int i = 0; i < indList.length; i++){
	    int tmp = (int) indList[i];
	    out.data[i][0] = this.data[tmp][0];
	}
	return out;
    }
    public vector subvector(vector indList){
	vector out = new vector(indList.nrow());
	for(int i = 0; i < indList.nrow(); i++){
	    int tmp = (int) indList.data[i][0];
	    out.data[i][0] = this.data[tmp][0];
	}    
	return out;
    }
    public static vector getCol(matrix m,int ind){
	vector out = new vector(m.nrow());
	for(int i = 0; i < m.ncol(); i++){
	    out.data[i][0] = m.data[i][ind];
	}
	return out;
    }
    public vector subvector(int i, int j){
	matrix m = this.subMatrix(i,j,0,0);
	vector v = vector.getCol(m,0);
	return v;
    }
    public static vector  parseVector(String str){
	return new vector(matrix.parseMatrix(str));
    }

    public matrix support(){
	double[][] tmp = new double[this.nrow()][2];
	int iter = 0;
	for(int i = 0; i < this.nrow(); i++){
	    if(this.data[i][0] != 0){
		tmp[iter][0] = i;
		tmp[iter][1] = this.data[i][0];
		iter += 1;
	    }
	}
	matrix out = new matrix(iter,2);
	if(this.rownames != null){
	    out.rownames = new String[iter];
	}
	for(int i = 0; i < iter; i++){
	    out.data[i][0] = tmp[i][0];
	    out.data[i][1] = tmp[i][1];
	    if(this.rownames != null){
		out.rownames[i] = this.rownames[(int) tmp[i][0]];
	    }
	}
	return out;
    }
}
