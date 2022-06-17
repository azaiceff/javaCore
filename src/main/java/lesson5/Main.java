package lesson5;

import java.io.BufferedReader;
import java.io.FileReader;

import static lesson5.Organizer.*;
import static lesson5.Randomizer.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Создание объекта AppData и запись его в файл");
        String filePath = "src/main/java/lesson5/data.csv";
        AppData appData = getNewAppData();
        System.out.println(appData);
        saveAppDataToFile(appData, filePath);

        System.out.println("Чтение из файла");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String str;
            while((str = reader.readLine()) != null){
                System.out.println(str);
            }
        }

        System.out.println("\nСоздание объекта AppData из файла");
        AppData appDataFromFile = getNewAppDataFromFile(filePath);
        System.out.println(appDataFromFile);

        System.out.println("\nПерезапись файла новыми данными и вывод его в консоль через преобразование в строку");
        saveAppDataToFile(getNewAppData(), filePath);
        System.out.println(Organizer.getStringFromFile(filePath));
    }
}
