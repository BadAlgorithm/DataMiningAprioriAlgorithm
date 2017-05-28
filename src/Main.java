import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        long startTime = System.nanoTime();
        try {
            String fileString = new String(Files.readAllBytes(Paths.get("./json_data_nodescp.json")));
            JsonObject dataJson = new JsonParser().parse(fileString).getAsJsonObject();
            new Apriori().computeApriori(400, dataJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println((System.nanoTime() - startTime) / 1000000000 + "s");
    }
}
