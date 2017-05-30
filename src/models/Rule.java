package models;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by minir on 28/05/2017.
 */
public class Rule {

    private final List<Item> _ruleItems;
    private final Item _appliedToItem;
    private double _condifidence;
    private final Pattern _pattern;

    public Rule(List<Item> ruleItems, Item appliedToItem, double confidence, Pattern pattern) {
        _ruleItems = ruleItems;
        _appliedToItem = appliedToItem;
        _condifidence = confidence;
        _pattern = pattern;
    }

    public Item getAppliedToItem() {
        return _appliedToItem;
    }

    public double getCondifidence() {
        return _condifidence;
    }

    public Pattern getPattern() {
        return _pattern;
    }

    public void setConfidence(double confidence) {
        _condifidence = confidence;
    }

    public List<Item> getRuleItems() {
        return _ruleItems;
    }

    public String toString() {
        return _ruleItems.stream().map(i -> i.getDescription()).reduce("",
                (a, b) -> a + " " + b)
                + " => "
                + _appliedToItem.getDescription()
                + "(Support: "
                + _pattern.getSupport()
                + ", "
                + "Confidence: "
                + String.format("%.2f", _condifidence)
                + ")";
    }
}
