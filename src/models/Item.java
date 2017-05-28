package models;

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

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
