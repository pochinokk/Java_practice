package practice3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreList<T> {
    private final List<T> list = new ArrayList<>();
    private final Semaphore semaphore = new Semaphore(1);
    public int size() {
        return list.size();
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    public boolean add(T t) {
        try {
            semaphore.acquire();
            boolean result = list.add(t);
            return result;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            semaphore.release();
        }
    }

    public boolean contains(Object o) {
        try {
            semaphore.acquire();
            boolean result = list.contains(o);
            return result;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            semaphore.release();
        }
    }
    public boolean remove(Object o) {
        try {
            semaphore.acquire();
            boolean result = list.remove(o);
            return result;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            semaphore.release();
        }
    }

    public void clear() {
        try {
            semaphore.acquire();
            list.clear();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }

    public T get(int index) {
        try {
            semaphore.acquire();
            T element = list.get(index);
            return element;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            semaphore.release();
        }
    }

}
