package lesson5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Organizer {
    static String getStringFromFile(String filePath) throws IOException{
        StringBuilder file = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                file.append(Arrays.toString(line.split(";"))).append("\n");
            }
        }
        return String.valueOf(file);
    }

    static AppData getNewAppDataFromFile(String filePath) throws IOException {
        String[] header;
        List<List<String>> dataStrings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String strLine = reader.readLine();
            header = strLine.split(";");
            while ((strLine = reader.readLine()) != null) {
                String[] dataArrayStrings = strLine.split(";");
                dataStrings.add(Arrays.asList(dataArrayStrings));
            }
        }
        return new AppData(header, getIntArrayFromList(dataStrings));
    }

    private static int[][] getIntArrayFromList(List<List<String>> dataStrings) {
        int[][] data = new int[dataStrings.size()][];
        for (int i = 0; i < data.length; i++) {
            data[i] = new int[dataStrings.get(i).size()];
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = Integer.parseInt(dataStrings.get(i).get(j));
            }
        }
        return data;
    }
    static void saveAppDataToFile(AppData appData, String filePath) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(appData.toString());
        }
    }
}
