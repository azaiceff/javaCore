package lesson7_8.project;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson7_8.project.enitity.WeatherData;
import lesson7_8.project.enitity.WeatherResponse;
import lesson7_8.project.enums.Periods;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccuWeatherProvider implements WeatherProvider {

    private static final String BASE_HOST = "https://dataservice.accuweather.com";
    private static final String FORECAST_ENDPOINT = "forecasts";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "currentconditions";
    private static final String API_VERSION = "v1";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();
    private static String cityName;

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void getWeather(Periods periods) throws IOException, SQLException {
        String cityKey = detectCityKey();
        if (periods.equals(Periods.NOW)) {
            String url = getUrl(CURRENT_CONDITIONS_ENDPOINT, API_VERSION, cityKey);

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            assert response.body() != null;
            String jsonString = response.body().string().replace("[", "").replace("]", "");
            WeatherResponse weatherResponse = objectMapper.readValue(jsonString, WeatherResponse.class);
            weatherResponse.setCity(cityName);
            DatabaseRepositorySQLiteImpl dataBase = new DatabaseRepositorySQLiteImpl();
            dataBase.saveWeatherData(weatherResponse);
            System.out.println(weatherResponse);

        } else if (periods.equals(Periods.FIVE_DAYS)) {
            String url = getUrl(FORECAST_ENDPOINT, API_VERSION, "daily", "5day", cityKey);

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            assert response.body() != null;
            JsonNode jsonResponse = objectMapper.readTree(response.body().string()).get("DailyForecasts");

            List<WeatherData> weatherDataList = new ArrayList<>();
            DatabaseRepositorySQLiteImpl dateBase = new DatabaseRepositorySQLiteImpl();

            for (JsonNode jsonNode : jsonResponse) {
                double tempMinF = fahrenheitToCelsius(jsonNode.get("Temperature").get("Minimum").at("/Value").asDouble());
                double tempMaxF = fahrenheitToCelsius(jsonNode.get("Temperature").get("Maximum").at("/Value").asDouble());
                String date = jsonNode.at("/Date").asText().substring(0, 10);
                String weather_text = jsonNode.get("Day").at("/IconPhrase").asText();
                WeatherData weatherData = new WeatherData(cityName, date, weather_text, tempMaxF, tempMinF);
                weatherDataList.add(weatherData);
            }
            for (WeatherData weatherData : weatherDataList) {
                dateBase.saveWeatherData(weatherData);
                System.out.println(weatherData);
            }
        }
    }

    private static double fahrenheitToCelsius(double fahrenheit) {
        BigDecimal result = BigDecimal.valueOf((fahrenheit - 32.0) * 5.0 / 9.0);
        return result.setScale(1, RoundingMode.DOWN).doubleValue();
    }

    public String getUrl(String... path) {
        return UriComponentsBuilder.fromHttpUrl(BASE_HOST)
                .pathSegment(path)
                .queryParam("apikey", API_KEY)
                .build().toString();
    }

    public String detectCityKey() throws IOException {
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();
        String detectLocationURL = UriComponentsBuilder.fromHttpUrl(getUrl("locations", API_VERSION, "cities", "autocomplete"))
                .queryParam("q", selectedCity)
                .build().toString();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(detectLocationURL)
                .build();

        Response response = client.newCall(request).execute();
        assert response.body() != null;
        if (!response.isSuccessful()) {
            throw new IOException("Невозможно прочесть информацию о городе. " +
                    "Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
        }
        String jsonResponse = response.body().string();
        System.out.println("Произвожу поиск города " + selectedCity);

        if (objectMapper.readTree(jsonResponse).size() > 0) {
            cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocalizedName").asText();
            String countryName = objectMapper.readTree(jsonResponse).get(0).at("/Country/LocalizedName").asText();
            System.out.println("В городе " + cityName + ", страна " + countryName);
        } else throw new IOException("Server returns 0 cities");

        return objectMapper.readTree(jsonResponse).get(0).at("/Key").asText();
    }
}
