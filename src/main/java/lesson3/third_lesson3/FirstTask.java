package lesson3.third_lesson3;

import java.util.Arrays;
import java.util.Random;

public class FirstTask {
    private static final Random random = new Random();

    public static void swappingElementsArrays() {
        Integer[] intArray = getIntegerArray();
        Character[] charArray = getCharArray();
        swappingElementsArray(intArray);
        System.out.println();
        swappingElementsArray(charArray);
        System.out.println();
    }

    private static void swappingElementsArray(Object[] array) {
        System.out.println(Arrays.toString(array));
        int[] element = getRandomElements(array.length);
        System.out.println("Меняем местами " + (element[0] + 1) + "-й и " + (element[1] + 1) + "-й элементы данного массива");
        swappingElementsArray(array, element[0], element[1]);
        System.out.println("Результат работы метода:");
        System.out.println(Arrays.toString(array));
    }

    private static void swappingElementsArray(Object[] arr, int firstElement, int secondElement) {
        Object obj = arr[firstElement];
        arr[firstElement] = arr[secondElement];
        arr[secondElement] = obj;
    }

    private static int[] getRandomElements(int arrayLength) {
        int[] element = new int[2];
        element[0] = getRandomInt(arrayLength);
        element[1] = getRandomInt(arrayLength);
        while (element[0] == element[1]) {
            element[1] = getRandomInt(arrayLength);
        }
        return element;
    }

    private static Integer[] getIntegerArray() {
        Integer[] intArray = new Integer[getRandomInt(10, 5)];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = random.nextInt(50) + 10;
        }
        return intArray;
    }

    private static Character[] getCharArray() {
        Character[] charArray = new Character[getRandomInt(10, 5)];
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) Character.toUpperCase(getRandomInt(26) + 'a');
        }
        return charArray;
    }

    private static int getRandomInt(int a, int b) {
        return random.nextInt(a) + b;
    }

    private static int getRandomInt(int a) {
        return getRandomInt(a, 0);
    }

    private FirstTask() {
        throw new IllegalStateException("FirstTask class");
    }
}
