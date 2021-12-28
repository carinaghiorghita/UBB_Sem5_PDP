package ubb.pdp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private static final int CAPACITY = 2;
    private final Queue<Integer> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition cond = lock.newCondition();

    public Buffer() {
    }

    public void put(int val) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == CAPACITY) {
                System.out.println(Thread.currentThread().getName() + ": Queue is currently full");
                cond.await();
            }

            queue.add(val);
            System.out.printf("%s added %d into the queue %n", Thread.currentThread().getName(), val);

            cond.signal();

        } finally {
            lock.unlock();
        }
    }

    public int get() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == 0) {
                System.out.println(Thread.currentThread().getName() + ": Buffer is currently empty");
                cond.await();
            }

            Integer value = queue.poll();
            if (value != null) {
                System.out.printf("%s extracted value %d from the queue \n", Thread.currentThread().getName(), value);

                cond.signal();
            }
            return value;
        } finally {
            lock.unlock();
        }
    }
}
