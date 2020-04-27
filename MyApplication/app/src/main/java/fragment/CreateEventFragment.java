package fragment;


import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import common.DatabaseHelper;
import controller.CreateEventFragmentController;
import presenter.CreateEventFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends Fragment implements CreateEventFragmentController.View {


    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.title_view)
    EditText titleView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.description_view)
    EditText descriptionView;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.startdate_view)
    TextView startdateView;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.enddate_view)
    TextView enddateView;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.createdby_view)
    TextView createdbyView;

    String start_date;
    String end_date;

    Unbinder unbinder;
    CreateEventFragmentPresenter fragmentPresenter;
    @BindView(R.id.save_btn)
    Button saveBtn;
    DatabaseHelper db;


    public CreateEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_event, container, false);
        unbinder = ButterKnife.bind(this,view);
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).show();
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle("Add Event");
        db=new DatabaseHelper(getContext());
        Cursor cursor=db.allData();
        while (cursor.moveToNext())

        {
            createdbyView.setText(cursor.getString(0));
        }


        fragmentPresenter = new CreateEventFragmentPresenter(getActivity());
        fragmentPresenter.onAttachView(this);
        return view;
    }


    @OnClick({R.id.startdate_view, R.id.enddate_view, R.id.save_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.startdate_view:
                final Calendar startCalendar = Calendar.getInstance();
                int startYear = startCalendar.get(Calendar.YEAR);
                int startMonth = startCalendar.get(Calendar.MONTH);
                int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog startDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        start_date = dayOfMonth + "-" + ++month + "-" + year;
                       startdateView.setText(start_date);

                    }
                }, startYear, startMonth, startDay);

                startDatePickerDialog.show();
                break;
            case R.id.enddate_view:
                final Calendar endCalendar = Calendar.getInstance();
                int endYear = endCalendar.get(Calendar.YEAR);
                int endMonth = endCalendar.get(Calendar.MONTH);
                int endDay = endCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog endDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        end_date = dayOfMonth + "-" + ++month + "-" + year;
                        enddateView.setText(end_date);

                    }
                }, endYear, endMonth, endDay);
                endDatePickerDialog.show();
                break;
            case R.id.save_btn: fragmentPresenter.createEvent();
                break;
        }
    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentPresenter.onDetachView();
        unbinder.unbind();
    }
    @Override
    public String getTitle() {
        return titleView.getText().toString();
    }

    @Override
    public String getDescription() {
        return descriptionView.getText().toString();
    }

    @Override
    public String getStartDate() {
        return start_date;
    }

    @Override
    public String getEndDate() {
         return end_date;
    }

    @Override
    public String getCreatedBy() {

        return createdbyView.getText().toString();
    }

    @Override
    public void reloadPage() {
        getFragmentManager().beginTransaction().replace(R.id.main_contain, new EventListFragment()).commit();
    }

    @Override
    public void setTitleError(String s) {
         titleView.setError(s);
    }

    @Override
    public void setDescriptionError(String s) {

        descriptionView.setError(s);

    }

    @Override
    public void setStartDateError(String s) {

        startdateView.setError(s);
    }

    @Override
    public void setEndDateError(String s) {
        enddateView.setError(s);

    }


}
