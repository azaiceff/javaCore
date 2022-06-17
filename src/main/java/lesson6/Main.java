package lesson6;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .build();

        Request request = new Request.Builder()
                .url("http://api.openweathermap.org/data/2.5/weather?q=Moscow,ru&APPID=b2da353f04e8cc0b292419206ded1edd")
                .build();

        Response response = client.newCall(request).execute();

        assert response.body() != null;
        String body = response.body().string();
        System.out.println(body);
    }
}
