package lesson7.project;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    @JsonProperty("WeatherText")
    private String WeatherText;
    @JsonProperty ("Temperature")
    Temperature TemperatureObject;
    public String getWeatherText() {
        return WeatherText;
    }
    public Temperature getTemperature() {
        return TemperatureObject;
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
    @JsonProperty ("Metric")
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
    @JsonProperty ("Value")
    private float Value;
    public float getValue() {
        return Value;
    }
    public void setValue(float Value) {
        this.Value = Value;
    }
}
