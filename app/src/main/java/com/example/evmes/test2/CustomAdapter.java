package com.example.evmes.test2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.evmes.test2.ViewHolder;

import java.util.List;


public class CustomAdapter extends ArrayAdapter<ViewHolder> {

    //the hero list that will be displayed
    private List<ViewHolder> viewList;

    //the context object
    private Context mCtx;

    //here we are getting the herolist and context 
    //so while creating the object of this adapter class we need to give herolist and context
    public CustomAdapter(List<ViewHolder> viewList, Context mCtx) {
        super(mCtx, R.layout.list_items, viewList);
        this.viewList = viewList;
        this.mCtx = mCtx;
    }

    //this method will return the list item 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        //TextView textViewImageUrl = listViewItem.findViewById(R.id.textViewImageUrl);

        ImageView img = listViewItem.findViewById(R.id.imageView);


        //Getting the hero for the specified position
        ViewHolder view = viewList.get(position);

        //setting hero values to textviews
        textViewName.setText(view.getTxtTitle());
        //textViewImageUrl.setText(view.getUrl());

        view.imageView = img;

        Glide
            .with(mCtx)
            .load(view.getUrl())
            //.centerCrop()
            //.placeholder( R.id._dynamic)
            .into(view.imageView);



        //returning the listitem
        return listViewItem;
    }



}