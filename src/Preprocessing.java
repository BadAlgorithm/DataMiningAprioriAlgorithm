import models.Item;
import models.Transaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by calebmacdonaldblack on 28/5/17.
 */
public class Preprocessing {
    public List<Transaction> convert(final String filename) throws IOException {
        final String[] lines = new String(Files.readAllBytes(Paths.get(filename))).split("\n");
        final List<String[]> splitLines = Arrays.stream(lines).map(l -> l.split(",")).collect(Collectors.toList());
        final Map<String, List<Item>> transactionMap = new HashMap<>();
        splitLines.forEach(line -> {
            transactionMap.putIfAbsent(line[0], new ArrayList<>());
            transactionMap.get(line[0]).add(new Item(line[2], line[1]));
        });
        List<Transaction> transactions = transactionMap.entrySet().stream().map(transaction -> new Transaction(transaction.getKey(), transaction.getValue())).collect(Collectors.toList());
        Collections.shuffle(transactions);

        return transactions.subList(0, transactions.size() / 4);
    }
}
