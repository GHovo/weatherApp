package varmtech.com.weatherapp.general.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import varmtech.com.weatherapp.R;
import varmtech.com.weatherapp.general.view.fragment.GeneralFragment;
import varmtech.com.weatherapp.managers.OwnFragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OwnFragmentManager.addFragment(R.id.fragment_container, getSupportFragmentManager(),  new GeneralFragment());
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            if (fragment instanceof GeneralFragment) {
                finish();
            }
        }
    }
}
