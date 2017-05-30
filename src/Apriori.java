import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import models.Item;
import models.Pattern;
import models.Transaction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by minir on 27/05/2017.
 */

public class Apriori {

    public void computeApriori(int minSupport, List<Transaction> transactions) {
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
        System.out.println(findFrequentPatterns(frequentPatterns, transactions, minSupport, new ArrayList<>()).toString());
        ;
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
                np = np.incrementSupport();
            }
        }));
        List<Pattern> newFrequentPatterns = newPatterns.stream().filter(np -> np.getSupport() >= minSupport).collect(Collectors.toList());

        doneFrequentPatterns.addAll(newFrequentPatterns);

        if (newFrequentPatterns.size() == 0) {
            return doneFrequentPatterns;
        } else {
            return findFrequentPatterns(newFrequentPatterns, transactions, minSupport, doneFrequentPatterns);
        }
    }
}
