package serviceImpl;

import common.EventResponseHandler;

import request.CreateEventRequest;
import request.UpdateEventRequest;
import service.EventService;
import service.SessionFacade;

public class SessionFacadeImpl implements SessionFacade {

    private static SessionFacade sessionFacade;
    private EventService eventService;

    public SessionFacadeImpl()
    {
        eventService=new EventServiceImpl();
    }

    public synchronized static SessionFacade getInstance()
    {
       if(sessionFacade==null)
       {
           sessionFacade=new SessionFacadeImpl();
       }
       return sessionFacade;
    }



    @Override
    public void createEvent(CreateEventRequest request, EventResponseHandler.ResponseListener responseListener, EventResponseHandler.ErrorListener errorListener) {
          eventService.createEvent(request,responseListener,errorListener);
    }

    @Override
    public void getAllEvent(EventResponseHandler.ResponseListener responseListener, EventResponseHandler.ErrorListener errorListener) {
        eventService.getAllEvent(responseListener,errorListener);
    }

    @Override
    public void updateEvent(UpdateEventRequest request,Integer id, EventResponseHandler.ResponseListener responseListener, EventResponseHandler.ErrorListener errorListener) {
        eventService.updateEvent(request,id,responseListener,errorListener);
    }

    @Override
    public void deleteEvent(Integer id, EventResponseHandler.ResponseListener responseListener, EventResponseHandler.ErrorListener errorListener) {
        eventService.deleteEvent(id,responseListener,errorListener);
    }
}
