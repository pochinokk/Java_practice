package practice3;

public class TestListAndMap {
    public static void main(String[] args) {
        SemaphoreList<Integer> list = new SemaphoreList<>();
        list.add(5);
        list.add(3);
        list.add(-10);
        System.out.println("Полученный элемент: " + list.get(1));
        System.out.println("Результат удаления: " + list.remove(5));
        SynchronizedMap<Integer, String> map = new SynchronizedMap();
        map.put(1, "Понедельник");
        map.put(2, "Вторник");
        System.out.println("Полученный элемент: " + map.get(2));
        System.out.println("Результат проверки вхождения: " + map.containsKey(4));
    }
}
