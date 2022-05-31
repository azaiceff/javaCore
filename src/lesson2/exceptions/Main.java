package lesson2.exceptions;

import java.util.Random;

public class Main {
    private final static Random random = new Random();

    public static void main(String[] args) {
        int count = 0;
        String[][][][] ar = getStrArr();
        System.out.println(("Всего двумерных массивов: "
                + ar.length * ar[ar.length - 1].length + " шт.\n").toUpperCase());

        for (String[][][] arr: ar) {
            for (String[][] array: arr) {
                System.out.println("Массив № " + ++count);
                System.out.println("Сумма всех чисел массива: " + sumArray(array));
                System.out.println();
            }
        }
    }
    private static int sumArray(String[][] strArr){
        if (strArr.length != 4 ){
            try {
                throw new RuntimeException();
            } catch (RuntimeException e) {
                System.out.println("Массив не того размера [" + strArr.length + "][]");
                return 0;
            }
        }
        int sum = 0;
        for (int i = 0; i < strArr.length ; i++) {
            if (strArr[i].length != 4) {
                try {
                    throw new RuntimeException();
                } catch (RuntimeException e) {
                    System.out.println("Массив не того размера [4][" + strArr[i].length + "]");
                    return 0;
                }
            }
            for (int j = 0; j < strArr[i].length; j++) {
                try {
                    if (strArr[i][j] == null){
                        throw new NullPointerException();
                    }
                    sum += Integer.parseInt(strArr[i][j]);
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException Array[" + i + "][" + j
                            + "] = " + strArr[i][j]);
                } catch (NullPointerException e) {
                    System.out.println("NullPointerException Array[" + i + "][" + j
                            + "] = " + strArr[i][j]);
                }
            }
        }
        return sum;
    }
    private static String[][][][] getStrArr(){
        String[][][][] arr = new String[getRandomInt()][getRandomInt()][][];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                String [][] array = new String[getLen()][getLen()];
                arr[i][j] = array;
                for (int k = 0; k < array.length; k++) {
                    for (int l = 0; l < array[k].length; l++) {
                        if(random.nextInt(10) == 0){
                            arr[i][j][k][l] = getRandomInt() + String.valueOf(getRandomChar()) + getRandomInt();
                        }else if(random.nextInt(10) == 0){
                            arr[i][j][k][l] = null;
                        }else arr[i][j][k][l] = String.valueOf(random.nextInt(40));
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
        if(random.nextInt(6) == 0){
            len = 5;
        }
        return len;
    }
}
