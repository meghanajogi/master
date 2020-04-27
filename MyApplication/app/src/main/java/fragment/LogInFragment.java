package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import activity.MainActivity;
import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import controller.LogInFragmentController;
import presenter.LogInFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment implements LogInFragmentController.View {


    @BindView(R.id.username_edit)
    EditText usernameEdit;
    Unbinder unbinder;
    LogInFragmentPresenter fragmentPresenter;

    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);
        unbinder = ButterKnife.bind(this, view);
         fragmentPresenter=new LogInFragmentPresenter(getActivity());
         fragmentPresenter.onAttachView(this);
        return view;
    }

    @OnClick(R.id.signIn_btn)
    public void onViewClicked() {
        fragmentPresenter.loginOperation();

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
    public String getName() {
        return usernameEdit.getText().toString();
    }



    @Override
    public void moveNext() {
        Intent intent=new Intent(getActivity(), MainActivity.class);
        startActivity(intent);


    }
}
