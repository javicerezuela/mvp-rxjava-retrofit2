package beta.app.way.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by panjiyudasetya on 12/21/16.
 */

public class BaseResponse {
    @SerializedName("code")
    private int responseCode;
    @SerializedName("message")
    private String message;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
