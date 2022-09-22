import java.util.Arrays;

public class Lab_MatrixMul {
    public static void main(String[] args) {
        int[][] inputA = { { 5, 6, 7 },
                { 4, 8, 9 } };
        int[][] inputB = { { 6, 4 },
                { 5, 7 },
                { 1, 1 } };
        MyData matA = new MyData(inputA);
        MyData matB = new MyData(inputB);
        int matC_r = matA.data.length;
        int matC_c = matB.data[0].length;
        MyData matC = new MyData(matC_r, matC_c);

        // Q4 construct 2D array for each "thread" with respected to each cell in
        // matC,then start each thread
        Thread t = new Thread(new MatrixMulThread(matC_r, matC_c, matA, matB, matC));
        t.start();
        // Q5 join each thread
        try {
            t.join();
        } catch (Exception e) {
            System.out.println(e);
        }
        matC.show();
    }
}

class MatrixMulThread implements Runnable {
    int processing_row;
    int processing_col;
    MyData datA;
    MyData datB;
    MyData datC;

    MatrixMulThread(int tRow, int tCol, MyData a, MyData b, MyData c) {
        // Q1 code here
        processing_row = tRow;
        processing_col = tCol;
        datA = a;
        datB = b;
        datC = c;
    }

    /* Q2 */
    public void run() {
        // Q3

        for (int i = 0; i < datA.data.length; i++) {
            for (int j = 0; j < datB.data[0].length; j++) {
                datC.data[i][j] = 0;
                for (int k = 0; k < datB.data.length; k++) {

                    datC.data[i][j] += datA.data[i][k] * datB.data[k][j];
                }
            }
        }
        // System.out.println("perform sum of multiplication of assigned row and col");
    }
} // class

class MyData {
    int[][] data;

    MyData(int[][] m) {
        data = m;
    }

    MyData(int r, int c) {
        data = new int[r][c];
        for (int[] aRow : data)
            Arrays.fill(aRow, 9);
        // 9 will be overwritten anyway
    }

    void show() {
        System.out.println(Arrays.deepToString(data));
    }
}// class

