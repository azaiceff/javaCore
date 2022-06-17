package lesson3.first_lesson3;

import lesson3.first_lesson3.fruit.Apple;
import lesson3.first_lesson3.fruit.Fruit;
import lesson3.first_lesson3.fruit.Orange;
import lesson3.first_lesson3.fruit.Tomato;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Organizer {
    private static final Random random = new Random();
    private static Box[] BOXES = new Box<?>[getRandomInt(10,3)];
    public static void setBOXES(Box[] BOXES) {
        Organizer.BOXES = BOXES;
    }
    static void creatingBoxes(){
        for (int i = 0; i < BOXES.length; i++) {
            BOXES[i] = new Box<>(creatingFruitList());
            System.out.println(BOXES[i]);
        }
    }
    private static ArrayList<? extends Fruit> creatingFruitList(){
        if(random.nextBoolean()) {
            ArrayList<Apple> fruitList = new ArrayList<>();
            for (int i = 0; i < getRandomInt(10, 5); i++) {
                fruitList.add(new Apple(getRandomDouble()));
            }
            return fruitList;
        }else if (random.nextBoolean()) {
            ArrayList<Orange> fruitList = new ArrayList<>();
            for (int i = 0; i < getRandomInt(10,5); i++) {
                fruitList.add(new Orange(getRandomDouble()));
            }
            return fruitList;
        }else{
            ArrayList<Tomato> fruitList = new ArrayList<>();
            for (int i = 0; i < getRandomInt(10,5); i++) {
                fruitList.add(new Tomato(getRandomDouble()));
            }
            return fruitList;
        }
    }
    static void swappingElementsArray(){
        Integer[] array = new Integer[getRandomInt(10,5)];
        for (int i = 0; i < array.length; i++) {
            array[i] = getRandomInt(20);
        }
        int firstElement = getRandomInt(array.length);
        int secondElement = getRandomInt(array.length);
        while (firstElement == secondElement){
            secondElement = getRandomInt(array.length);
        }
        System.out.println(Arrays.toString(array));
        System.out.println("Меняем местами " + (firstElement + 1) + "-й и " + (secondElement + 1) + "-й элементы массива");
        swappingElementsArray(array, firstElement,secondElement);
        System.out.println(Arrays.toString(array));
    }
    private static void swappingElementsArray(Object[] arr, int firstElement, int secondElement) {
        Object obj = arr[firstElement];
        arr[firstElement] = arr[secondElement];
        arr[secondElement] = obj;
    }
    private static Double getRandomDouble(){
        return Math.floor( random.nextDouble(10) * 100)/100 ;
    }
    private static int getRandomInt(int a,int b){
        return random.nextInt(a) + b;
    }
    private static int getRandomInt(int a){
        return getRandomInt(a,0);
    }
    public static Box[] getBoxes(){
        return BOXES;
    }
}
