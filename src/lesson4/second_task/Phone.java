package lesson4.second_task;

import java.util.Random;

public class Phone {
    private static final Random random = new Random();

    public static String getRandomPhone() {
        int num1;
        int num2;
        int num3;
        int set2;
        int set3;
        num1 = random.nextInt(7) + 1;
        num2 = random.nextInt(8);
        num3 = random.nextInt(8);
        set2 = random.nextInt(643) + 100;
        set3 = random.nextInt(8999) + 1000;
        return "(" + num1 + "" + num2 + "" + num3 + ")" + "-" + set2 + "-" + set3;
    }

    private Phone() {
        throw new IllegalStateException("Phone class");
    }
}
