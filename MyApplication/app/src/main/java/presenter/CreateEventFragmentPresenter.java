package presenter;

import android.content.Context;
import android.widget.Toast;

import common.EventResponseHandler;

import java.util.Random;

import common.DatabaseHelper;
import controller.CreateEventFragmentController;
import request.CreateEventRequest;
import response.CreateEventResponse;
import serviceImpl.SessionFacadeImpl;


public class CreateEventFragmentPresenter extends BasePresenter<CreateEventFragmentController.View> {
 Context context;
 DatabaseHelper db;

    public CreateEventFragmentPresenter(Context context) {
        this.context=context;
    }


    public  void createEvent() {
        if (emptyFieldError()) {
            CreateEventRequest request = new CreateEventRequest();
            final int random = new Random().nextInt(61) + 10;
            request.setId((random));
            request.setTitle(getView().getTitle());
            request.setDescription(getView().getDescription());
            request.setStartDate(getView().getStartDate());
            request.setEndDate(getView().getEndDate());
            request.setCreatedBy(getView().getCreatedBy());


            SessionFacadeImpl.getInstance().createEvent(request, new EventResponseHandler.ResponseListener() {
                @Override
                public void onSuccess(Object response) {
                    CreateEventResponse eventResponse = (CreateEventResponse) response;
                    if (response != null) {
                        Toast.makeText(context, "Event created successfully", Toast.LENGTH_SHORT).show();
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

    private boolean emptyFieldError() {
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
            getView().setStartDateError("Please enter start date");
            flag=false;
        }
        if(getView().getEndDate()==null ||getView().getEndDate().equals("")|| getView().getEndDate().isEmpty())
        {
            getView().setEndDateError("Please enter end date");
            flag=false;
        }
     return flag;
    }

    @Override
    protected CreateEventFragmentController.View createDummyView() {
        return new CreateEventFragmentController.View() {
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
            public void reloadPage() {

            }

            @Override
            public void setTitleError(String s) {

            }

            @Override
            public void setDescriptionError(String s) {

            }

            @Override
            public void setStartDateError(String s) {

            }

            @Override
            public void setEndDateError(String s) {

            }


        };
    }
}
