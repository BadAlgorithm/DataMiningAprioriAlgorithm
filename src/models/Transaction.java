package models;

import java.util.List;

/**
 * Created by minir on 28/05/2017.
 */
public class Transaction {

    private List<Item> items;
    private String transId;

    public Transaction(String transId, List<Item> items) {
        this.items = items;
        this.transId = transId;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getTransId() {
        return transId;
    }

    @Override
    public String toString() {
        return "Invoice #: " + transId + "\n" + items.stream().map(Object::toString).reduce("", String::concat);
    }
}
