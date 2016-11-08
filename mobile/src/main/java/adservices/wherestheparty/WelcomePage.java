package adservices.wherestheparty;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;


public class WelcomePage extends AppCompatActivity {
    MediaPlayer mp;
    private String[] navdrawerlistitems;
    private DrawerLayout dl;
    private ListView lv;
    private Toolbar tb;
    private CharSequence dtitle;
    private CharSequence mtitle;
    android.support.v7.app.ActionBarDrawerToggle abdt;
    Login m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.welcome_page);

        mp = MediaPlayer.create(this, R.raw.c);
        mp.start();

        m = new Login();
            setUpToolbar();
        mtitle=dtitle=getTitle();
        navdrawerlistitems=getResources().getStringArray(R.array.navigation_drawer_items);
        dl=(DrawerLayout)findViewById(R.id.welcome_page);
        lv=(ListView)findViewById(R.id.list);

        DataModel[] draweritem=new DataModel[4];

        draweritem[0]=new DataModel("Home");
        draweritem[1]=new DataModel("Profile");
        draweritem[2]=new DataModel("Friends List");
        draweritem[3]=new DataModel("Log Out");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter da=new DrawerItemCustomAdapter(this,R.layout.list_view_item_row,draweritem);
        lv.setAdapter(da);
        lv.setOnItemClickListener(new DrawerItemClickListener());
        dl=(DrawerLayout)findViewById(R.id.welcome_page);
        dl.addDrawerListener(abdt);
        setUpDrawerToggle();

    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            selectItem(i);
        }
    }

        private void selectItem(int position){
            Fragment fragment=null;
            switch(position) {
                case 0:
                    startActivity(new Intent(this, WelcomePage.class));
                    finish();
                    break;
                case 1:
                    fragment = new ProfileFragment();

                    break;

                case 2:
                    fragment = new OrganizeFragment();

                    break;
                case 3:
                    LoginManager.getInstance().logOut();
                    mp.stop();
                    startActivity(new Intent(this, Login.class));
                    finish();
                    break;

            }



            if(fragment != null){
                FragmentManager fm=getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,fragment).commit();
                lv.setItemChecked(position,true);
                lv.setSelection(position);
                setTitle(navdrawerlistitems[position]);
                dl.closeDrawer(lv);
            }
            else{

            }
        }


    @Override
    public boolean onOptionsItemSelected(MenuItem menu){
        if(abdt.onOptionsItemSelected(menu)) {
            return true;
        }
        return super.onOptionsItemSelected(menu);
    }
        @Override
      public void setTitle(CharSequence title){
            mtitle=title;
            getSupportActionBar().setTitle(mtitle);
        }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        abdt.syncState();
    }

    void setUpDrawerToggle(){

        abdt=new android.support.v7.app.ActionBarDrawerToggle(this,dl,tb,R.string.app_name,R.string.app_name);
        abdt.syncState();

    }

    public void setUpToolbar(){
        tb=(Toolbar)findViewById(R.id.tool);
        setSupportActionBar(tb);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
