package kp.ieeeinternal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.HashMap;

/**
 * Created by kp on 30-01-2015.
 */
public class LoginActivity extends Activity {
    private static final String LOGIN_URL = "http://vitacademics-rel.herokuapp.com/api/vellore/login/auto?";
    public static final String PREFS_TITLE = "USER_LOGIN";
    public static final String LOGIN_SUCCESS = "Successful execution";
    public static final String LOGIN_FAIL = "Invalid credentials or captcha";
    public static final String LOGIN_EXCEPTION = "parse_error";
    public static final String LOGIN_SERVER_DOWN = "We are having server problems! Try Later";
    String regno;
    String dob;
    ProgressDialog mProgressDialogue;
    TextView t1;
    HashMap<String,String> loginDetails;
    Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        SharedPreferences settings = getSharedPreferences(PREFS_TITLE,0);
        boolean hasLoggedIn = settings.getBoolean("hasLoggedIn",false);
        intent = new Intent(LoginActivity.this,MainActivity.class);
        if(hasLoggedIn){
            startActivity(intent);
            LoginActivity.this.finish();
        }
        loginDetails = new HashMap<String,String>();
        final EditText e1 = (EditText) findViewById(R.id.regno);
        final EditText e2 = (EditText) findViewById(R.id.dob);
        t1 = (TextView) findViewById(R.id.loginStatus);
        Button loginb = (Button) findViewById(R.id.loginb);
        loginb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regno = e1.getText().toString().trim();
                dob = e2.getText().toString().trim();

                if(regno.equals("") || dob.equals(""))
                    Toast.makeText(LoginActivity.this,"Fields cannot be empty",Toast.LENGTH_SHORT).show();
                else
                    new ParentLogin(LOGIN_URL+"regno="+regno+"&dob="+dob).execute();
            }
        });


    }
    public String jsonParse(String jsonLogin){
        try {
            JSONObject loginDetails = new JSONObject(jsonLogin);
            String message = loginDetails.getJSONObject("status").get("message").toString();
            return message;
        }catch(Exception e){
            Log.d("Error parsing json","code:00");
        }
        return "parse_error";
    }
    private class ParentLogin extends AsyncTask<Void,Void,Void>{
        private String jsonLogin;
        private String memberUrl;
        public ParentLogin(String memberUrl){
            this.memberUrl = memberUrl;
            Log.d("memUrl",memberUrl);
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressDialogue.dismiss();
            if(jsonParse(jsonLogin).equals(LOGIN_SUCCESS)){
                SharedPreferences settings = getSharedPreferences(PREFS_TITLE,0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("hasLoggedIn",true);
                editor.putString("memberRegNo",regno);
                editor.commit();
                Toast.makeText(LoginActivity.this,"Logged In!",Toast.LENGTH_SHORT).show();
                startActivity(intent);
                LoginActivity.this.finish();
            }
            else if(jsonParse(jsonLogin).equals(LOGIN_FAIL)){
                Toast.makeText(LoginActivity.this,LOGIN_FAIL,Toast.LENGTH_LONG).show();
            }
            else if(jsonParse(jsonLogin).equals("parse_error")){
                Toast.makeText(LoginActivity.this,"Check your network settings",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(LoginActivity.this,LOGIN_SERVER_DOWN,Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPreExecute()
        {
           super.onPreExecute();

            mProgressDialogue = new ProgressDialog(LoginActivity.this);
            mProgressDialogue.setMessage("Logging In");
            mProgressDialogue.setTitle("IEEE-Internal");
            mProgressDialogue.setIndeterminate(false);
            mProgressDialogue.show();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            try{
                jsonLogin = Jsoup.connect(memberUrl).ignoreContentType(true).
                        timeout(20000).execute().body();
            }catch(Exception e){
                Log.d("Jsoup",e.getMessage());
            }
            return null;
        }
    }
}
