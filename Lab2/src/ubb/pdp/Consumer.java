package ubb.pdp;

public class Consumer extends Thread{
    private Buffer buffer;
    private Integer length;
    private Integer sum;

    public Consumer(Buffer buffer, Integer length){
        super("Consumer");
        this.buffer = buffer;
        this.length = length;
        this.sum = 0;
    }

    @Override
    public void run() {
        for(int i=0; i<length; ++i){
            try {
                sum+= buffer.get();
                System.out.printf("Consumer: Sum is %d\n",sum);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("Final sum is %d\n",sum);
    }
}
