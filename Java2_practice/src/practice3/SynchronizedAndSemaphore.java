package practice3;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SynchronizedAndSemaphore {
    private final List<Integer> list = new ArrayList<>();
    private final Semaphore semaphore = new Semaphore(1);

    public void add(int value) throws InterruptedException {
        semaphore.acquire();
        list.add(value);
        semaphore.release();
    }

    public int get(int index) throws InterruptedException {
        semaphore.acquire();
        int value = list.get(index);
        semaphore.release();
        return value;
    }
}