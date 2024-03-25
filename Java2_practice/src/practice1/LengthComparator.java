package practice1;
import java.util.Comparator;

public class LengthComparator {
    public static void main(String[] args) {
        Comparator<Object[]> comp = Comparator.comparingInt(a -> a.length);

        String[] str1 = {"apple", "orange", "banana"};
        String[] str2 = {"orange", "pear", "grape", "kiwi", "melon" };

        Double[] dbl1 = {1.6, 2.1, -3.3, 4.98};
        Double[] dbl2 = {-4.32, 5.6};

        Integer[] int1 = {1, 0, -2};
        Integer[] int2 = {8, -4, 100};

        if(comp.compare(str1, str2) == 1)
            System.out.println("Первый массив длиннее");
        else if (comp.compare(str1, str2) == 0)
            System.out.println("Длины массивов равны");
        else
            System.out.println("Второй массив длиннее");


        if(comp.compare(dbl1, dbl2) == 1)
            System.out.println("Первый массив длиннее");
        else if (comp.compare(dbl1, dbl2) == 0)
            System.out.println("Длины массивов равны");
        else
            System.out.println("Второй массив длиннее");

        if(comp.compare(int1, int2) == 1)
            System.out.println("Первый массив длиннее");
        else if (comp.compare(int1, int2) == 0)
            System.out.println("Длины массивов равны");
        else
            System.out.println("Второй массив длиннее");
    }

}
