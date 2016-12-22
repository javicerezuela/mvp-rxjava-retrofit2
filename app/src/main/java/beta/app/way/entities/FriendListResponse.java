package beta.app.way.entities;
import com.google.gson.annotations.SerializedName;

/**
 * Created by panjiyudasetya on 12/21/16.
 */

public class FriendListResponse extends BaseResponse {
    @SerializedName("data")
    private DataFriend data;

    public DataFriend getData() {
        return data == null ? new DataFriend() : data;
    }
}
