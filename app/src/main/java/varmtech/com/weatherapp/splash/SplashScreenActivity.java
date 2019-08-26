package varmtech.com.weatherapp.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import varmtech.com.weatherapp.AppApplication;
import varmtech.com.weatherapp.R;
import varmtech.com.weatherapp.databases.dao.EmployeeDao;
import varmtech.com.weatherapp.databases.database.AppDatabase;
import varmtech.com.weatherapp.general.view.activity.MainActivity;
import varmtech.com.weatherapp.utils.DeviceUtils;

public class SplashScreenActivity extends AppCompatActivity {
    private static final String TAG = SplashScreenActivity.class.getName();

    // Views
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configurationActionBar(getWindow(), getSupportActionBar());
        setContentView(R.layout.activity_splash_screen);
        isRedirectNextScreen();

        AppDatabase db = AppApplication.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();
//        Employee employee = new Employee();
//        employee.id = 1;
//        employee.name = "John Smith";
//        employee.salary = 10000;
//        employeeDao.insert(employee);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRedirectNextScreen();
    }

    private void isRedirectNextScreen() {
        new Handler()
                .postDelayed(this::handleNetworkState, 3000);
    }

    private void handleNetworkState() {
        if (!DeviceUtils.isNetworkConnectionAvailable(this)) {
            Log.i(TAG, "handleNetworkState: have not network connection");
            showErrorDialog(getString(R.string.splash_error_dialog_title),
                    getString(R.string.splash_error_dialog_message));
        } else {
            Log.i(TAG, "handleNetworkState: have network connection");
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    private void configurationActionBar(Window window, ActionBar actionBar) {
        if (window != null && actionBar != null) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            actionBar.hide();
        }
    }

    private void showErrorDialog(String dialogTitle, String dialogMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(dialogMessage)
                .setTitle(dialogTitle)
                .setCancelable(false)
                .setNegativeButton(R.string.splash_negative_btn_text, (dialogInterface, i) -> {
                    alertDialog.dismiss();
                    alertDialog.cancel();
                }).setPositiveButton(R.string.splash_positive_btn_text, (dialogInterface, i) -> {
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        });
        alertDialog = builder.create();
        alertDialog.show();
    }
}
