package response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateEventRes {


    @SerializedName("status")
    @Expose
    private CreateEventRes status;

    public CreateEventRes getStatus() {
        return status;
    }

    public void setStatus(CreateEventRes status) {
        this.status = status;}
}
