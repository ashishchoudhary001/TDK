package model;

/**
 * Created by ParmeshMahore on 2/7/17.
 */

public class Cuisine {
    private String name;

    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Cuisine [name = " + name + ", type = " + type + "]";
    }
}
