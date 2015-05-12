package basic;

import java.io.IOException;
import java.util.regex.Pattern;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Iterator;

public final class FormatTranslater{
    private static final Pattern NEWLINE_PATTERN = Pattern.compile("\\n");
    private static final Pattern SPACE_PATTERN = Pattern.compile("\\s+");
    public static String HashMapToString(HashMap<Integer,LinkedList<Integer>> hashMap){
	Set<Integer> keySet = hashMap.keySet();
	String str = "";
	int key_ind = 1;
	for(int key : keySet){	    
	    str += "The " + (key_ind++) + "st key: " + key + ",";
	    str += "The value is: ";
	    int num_of_value = 0;
	    for(int i : hashMap.get(key)){
		num_of_value++;
	        str += i;
		str += " ";
	    }
	    str += "\n";
	    str += "Total num: " + num_of_value;
	    str += "\n";
        }
	return str;		    
    }
    public static double[][] rowBind(double[] vectorA, double[] vectorB) throws ArrayIndexOutOfBoundsException{
	double[][] matrix = new double[2][vectorA.length];
	for( int i = 0; i < vectorA.length; i++){
	    try{
		matrix[0][i] = vectorA[i];
		matrix[1][i] = vectorB[i];
	    }catch (ArrayIndexOutOfBoundsException e){
		throw e;
	    }
	}
	return matrix;
    }
    public static String vectorToString(double[] vector){
	String str = "";
	int length = vector.length;
	for( int i = 0; i < length; i++){
	    str += vector[i];
	    str += " ";
	}
	str += "\n";
	return str;
    }
    public static String doubleMatrixToString(double[][] matrix){
	String str = "";
	int numRows = matrix.length;
	int numCols = matrix[0].length;
	for( int i = 0; i < numRows; i++){
	    for( int j = 0; j < numCols; j++){
		str += matrix[i][j];
		str += " ";
	    }
	    str += "\n";
	}
	return str;
    };
    public static double[] stringToDoubleVector(String str){
	String[] elements = SPACE_PATTERN.split(str);
	int length = elements.length;
	double[] doubleVector = new double[length];
	for( int i = 0; i < length; i++){
	    doubleVector[i] = Double.parseDouble(elements[i]);
	}
	return doubleVector;
    }
    public static double[][] stringToDoubleMatrix(String str) throws ArrayIndexOutOfBoundsException{

	String[] rows = NEWLINE_PATTERN.split(str);
	int numRows = rows.length;
	int numCols = SPACE_PATTERN.split(rows[0]).length;
	double[][] matrix = new double[numRows][numCols];
	for(int i = 0; i < numRows; i++){
	    String[] elements = SPACE_PATTERN.split(rows[i]);
	    for( int j = 0; j < numCols; j++){
		try{
		    matrix[i][j] = Double.parseDouble(elements[j]);
		}catch (ArrayIndexOutOfBoundsException e){
		    throw e;
		}
	    }
	}
	return matrix;		
    };
    
}
    
