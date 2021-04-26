package com.railway.control;

import com.railway.drivermanager.Driver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class DropDown extends Element {

    public DropDown(String locator) {
        super(locator);
    }

    private Select createNewSelect (){
        Driver.waitForJavaScriptIdle();
        WebElement temp = Driver.findElement(this.locator);
        return new Select(temp);
    }

    public void select(int index) {
        try {
            this.createNewSelect().selectByIndex(index);
        } catch (StaleElementReferenceException e) {
            select(index);
        }
    }

    public String getSelectedOption() {
        String currentOpt = null;
        try {
            currentOpt = this.createNewSelect().getFirstSelectedOption().getText();
        } catch (StaleElementReferenceException e) {
            getSelectedOption();
        }
        return currentOpt;
    }

    public int getSize() {
        int listSize = 1;
        try {
            List<WebElement> list = this.createNewSelect().getOptions();
            listSize = list.size();
        } catch (StaleElementReferenceException e) {
            getSize();
        }
        return listSize;
    }
}
