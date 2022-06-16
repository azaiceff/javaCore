package lesson5;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Создание объекта AppData и запись его в файл");
        String filePath = "src/lesson5/data.csv";
        AppData appData = Randomizer.getNewAppData();
        System.out.println(appData);
        Organizer.saveAppDataToFile(appData, filePath);

        System.out.println("Чтение из файла");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String str;
            while((str = reader.readLine()) != null){
                System.out.println(str);
            }
        }

        System.out.println("\nСоздание объекта AppData из файла");
        AppData appDataFromFile = Organizer.getNewAppDataFromFile(filePath);
        System.out.println(appDataFromFile);

        System.out.println("\nПерезапись файла новыми данными и вывод его в консоль через преобразование в строку");
        Organizer.saveAppDataToFile(Randomizer.getNewAppData(), filePath);
        System.out.println(Organizer.getStringFromFile(filePath));
    }
}
