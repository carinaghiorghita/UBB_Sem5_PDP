package ubb.pdp.Thread;

import ubb.pdp.Model.Matrix;

import java.util.AbstractMap;

public class RowTask extends MatrixTask{
    public RowTask(int startRow, int startColumn, int elementCount, Matrix A, Matrix B, Matrix C) {
        super(startRow, startColumn, elementCount, A, B, C);
    }

    @Override
    protected void computeElements() {
        int i = startRow, j = startColumn;
        int count = elementCount;
        while (count > 0 && i < C.rows() && j < C.columns()) {
            elements.add(new AbstractMap.SimpleEntry<>(i, j));
            j++;
            count--;
            if (j == C.rows()) {
                j = 0;
                i++;
            }
        }
    }
}
