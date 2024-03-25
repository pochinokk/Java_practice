package practice2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestHumanList {
    public static void main(String[] args) {
        List<Human> list = new ArrayList<>();
        list.add(new Human ("Иван","Иванов", LocalDate.of(2004,11,24),56));
        list.add(new Human ("Петр","Петров", LocalDate.of(1995,3,15),60));
        list.add(new Human ("Николай","Николаев", LocalDate.of(2003,8,9),48));
        list.add(new Human ("Василий","Васильев", LocalDate.of(2010,12,16),45));
        list.add(new Human ("Алексей","Алексеев", LocalDate.of(1984,6,3),78));

        System.out.println("Список");
        System.out.println(list.toString().replace("]", "\n]"));

        List<Human> list2 = list.stream()
                .filter(h -> h.getAge() > 20)
                .collect(Collectors.toList());
        System.out.println("\nФильтрация по возрасту: больше чем 20");
        System.out.println(list2.toString().replace("]", "\n]"));

        list2 = list.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("\nСортировка по последней букве имени");
        System.out.println(list2.toString().replace("]", "\n]"));

        list2 = list.stream()
                .map(h -> {
                    h.setBirthDate(h.getBirthDate().withYear(h.getBirthDate().getYear()-3));
                    return h;
                })
                .collect(Collectors.toList());
        System.out.println("\nУвеличение возраста каждого на 3");
        System.out.println(list2.toString().replace("]", "\n]"));

        double averageAge = list.stream()
                .mapToInt(Human::getAge)
                .average()
                .orElse(0);
        System.out.println("\nСредний возраст всех людей в списке: " + averageAge);
    }
}
