package models;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by minir on 28/05/2017.
 */
public class Pattern {

    private final List<Item> _items;
    private int _support;

    public Pattern(List<Item> items, int support) {
        _items = items;
        _support = support;
    }

    public void getSimilarity(){

    }

    public List<Item> getItems() {
        return _items;
    }

    public int getSupport() {
        return _support;
    }

    public Pattern incrementSupport() {
        return new Pattern(_items, _support + 1);
    }

    public void iHateJava() {
        _support++;
    }

    public String compositeKey() {
        return _items.stream().map(x -> x.getStockCode()).reduce("", (a, b) -> a + b);
    }

    public String toString() {
        String compositeDesc = _items.stream().map(Item::getDescription).reduce("", (a, b) -> a + " " + b);
        return "(pattern:" + compositeDesc + " support: " + _support + ")";
    }
}
