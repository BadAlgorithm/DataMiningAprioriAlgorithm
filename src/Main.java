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
        if (args.length < 3) {
            System.err.println("Arguments: <filename> <min support> <min confidence>");
        }
        long startTime = System.nanoTime();
        new Apriori().computeApriori(Integer.valueOf(args[1]), Double.valueOf(args[2]),  new Preprocessing().convert(args[0])).forEach(x -> System.out.println(x.toString()));
        System.out.println((System.nanoTime() - startTime) / 1000000000 + "s");
    }
}
