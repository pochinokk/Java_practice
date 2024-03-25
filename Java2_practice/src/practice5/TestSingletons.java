package practice5;

public class TestSingletons {
    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.getInstance();
        System.out.println("singleton1: " + singleton1);

        Singleton2 singleton2 = Singleton2.getInstance();
        System.out.println("singleton2: " + singleton2);

        Singleton3 singleton3 = Singleton3.getInstance();
        System.out.println("singleton3: " + singleton3);

        Singleton4 singleton4 = Singleton4.getInstance();
        System.out.println("singleton4: " + singleton4);
    }
}
