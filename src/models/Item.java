package models;

import java.util.Locale;

/**
 * Created by minir on 28/05/2017.
 */
public class Item {

    private final String id;
    private final String description;

    public Item(final String description, final String id) {
        this.description = description;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Stock Code: " +  id + "\n" + "Description: " + description + "\n";
    }
}
