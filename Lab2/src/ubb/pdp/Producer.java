package ubb.pdp;

import java.util.List;

public class Producer extends Thread {
    public Buffer buffer;
    public List<Integer> v1, v2;

    public Producer(Buffer buffer, List<Integer> v1, List<Integer> v2) {
        super("Producer");
        this.buffer = buffer;
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public void run() {
        for (int i = 0; i < v1.size(); ++i) {
            try {
                System.out.printf("Producer: Sending %d * %d = %d\n",v1.get(i),v2.get(i),v1.get(i)*v2.get(i));
                buffer.put(v1.get(i)*v2.get(i));
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
