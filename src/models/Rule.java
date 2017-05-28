package models;

import java.util.List;

/**
 * Created by minir on 28/05/2017.
 */
public class Rule {

    private List<Item> ruleItems;
    private Item appliedToItem;
    private double condifidence;

    public Rule(List<Item> ruleItems, Item appliedToItem, double condifidence) {
        this.ruleItems = ruleItems;
        this.appliedToItem = appliedToItem;
        this.condifidence = condifidence;
    }
}
