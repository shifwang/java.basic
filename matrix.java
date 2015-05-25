package basic;

import java.io.*;
import java.util.*;
import java.lang.Math.*;

import static basic.FormatTranslater.*;

public class matrix{
    public int numRow;
    public int numCol;
    public double[][] data;
    public String[] rownames;
    public String[] colnames;
    public matrix(int n1, int n2){
	rownames = null;
	colnames = null;
	numRow = n1;
	numCol = n2;
	data = new double[n1][n2];
	for(int i = 0; i < n1; i++)
	    for(int j = 0; j < n2; j++){
		data[i][j] = 0;
	    }
    }
    public void getRowNames(String[] str){
	if(str == null) {
	    this.rownames = null;
	    return;
	}
	int n = str.length;
	if (n != this.numRow){System.out.println("Error:row name require " + this.numRow + " But get " + n);};
	this.rownames = new String[n];
	for(int i = 0; i < n; i++){
	    this.rownames[i] = str[i];
	}	
    }
    public void getRowNames(String[] str,int[] indlist){
	if(str == null) {
	    this.colnames = null;
	    return;
	}
	try{
	    this.rownames = new String[this.numRow];
	    for(int i = 0; i < indlist.length; i++){
		this.rownames[indlist[i]] = str[indlist[i]];
	    }
	}catch (Exception e){
	    System.out.println("Error: reading row names failed.");	    
	}
    }
    public void getColNames(String[] str){
	if(str == null){
	    this.colnames = null;
	    return;
	}
	int n = str.length;
	if (n != this.numCol){System.out.println("Error:col name require " + this.numCol + " But get " + n);};
	this.colnames = new String[n];
	for(int i = 0; i < n; i++){
	    this.colnames[i] = str[i];
	}	
    }
    public void getColNames(String[] str,int[] indlist){
	if(str == null){
	    this.colnames = null;
	    return;
	}
	try{
	    this.colnames = new String[this.numCol];
	    for(int i = 0; i < indlist.length; i++){
		
		this.colnames[indlist[i]] = str[i];
		
	    }
	}catch (Exception e){
	    System.out.println("Error: reading col names failed.");
	}
    }    
    public int nrow(){
	return numRow;
    }
    public int ncol(){
	return numCol;
    }
    public double at(int i, int j){
	return data[i][j];
    }
    public matrix(){
	this.numRow = 0;
	this.numCol = 0;
	this.data = null;
    }
    public matrix(matrix m){
	this.assign(m);
    }
    public matrix(double[][] d){
	this.rownames = null;
	this.colnames = null;
	this.numRow = d.length;
	this.numCol = d[0].length;
	this.data = new double[this.numRow][this.numCol];
	for(int i = 0; i < numRow; i++){
	    for(int j = 0; j < numCol; j++){
		this.data[i][j] = d[i][j];
	    }
	}
    }
    public matrix(double[][] d, String[] nameOfCols, String[] nameOfRows){
	this.getRowNames(nameOfRows);
	this.getColNames(nameOfCols);
	this.numRow = d.length;
	this.numCol = d[0].length;
	this.data = new double[this.numRow][this.numCol];
	for(int i = 0; i < numRow; i++){
	    for(int j = 0; j < numCol; j++){
		this.data[i][j] = d[i][j];
	    }
	}
    }
    public void assign(matrix m){
	if( m.rownames != null){
	    this.rownames = new String[m.rownames.length];
	    for(int i = 0; i < m.rownames.length; i++)
		this.rownames[i] = m.rownames[i];
	}else{
	    this.rownames = null;
	}
	if( m.colnames != null){
	    this.colnames = new String[m.colnames.length];
	    for(int i = 0; i < m.colnames.length; i++){
		this.colnames[i] = m.colnames[i];
	    }
	}else{
	    this.colnames = null;
	}
	this.numRow = m.nrow();
	this.numCol = m.ncol();
	this.data = new double[numRow][numCol];
	for( int i = 0; i < numRow; i++){
	    for(int j = 0; j < numCol; j++){
		data[i][j] = m.data[i][j];
	    }
	}
	
    }
    public matrix t(){
	int n1 = this.nrow();
	int n2 = this.ncol();
	double[][] array = new double[n2][n1];
	for(int i = 0; i < n2; i++){
	    for(int j = 0; j < n1; j++){
		array[i][j] = this.data[j][i];
	    }
	}
	return new matrix(array);//WARNING: WHEN DOING TRANSPOSE, THE NAME HAS BEEN REMOVED
    }
    public matrix times(double c){
	matrix out = new matrix(this);
	for(int i = 0; i < this.numRow; i++){
	    for(int j = 0; j < this.numCol; j++){
		    out.data[i][j]  = this.data[i][j] * c;
		}
	}
	return out;
    }
    public matrix times(matrix m2){	
	int n1 = this.numRow;
	int n2 = this.numCol;
	if( n2 != m2.nrow()){
	    System.out.println("numCol of A1 is " + n2 + ", numRow of A2 is " + m2.nrow() + ".");
	    System.out.println("Error, Matrix dimension not consistent!");
	    return null;
	}
	int n3 = m2.ncol();
	matrix out = new matrix(n1,n3);
	for(int i = 0; i < n1; i++)
	    for(int j = 0; j < n2; j++)
		for(int k = 0; k < n3; k++){
		    out.data[i][k] += this.data[i][j] * m2.data[j][k];
		}
	return out;
    }
    public matrix times(matrix m2, int[] supp){
	int n1 = this.numRow;
	int n2 = this.numCol;
	if( n2 != m2.nrow()){
	    System.out.println("Error, Matrix dimension not consistent!");
	    return null;
	}
	int n3 = m2.ncol();
	matrix out = new matrix(n1,n3);
	for(int i = 0; i < n1; i++)
	    for(int j = 0; j < supp.length; j++)		
		for(int k = 0; k < n3; k++){
		    out.data[i][k] += this.data[i][supp[j]] * m2.data[supp[j]][k];
		}
	return out;
	
    }
    public matrix minus(matrix m2){
	int n1 = this.nrow();
	int n2 = this.ncol();
	if( n1 != m2.nrow() || n2 != m2.ncol()){
	    System.out.println("Error, Matrix dimension not consistent!");
	    return null;
	}
	matrix out = new matrix(n1,n2);
	for(int i = 0; i < n1; i++)
	    for(int j = 0; j < n2; j++)
		out.data[i][j] = this.data[i][j] - m2.data[i][j];
	return out;
    }
    public matrix add(double constant){
	int n1 = this.nrow();
	int n2 = this.ncol();
	matrix out = new matrix(n1,n2);
	for(int i = 0; i < n1; i++)
	    for(int j = 0; j < n2; j++)
		out.data[i][j] = this.data[i][j] + constant;
	return out;
    }
    public void deleteAbs(double constant){
	for(int i = 0; i < this.numRow; i++){
	    for(int j = 0; j < this.numCol; j++){
		if(Math.abs(this.data[i][j]) > constant){
		    this.data[i][j] = 0;
		}
	    }
	}
    }
    public matrix add(matrix m){
	int n1 = this.nrow();
	int n2 = this.ncol();
	if( n1 != m.nrow() || n2 != m.ncol()){
	    System.out.println("Error, matrix dimension not consistent!");
	    return null;
	}
	matrix out = new matrix(n1,n2);
	for(int i = 0; i < n1; i++)
	    for(int j = 0; j < n2; j++)
		out.data[i][j] = this.data[i][j] + m.data[i][j];
	return out;
    }
    public matrix subMatrix(int xmin, int xmax, int ymin, int ymax){
	boolean xreverse = false;
	if(xmin < 0){
	    if(xmax >= 0){ System.out.println("Error: xmin xmax has wrong sign!");}
	    else{
		xreverse = true;
		xmin = -1 * xmin - 1;
		xmax = -1 * xmax - 1;
	    }
	}
	boolean yreverse = false;
	if(ymin < 0){
	    if(ymax >= 0){ System.out.println("Error: ymin ymax has wrong sign!");
	    }else{
		yreverse = true;
		ymin = -1 * ymin - 1;
		ymax = -1 * ymax - 1;
	    }
	}
	int n1 = xreverse? (this.nrow() - xmax + xmin - 1):(xmax - xmin + 1);
	int n2 = yreverse? (this.ncol() - ymax + ymin - 1):(ymax - ymin + 1);
	matrix out = new matrix(n1,n2);
	if(xreverse & yreverse){
	    for(int i = 0; i < xmin; i++){for(int j = 0; j < ymin; j++){out.data[i][j] = this.data[i][j];}}
	    for(int i = xmax+1; i < this.numRow; i++){for(int j = 0; j < ymin; j++){out.data[i-xmax+xmin-1][j] = this.data[i][j];}}
	    for(int i = 0; i < xmin; i++){for(int j = ymax+1; j < this.numCol; j++){out.data[i][j-ymax-1+ymin] = this.data[i][j];}}
	    for(int i = xmax+1; i < this.numRow; i++){for(int j = ymax+1; j < this.numCol; j++){out.data[i-xmax-1+xmin][j-ymax-1+ymin] = this.data[i][j];}}
	}else if(xreverse & !yreverse){
	    for(int i = 0; i < xmin; i++){for(int j = 0; j < n2; j++){out.data[i][j] = this.data[i][j+ymin];}}
	    for(int i = xmax+1; i < this.numRow; i++){for(int j = 0; j < n2; j++){out.data[i-xmax+xmin-1][j] = this.data[i][j+ymin];}}
	}else if(!xreverse & yreverse){
	    for(int i = 0; i < n1; i++){for(int j = 0; j < ymin; j++){out.data[i][j] = this.data[i+xmin][j];}}
	    for(int i = 0; i < n1; i++){for(int j = ymax+1; j < this.numCol; j++){out.data[i][j-ymax-1+ymin] = this.data[i+xmin][j];}}
	}else if(!xreverse & !yreverse){
	    
	    for(int i = 0; i < n1; i++){for(int j = 0; j < n2; j++){out.data[i][j] = this.data[i+xmin][j+ymin];}}
	}
	if(this.colnames != null){
	    out.colnames = new String[out.ncol()];
	    if(!yreverse){
		
		for(int i = 0; i < out.ncol(); i++){out.colnames[i] = this.colnames[i+ymin];}
	    }else{
		for(int i = 0; i < ymin; i++){out.colnames[i] = this.colnames[i];}
		for(int i = ymax+1; i < this.numCol; i++){out.colnames[i-ymax-1+ymin] = this.colnames[i];}
	    }
	}
	if(this.rownames != null){
	    out.rownames = new String[out.nrow()];
	    if(!xreverse){
		for(int i = 0; i < out.nrow(); i++){out.rownames[i] = this.rownames[i+xmin];}
	    }else{
		for(int i = 0; i < xmin; i++){out.rownames[i] = this.rownames[i];}
		for(int i = xmax+1; i < this.numRow; i++){out.colnames[i-xmax-1+xmin] = this.rownames[i];}
	    }
	}
	
	return out;
    }
    public void assignSubMatrix(int xmin, int ymin, matrix m){
	for(int i = 0; i < this.nrow(); i++){
	    for(int j = 0; j < this.ncol(); j++){
		this.data[xmin+i][ymin+j] = m.data[i][j];
	    }
	}
	if(m.colnames != null){
	    this.colnames = new String[this.ncol()];
	    for(int i = 0; i < this.numCol; i++){
		this.colnames[i] = m.colnames[i+ymin];
	    }
	}
	if(m.rownames != null){
	    this.rownames = new String[this.nrow()];
	    for(int i = 0; i < this.numRow; i++){
		this.rownames[i] = m.rownames[i+xmin];
	    }
	}
	
    }
    public matrix rbind(matrix m1, matrix m2){
	if(m1.ncol() != m2.ncol()){
	    System.out.println("Error doing rbind! m1 has "+ m1.ncol() +" collumns while m2 has " + m2.ncol());
	}
	matrix out = new matrix(m1.nrow()+m2.nrow(),m1.ncol());
	for(int i = 0; i < m1.nrow(); i++){
	    for(int j = 0; j < m1.ncol(); j++){
		out.data[i][j] = m1.data[i][j];
	    }
	}
	for(int i = 0; i < m2.nrow(); i++){
	    for(int j = 0; j < m1.ncol(); j++){
		out.data[i+m1.numRow][j] = m2.data[i][j];
	    }
	}
	return out;
    }
    public matrix cbind(matrix m1, matrix m2){
	if(m1.nrow() != m2.nrow()){
	    System.out.println("Error doing cbind! m1 has "+ m1.nrow() +" rows while m2 has " + m2.nrow());
	}
	matrix out = new matrix(m1.nrow(),m1.ncol()+m2.ncol());
	for(int i = 0; i < m1.nrow(); i++){
	    for(int j = 0; j < m1.ncol(); j++){
		out.data[i][j] = m1.data[i][j];
	    }
	}
	for(int i = 0; i < m2.nrow(); i++){
	    for(int j = 0; j < m2.ncol(); j++){
		out.data[i][j+m1.numCol] = m2.data[i][j];
	    }
	}
	return out;
    }
    public void rowdiff(){
	this.colnames = null;
	for(int i = 0; i < this.numRow; i++){
	    this.data[i][0] = 0;
	    for(int j = 1; j < this.numCol; j++){
		this.data[i][j] = this.data[i][j] - this.data[i][j-1];
	    }
	}
    }
    public void coldiff(){
	this.rownames = null;
	for(int j = 0; j < this.numCol; j++){
	    this.data[0][j] = 0;
	    for(int i = 1; i < this.numRow; i++){
		this.data[i][j] = this.data[i][j] - this.data[i-1][j];
	    }
	}
    }
    public void colNormalize(){
	for(int j = 0; j < this.numCol; j++){
	    double sum = 0;
	    double square = 0;
	    for(int i = 0; i < this.numRow; i++){
		sum += this.data[i][j];
		square += this.data[i][j] * this.data[i][j];
	    }
	    sum /= this.numRow;
	    square /= this.numRow;
	    if(square == 0){
		System.out.println("Warning: all zero collumns persists!");
		continue;
	    }
	    if( square < sum * sum){
		System.out.println("Error when standarding! square < sum * sum");
	    }
	    double std = Math.sqrt(square - sum * sum);
	    for(int i = 0; i < this.numRow; i++){
		this.data[i][j] = (this.data[i][j] - sum)/std;
	    }

	}
    }
    public void log(){
	for(int i = 0; i < this.numRow; i++){
	    for(int j = 0; j < this.numCol; j++){
		this.data[i][j] = Math.log(this.data[i][j]+1);
	    }
	}
    }
    public double l2(){
	double sum = 0;
	for(int i = 0; i < this.nrow(); i++){
	    for(int j = 0; j < this.ncol(); j++){
		sum += this.data[i][j]*this.data[i][j];
	    }
	}
	return sum;
    }
    public matrix getCols(int[] supp){
	int m = this.nrow();
	int n = supp.length;
	matrix out = new matrix(m,n);	
	for(int i = 0; i < n; i++){
	    int tmp = supp[i];
	    for(int j = 0; j < m; j++){
		out.data[j][i] = this.data[j][tmp];
	    }
	}
	if(this.rownames != null){
	    out.getRowNames(this.rownames);
	}
	if(this.colnames != null){
	    out.colnames = new String[out.ncol()];
	    for(int i = 0; i < out.ncol(); i++){
		out.colnames[i] = this.colnames[supp[i]];
	    }
	}
	return out;
    }
    public matrix getCols(boolean[] isSelected){
	if(isSelected.length != this.ncol()){
	    System.out.println("Error in getCols(boolean[] isSelected)! length not compatible!");
	    return null;
	}
	int m = this.nrow();	
	int n = 0;
	for(int i = 0; i < isSelected.length; i++){
	    if(isSelected[i])
		n += 1;
	}
	matrix out = new matrix(m,n);
	int iter = 0;
	for(int i = 0; i < isSelected.length; i++){
	    if(!isSelected[i]){
		continue;
	    }
	    for(int j = 0; j < m; j++){
		out.data[j][iter] = this.data[j][i];
	    }
	    iter++;
	}
	if(this.rownames != null){
	    out.getRowNames(this.rownames);
	}
	if(this.colnames != null){
	    iter = 0;
	    out.colnames = new String[out.ncol()];
	    for(int i = 0; i < isSelected.length; i++){
		if(!isSelected[i]){
		    continue;
		}
		out.colnames[iter] = this.colnames[i];
		iter++;
	    }
	}
	return out;
    }
    public String toString(){
	String out = "";
	if(this.colnames != null){
	    for(int i = 0; i < this.colnames.length; i++){
		//DEBUG: I don't know why cannot include the last character
		out = out + this.colnames[i].substring(0,this.colnames[i].length());
		out += " ";
	    }
	    
	    out += "\n";
	}
	if(this.rownames == null){
	    out += doubleMatrixToString(this.data);
	}else{
	    String[] lines = doubleMatrixToString(this.data).split("\n");
	    for(int i = 0; i < this.rownames.length; i++){
		if(this.rownames[i] != null){
		    out += this.rownames[i].substring(0,this.rownames[i].length());
		}else{
		    out += "-";
		}
		out += " ";
		out += lines[i];
		out += "\n";
	    }
	}
	return out; 
    }
    public static matrix parseMatrix(String str){
	double[][] tmp = stringToDoubleMatrix(str);
	matrix m = new matrix(tmp);
	return m;
    }
    public static matrix parseMatrix(String str, String[] nameOfCols, String[] nameOfRows){
	double[][] tmp = stringToDoubleMatrix(str);
	matrix m = new matrix(tmp,nameOfCols,nameOfRows);
	return m;
    }
    public void biggerThan(double thres){
	for(int i = 0; i < this.numRow; i++){
	    for(int j = 0; j < this.numCol; j++){
		this.data[i][j] = this.data[i][j]>thres? 1:0;
	    }
	}
    }
}
