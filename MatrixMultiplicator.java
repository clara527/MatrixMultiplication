// Clara Yalamanchili - CSI 2300 
// Matrix Multiplication App

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MatrixMultiplicator {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Matrix Multiplication App");

        if (args.length == 1) {
            int size = Integer.parseInt(args[0]);
            System.out.println("Your matrix size (through the command line / command prompt): " + size);

            // Using randomMatrix method to create 2 matrices holding random values of user's desired size
            int[][] matrix1 = randomMatrix(size);
            int[][] matrix2 = randomMatrix(size);

            // Each matrix is written and saved to text file
            fileSave(matrix1, "matrix1.txt");
            fileSave(matrix2, "matrix2.txt");

            System.out.println("Matrix #1 has been saved to 'matrix1.txt'");
            display(matrix1);
            System.out.println("Matrix #2 has been saved to 'matrix2.txt'");
            display(matrix2);

            // Multiply the matrices together to create matrix #3
            int[][] result = multiply(matrix1, matrix2);

            // Save result to matrix3.txt
            fileSave(result, "matrix3.txt");

            System.out.println("Final multiplication result (matrix #3) that has been saved to 'matrix3.txt'");
            display(result);

        } else if (args.length == 2) {
            try (BufferedReader reader1 = new BufferedReader(new FileReader(args[0]));
                 BufferedReader reader2 = new BufferedReader(new FileReader(args[1]))) {
                 
                int[][] matrix1 = fileRead(reader1);
                int[][] matrix2 = fileRead(reader2);
                
                System.out.println("Matrix 1 (read from " + args[0] + ")");
                display(matrix1);
                System.out.println("Matrix 2 (read from " + args[1] + ")");
                display(matrix2);

                // Use multiply method to calculate dot product
                int[][] result = multiply(matrix1, matrix2);

                // Method "save" called to save to matrix3 file
                fileSave(result, "matrix3.txt");

                System.out.println("Final multiplication result (matrix #3) that has been saved to the text file 'matrix3.txt'");
                display(result);

            } catch (IOException e) {
                System.out.println("Cannot read the text file " + e.getMessage());
            }

        } else {
            System.out.println("Note: your desired matrix size must be an integer.");
            System.out.println("What is your desired matrix size?");
            int size = scan.nextInt();
            System.out.println("Your matrix size is " + size);

            // Uses the method we made, randomMatrix, to create 2 different matrices of random numbers with the size that the user inputted
            int[][] matrix1 = randomMatrix(size);
            int[][] matrix2 = randomMatrix(size);

            // Save matrices to files
            fileSave(matrix1, "matrix1.txt");
            fileSave(matrix2, "matrix2.txt");

            System.out.println("The result of matrix #1 has been saved to the text file 'matrix1.txt'");
            display(matrix1);
            System.out.println("The result of matrix #2 has been saved to the text file 'matrix2.txt'");
            display(matrix2);

            // Multiply matrices
            int[][] result = multiply(matrix1, matrix2);

            // Save result to matrix3.txt
            fileSave(result, "matrix3.txt");

            System.out.println("The final result matrix has been saved to the text file 'matrix3.txt'");
            display(result);
        }

        scan.close();
    }

    // Method that creates matrices with random numbers using the Random function with the user's desired size they inputted
    public static int[][] randomMatrix(int size) {
        Random random = new Random();
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(10); // Random numbers between 0 and 9
            }
        }
        return matrix;
    }

    // Method that multiplies the two random matrices of the size the user inputted by calculating the dot product
    public static int[][] multiply(int[][] matrix1, int[][] matrix2) {
        int nrows1 = matrix1.length;
        int ncols1 = matrix1[0].length;
        int ncols2 = matrix2[0].length;

        int[][] result = new int[nrows1][ncols2];

        // Matrix multiplication logic
        for (int i = 0; i < nrows1; i++) {
            for (int j = 0; j < ncols2; j++) {
                for (int k = 0; k < ncols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    // Method that displays the values in the random matrices
    public static void display(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method that saves the randomly generated matrix to command line file
    public static void fileSave(int[][] matrix, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    writer.write(matrix[i][j] + " ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("There was a problem saving the matrix to file, please try again and enter one integer. Example: '4' " + e.getMessage());
        }
    }

    // Method that takes file to read from two dimensional array
    public static int[][] fileRead(BufferedReader reader) throws IOException {
        ArrayList<int[]> matrixList = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] rowNum = line.split(" ");
            int[] row = new int[rowNum.length];

            for (int i = 0; i < rowNum.length; i++) {
                row[i] = Integer.parseInt(rowNum[i]);
            }
            matrixList.add(row); // Adds each generated row into the matrix until (hence the while loop) every row is read from file
        }

        int[][] matrixMult = new int[matrixList.size()][]; // ArrayList is converted into a two dimensional array so that the matrices can be multiplied
        for (int i = 0; i < matrixList.size(); i++) {
            matrixMult[i] = matrixList.get(i);
        }
        return matrixMult; // Result matrix is returned so that it can be saved to file
    }
}