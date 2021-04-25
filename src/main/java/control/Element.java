package control;

import drivermanager.Driver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Element {
    protected String locator;

    Element(String locator) {
        this.locator = locator;
    }

    public WebElement getCurrentElement() {
        return Driver.findElement(locator);
    }

    public List<WebElement> getCurrentElements() {
        return Driver.findElements(locator);
    }

    public void click() {
        getCurrentElement().click();
    }

    public String getText() {
        return getCurrentElement().getText();
    }
}
