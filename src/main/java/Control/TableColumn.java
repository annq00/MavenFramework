package Control;

public class TableColumn extends Element {

    public TableColumn(String locator) {
        super(locator);
    }

    public int getTableColumnSize() {
        return this.getCurrentElements().size();
    }
}
