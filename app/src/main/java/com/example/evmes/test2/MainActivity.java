package com.example.evmes.test2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;


import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;





public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;
    private Button mButtonDo;
    private TextView mTextView;
    private ListView mListView;

    private String mJSONURLString = "https://itunes.apple.com/search?term=Michael+jackson";
    List<ViewHolder> listItems = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        // Get the widget reference from XML layout
        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mButtonDo = (Button) findViewById(R.id.btn_do);
        mListView =  findViewById(R.id._dynamic);



        // Set a click listener for button widget
        mButtonDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                Log.d("url string", mJSONURLString);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, mJSONURLString, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {


                                try{

                                    JSONArray array = response.getJSONArray("results");

                                    for (int i = 0; i < array.length(); i++){

                                        System.out.println();

                                        System.out.println(array.getJSONObject(i).get("trackName"));
                                        String trackName =  array.getJSONObject(i).get("trackName").toString();
                                        System.out.println(array.getJSONObject(i).get("artworkUrl100"));
                                        String url = array.getJSONObject(i).get("artworkUrl100").toString();

                                        ViewHolder vh = new ViewHolder(trackName, url);


                                        System.out.println(vh.imageView);
                                        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService
                                                (Context.LAYOUT_INFLATER_SERVICE);
                                        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.list_items, null);
                                        ImageView img = (ImageView) ll.findViewById(R.id.imageView);
                                        System.out.println("image");
                                        System.out.println(img);
                                        vh.imageView = img;



                                        listItems.add(vh);
                                        System.out.println(vh.imageView);

                                    }

                                    CustomAdapter customAdapter = new CustomAdapter(listItems, getApplicationContext());

                                     mListView.setAdapter(customAdapter);




                                }
                                catch (JSONException e){
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error

                            }
                        });

                requestQueue.add(jsonObjectRequest);

            }
        });
    }
}
