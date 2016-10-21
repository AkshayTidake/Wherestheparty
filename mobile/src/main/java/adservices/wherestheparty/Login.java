package adservices.wherestheparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
public class Login extends FragmentActivity implements View.OnClickListener {
TextView tv;
LoginButton loginButton;
    CallbackManager callbackManager;
    private static final String TAG = "FacebookLogin";
@Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login);



    callbackManager = CallbackManager.Factory.create();
    loginButton = (LoginButton)findViewById(R.id.login_button);
    loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Intent i = new Intent(Login.this,WelcomePage.class);
            startActivity(i);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    });

    tv = (TextView)findViewById(R.id.hiw);
    tv.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hiw:
                Intent i = new Intent(this,How_Its_Work.class);
                startActivity(i);
                break;
            case R.id.login_button:
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
                break;
        }
    }
}
