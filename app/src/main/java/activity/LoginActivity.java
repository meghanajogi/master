package activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import fragment.LogInFragment;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        getSupportFragmentManager().beginTransaction().replace(R.id.login_contain, new LogInFragment()).commit();
    }
}
