package ubb.pdp.Thread;

import ubb.pdp.Model.Matrix;

import java.util.AbstractMap;

public class ColumnTask extends MatrixTask{
    public ColumnTask(int startRow, int startColumn, int elementCount, Matrix A, Matrix B, Matrix C) {
        super(startRow, startColumn, elementCount, A, B, C);
    }

    @Override
    protected void computeElements() {
        int i = startRow, j = startColumn;
        int count = elementCount;
        while (count > 0 && i < C.rows() && j < C.columns()) {
            elements.add(new AbstractMap.SimpleEntry<>(i, j));
            i++;
            count--;
            if (i == C.columns()) {
                i = 0;
                j++;
            }
        }
    }

}
