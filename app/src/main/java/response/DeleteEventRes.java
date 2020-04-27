package response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteEventRes {


    @SerializedName("status")
    @Expose
    private DeleteEventRes  status;

    public DeleteEventRes  getStatus() {
        return status;
    }

    public void setStatus(DeleteEventRes  status) {
        this.status = status;
    }



}
