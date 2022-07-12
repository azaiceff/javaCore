package lesson7_8.project.enitity;


public record WeatherData(String city, String localDate, String text, Double temperature, Double temperatureMin) {

    @Override
    public String toString() {
        String printInfo = "В городе " + city + " на дату - " + localDate + " ожидается: " + text
                + "\nтемпература: " + String.format("%.2f", temperature) + "C";
        if(temperatureMin != 0){
            printInfo +=  " min: " + String.format("%.2f", temperatureMin) + "С";
        }
        return printInfo;
    }
}
