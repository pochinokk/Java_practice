package practice4;

public class TestExtendExecutorService {
    public static void main(String[] args) {
    ExtendExecutorService es = new ExtendExecutorService(5);
    for(int i = 0; i < 3; i++)
    {
        es.execute(new MyThread("Поток A"));
        es.execute(new MyThread("Поток B"));
        es.execute(new MyThread("Поток C"));
        es.execute(new MyThread("Поток D"));
        es.execute(new MyThread("Поток E"));
    }
    es.shutdown();
    }
}
