package varmtech.com.weatherapp.managers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class OwnFragmentManager {
    public static void addFragment(int resId, FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null) {
            fragmentManager
                    .beginTransaction()
                    .add(resId, fragment, fragment.getClass().getName())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
