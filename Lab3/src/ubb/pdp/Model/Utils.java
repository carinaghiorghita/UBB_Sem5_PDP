package ubb.pdp.Model;

import ubb.pdp.Thread.*;

public class Utils {
    public static MatrixTask initRowTask(int index, Matrix A, Matrix B, Matrix C, int nrThreads) {
        int sizeC = C.rows() * C.columns();
        int count = sizeC / nrThreads;

        int startRow = count * index / C.rows();
        int startCol = count * index % C.rows();

        if (index == nrThreads - 1)
            count += sizeC % nrThreads;

        return new RowTask(startRow, startCol, count, A, B, C);
    }

    public static MatrixTask initColumnTask(int index, Matrix A, Matrix B, Matrix C, int nrThreads) {
        int sizeC = C.rows() * C.columns();
        int count = sizeC / nrThreads;

        int startRow = count * index % C.rows();
        int startCol = count * index / C.rows();

        if (index == nrThreads - 1)
            count += sizeC % nrThreads;

        return new ColumnTask(startRow, startCol, count, A, B, C);
    }

    public static MatrixTask initKTask(int index, Matrix A, Matrix B, Matrix C, int nrThreads) {
        int sizeC = C.rows() * C.columns();
        int count = sizeC / nrThreads;

        if (index < sizeC % nrThreads)
            count++;

        int startRow = index / C.columns();
        int startCol = index % C.rows();

        return new KTask(startRow, startCol, count, nrThreads, A, B, C);
    }

    public static int computeMatrixElement(Matrix A, Matrix B, int row, int column) {
        int result = 0;
        if (row < A.rows() && column < B.columns()) {
            int i = 0;
            while (i < A.columns()) {
                result += A.get(row, i) * B.get(i, column);
                i++;
            }
            return result;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
