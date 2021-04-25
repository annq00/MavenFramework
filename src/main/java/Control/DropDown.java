package Control;

import drivermanagers.Driver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class DropDown extends Element {

    public DropDown(String locator) {
        super(locator);
    }

    public void select(int index) {
        try {
            Driver.getWebDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            WebElement temp = Driver.findElement(this.locator);
            Select select = new Select(temp);
            select.selectByIndex(index);
        } catch (StaleElementReferenceException e) {
            select(index);
        }
    }

    public String getSelectedOption() {
        String currentOpt = null;
        try {
            WebElement temp = Driver.findElement(this.locator);

            Select sl = new Select(temp);
            currentOpt = sl.getFirstSelectedOption().getText();

        } catch (StaleElementReferenceException e) {
            getSelectedOption();
        }
        return currentOpt;
    }

    public int getSize() {
        int listSize = 1;
        try {
            WebElement temp = Driver.findElement(this.locator);
            Select sl = new Select(temp);
            List<WebElement> list = sl.getOptions();
            listSize = list.size();


        } catch (StaleElementReferenceException e) {
            getSize();
        }
        return listSize;
    }
}
