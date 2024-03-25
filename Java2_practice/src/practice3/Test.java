package practice3;


import java.util.concurrent.Semaphore;

public class Test{
static volatile int buf;
        private static final Semaphore semaphore = new Semaphore(2);

        static void increment() {
                try {
                        semaphore.acquire();
                        buf++;
                        semaphore.release();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
        }

public static void main(String[] args) throws Exception{

        buf=0;
        Thread one=new Thread(()->{
        for(int i=0;i< 5000;i++){
        increment();
        }
        });
        Thread two=new Thread(()->{
        for(int i=0;i< 5000;i++){
        increment();
        }
        });
        one.start();
        two.start();
        Thread.sleep(3000);
        System.out.println(buf);
}
}