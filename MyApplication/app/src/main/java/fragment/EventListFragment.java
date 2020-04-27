package fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;
import java.util.Objects;

import adapter.EventListFragmentAdapter;
import presenter.EventListFragmentAdapterPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import common.Constants;
import common.DatabaseHelper;
import common.Events;
import controller.EventListFragmentController;
import presenter.EventListFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment implements EventListFragmentController.View, EventListFragmentAdapter.DataListener {
    Unbinder unbinder;
    @BindView(R.id.event_recycle)
    RecyclerView eventRecycle;
    EventListFragmentAdapterPresenter adapterPresenter;
    EventListFragmentAdapter fragmentAdapter;
    EventListFragmentPresenter fragmentPresenter;
    DatabaseHelper db;
    @BindView(R.id.user_name)
    TextView userName;


    public EventListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).show();
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle("Events");
        db = new DatabaseHelper(getContext());
        Cursor cursor = db.allData();
        while (cursor.moveToNext()) {
            userName.setText(cursor.getString(0));
        }

        fragmentPresenter = new EventListFragmentPresenter(getActivity());
        fragmentPresenter.onAttachView(this);
        fragmentPresenter.getAllEvent();
        return view;
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

    @OnClick(R.id.create_btn)
    public void onViewClicked() {
        getFragmentManager().beginTransaction().replace(R.id.main_contain, new CreateEventFragment()).addToBackStack(null).commit();

    }

    @Override
    public void setUpRecyclerView(List<Events> eventsList) {
        adapterPresenter = new EventListFragmentAdapterPresenter(eventsList);
        fragmentAdapter = new EventListFragmentAdapter(adapterPresenter, getActivity(), this);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        if (eventRecycle != null) {
            eventRecycle.setLayoutManager(layoutManager);
            eventRecycle.setItemAnimator(new DefaultItemAnimator());
            eventRecycle.setAdapter(fragmentAdapter);


        }


    }

    @Override
    public void editOperation(Events eventId) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.EVENTS, eventId);
        EditEventFragment frag = new EditEventFragment();
        frag.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_contain, frag).addToBackStack(null).commit();
    }

    @Override
    public void deleteOperation(Events eventId) {
        fragmentPresenter.deleteEvent(eventId);
    }
}
