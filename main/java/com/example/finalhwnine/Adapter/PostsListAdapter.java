package com.example.finalhwnine.Adapter;

/**
 * Created by User on 4/25/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalhwnine.Model.Album;
import com.example.finalhwnine.Model.Post;
import com.example.finalhwnine.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by User on 4/24/2017.
 */

public class PostsListAdapter extends ArrayAdapter<Post> {
    ArrayList<Post> posts;
    Context context;
    int resource;
    public PostsListAdapter(Context context, int resource, ArrayList<Post> posts) {
        super(context, resource, posts);
        this.posts = posts;
        this.context = context;
        this.resource = resource;
        //Log.d("debug111","success");
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.content_posts,null,true);
        }
        final Post post = getItem(position);
        TextView textName = (TextView)convertView.findViewById(R.id.textPostsName);
        textName.setText(post.getContent());
        return convertView;
    }
}