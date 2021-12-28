package ubb.pdp.Thread;

import jdk.jshell.execution.Util;
import ubb.pdp.Model.Matrix;
import ubb.pdp.Model.Utils;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public abstract class MatrixTask extends Thread{
    protected final List<AbstractMap.SimpleEntry<Integer, Integer>> elements;
    protected final int startRow, startColumn, elementCount;
    protected final Matrix A, B, C;
    protected int K;

    public MatrixTask (int startRow, int startColumn, int elementCount, Matrix A, Matrix B, Matrix C){
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.elementCount = elementCount;
        this.A = A;
        this.B = B;
        this.C = C;
        this.elements = new ArrayList<>();
        computeElements();
    }

    public MatrixTask (int startRow, int startColumn, int elementCount, int K, Matrix A, Matrix B, Matrix C){
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.elementCount = elementCount;
        this.K = K;
        this.A = A;
        this.B = B;
        this.C = C;
        this.elements = new ArrayList<>();
        computeElements();
    }

    protected abstract void computeElements();

    @Override
    public void run() {
        for (AbstractMap.SimpleEntry<Integer, Integer> element : elements) {
            int row = element.getKey();
            int column = element.getValue();
            C.set(row, column, Utils.computeMatrixElement(A, B, row, column));
        }
    }
}
