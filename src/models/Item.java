package models;

/**
 * Created by minir on 28/05/2017.
 */
public class Item {

    private final String _stockCode;
    private final String _description;

    public Item(final String description, final String stockCode) {
        _description = description;
        _stockCode = stockCode;
    }

    public String getStockCode() {
        return _stockCode;
    }

    public String getDescription() {
        return _description;
    }

    @Override
    public String toString() {
        return "Stock Code: " + _stockCode + "\n" + "Description: " + _description + "\n";
    }
}
