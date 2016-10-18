package adservices.wherestheparty;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Akshay on 10/16/2016.
 */

public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {
    Context context;
    int reource;
    DataModel []data=null;
    public DrawerItemCustomAdapter(Context context, int resource, DataModel[] data) {
        super(context, resource, data);
        this.context=context;
        this.reource=resource;
        this.data=data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listitem=convertView;

        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        listitem=inflater.inflate(reource,parent,false);

        TextView tv=(TextView)listitem.findViewById(R.id.tv);

        DataModel folder=data[position];
        tv.setText(folder.item);

        return listitem;


    }
}
