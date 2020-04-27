package presenter;

import android.content.Context;
import android.widget.Toast;

import common.EventResponseHandler;

import java.util.List;

import common.Events;
import controller.EventListFragmentController;
import response.DeleteEventResponse;
import response.EventResponse;
import serviceImpl.SessionFacadeImpl;

public class EventListFragmentPresenter extends BasePresenter<EventListFragmentController.View>{
    Context context;

    public EventListFragmentPresenter(Context context)
    {
        this.context=context;
    }


    public void getAllEvent()
    {
        SessionFacadeImpl.getInstance().getAllEvent(new EventResponseHandler.ResponseListener() {
            @Override
            public void onSuccess(Object response) {

                EventResponse eventResponse= (EventResponse) response;
                if (response != null) {
                    getView().setUpRecyclerView(eventResponse.getData());
                }
            }
        }, new EventResponseHandler.ErrorListener() {
            @Override
            public void onError(Throwable errorWrapper) {

            }
        });
    }



    @Override
    protected EventListFragmentController.View createDummyView() {
        return new EventListFragmentController.View() {
            @Override
            public void setUpRecyclerView(List<Events> eventsList) {

            }
        };
    }

    public void deleteEvent(Events eventId) {

SessionFacadeImpl.getInstance().deleteEvent(eventId.getId(), new EventResponseHandler.ResponseListener() {
    @Override
    public void onSuccess(Object response) {
        DeleteEventResponse deleteEventResponse = (DeleteEventResponse) response;
        if (response != null) {
            Toast.makeText(context, "Event deleted successfully", Toast.LENGTH_SHORT).show();
        }
    }
}, new
        EventResponseHandler.ErrorListener() {
            @Override
            public void onError(Throwable errorWrapper) {

            }
        });





    }
}
