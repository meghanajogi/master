package serviceImpl;

import common.EventResponseHandler;
import common.ServiceGenerator;

import api.EventApi;
import request.CreateEventRequest;
import request.UpdateEventRequest;
import response.CreateEventResponse;
import response.DeleteEventResponse;
import response.EventResponse;
import response.UpdateEventResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.EventService;

public class EventServiceImpl implements EventService {

  private EventApi eventApi;
  private static  EventServiceImpl eventServiceImpl;

  public static EventServiceImpl getInstance()
  {
       if(eventServiceImpl==null)
       {
           eventServiceImpl=new EventServiceImpl();
       }
       return eventServiceImpl;
  }
public EventServiceImpl()
{
    eventApi= ServiceGenerator.createService(EventApi.class);
    eventApi= ServiceGenerator.createService(EventApi.class);
}
    @Override
    public void createEvent(CreateEventRequest request, EventResponseHandler.ResponseListener responseListener, EventResponseHandler.ErrorListener errorListener) {
        Call<CreateEventResponse> responseCall=eventApi.createEvent(request);
        responseCall.enqueue(new Callback<CreateEventResponse>() {
            @Override
            public void onResponse(Call<CreateEventResponse> call, Response<CreateEventResponse> response) {
               responseListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CreateEventResponse> call, Throwable t) {
               errorListener.onError(t);
            }
        });


    }

    @Override
    public void getAllEvent(EventResponseHandler.ResponseListener responseListener, EventResponseHandler.ErrorListener errorListener) {
      Call<EventResponse> responseCall=eventApi.getAllEvent();
      responseCall.enqueue(new Callback<EventResponse>() {
          @Override
          public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
              responseListener.onSuccess(response.body());
          }

          @Override
          public void onFailure(Call<EventResponse> call, Throwable t) {
             errorListener.onError(t);
          }
      });


    }

    @Override
    public void updateEvent(UpdateEventRequest request,Integer id, EventResponseHandler.ResponseListener responseListener, EventResponseHandler.ErrorListener errorListener) {
        Call<UpdateEventResponse> responseCall= eventApi.updateEvent(request,id);
        responseCall.enqueue(new Callback<UpdateEventResponse>() {
            @Override
            public void onResponse(Call<UpdateEventResponse> call, Response<UpdateEventResponse> response) {
                responseListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UpdateEventResponse> call, Throwable t) {
              errorListener.onError(t);
            }
        });
    }

    @Override
    public void deleteEvent(Integer id, EventResponseHandler.ResponseListener responseListener, EventResponseHandler.ErrorListener errorListener) {

        Call<DeleteEventResponse> responseCall= eventApi.deleteEvent(id);
        responseCall.enqueue(new Callback<DeleteEventResponse>() {
            @Override
            public void onResponse(Call<DeleteEventResponse> call, Response<DeleteEventResponse> response) {
                responseListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<DeleteEventResponse> call, Throwable t) {
                errorListener.onError(t);
            }
        });

    }


}
