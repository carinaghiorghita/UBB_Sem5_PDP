package ubb.pdp.Thread;

import ubb.pdp.Model.Matrix;

import java.util.AbstractMap;

public class KTask extends MatrixTask{
    public KTask(int startRow, int startColumn, int elementCount, int K, Matrix A, Matrix B, Matrix C) {
        super(startRow, startColumn, elementCount, K, A, B, C);
    }

    @Override
    protected void computeElements() {
        int i = startRow, j = startColumn;
        int count = elementCount;
        while (count > 0 && i < C.rows() && j < C.columns()) {
            elements.add(new AbstractMap.SimpleEntry<>(i, j));
            count--;
            i += (j + K) / C.columns();
            j = (j + K) % C.columns();
        }
    }
}
