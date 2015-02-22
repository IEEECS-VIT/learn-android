package kp.ieeeinternal;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;

import java.util.ListResourceBundle;

/**
 * Created by Krishna Kalubandi on 22-02-2015.
 */
public class AnnouncementActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomAdapter customAdapter = new CustomAdapter(this);
        setListAdapter(customAdapter);
    }
}
