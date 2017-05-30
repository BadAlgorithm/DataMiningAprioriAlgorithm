package models;

import java.util.List;

/**
 * Created by minir on 28/05/2017.
 */
public class Transaction {

    private final List<Item> _items;
    private final String _transId;

    public Transaction(String transId, List<Item> items) {
        _items = items;
        _transId = transId;
    }

    public List<Item> getItems() {
        return _items;
    }

    public String getTransId() {
        return _transId;
    }

    @Override
    public String toString() {
        return "Invoice #: " + _transId + "\n" + _items.stream().map(Object::toString).reduce("", String::concat);
    }
}
