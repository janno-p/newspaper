package ee.ttu.ld.imbi.newspaper.model;

import java.util.Date;

public class Newspaper {
    private int id;
    private String name;
    private Date foundedAt;
    private String description;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFoundedAt(Date foundedAt) {
        this.foundedAt = foundedAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getFoundedAt() {
        return foundedAt;
    }

    public String getDescription() {
        return description;
    }
}
