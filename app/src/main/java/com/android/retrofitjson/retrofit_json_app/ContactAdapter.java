package com.android.retrofitjson.retrofit_json_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by batsa on 16.02.2017.
 */

public class ContactAdapter extends ArrayAdapter<Contact>{

    List<Contact> contactList;
    Context context;
    private LayoutInflater layoutInflater;

    public ContactAdapter(Context context, List<Contact> objects) {
        super(context,0, objects);
        this.context = context;
        this.layoutInflater = layoutInflater.from(context);
        contactList = objects;
    }

    public Contact getItem(int position){
        return contactList.get(position);
    }

    private static class ViewHolder{
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textViewName;
        public final TextView textViewEmail;

        public ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textViewName, TextView textViewEmail) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewName = textViewName;
            this.textViewEmail = textViewEmail;
        }

        public static ViewHolder create(RelativeLayout rootView){
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewEmail = (TextView) rootView.findViewById(R.id.textViewEmail);
            return new ViewHolder(rootView,imageView,textViewName,textViewEmail);
        }
    }


}
