package presenter;

import android.content.Context;
import android.widget.Toast;

import common.EventResponseHandler;

import common.Events;
import controller.EditEventFragmentController;
import request.UpdateEventRequest;
import response.UpdateEventResponse;
import serviceImpl.SessionFacadeImpl;

public class EditEventFragmentPresenter extends BasePresenter<EditEventFragmentController.View>  {
Context context;
Events events;


    public EditEventFragmentPresenter(Context context,Events events) {

        this.context=context;
        this.events=events;
    }

    public  void setEvents()
    {
        getView().setTitle(events.getTitle());
        getView().setDescription(events.getDescription());
        getView().setStartDate(events.getStartDate());
        getView().setEndDate(events.getEndDate());
        getView().setCreatedBy(events.getCreatedBy());
    }

    public void updateEvent() {

        if (isValidate()) {
            UpdateEventRequest request = new UpdateEventRequest();
            request.setId(events.getId());
            request.setTitle(getView().getTitle());
            request.setDescription(getView().getDescription());
            request.setStartDate(getView().getStartDate());
            request.setEndDate(getView().getEndDate());
            request.setCreatedBy(getView().getCreatedBy());

            SessionFacadeImpl.getInstance().updateEvent(request, events.getId(), new EventResponseHandler.ResponseListener() {
                @Override
                public void onSuccess(Object response) {

                    UpdateEventResponse eventResponse = (UpdateEventResponse) response;
                    if (response != null) {
                        Toast.makeText(context, "event updated successfully", Toast.LENGTH_SHORT).show();
                        getView().reloadPage();

                    }
                }
            }, new EventResponseHandler.ErrorListener() {
                @Override
                public void onError(Throwable errorWrapper) {

                }
            });
        }
    }

    private boolean isValidate() {



        Boolean flag=true;
        if(getView().getTitle()==null ||getView().getTitle().equals("")|| getView().getTitle().isEmpty())
        {
            getView().setTitleError("Please enter title");
            flag=false;
        }


        if(getView().getDescription()==null ||getView().getDescription().equals("")|| getView().getDescription().isEmpty())
        {
            getView().setDescriptionError("Please enter descripyion");
            flag=false;
        }
        if(getView().getStartDate()==null ||getView().getStartDate().equals("")|| getView().getStartDate().isEmpty())
        {
            getView().setSdateError("Please enter start date");
            flag=false;
        }
        if(getView().getEndDate()==null ||getView().getEndDate().equals("")|| getView().getEndDate().isEmpty())
        {
            getView().setEdateError("Please enter end date");
            flag=false;
        }
        return flag;
    }

    @Override
    protected EditEventFragmentController.View createDummyView() {
        return new EditEventFragmentController.View() {
            @Override
            public void setTitle(String s) {

            }

            @Override
            public void setDescription(String s) {

            }

            @Override
            public void setStartDate(String s) {

            }

            @Override
            public void setEndDate(String s) {

            }

            @Override
            public void setCreatedBy(String s) {

            }

            @Override
            public void reloadPage() {

            }

            @Override
            public String getTitle() {
                return null;
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public String getStartDate() {
                return null;
            }

            @Override
            public String getEndDate() {

                return null;
            }

            @Override
            public String getCreatedBy() {
                return null;
            }

            @Override
            public void setTitleError(String s) {

            }

            @Override
            public void setDescriptionError(String s) {

            }

            @Override
            public void setSdateError(String s) {

            }

            @Override
            public void setEdateError(String s) {

            }
        };
    }
}
