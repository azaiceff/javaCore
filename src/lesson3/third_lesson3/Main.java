package lesson3.third_lesson3;

import lesson3.third_lesson3.fruit.Apple;
import lesson3.third_lesson3.fruit.Orange;

import java.util.ArrayList;
import java.util.Random;

import static lesson3.third_lesson3.FirstTask.swappingElementsArrays;

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Задание №1");
        swappingElementsArrays();
        System.out.println("Задание №2");
        Box<Apple> appleBox1 = new Box<>(new ArrayList<>());//Для хранения фруктов внутри коробки можно использовать ArrayList;
        for (int i = 0; i < random.nextInt(3) + 2; i++) {
            addApple(appleBox1);
        }
        Box<Apple> appleBox2 = new Box<>(new ArrayList<>());
        for (int i = 0; i < random.nextInt(3) + 2; i++) {
            addApple(appleBox2);
        }
        Box<Orange> orangeBox1 = new Box<>(new ArrayList<>());
        for (int i = 0; i < random.nextInt(3) + 2; i++) {
            addOrange(orangeBox1);
        }
        Box<Orange> orangeBox2 = new Box<>(new ArrayList<>());
        for (int i = 0; i < random.nextInt(3) + 2; i++) {
            //   orangeBox2.boxFruit().add(new Apple());// не компилируется - джинерики не дают
            //   addApple(orangeBox2);// - также не работает
            orangeBox2.boxFruit().add(new Orange());// а так все хорошо
        }
        System.out.println(appleBox1 + " вес: " + appleBox1.getWeight());
        System.out.println(appleBox2 + " вес: " + appleBox2.getWeight());
        System.out.println(appleBox1.compare(appleBox2));
        System.out.println(appleBox1 + " вес: " + appleBox1.getWeight());
        System.out.println(orangeBox1 + " вес: " + orangeBox1.getWeight());
        System.out.println(appleBox1.compare(orangeBox1));
        System.out.println(appleBox1 + " -> " + appleBox2);
        appleBox1.pourOver(appleBox2); // работает
        System.out.println(appleBox1 + " -> " + appleBox2);
        //appleBox3.pourOver(orangeBox2);// сделать не получится, джинерики не дают этого сделать
        System.out.println(orangeBox1 + " -> " + orangeBox2);
        orangeBox1.pourOver(orangeBox2); //работает
        System.out.println(orangeBox1 + " -> " + orangeBox2);
    }

    //Не забываем про метод добавления фрукта в коробку.
    private static void addApple(Box<Apple> box) {
        box.boxFruit().add(new Apple());
    }

    private static void addOrange(Box<Orange> box) {
        box.boxFruit().add(new Orange());
    }
}
