package lesson7_8.project.repository;

import lesson7_8.project.ApplicationGlobalState;
import lesson7_8.project.enitity.WeatherData;
import lesson7_8.project.enitity.WeatherResponse;
import lesson7_8.project.repository.DatabaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepositorySQLiteImpl implements DatabaseRepository {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    String filename = "null";
    String createTableQuery = "CREATE TABLE IF NOT EXISTS weather (\n" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "city TEXT NOT NULL,\n" +
            "localDate TEXT NOT NULL,\n" +
            "WeatherText TEXT NOT NULL,\n" +
            "temperature REAL NOT NULL,\n" +
            "temperatureMin REAL\n" +
            ");";
    String insertWeatherQuery = "INSERT INTO weather (city, localDate, WeatherText, temperature, temperatureMin) VALUES (?,?,?,?,?)";

    public DatabaseRepositorySQLiteImpl() {
        filename = ApplicationGlobalState.getInstance().getDbFileName();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + filename);
    }

    public void createTableIfNotExists() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean saveWeatherData(WeatherResponse weatherData) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement saveWeather = connection.prepareStatement(insertWeatherQuery)) {
            saveWeather.setString(1, weatherData.getCity());
            saveWeather.setString(2, weatherData.getLocalDate());
            saveWeather.setString(3, weatherData.getWeatherText());
            saveWeather.setDouble(4, weatherData.getTemperature());
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException("Failure on saving weather object");
    }
    @Override
    public boolean saveWeatherData(WeatherData weatherData) throws SQLException {
        try (Connection connection = getConnection();
            PreparedStatement saveWeather = connection.prepareStatement(insertWeatherQuery)) {
            saveWeather.setString(1, weatherData.city());
            saveWeather.setString(2, weatherData.localDate());
            saveWeather.setString(3, weatherData.text());
            saveWeather.setDouble(4, weatherData.temperature());
            saveWeather.setDouble(5, weatherData.temperatureMin());

            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException("Failure on saving weather object");
    }


    @Override
    public List<WeatherData> getAllSavedData() throws SQLException {
        List<WeatherData> arrayList = new ArrayList<>();
        try(Connection connection = getConnection();
        Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM weather");
            while(resultSet.next()){
                arrayList.add(new WeatherData(resultSet.getString(2),resultSet.getString(3), resultSet.getString(4) ,resultSet.getDouble(5), resultSet.getDouble(6)));
            }
        }
        return arrayList;
    }
}
