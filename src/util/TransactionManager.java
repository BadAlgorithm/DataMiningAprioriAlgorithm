package util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import models.Item;
import models.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TransactionManager {

    private static final String ITEM_KEY = "item";
    private static final String DESCRIPTION_KEY = "desc";

    public static List<Transaction> convertJsonTransactions(JsonObject jsonData) {
        Set<Map.Entry<String, JsonElement>> jsonSet = jsonData.entrySet();
        List<Transaction> transactions = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : jsonSet){
            List<Item> transactionItems = new ArrayList<>();
            JsonElement transactonElement = entry.getValue();
            String idKey = entry.getKey();
            System.out.println(idKey);
            JsonArray transactionItemArray = transactonElement.getAsJsonArray();
            for (int i = 0; i < transactionItemArray.size(); i++){
                JsonObject transObject = transactionItemArray.get(i).getAsJsonObject();
                String itemDesc = transObject.get(DESCRIPTION_KEY).toString();
                String itemCode = transObject.get(ITEM_KEY).toString();
                Item newItem = new Item(itemDesc.substring(1, itemDesc.length()-1), itemCode.substring(1, itemCode.length() -1));
                transactionItems.add(newItem);
            }
            transactions.add(new Transaction(idKey, transactionItems));
        }
        return transactions;
    }
}
