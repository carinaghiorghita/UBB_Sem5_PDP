package ubb.pdp;

import ubb.pdp.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int rowsA = 1000;
    private static final int rowsB = 1000;
    private static final int colsA = 1000;
    private static final int colsB = 1000;
    private static final int nrThreads = 3;
    private static final ThreadApproach THREAD_APPROACH = ThreadApproach.THREAD_POOL;
    private static final GenerationStrategy GENERATION_STRATEGY = GenerationStrategy.KTH;

    public static void main(String[] args) {
	    Matrix A = new Matrix(rowsA,colsA);
        Matrix B = new Matrix(rowsB,colsB);

//        System.out.println("Matrix A:");
//        System.out.println(A);
//        System.out.println("Matrix B:");
//        System.out.println(B);

        if (colsA == rowsB){
            Matrix C = new Matrix(rowsA, colsB);
            System.out.println("Start calculating matrix");
            double startTime = System.nanoTime();

            switch (THREAD_APPROACH) {
                case CLASSIC -> classicThreadsDriver(A, B, C);
                case THREAD_POOL -> threadPoolDriver(A, B, C);
                default -> System.out.println("Invalid thread approach");
            }

            double stopTime = System.nanoTime();
            System.out.println("Stop calculating matrix");
            double totalTime = (stopTime - startTime) / 1_000_000_000.0;
            System.out.println("Elapsed running time: " + totalTime + "s");
        }
        else {
            System.out.println("Cannot multiply matrices - make sure the number of columns in A and the number of rows in B is the same.");
        }
    }

    private static void classicThreadsDriver(Matrix A, Matrix B, Matrix C){
        List<Thread> threads = new ArrayList<>();

        switch (GENERATION_STRATEGY){
            case ROWS:
                for(int i=0;i<nrThreads;++i) {
                    threads.add(Utils.initRowTask(i,A,B,C,nrThreads));
                }
                break;
            case COLUMNS:
                for(int i=0;i<nrThreads;++i) {
                    threads.add(Utils.initColumnTask(i,A,B,C,nrThreads));
                }
                break;
            case KTH:
                for(int i=0;i<nrThreads;++i) {
                    threads.add(Utils.initKTask(i,A,B,C,nrThreads));
                }
                break;
            default:
                System.out.println("Wrong matrix generation strategy");
                break;
        }

        for(Thread thread : threads){
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Matrix C:");
        System.out.println(C.toString());

    }

    private static void threadPoolDriver(Matrix A, Matrix B, Matrix C){
        ExecutorService executorService = new ThreadPoolExecutor(
                nrThreads,
                nrThreads,
                0L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(nrThreads, true)
        );

        switch (GENERATION_STRATEGY){
            case ROWS:
                for(int i=0;i<nrThreads;++i) {
                    executorService.submit(Utils.initRowTask(i,A,B,C,nrThreads));
                }
                break;
            case COLUMNS:
                for(int i=0;i<nrThreads;++i) {
                    executorService.submit(Utils.initColumnTask(i,A,B,C,nrThreads));
                }
                break;
            case KTH:
                for(int i=0;i<nrThreads;++i) {
                    executorService.submit(Utils.initKTask(i,A,B,C,nrThreads));
                }
                break;
            default:
                System.out.println("Wrong matrix generation strategy");
                break;
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(300, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
            System.out.println("Matrix C:");
            System.out.println(C.toString());
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
