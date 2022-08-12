package lesson4.first_task;

import java.util.*;

public class FirstTask {
    private static final Random random = new Random();

    public static void firstTask(String[] words) {
        StringBuilder str = new StringBuilder();
        for (String set : returnSetString(words)) {
            int count = 0;
            for (String word : words) {
                if (set.equalsIgnoreCase(word)) {
                    count++;
                }
            }
            str.append(set).append("-").append(count).append("шт. ");
        }
        str.append("\n*******************\n");
        System.out.print(str);
    }

    private static Set<String> returnSetString(String[] strArr) {
        return new HashSet<>(Arrays.asList(strArr));
    }

    public static String[][] getStringArray() {
        String[][] str = new String[getRandomInt(5, 2)][];
        for (int i = 0; i < str.length; i++) {
            String[] stringArray = new String[getRandomInt(20, 10)];
            str[i] = stringArray;
            for (int j = 0; j < stringArray.length; j++) {
                stringArray[j] = String.valueOf(Words.values()[getRandomInt(Words.values().length, 0)]);
            }
        }
        return str;
    }

    private static int getRandomInt(int a, int b) {
        return random.nextInt(a) + b;
    }

    private FirstTask() {
        throw new IllegalStateException("FirstTask class");
    }
}
