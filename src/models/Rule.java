package models;

import java.util.List;

/**
 * Created by minir on 28/05/2017.
 */
public class Rule {

    private final List<Item> _ruleItems;
    private final Item _appliedToItem;
    private final double _condifidence;

    public Rule(List<Item> ruleItems, Item appliedToItem, double confidence) {
        _ruleItems = ruleItems;
        _appliedToItem = appliedToItem;
        _condifidence = confidence;
    }

    public Item getAppliedToItem() {
        return _appliedToItem;
    }

    public double getCondifidence() {
        return _condifidence;
    }

    public List<Item> getRuleItems() {
        return _ruleItems;
    }
}
