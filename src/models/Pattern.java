package models;

import java.util.List;

/**
 * Created by minir on 28/05/2017.
 */
public class Pattern {

    private List<Item> items;
    private int support;

    public Pattern(List<Item> items, int support) {
        this.items = items;
        this.support = support;
    }

    public void getSimilarity(){

    }

    public List<Item> getItems() {
        return items;
    }

    public int getSupport() {
        return support;
    }

    public Pattern incrementSupport() {
        return new Pattern(items, support + 1);
    }
}
