package add_task;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static final Random random = new Random();
    private static final int[][] arr = {
            {2, 4, 3, 4},
            {1, 2, 5, 3},
            {3, 1, 6, 1},
            {1, 3, 4, 5}
    };

    public static void main(String[] args) {

        printArray(arr);
        for (int i = 0; i < 5; i++) {
            System.out.println(summary(arr, random.nextInt(arr.length), random.nextInt(arr.length)));
        }

        for (int[][] intArray : getIntegerArrays()) {
            printArray(intArray);
            System.out.println(summary(intArray, random.nextInt(intArray.length), random.nextInt(intArray[0].length)));
        }
    }

    private static int summary(int[][] intArray, int a, int b) {
        System.out.println("i = " + a + ", j = " + b + " элемент: " + intArray[a][b]);
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            int c = a - 1 + i;
            c = (c + intArray.length) % intArray.length;
            for (int j = 0; j < 3; j++) {
                int d = b - 1 + j;
                d = (d + intArray[i].length) % intArray[i].length;
                //if (c == a && d == b) continue;
                sum += intArray[c][d];
            }
        }
        return sum - intArray[a][b];
    }

    private static int[][][] getIntegerArrays() {
        int[][][] arr = new int[10][][];
        for (int i = 0; i < arr.length; i++) {
            int[][] ar = new int[random.nextInt(4) + 3][random.nextInt(4) + 3];
            for (int j = 0; j < ar.length; j++) {
                for (int k = 0; k < ar[j].length; k++) {
                    ar[j][k] = random.nextInt(5);
                    arr[i] = ar;
                }
            }
        }
        return arr;
    }

    private static void printArray(int[][] intArray) {
        for (int[] i : intArray) {
            System.out.print(Arrays.toString(i));
            System.out.println();
        }
    }
}
