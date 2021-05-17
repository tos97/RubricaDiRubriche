package Models;

import java.util.UUID;

public class Role {
    private String type;
    private String description;
    private UUID uid;

    public Role() { }

    public Role(String type, String description, UUID uid) {
        this.type = type;
        this.description = description;
        this.uid = uid;
    }

    public Role(String type, String description, String uid) {
        this.type = type;
        this.description = description;
        this.uid = UUID.fromString(uid);
    }

    public Role(String type, String description) {
        this.type = type;
        this.description = description;
        this.uid = UUID.randomUUID();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid.toString();
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public void setId(String uid) {
        this.uid = UUID.fromString(uid);
    }
}
