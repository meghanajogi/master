package controller;

public class CreateEventFragmentController {

    public interface View {


        String getTitle();

        String getDescription();

        String getStartDate();

        String getEndDate();

        String getCreatedBy();

        void reloadPage();

        void setTitleError(String s);

        void setDescriptionError(String s);

        void setStartDateError(String s);

        void setEndDateError(String s);


    }

}
