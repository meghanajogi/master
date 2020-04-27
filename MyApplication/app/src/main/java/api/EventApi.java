package api;

import common.Constants;
import request.CreateEventRequest;
import request.UpdateEventRequest;
import response.CreateEventResponse;
import response.DeleteEventResponse;
import response.EventResponse;
import response.UpdateEventResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventApi {


    @POST(Constants.BASE_URL+"notification")
    Call<CreateEventResponse> createEvent(@Body CreateEventRequest request);

    @GET(Constants.BASE_URL+"notification")
    Call<EventResponse> getAllEvent();


    @PUT(Constants.BASE_URL+"notification/{id}")
    Call<UpdateEventResponse> updateEvent(@Body UpdateEventRequest request, @Path("id") int id);


    @DELETE(Constants.BASE_URL+"notification/{id}")
    Call<DeleteEventResponse> deleteEvent(@Path("id") int id);


}
