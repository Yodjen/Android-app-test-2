package com.android.retrofitjson.retrofit_json_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.retrofitjson.retrofit_json_app.R;
import com.android.retrofitjson.retrofit_json_app.model.Contact;
import com.squareup.picasso.Picasso;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = layoutInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Contact item = getItem(position);

        vh.textViewName.setText(item.getName());
        vh.textViewEmail.setText(item.getEmail());
        Picasso.with(context).load(item.getProfilePic()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
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
