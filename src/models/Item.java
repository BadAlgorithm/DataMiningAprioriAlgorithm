package models;

import java.util.Locale;

/**
 * Created by minir on 28/05/2017.
 */
public class Item {

    private String id;
    private String description;

    public Item(String description, String id) {
        this.description = description;
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "Item - ID: %1s, Desc: %2s", this.id, this.description);
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
