package beta.app.way.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by panjiyudasetya on 12/16/16.
 */

public class Friend {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("avatar")
    private String avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
