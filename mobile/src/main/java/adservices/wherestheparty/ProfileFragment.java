package adservices.wherestheparty;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Akshay on 10/16/2016.
 */

public class ProfileFragment extends Fragment {

      public ProfileFragment() {
      }

        @Override
          public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

              View rootView=inflater.inflate(R.layout.fragment_connect,container,false);
              return rootView;

          }

}
