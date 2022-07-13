package lesson7_8.project;



import lesson7_8.project.enitity.WeatherData;
import lesson7_8.project.enums.Functionality;
import lesson7_8.project.enums.Periods;
import lesson7_8.project.provider.AccuWeatherProvider;
import lesson7_8.project.provider.WeatherProvider;
import lesson7_8.project.repository.DatabaseRepositorySQLiteImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    WeatherProvider weatherProvider = new AccuWeatherProvider();
    Map<Integer, Functionality> variantResult = new HashMap();

    public Controller() {
        variantResult.put(1, Functionality.GET_CURRENT_WEATHER);
        variantResult.put(2, Functionality.GET_WEATHER_IN_NEXT_5_DAYS);
        variantResult.put(3, Functionality.PRINT_DATE_BASE);
    }

    public void onUserInput(String input) throws IOException, SQLException {
        int command = Integer.parseInt(input);
        if (!variantResult.containsKey(command)) {
            throw new IOException("There is no command for command-key " + command);
        }

        switch (variantResult.get(command)) {
            case GET_CURRENT_WEATHER:
                getCurrentWeather();
                break;
            case GET_WEATHER_IN_NEXT_5_DAYS:
                getWeatherIn5Days();
                break;
            case PRINT_DATE_BASE:
                printDateBase();
                break;
        }
    }
    public void printDateBase() throws SQLException {
        DatabaseRepositorySQLiteImpl bd = new DatabaseRepositorySQLiteImpl();
        for (WeatherData data: bd.getAllSavedData()){
            System.out.println(data);
        }
    }

    public void getCurrentWeather() throws IOException, SQLException {
        weatherProvider.getWeather(Periods.NOW);
    }

    public void getWeatherIn5Days() throws IOException, SQLException {
        weatherProvider.getWeather(Periods.FIVE_DAYS);
    }
}
