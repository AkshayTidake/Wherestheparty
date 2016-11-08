package adservices.wherestheparty;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class How_Its_Work extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mp;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how__its__work);
        mp = MediaPlayer.create(this, R.raw.n);
        mp.start();

        b= (Button)findViewById(R.id.b);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b:
                mp.stop();
                Intent in = new Intent(this, Login.class);
                startActivity(in);
                finish();
                break;
        }
    }
}
