package sjsu.magal.cs146.project1.part1;

import java.util.Random;

public class Matrix {

	double[][] data;
	Random gen = new Random();

//	public static void main(String[] args) {
//			double[][] m1 = { { 1, 2, 6, 8, 0, 3, 7, 8 }, { 1, 9, 8, 1, 18, 4, 3, 9 }, { 1, 0, 1, 4, 6, 1, 2, 4 }, { 7, 1, 0, 2, 4, 7, 5, 8 }, { 1, 0, 5, 3, 6, 9, 4, 2 }, { 1, 0, 5, 7, 10, 9, 3, 2 }, { 10, 9, 4, 3, 0, 8, 4, 0 }, { 8, 9, 7, 3, 1, 2, 5, 4 }};
//			double[][] m2 = { { 5, 1, 7, 10, 1, 3, 2, 5 }, { 2, 7, 8, 6, 7, 5, 3, 9 }, { 8, 9, 7, 3, 1, 2, 5, 4 }, { 1, 0, 5, 3, 6, 9, 4, 2 }, { 1, 2, 6, 8, 0, 3, 7, 9 }, { 7, 1, 0, 2, 4, 7, 9, 8 }, {8, 8, 0, 1, 3, 5, 0, 5}, {5, 9, 0, 2, 4, 8, 10, 0}};
//			Matrix m11 = new Matrix(m1);
//			Matrix m22 = new Matrix(m2);
////			System.out.println("m11:");
////			m11.printMatrix();
////			System.out.println("m22");
////			m22.printMatrix();
//			Matrix result = m11.productRegular(m22);
////			System.out.println("regular");
////			result.printMatrix();
//			Matrix result1 = m11.productStrassen(m22);
////			System.out.println("strassen");
////			result1.printMatrix();
//	}

	public Matrix(double[][] x) { // creates matrix x
		data = x;
	}

	public Matrix(int n) { // creates an empty nxn matrix
		data = new double[n][n];
	}

	public int getDimension() { // returns dimension (n)
		return data.length;
	}

	public Matrix random() { // fills the matrix with random values
		for(int i = 0; i < this.getDimension(); i++){
			for(int j = 0; j < this.getDimension(); j++){
				data[i][j] = gen.nextDouble();
			}
		}
		return this;
	}

	public Matrix productRegular(Matrix b) { // multiplies in n^3
		Matrix result = new Matrix(b.getDimension());
		for (int i = 0; i < b.getDimension(); i++) { // cols of A
			for (int j = 0; j < b.getDimension(); j++) { // rows of A
				for (int k = 0; k < b.getDimension(); k++) { // rows of B
					result.data[i][j] += this.data[i][k] * b.data[k][j];
				}
			}
		}
		return result;
	}

	public Matrix productStrassen(Matrix b) { // multiplies using strassen		
		Matrix toReturn = new Matrix(this.data.length);
		if (b.data.length == 1) {
			// base case
			toReturn.data[0][0] = this.data[0][0] * b.data[0][0];
			return toReturn;	
		} else { 
			//split each matrix into four
			int boundary = this.data.length / 2;
			Matrix m1a = new Matrix(boundary);
			Matrix m2a = new Matrix(boundary);
			Matrix m3a = new Matrix(boundary);
			Matrix m4a = new Matrix(boundary);
			
			Matrix m1b = new Matrix(boundary);
			Matrix m2b = new Matrix(boundary);
			Matrix m3b = new Matrix(boundary);
			Matrix m4b = new Matrix(boundary);

			for (int i = 0; i < this.data[0].length; i++) { // rows
				for (int j = 0; j < this.data[0].length; j++) { // cols
					if ((i < boundary) && (j < boundary)) {				 // m1, in quadrant 1
						m1a.data[i][j] = this.data[i][j]; 	
						m1b.data[i][j] = b.data[i][j];
					} else if ((i < boundary) && (j >= boundary)) { 	//m2 in quadrant 2
						m2a.data[i][j - boundary] = this.data[i][j];
						m2b.data[i][j - boundary] = b.data[i][j];
					} else if ((i >= boundary) && (j < boundary)) { ///m3 in quadrant 3
						m3a.data[i - boundary][j] = this.data[i][j];
						m3b.data[i - boundary][j] = b.data[i][j];
					} else { // m4, in quadrant 4
						m4a.data[i - boundary][j - boundary] = this.data[i][j];
						m4b.data[i - boundary][j - boundary] = b.data[i][j];
					}
				}
			}
			
			Matrix p1 = m1a.productStrassen(subtraction(m2b,m4b));
			Matrix p2 = (addition(m1a,m2a)).productStrassen(m4b);		
			Matrix p3 = (addition(m3a,m4a)).productStrassen(m1b);
			Matrix p4 = m4a.productStrassen(subtraction(m3b,m1b));
			Matrix p5 = (addition(m1a,m4a)).productStrassen(addition(m1b,m4b));
			Matrix p6 = (subtraction(m2a,m4a)).productStrassen(addition(m3b,m4b));
			Matrix p7 = (subtraction(m1a,m3a)).productStrassen(addition(m1b,m2b));
			
			Matrix temp = addition(p5,p4);   
			Matrix temp1 = subtraction(p2,p6);
			Matrix c11 = subtraction(temp,temp1); //(m1a+m4a)*(m3b-m1b) + m4a*(m3b-m1b)
			Matrix c12 = addition(p1,p2);	
			Matrix c21 = addition(p3, p4);
			Matrix c22 = subtraction((addition(p5,p1)),addition(p3,p7));

			//rejoin all four
			for(int i = 0; i < toReturn.data[0].length; i++){
				for(int j = 0; j < toReturn.data.length; j++){
					if ((i < boundary) && (j < boundary)) {				 
						toReturn.data[i][j] = c11.data[i][j];
					} else if ((i < boundary) && (j >= boundary)) { 	
						toReturn.data[i][j] = c12.data[i][j-boundary];
					} else if ((i >= boundary) && (j < boundary)) { 
						toReturn.data[i][j] = c21.data[i-boundary][j];
					} else { 
						toReturn.data[i][j] = c22.data[i-boundary][j-boundary];
					}
				}
			}
		}
		return toReturn;		
	}
	
	public void printMatrix(){
		for(int i = 0; i < this.getDimension(); i++){
			for(int j = 0; j < this.getDimension(); j++){
				System.out.print(this.data[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
	public Matrix subtraction(Matrix a, Matrix b){
		Matrix toReturn = new Matrix(a.getDimension());
		for (int i = 0; i < a.getDimension(); i++){
	           for (int j = 0; j < a.getDimension(); j++)
	              toReturn.data[i][j] = a.data[i][j] - b.data[i][j];
		}
		return toReturn;
	}
	public Matrix addition(Matrix a, Matrix b){
		Matrix toReturn = new Matrix(a.getDimension());
		for (int i = 0; i < a.getDimension(); i++){
	           for (int j = 0; j < a.getDimension(); j++)
	              toReturn.data[i][j] = a.data[i][j] + b.data[i][j];
		}
		return toReturn;
	}
}
