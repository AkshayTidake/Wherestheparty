package adservices.wherestheparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class Login extends FragmentActivity implements View.OnClickListener {
    TextView tv;
    Profile p;
    Button home;
    LoginButton loginButton;
    CallbackManager callbackManager;
    private static final String TAG = "FacebookLogin";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login);
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
            loginButton = (LoginButton) findViewById(R.id.login_button);
            LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.d(TAG, "facebook:onSuccess:" + loginResult);
                    startActivity(new Intent(Login.this,Verify.class));

                }

                @Override
                public void onCancel() {
                    Log.d(TAG, "facebook:onCancel");
                }

                @Override
                public void onError(FacebookException error) {
                    Log.d(TAG, "facebook:onError", error);
                }
            });

            tv = (TextView) findViewById(R.id.hiw);
            home = (Button)findViewById(R.id.home);
            tv.setOnClickListener(this);
            home.setOnClickListener(this);

        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }


        @Override
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.hiw:
                    Intent i = new Intent(this, How_Its_Work.class);
                    startActivity(i);
                    break;
                case R.id.login_button:
                    LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile","user_birthday", "user_friends"));
                    break;
                case R.id.home:
                    Profile.getCurrentProfile();
                    p = Profile.getCurrentProfile();
                    if (p != null) {
                        // user has logged in
                        Intent in = new Intent(Login.this, WelcomePage.class);
                        startActivity(in);
                        finish();
                    } else {
                        // user has not logged in
                        Toast.makeText(this,"Please Login your account",Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }


}



