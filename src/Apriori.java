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
            if (b.getSupport() >= minSupport)  {
                frequentPatterns.add(b);
            }
        });
        findFrequentPatterns(frequentPatterns, transactions, minSupport, new ArrayList<Pattern>());
    }

    private List<Pattern> findFrequentPatterns(List<Pattern> frequentPatterns, List<Transaction> transactions, int minSupport, List<Pattern> doneFrequentPatterns) {
        List<Pattern> newPatterns = new ArrayList<>();

        for(Pattern fp: frequentPatterns) {
            int startIndex = frequentPatterns.indexOf(fp);
            if (startIndex >= frequentPatterns.size() - 1) break;

            frequentPatterns.subList(startIndex + 1, frequentPatterns.size()).forEach(p -> {
                List<Item> newList = new ArrayList<Item>(fp.getItems());
                newList.addAll(p.getItems());
                Collections.sort(newList, (a, b) -> a.getStockCode().compareTo(b.getStockCode()));

                newPatterns.add(new Pattern(newList.stream().distinct().collect(Collectors.toList()), 0)); //Might not work as intended
            });
        }
        newPatterns.forEach(x -> x.getItems().forEach(y -> System.out.println(y.getStockCode())));

        Map<List<String>, Pattern> frequencies = new HashMap<>();
        transactions.forEach(t -> {
            List<Item> items = new ArrayList<Item>(t.getItems());
//            newPatterns.forEach();
        });


        return null;
    }

    private List<List<String>> findFrequentPatterns(List<List<String>> frequentPatterns, Set<Map.Entry<String, JsonElement>> transactionSet, int minSupport,List<List<String>> doneFrequentPatters) {
        List<List<String>> newPatterns = new ArrayList<>();

        for (List<String> freqPattern : frequentPatterns) {
            int startIndex = frequentPatterns.indexOf(freqPattern);
            if (startIndex >= frequentPatterns.size() - 1) {
                break;
            }
            List<List<String>> subList = new ArrayList<>(frequentPatterns.subList(startIndex + 1, frequentPatterns.size()));
            for (List<String> pattern : subList) {
                List<String> newList = new ArrayList<>(freqPattern);
                newList.addAll(pattern);
                Collections.sort(newList);
                List<String> distinct = newList.stream().distinct().collect(Collectors.toList());
                newPatterns.add(distinct);
                newPatterns = newPatterns.stream().distinct().collect(Collectors.toList());
            }
        }

        HashMap<List<String>, Integer> patternFrequencies = new HashMap<>();
        for (Map.Entry<String, JsonElement> transaction : transactionSet) {
            List<String> transactionItems = new ArrayList<>();
            for (JsonElement itemElement: transaction.getValue().getAsJsonArray()) {
                transactionItems.add(itemElement.getAsJsonObject().get("item").getAsString());
            }
            for(List<String> pattern: newPatterns) {
                if (transactionItems.containsAll(pattern)){
                    if (patternFrequencies.containsKey(pattern)) {
                        patternFrequencies.put(pattern, patternFrequencies.get(pattern) + 1);
                    } else {
                        patternFrequencies.put(pattern, 1);
                    }
                }
            }
        }
        List<List<String>> newFrequentPatterns = new ArrayList<>();
        for (Map.Entry<List<String>, Integer> entry : patternFrequencies.entrySet()) {
            if (entry.getValue() >= minSupport) {
                newFrequentPatterns.add(entry.getKey());
            }
        }
        System.out.println(patternFrequencies.toString());
        doneFrequentPatters.addAll(newFrequentPatterns);
        if (newFrequentPatterns.size() == 0){
            return doneFrequentPatters;
        } else {
            return findFrequentPatterns(newFrequentPatterns, transactionSet, minSupport, doneFrequentPatters);
        }
    }
}
