package lesson7_8.project;

import lesson7_8.project.enitity.WeatherData;
import lesson7_8.project.enitity.WeatherResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// Репозиторий для работы
public interface DatabaseRepository {

    boolean saveWeatherData(WeatherResponse weatherData) throws SQLException;
    boolean saveWeatherData(WeatherData weatherData) throws SQLException;

    List<WeatherData> getAllSavedData() throws IOException, SQLException;
}
