package ubb.pdp;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> v1 = new ArrayList<>();
        v1.add(1);
        v1.add(2);
        v1.add(3);
        v1.add(4);
        v1.add(5);

        ArrayList<Integer> v2 = new ArrayList<>();
        v2.add(2);
        v2.add(2);
        v2.add(2);
        v2.add(2);
        v2.add(2);

        Buffer buffer = new Buffer();
        Producer producer = new Producer(buffer,v1,v2);
        Consumer consumer = new Consumer(buffer,v1.size());

        producer.start();
        consumer.start();
    }
}
