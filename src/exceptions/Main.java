package exceptions;

import java.util.Random;

public class Main {
    private final static Random random = new Random();

    public static void main(String[] args) {

        System.out.println("Сумма массива: " + sumArray(getStrArr()));
    }
    private static int sumArray(String[][] strArr){
        if (strArr.length != 4 ){
            try {
                throw new RuntimeException();
            } catch (RuntimeException e) {
                System.out.println("Массив не того размера");
                return 0;
            }
        }
        int sum = 0;
        for (String[] arr : strArr) {
            if(arr.length != 4){
                try {
                    throw new RuntimeException();
                } catch (RuntimeException e) {
                    System.out.println("Массив не того размера");
                    return 0;
                }
            }
            for (String str: arr) {
                try {
                    sum += Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    System.out.println("Hello World!");
                }
            }
        }
        return sum;
    }
    private static String[][] getStrArr(){
        int len = 4;
        if(random.nextBoolean()){
            len = 5;
        }
        String[][] arr = new String[4][len];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(random.nextInt(10) == 0){
                    arr[i][j] = "Hello World!";
                }else arr[i][j] = String.valueOf(random.nextInt(20));
            }
        }
        return arr;
    }
}
