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
public class MatrixTest extends TestCase {

	private Matrix A, B; //input matrices
	private Matrix productRegularResult, productStrassenResult; // Matrices for storing the results
	private int N; // size of the NXN matrix
	
	@Before
	public void setUp() throws Exception
	{
	   N = 4; // size of the matrix
	   double[][] array1 = new double[N][N];
	   double[][] array2 = new double[N][N];
	   A = new Matrix(array1);
	   B = new Matrix(array2);
	   productRegularResult = new Matrix(N);
	   productStrassenResult = new Matrix(N);
	} // setUp()
	
	/* compare result matrices of regular multiplication method and Strassen multiplication method:
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
		 long endTime1 = System.currentTimeMillis();
	     long totalTime1 = endTime1 - startTime1;
	     System.out.println("Regular 16: " + totalTime1);
		 
	     for (int i = 0; i < N; i++) {
	    	assertArrayEquals(productRegularResult.data[i], productStrassenResult.data[i], 0.0001 ); // data[][] is a data member for storing matrix values in class Matrix.
	     }
	}
	
	
	/* multiplying a 2D array using the regular method:
	 */
	@Test
	public void testProductRegular() {
	    
	    //expected output
		double[][] expected = {{96.0,94.0,81.0,128.0},{144.0,117.0,112.0,162.0},{132.0,112.0,101.0,152.0},{112.0,86.0,87.0,130.0}};
	    
		// input 2D arrain
		double[][] array1 = {{2.0,4.0,5.0,7.0},{6.0,7.0,2.0,8.0},{4.0,6.0,3.0,9.0},{8.0,4.0,1.0,5.0}};
		double[][] array2 = {{6.0,4.0,5.0,8.0},{8.0,7.0,8.0,8.0},{2.0,6.0,5.0,9.0},{6.0,4.0,2.0,5.0}}; 		
	    
		Matrix m1 = new Matrix(array1);
		Matrix m2 = new Matrix(array2);
	      
	    // run productRegular()
		productRegularResult = m1.productRegular(m2);
	     
	    for (int i = 0; i < N; i++) {
			assertArrayEquals(expected[i],productRegularResult.data[i], 0.0); // data[][] is a data member for storing matrix values in class Matrix.
		}  
	}
	
	/* multiplying a 2D array using the Strassen method:
	 */
	@Test
	public void testProductStrassen() {
	    
	    //expected output
		double[][] expected = {{96.0,94.0,81.0,128.0},{144.0,117.0,112.0,162.0},{132.0,112.0,101.0,152.0},{112.0,86.0,87.0,130.0}};
	    
		// input 2D array
		double[][] array1 = {{2.0,4.0,5.0,7.0},{6.0,7.0,2.0,8.0},{4.0,6.0,3.0,9.0},{8.0,4.0,1.0,5.0}};
		double[][] array2 = {{6.0,4.0,5.0,8.0},{8.0,7.0,8.0,8.0},{2.0,6.0,5.0,9.0},{6.0,4.0,2.0,5.0}}; 		
	    
		Matrix m1 = new Matrix(array1);
		Matrix m2 = new Matrix(array2);
	      
	    // run productRegular()
		productStrassenResult= m1.productStrassen(m2);
	     
	    for (int i = 0; i < N; i++) {
			assertArrayEquals(expected[i],productStrassenResult.data[i], 0.0); // data[][] is a data member for storing matrix values in class Matrix.
		}	    
	}
	
	
	
} // class MatrixTest