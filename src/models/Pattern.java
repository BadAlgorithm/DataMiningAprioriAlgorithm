package models;

import java.util.List;

/**
 * Created by minir on 28/05/2017.
 */
public class Pattern {

    private final List<Item> _items;
    private final int _support;

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
        return new Pattern(items, support + 1);
    }
}
