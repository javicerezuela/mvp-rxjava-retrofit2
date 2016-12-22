package beta.app.way.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panjiyudasetya on 12/21/16.
 */

public class DataFriend {
    @SerializedName("friends")
    private List<Friend> friends;

    public List<Friend> getFriends() {
        return friends == null ? new ArrayList<Friend>() : friends;
    }
}
