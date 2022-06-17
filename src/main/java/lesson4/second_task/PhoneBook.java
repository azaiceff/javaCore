package lesson4.second_task;

import java.util.*;

public class PhoneBook {
    private static final Random random = new Random();
    private static final TreeMap<String, ArrayList<String>> PHONE_BOOK = new TreeMap<>();

    public static SortedMap<String, ArrayList<String>> getPhoneBook() {
        return PHONE_BOOK;
    }

    private PhoneBook() {
        throw new IllegalStateException("PhoneBook class");
    }

    private static void add(String name, String phone) {
        if (!PHONE_BOOK.containsKey(name)) {
            PHONE_BOOK.put(name, new ArrayList<>());
        }
        PHONE_BOOK.get(name).add(phone);
    }

    public static String getPhone(String name) {
        if (name.equals("Putin")) {
            return "Это секретная информация";
        }
        if (!PHONE_BOOK.containsKey(name)) {
            return "Не найден в телефонной книге";
        }
        return PHONE_BOOK.get(name).toString();
    }

    public static void completionPhoneBook() {
        int phoneNumbers = getRandomInt(30, 10);
        for (int i = 0; i < phoneNumbers; i++) {
            add(getRandomName(), Phone.getRandomPhone());
        }
    }

    public static String getRandomName() {
        return Name_Enum.values()[getRandomInt(Name_Enum.values().length, 0)].toString();
    }

    private static int getRandomInt(int a, int b) {
        return random.nextInt(a) + b;
    }
}
