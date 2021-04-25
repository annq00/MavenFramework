package com.railway.control;

public class TableColumn extends Element {

    public TableColumn(String locator) {
        super(locator);
    }

    public int getTableColumnSize() {
        return this.getCurrentElements().size();
    }
}
