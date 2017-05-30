import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.Transaction;
import util.TransactionManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        new Apriori().computeApriori(600, .0,  new Preprocessing().convert("online_retail.csv")).forEach(x -> System.out.println(x.toString()));
        System.out.println((System.nanoTime() - startTime) / 1000000000 + "s");
    }
}
