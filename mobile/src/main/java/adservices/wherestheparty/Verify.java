package adservices.wherestheparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

public class Verify extends AppCompatActivity implements View.OnClickListener {

    ProfilePictureView profilePictureView;
    TextView e, et;
    Profile p;
    Button b;
    String l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_verify);

        profilePictureView = (ProfilePictureView) findViewById(R.id.prp);
        e = (TextView) findViewById(R.id.name);
        et = (TextView) findViewById(R.id.num);
        b = (Button)findViewById(R.id.done);

        b.setOnClickListener(this);
        l = p.getCurrentProfile().getId();
        profilePictureView.setProfileId(l);
        e.setText(p.getCurrentProfile().getFirstName());
        et.setText(p.getCurrentProfile().getLastName());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.done:
                startActivity(new Intent(Verify.this,WelcomePage.class));
                finish();
                break;
        }
    }
}




