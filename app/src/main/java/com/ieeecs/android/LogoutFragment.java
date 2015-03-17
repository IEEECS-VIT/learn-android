package com.ieeecs.android;

import android.app.Application;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.test.ApplicationTestCase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Krishna Kalubandi on 25-02-2015.
 */
public class LogoutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences settings = getActivity().getSharedPreferences("USER_LOGIN",0);
        settings.getBoolean("hasLoggedIn",false);
        settings.edit().putBoolean("hasLoggedIn",true).commit();
        settings.edit().clear().commit();
        Toast.makeText(getActivity(), "Logged Out! Please restart the app", Toast.LENGTH_SHORT).show();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
     */
    public static class ApplicationTest extends ApplicationTestCase<Application> {
        public ApplicationTest() {
            super(Application.class);
        }
    }
}
