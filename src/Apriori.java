import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import models.Item;
import models.Transaction;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by minir on 27/05/2017.
 */

public class Apriori {

    public Apriori() {

    }

    public void computeApriori(int minSupport, List<Transaction> transactions) {
        Map<String, Integer> itemFrequencies = new HashMap<>();
        List<Pattern> patterns = new ArrayList<>();
        transactions.forEach(transaction -> transaction.getItems().forEach(item -> {
                itemFrequencies.putIfAbsent(item.getId(), 0);
                itemFrequencies.put(item.getId(), itemFrequencies.get(item.getId()) + 1);
            }));
        itemFrequencies.forEach((k, v) -> {

        });
    }

    public void computeApriori(int minSupport, JsonObject transactions) {
        Set<Map.Entry<String, JsonElement>> transactionSet = transactions.entrySet();
        HashMap<String, Integer> itemFrequency = new HashMap<>();
        List<List<String>> frequentItems = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : transactionSet) {
            JsonArray items = entry.getValue().getAsJsonArray();
            for (JsonElement itemElement : items) {
                JsonObject item = itemElement.getAsJsonObject();
                String stockCode = item.get("item").getAsString();
                if (itemFrequency.containsKey(stockCode)) {
                    itemFrequency.put(stockCode, itemFrequency.get(stockCode) + 1);
                } else {
                    itemFrequency.put(stockCode, 1);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : itemFrequency.entrySet()) {
            if (entry.getValue() >= minSupport) {
                List<String> pattern = new ArrayList<>();
                pattern.add(entry.getKey());
                frequentItems.add(pattern);
            }
        }
        System.out.println("Starting analysing patterns");
        Comparator<List<String>> comparator = (List<String> a, List<String> b) -> {
            Double aVal = Similarity.similarity(a.get(0), a.get(1));
            Double bVal = Similarity.similarity(b.get(0), b.get(1));
            return bVal.compareTo(aVal);
        };
        List<List<String>> frequentPatterns = findFrequentPatterns(frequentItems, transactionSet, minSupport, new ArrayList<>());
        Collections.sort(frequentPatterns, comparator);
        System.out.println(frequentPatterns.toString());
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
