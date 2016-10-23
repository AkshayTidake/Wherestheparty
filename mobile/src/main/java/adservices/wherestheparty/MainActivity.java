package adservices.wherestheparty;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.Profile;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv;
    Profile p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.gs);
        tv.setOnClickListener(this);

        try{
            PackageInfo info = getPackageManager().getPackageInfo(
                    "adservices.wherestheparty", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:",Base64.encodeToString(md.digest(), Base64.DEFAULT));

            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gs:
                Profile.getCurrentProfile();
                p = Profile.getCurrentProfile();
                if (p != null) {
                    // user has logged in
                    Intent i = new Intent(MainActivity.this, WelcomePage.class);
                    startActivity(i);
                } else {
                    // user has not logged in
                    Intent i = new Intent(MainActivity.this, Login.class);
                    startActivity(i);
                }
                break;
        }
    }
}
