package kp.ieeeinternal;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener
        ,View.OnClickListener{
    TextView tv,tvLoggedInAs;

    ImageView iconLogout;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences("USER_LOGIN",0);
        final ListView listView = (ListView) findViewById(R.id.listView);
        tv = (TextView) findViewById(R.id.textView);
        String[] items = {"Announcement","Meetings","Events","Message","Other"};
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,R.layout.customlistview,items);
        tvLoggedInAs = (TextView) findViewById(R.id.tvLoggedInAs);
        tvLoggedInAs.setText("Logged in as " + settings.getString("memberRegNo","No data"));
        iconLogout = (ImageView) findViewById(R.id.iconLogout);
        iconLogout.setOnClickListener(this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
    }





    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = (String) parent.getItemAtPosition(position);


        }

    @Override
    public void onClick(View v) {
        settings.getBoolean("hasLoggedIn",false);
        settings.edit().putBoolean("hasLoggedIn",true).commit();
        settings.edit().clear().commit();
        Toast.makeText(MainActivity.this, "Logged Out! Please restart the app", Toast.LENGTH_SHORT).show();
    }
}

