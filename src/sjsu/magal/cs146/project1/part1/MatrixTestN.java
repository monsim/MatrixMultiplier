package sjsu.magal.cs146.project1.part1;
/*
Class: CS146-01 & 2
Semester: Fall 2016
Project: #1 - Matrix Multiplication
sample JUnit tests for checking and comparing the result of
regular O(n^3) matrix multiplication algorithm with Strassen
multiplication algorithm.	 
*/

import static org.junit.Assert.assertArrayEquals;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;


/**
* The main JUnit Test class to test regular multiplication 
* and Strassen multiplication method.
* 
*
*/
public class MatrixTestN extends TestCase {

	private Matrix A, B; //input matrices
	private Matrix A1, B1;
	private Matrix A2, B2;
	private Matrix productRegularResult, productStrassenResult, productRegularResult1, productStrassenResult1,  productRegularResult2, productStrassenResult2; // Matrices for storing the results
	private int N; // size of the NXN matrix
	private int N1;
	private int N2;
	
	@Before
	public void setUp() throws Exception
	{
	   N = 16; // size of the matrix
	   N1 = 512;
	   N2 = 1024;
	   double[][] array1 = new double[N][N];
	   double[][] array2 = new double[N][N];
	   A = new Matrix(array1);
	   B = new Matrix(array2);
	   double[][] array11 = new double[N1][N1];
	   double[][] array21 = new double[N1][N1];
	   A1 = new Matrix(array11);
	   B1 = new Matrix(array21);
	   double[][] array12 = new double[N2][N2];
	   double[][] array22 = new double[N2][N2];
	   A2 = new Matrix(array12);
	   B2 = new Matrix(array22);
	   productRegularResult = new Matrix(N);  //n = 16
	   productStrassenResult = new Matrix(N);
	   productRegularResult1 = new Matrix(N1); //n = 512
	   productStrassenResult1 = new Matrix(N1);
	   productRegularResult2 = new Matrix(N2); //n = 1024
	   productStrassenResult2 = new Matrix(N2);
	} // setUp()
	
	/* compare result matrices of regular multiplication method and Strassen multiplication method for n = 16:
	 */
	@Test
	public void testProductCompare() {
	    
	     
	     //run user defined random() method to generate the matrices
	     A = A.random();
	     B = B.random();  
	      
	     // run productRegular()
	     long startTime = System.currentTimeMillis();
	     productRegularResult = A.productRegular(B);
	     long endTime   = System.currentTimeMillis();
	     long totalTime = endTime - startTime;
	     System.out.println("Regular 16: " + totalTime);
	     
	     // run productStrassen()
	     long startTime1 = System.currentTimeMillis();
		 productStrassenResult = A.productStrassen(B);
		 long endTime1   = System.currentTimeMillis();
	     long totalTime1 = endTime1 - startTime1;
	     System.out.println("Strassen 16: " + totalTime1);
		 
	     for (int i = 0; i < N; i++) {
	    	assertArrayEquals(productRegularResult.data[i], productStrassenResult.data[i], 0.0001 ); // data[][] is a data member for storing matrix values in class Matrix.
	     }
	}
	
	/* compare result matrices of regular multiplication method and Strassen multiplication method for n = 512:
	 */
	@Test
	public void testProductCompare1() {
	    
	     
	     //run user defined random() method to generate the matrices
		 A1 = A1.random();
	     B1 = B1.random();
	     
	     // run productRegular()
	     long startTime = System.currentTimeMillis();
	     productRegularResult1 = A1.productRegular(B1);
	     long endTime   = System.currentTimeMillis();
	     long totalTime = endTime - startTime; 
	     System.out.println("Regular 512: " + totalTime);
	     // run productStrassen()
	     long startTime1 = System.currentTimeMillis();
		 productStrassenResult1 = A1.productStrassen(B1);
		 long endTime1   = System.currentTimeMillis();
		 long totalTime1 = endTime1 - startTime1; 
		 System.out.println("Strassen 512: " + totalTime1);
		 
	     for (int i = 0; i < N1; i++) {
	    	assertArrayEquals(productRegularResult1.data[i], productStrassenResult1.data[i], 0.0001 ); // data[][] is a data member for storing matrix values in class Matrix.
	     }
	}
	
	/* compare result matrices of regular multiplication method and Strassen multiplication method for n = 1024:
	 */
	@Test
	public void testProductCompare2() {
	    
	     
	     //run user defined random() method to generate the matrices
	     A2 = A2.random();
	     B2 = B2.random();
	     
	     
	     // run productRegular()
	     long startTime = System.currentTimeMillis();
	     productRegularResult2 = A2.productRegular(B2);
	     long endTime   = System.currentTimeMillis();
		 long totalTime = endTime - startTime; 
	     System.out.println("Regular 1024: " + totalTime);
	     
		 
	     // run productStrassen()
		 long startTime1 = System.currentTimeMillis();
		 productStrassenResult2 = A2.productStrassen(B2);
		 long endTime1   = System.currentTimeMillis();
		 long totalTime1 = endTime1 - startTime1; 
		 System.out.println("Strassen 1024: " + totalTime1);
		 
	     for (int i = 0; i < N2; i++) {
	    	assertArrayEquals(productRegularResult2.data[i], productStrassenResult2.data[i], 0.0001 ); // data[][] is a data member for storing matrix values in class Matrix.
	     }
	}
	
	
	
	
	
} // class MatrixTest