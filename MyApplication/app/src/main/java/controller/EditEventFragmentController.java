package controller;

public class EditEventFragmentController {

    public interface View
    {
          void setTitle(String s);

          void setDescription(String s);

          void setStartDate(String s);

         void setEndDate(String s);

         void setCreatedBy(String s);

         void reloadPage();

         String getTitle();

         String getDescription();

         String getStartDate();

         String getEndDate();

         String getCreatedBy();


        void setTitleError(String s);

        void setDescriptionError(String s);

        void setSdateError(String s);

        void setEdateError(String s);
    }
}
