import models.Item;
import models.Pattern;
import models.Rule;
import models.Transaction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by minir on 27/05/2017.
 */

public class Apriori {

    public List<Rule> computeApriori(int minSupport, double minConfidence, List<Transaction> transactions) {
        Map<String, Pattern> frequencies = new HashMap<>();
        transactions.forEach(x -> x.getItems().forEach(y -> {
            frequencies.putIfAbsent(y.getStockCode(), new Pattern(Arrays.asList(y), 0));
            frequencies.put(y.getStockCode(), frequencies.get(y.getStockCode()).incrementSupport());

        }));

        List<Pattern> frequentPatterns = new ArrayList<>();
        frequencies.forEach((a, b) -> {
            if (b.getSupport() >= minSupport) {
                frequentPatterns.add(b);
            }
        });

        List<Pattern> fp = findFrequentPatterns(frequentPatterns, transactions, minSupport, new ArrayList<>());
        return rules(fp, transactions, minConfidence);
    }

    private List<Pattern> findFrequentPatterns(List<Pattern> frequentPatterns, List<Transaction> transactions, int minSupport, List<Pattern> doneFrequentPatterns) {
        List<Pattern> newPatterns = new ArrayList<>();

        for (Pattern fp : frequentPatterns) {
            int startIndex = frequentPatterns.indexOf(fp);
            if (startIndex >= frequentPatterns.size() - 1) break;

            frequentPatterns.subList(startIndex + 1, frequentPatterns.size()).forEach(p -> {
                List<Item> newList = new ArrayList<>(fp.getItems());
                newList.addAll(p.getItems());
                Collections.sort(newList, (a, b) -> a.getStockCode().compareTo(b.getStockCode()));

                newPatterns.add(new Pattern(newList.stream().distinct().collect(Collectors.toList()), 0)); //Might not work as intended
            });
        }

        transactions.forEach(t -> newPatterns.forEach(np -> {
            if (t.containsItems(np.getItems())) {
                np.iHateJava();
            }
        }));


        Set<String> compositeKeys = new HashSet<>();

        List<Pattern> newFrequentPatterns = newPatterns.stream().filter(np -> np.getSupport() >= minSupport).filter(x -> {
            if (compositeKeys.contains(x.compositeKey())) {
                return false;
            } else {
                compositeKeys.add(x.compositeKey());
                return true;
            }
        }).collect(Collectors.toList());


        doneFrequentPatterns.addAll(newFrequentPatterns);

        if (newFrequentPatterns.size() == 0) {
            return doneFrequentPatterns;
        } else {
            return findFrequentPatterns(newFrequentPatterns, transactions, minSupport, doneFrequentPatterns);
        }
    }

    private List<Rule> rules(List<Pattern> frequentPatterns, List<Transaction> transactions, double minConfidence) {
        List<Rule> rules = new ArrayList<>();

        frequentPatterns.forEach(fp -> fp.getItems().forEach(i -> {
            List<Item> ruleItems = new ArrayList<>(fp.getItems());
            ruleItems.remove(i);
            rules.add(new Rule(ruleItems, i, 0, fp));
        }));

        rules.forEach(r -> {
            double ruleItemsSupport = transactions.stream().filter(x -> x.containsItems(r.getRuleItems())).collect(Collectors.toList()).size();
            r.setConfidence((double) r.getPattern().getSupport() / ruleItemsSupport);
        });
        return rules.stream().filter(r -> r.getCondifidence() >= minConfidence).collect(Collectors.toList());
    }
}
