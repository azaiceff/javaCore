package lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static lesson4.first_task.FirstTask.*;
import static lesson4.second_task.PhoneBook.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание №1");
        for (String[] words : getStringArray()) {
            System.out.println(Arrays.toString(words));
            firstTask(words);
        }
        System.out.println("Задание №2");
        completionPhoneBook();
        System.out.println("Всего имен - " + getPhoneBook().entrySet().size());
        for (Map.Entry<String, ArrayList<String>> phone : getPhoneBook().entrySet()) {
            System.out.println(phone.toString());
        }
        System.out.println();
        System.out.println();
        for (int i = 0; i < 10; i++) {
            String name = getRandomName();
            System.out.println(name + " - " + getPhone(name));
        }
    }
}
