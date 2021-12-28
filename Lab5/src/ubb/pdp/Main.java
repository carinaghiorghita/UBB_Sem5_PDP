package ubb.pdp;

import ubb.pdp.Algorithms.ParallelClassic;
import ubb.pdp.Algorithms.ParallelKaratsuba;
import ubb.pdp.Algorithms.SequentialClassic;
import ubb.pdp.Algorithms.SequentialKaratsuba;
import ubb.pdp.Model.AlgorithmChoice;
import ubb.pdp.Model.MethodChoice;
import ubb.pdp.Model.Polynomial;

import java.util.concurrent.ExecutionException;

import static ubb.pdp.Model.AlgorithmChoice.*;
import static ubb.pdp.Model.MethodChoice.*;

public class Main {
    private static final MethodChoice METHOD = PARALLEL;
    private static final AlgorithmChoice ALGORITHM = KARATSUBA;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Polynomial p1 = new Polynomial(5);
        System.out.println("p1=" + p1);
        Polynomial p2 = new Polynomial(5);
        System.out.println("p2=" + p2);
        long startTime = System.nanoTime();
        run(p1, p2);
        long stopTime = System.nanoTime();
        double totalTime = ((double) stopTime - (double) startTime) / 1_000_000_000.0;
        System.out.println("Elapsed running time: " + totalTime + "s");
    }

    private static void run(Polynomial polynomial1, Polynomial polynomial2) throws ExecutionException, InterruptedException {
        Polynomial result;
        if (METHOD.equals(SEQUENTIAL)) {
            if (ALGORITHM.equals(AlgorithmChoice.CLASSIC)) {
                result = SequentialClassic.multiply(polynomial1, polynomial2);
            } else {
                result = SequentialKaratsuba.multiply(polynomial1, polynomial2);
            }
        } else {
            if (ALGORITHM.equals(AlgorithmChoice.CLASSIC)) {
                result = ParallelClassic.multiply(polynomial1, polynomial2);
            } else {
                result = ParallelKaratsuba.multiply(polynomial1, polynomial2, 1);
            }
        }
        System.out.println("p1*p2=" + result.toString());

    }
}
