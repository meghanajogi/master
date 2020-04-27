package presenter;

import android.content.Context;
import android.widget.Toast;

import common.DatabaseHelper;
import controller.LogInFragmentController;

public class LogInFragmentPresenter extends BasePresenter<LogInFragmentController.View> {

Context context;
DatabaseHelper db;

public LogInFragmentPresenter(Context context)
{
    this.context=context;
}


public void loginOperation()
{
    db=new DatabaseHelper(context);
    String name=getView().getName();
    if(name.equals(""))
    {
        Toast.makeText(context, "Field is empty", Toast.LENGTH_SHORT).show();
    }
    else
    {

            Boolean checkuser = db.insert(name);
            if (checkuser == true) {
                Toast.makeText(context, "Registered successfully", Toast.LENGTH_SHORT).show();
                getView().moveNext();
            }
        }



    
}




    @Override
    protected LogInFragmentController.View createDummyView() {
        return new LogInFragmentController.View() {
            @Override
            public String getName() {
                return null;
            }


            @Override
            public void moveNext() {

            }
        };
    }
}
