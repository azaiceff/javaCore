package lesson7_8.project.enitity;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private String city;
    @JsonProperty("LocalObservationDateTime")
    private String localDate;
    @JsonProperty("WeatherText")
    private String WeatherText;
    @JsonProperty("Temperature")
    Temperature TemperatureObject;

    public String getCity() {
        return city;
    }

    public String getLocalDate() {
        return localDate;
    }

    public String getWeatherText() {
        return WeatherText;
    }

    public Double getTemperature() {
        return TemperatureObject.getMetric().getValue();
    }

    public void setCity(String city) {
        this.city = city;
    }
    public void setLocalDate(String localDate) {
        this.localDate = localDate.substring(0,10);
    }
    public void setTemperature(Temperature TemperatureObject) {
        this.TemperatureObject = TemperatureObject;
    }

    public void setWeatherText(String WeatherText) {
        this.WeatherText = WeatherText;
    }

    @Override
    public String toString() {
        return "Погода: " + WeatherText + " " + TemperatureObject.getMetric().getValue() + "C";
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Temperature {
    @JsonProperty("Metric")
    Metric MetricObject;

    public Metric getMetric() {
        return MetricObject;
    }

    public void setMetric(Metric MetricObject) {
        this.MetricObject = MetricObject;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Metric {
    @JsonProperty("Value")
    private double Value;

    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }
}
