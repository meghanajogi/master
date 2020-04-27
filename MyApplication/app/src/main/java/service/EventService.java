package service;

import common.EventResponseHandler;

import request.CreateEventRequest;
import request.UpdateEventRequest;

public interface EventService {

     void createEvent(CreateEventRequest request, EventResponseHandler.ResponseListener responseListener,EventResponseHandler.ErrorListener errorListener);

     void getAllEvent(EventResponseHandler.ResponseListener responseListener,EventResponseHandler.ErrorListener errorListener);

     void updateEvent(UpdateEventRequest request,Integer id,EventResponseHandler.ResponseListener responseListener, EventResponseHandler.ErrorListener errorListener);


     void deleteEvent(Integer id,EventResponseHandler.ResponseListener responseListener, EventResponseHandler.ErrorListener errorListener);

}
