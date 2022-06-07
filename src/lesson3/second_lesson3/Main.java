package lesson3.second_lesson3;

import lesson3.second_lesson3.exception.MyException;
import lesson3.second_lesson3.action.*;

import static lesson3.second_lesson3.action.FirstTask.*;
import static lesson3.second_lesson3.action.Organizer.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание №1");
        swappingElementsArrays();
        System.out.println("Задание №2");
        System.out.println("Создаем коробки");
        creatingListBox();
        System.out.println("На склад приехало " + getFruitBoxes().size() + " коробок");
        for (Box box: getFruitBoxes()) {
            System.out.println(box);
        }
        System.out.println();
        System.out.println("Взвесим и сравним коробки по весу");
        int count = 0;
        for (int i = 0; i < getFruitBoxes().size(); i++) {
            int j = 1;
            for (j += i; j < getFruitBoxes().size(); j++) {
                if(getFruitBoxes().get(i).equals(getFruitBoxes().get(j))){//Использовал переопределение метода equals.
                    // В тз конечно сказано создать метод compare(), в предыдущем варианте решения так и сделал.
                    // Здесь просто пробую альтернативные варианты решения поставленной задачи.
                    count++;
                    System.out.println("Коробка для " + getFruitBoxes().get(i).getType() + " № "
                            + getFruitBoxes().get(i).getId()
                            + " равна по весу коробке для " + getFruitBoxes().get(j).getType() + " № "
                            + getFruitBoxes().get(j).getId());
                    System.out.println("Вес коробки " + getFruitBoxes().get(i).getWeightBox() + "кг "
                            + getFruitBoxes().get(i));
                    System.out.println("Вес коробки " + getFruitBoxes().get(j).getWeightBox() + "кг "
                            + getFruitBoxes().get(j));
                }
            }
        }
        if(count == 0) System.out.println("Мы не нашли равные по весу коробки");
        System.out.println();
        System.out.println("Пересыпем все одинаковые по типу коробки в одну");
        for (Box box: getFruitBoxes()) {
            for (Box secondBox: getFruitBoxes()) {
                try {
                    box.pourOver(secondBox);
                } catch (MyException e) {
                   //System.out.println(e.getMessage());
                    //Закомментировал вывод сообщений об исключениях, что-бы не загромождать консоль.
                    // Можно включить и визуально проследить как осуществлялся перебор вариантов и какие
                    // при этом вылетали ошибки)
                    // Пробовал так-же решить эту задачу с помощью if-else-if, тоже хорошо все получается, но с
                    // пробросом исключений, как-то интереснее получилось, оставил такой вариант.
                }
            }
        }
        System.out.println();
        System.out.println("Удалим пустые коробки");
        removeAllEmptyBox();
        System.out.println("Осталось " + getFruitBoxes().size() + " коробки");
        for (Box box: getFruitBoxes()) {
            System.out.println(box);
        }
    }
}
