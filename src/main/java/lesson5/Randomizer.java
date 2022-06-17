package lesson5;

import java.util.Random;

class Randomizer {
    private static final Random random = new Random();

    static AppData getNewAppData() {
        int[][] data = getIntegerArray();
        return new AppData(getStrings(data), data);
    }

    private static String[] getStrings(int[][] intArray) {
        StringBuilder str = new StringBuilder();
        String[] arrayString = new String[intArray[0].length];
        for (int i = 0; i < arrayString.length; i++) {
            if (i == 0) {
                str.append("id");
            } else str.append("Value ").append(i);
            arrayString[i] = String.valueOf(str);
            str = new StringBuilder();
        }
        return arrayString;
    }

    private static int[][] getIntegerArray() {
        int[][] data = new int[getRandomInt(20, 10)][getRandomInt(3, 3)];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (j == 0) {
                    data[i][j] = i + 1;
                } else {
                    data[i][j] = getRandomInt(9, 1) * 100;
                }
            }
        }
        return data;
    }

    private static int getRandomInt(int a, int b) {
        return random.nextInt(a) + b;
    }
}
