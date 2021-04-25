package Control;

public class TextBox extends Element {

    public TextBox(String locator) {
        super(locator);
    }

    public void enter(String data) {
        this.getCurrentElement().sendKeys(data);
    }
}
