package practice6;

import java.util.Scanner;

public class TestPatterns {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Какой автомобиль нужно создать (0: легковой, 1: грузовик): ");
        int purpose = sc.nextInt();
        Automobile auto1 = new AutomobileFactory().createAutomobile(purpose);
        System.out.println(auto1.getName());

        AbstractSweetFactory factory1 = new SweetFactory1();
        AbstractSweetFactory factory2 = new SweetFactory2();

        System.out.println("Выберете продукт:");
        System.out.println("1: чизкейк");
        System.out.println("2: клубничные конфеты");
        System.out.println("3: шоколадный тор");
        System.out.println("4: шоколадные конфеты");
        int p = sc.nextInt();

        if (p == 1) {
            Cake cheesecake = factory1.createCake();
            System.out.println(cheesecake.getName());
        }
        else if (p == 2) {
            Sweets strawberry_sweets = factory1.createSweets();
            System.out.println(strawberry_sweets.getName());
        }
        else if (p == 3) {
            Cake chocolate_cake = factory2.createCake();
            System.out.println(chocolate_cake.getName());
        }
        else {
            Sweets chocolate_sweets = factory2.createSweets();
            System.out.println(chocolate_sweets.getName());
        }
        Book.builder();
        Book book = Book.builder()
                .name("Book")
                .author("John Johnson")
                .year(1895)
                .build();
        System.out.println(book);

        ConcretePrototype prototype = new ConcretePrototype();
        ConcretePrototype copy = (ConcretePrototype) prototype.clone();
        System.out.println(copy);

    }
}
