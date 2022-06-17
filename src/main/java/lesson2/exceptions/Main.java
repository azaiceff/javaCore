package lesson2.exceptions;

import java.util.Random;

public class Main {
    private static final Random random = new Random();
    private static final int SIZE_ARRAY = 4;
    public static void main(String[] args) {

        String[][][][] stringArray = getStrArr();
        System.out.println(("Всего двумерных массивов: "
                + stringArray.length * stringArray[stringArray.length - 1].length + " шт.\n").toUpperCase());
        System.out.println("Без возможности посчитать сумму в случае: \nMyArraySizeException | MyArrayDataException e");
        for (String[][][] arr : stringArray) {
            for (String[][] array : arr) {
                System.out.println();
                try {
                    System.out.println("Сумма всех чисел массива: " + sumArrayTS(array) + "\nПоехали дальше!");
                } catch (MyArraySizeException | MyArrayDataException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("\nВ случае MyArrayDataException, расчет суммы не прерывается");
        int count = 0;
        for (String[][][] arr: stringArray) {
            for (String[][] array: arr) {
                System.out.println("Массив № " + ++count);
                try {
                    System.out.println("Сумма всех чисел массива: " + sumArray(array) + "\nПоехали дальше!");
                } catch (MyArraySizeException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Обработали - поехали дальше)");
                }
                System.out.println();
            }
        }
        System.out.println("А не, все - стоп! Массивы закончились))\nВсего проверили массивов: " + count + "шт. " +
                " (это был ручной подсчет) :)");
    }
    private static int sumArray(String[][] strArr) throws MyArraySizeException{
        checkSizeArray(strArr);
        int sum = 0;
        for (int i = 0; i < strArr.length ; i++) {
            for (int j = 0; j < strArr[i].length; j++) {
                try {
                    checkNull(strArr[i][j]);
                    sum += Integer.parseInt(strArr[i][j]);
                } catch (NumberFormatException e) {
                    try {
                        throw new MyArrayDataException("Элемент массива [" + i + "][" + j + "] = "  + strArr[i][j] +
                                " невозможно привести к числу");
                    } catch (MyArrayDataException ex) {
                        System.out.println(ex.getMessage());
                    }
                } catch (NullPointerException e) {
                    System.out.println("NullPointerException. Элемент массива [" + i + "][" + j
                            + "] = " + strArr[i][j]);
                }
            }
        }
        return sum;
    }
    private static int sumArrayTS(String[][] strArr) throws MyArraySizeException, MyArrayDataException{
        checkSizeArray(strArr);
        int sum = 0;
        for (int i = 0; i < strArr.length ; i++) {
            for (int j = 0; j < strArr[i].length; j++) {
                try {
                    sum += Integer.parseInt(strArr[i][j]);
                } catch (NumberFormatException e) {
                        throw new MyArrayDataException("Элемент массива [" + i + "][" + j + "] = "  + strArr[i][j] +
                                " невозможно привести к числу");
                }
            }
        }
        return sum;
    }
    private static void checkSizeArray(String[][] array) throws MyArraySizeException{
        System.out.println("Размер: " + array.length + " х []");
        if (array.length != SIZE_ARRAY){
            throw new MyArraySizeException("Массив не того размера ["
                    + array.length + "][" + array[0].length + "]");
        }else {
            for (String[] strings : array) {
                if (strings.length != SIZE_ARRAY) {
                    throw new MyArraySizeException("Одномерный массив не того размера ["
                            + strings.length + "]" );
                }
            }
        }
    }
    private static void checkNull(String str){
        if(str == null){
            throw new NullPointerException();
        }
    }
    private static String[][][][] getStrArr(){
        String[][][][] arr = new String[getRandomInt()][getRandomInt()][][];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                String [][] array = new String[getLen()][];
                arr[i][j] = array;
                for (int k = 0; k < array.length; k++) {
                    String[] ar = new String[getLen()];
                    array[k] = ar;
                    for (int l = 0; l < ar.length; l++) {
                        if(random.nextInt(20) == 0){
                            arr[i][j][k][l] = String.valueOf(getRandomInt()) + getRandomChar() + getRandomInt();
                        }else if (random.nextInt(20) != 0) {
                            arr[i][j][k][l] = String.valueOf(random.nextInt(40));
                        }
                    }
                }
            }
        }
        return arr;
    }
    private static char getRandomChar(){
        return  (char) Character.toUpperCase(random.nextInt(26) + 'a');
    }
    private static int getRandomInt(){
        return random.nextInt(10) + 2;
    }
    private static int getLen(){
        int len = 4;
        if(random.nextInt(10) == 0){
            len = random.nextInt(3) + 3;
        }
        return len;
    }
}
