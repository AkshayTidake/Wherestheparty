package adservices.wherestheparty;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.fragment;
import static com.google.android.gms.analytics.internal.zzy.co;

public class WelcomePage extends AppCompatActivity {

    private String[] navdrawerlistitems;
    private DrawerLayout dl;
    private ListView lv;
    private Toolbar tb;
    private CharSequence dtitle;
    private CharSequence mtitle;
    android.support.v7.app.ActionBarDrawerToggle abdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
            setUpToolbar();
        mtitle=dtitle=getTitle();
        navdrawerlistitems=getResources().getStringArray(R.array.navigation_drawer_items);
        dl=(DrawerLayout)findViewById(R.id.welcome_page);
        lv=(ListView)findViewById(R.id.list);

        DataModel[] draweritem=new DataModel[3];

        draweritem[0]=new DataModel("profile");
        draweritem[1]=new DataModel("organize");
        draweritem[2]=new DataModel("logout");
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
                fragment = new ProfileFragment();
                    break;
                case 1:
                fragment = new OrganizeFragment();
                    break;

                case 2:
                startActivity(new Intent(this, MainActivity.class));
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
                Toast.makeText(WelcomePage.this,"error",Toast.LENGTH_LONG).show();
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
       getSupportActionBar().setIcon(R.drawable.home);

    }
}
