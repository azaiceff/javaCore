package lesson7_8.project;

public final class ApplicationGlobalState {

    private static ApplicationGlobalState INSTANCE;
    private String selectedCity = null;


    private ApplicationGlobalState() {
    }

    // Непотокобезопасный код для упрощения
    public static ApplicationGlobalState getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ApplicationGlobalState();
        }

        return INSTANCE;
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }
    public String getDbFileName() {
        return "src/main/java/lesson7_8/project/application.db";
    }

    public String getApiKey() {
        return "nlUNvwa5rJNPjucEH9OHn4QxKHnKeHH1";
    }
}
