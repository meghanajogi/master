package fragment;


import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import common.Constants;
import common.DatabaseHelper;
import common.Events;
import controller.EditEventFragmentController;
import presenter.EditEventFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditEventFragment extends Fragment implements EditEventFragmentController.View {


    @BindView(R.id.title_edit)
    EditText titleEdit;
    @BindView(R.id.description_edit)
    EditText descriptionEdit;
    @BindView(R.id.startdate_edit)
    TextView startdateEdit;
    @BindView(R.id.enddate_view)
    TextView enddateView;
    String start_date;
    String end_date;

    EditEventFragmentPresenter fragmentPresenter;

    Events events;
    Unbinder unbinder;
    @BindView(R.id.createdBy_view)
    TextView createdbyView;

    public EditEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_event, container, false);
        unbinder = ButterKnife.bind(this, view);
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).show();
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle("Edit event");
        getEventDetails();
        fragmentPresenter = new EditEventFragmentPresenter(getActivity(), events);
        fragmentPresenter.onAttachView(this);
        fragmentPresenter.setEvents();
        return view;


    }

    private void getEventDetails() {

        Bundle args = getArguments();
        if (args != null) {
            events = args.getParcelable(Constants.EVENTS);
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
    public void setTitle(String s) {
        titleEdit.setText(s);

    }

    @Override
    public void setDescription(String s) {
        descriptionEdit.setText(s);

    }

    @Override
    public void setStartDate(String s) {

        startdateEdit.setText(s);

    }

    @Override
    public void setEndDate(String s) {
        enddateView.setText(s);
    }

    @Override
    public void setCreatedBy(String s) {

        createdbyView.setText(s);

    }

    @Override
    public void reloadPage() {
        getFragmentManager().beginTransaction().replace(R.id.main_contain, new EventListFragment()).commit();
    }

    @Override
    public String getTitle() {
        return titleEdit.getText().toString();
    }

    @Override
    public String getDescription() {
        return descriptionEdit.getText().toString();
    }

    @Override
    public String getStartDate() {
        return startdateEdit.getText().toString();
    }

    @Override
    public String getEndDate() {
        return enddateView.getText().toString();
    }

    @Override
    public String getCreatedBy() {
        return createdbyView.getText().toString();
    }

    @Override
    public void setTitleError(String s) {

        titleEdit.setError(s);

    }

    @Override
    public void setDescriptionError(String s) {

        descriptionEdit.setError(s);

    }

    @Override
    public void setSdateError(String s) {
           startdateEdit.setError(s);
    }

    @Override
    public void setEdateError(String s) {
        enddateView.setError(s);

    }

    @OnClick({R.id.startdate_edit, R.id.enddate_view, R.id.done_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.startdate_edit:
                final Calendar startCalendar = Calendar.getInstance();
                int startYear = startCalendar.get(Calendar.YEAR);
                int startMonth = startCalendar.get(Calendar.MONTH);
                int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog startDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        start_date = dayOfMonth + "-" + ++month + "-" + year;
                        startdateEdit.setText(start_date);

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
            case R.id.done_btn:
                fragmentPresenter.updateEvent();
                break;
        }
    }
}
