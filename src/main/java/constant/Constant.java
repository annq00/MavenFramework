package constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Constant {
    public static String username = "test+1@gmail.com";
    public static String password = "123456789";
    public static final String FileSeparator = System.getProperty("file.separator");
    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String TEST_RESOURCES_FOLDER = "src" + FileSeparator + "test" + FileSeparator + "resources" + FileSeparator;
    public static final String BROWSER_SETTING_FILE = TEST_RESOURCES_FOLDER + "browsers.setting.properties";

    public static String generateTimeStampString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}

