package drivermanagers;


public class DriverManagerFactory {

    public static DriverManager getDriverManager(String type){
        DriverManager driverManager;
        switch (type){
            case "chrome":
                System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver_89.exe");
                driverManager = new ChromeDriverManager();
                break;
            default:
                System.setProperty("webdriver.edge.driver","src/main/resources/drivers/msedgedriver.exe");
                driverManager = new EdgeDriverManager();
        }
        return driverManager;
    }
}