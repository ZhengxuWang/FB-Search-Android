package com.example.finalhwnine.Adapter;

/**
 * Created by User on 4/25/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalhwnine.Model.User;
import com.example.finalhwnine.R;
import com.example.finalhwnine.Result.ResultActivity;
import com.example.finalhwnine.Result.ResultDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by User on 4/24/2017.
 */

public class UsersListAdapter extends ArrayAdapter<User> {
    ArrayList<User> users;
    Context context;
    int resource;
    SharedPreferences favorite;
    SharedPreferences.Editor editor;
    public UsersListAdapter(Context context, int resource, ArrayList<User> users) {
        super(context, resource, users);
        this.users = users;
        this.context = context;
        this.resource = resource;
        //Log.d("debug111","success");
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.content_users,null,true);
        }
        favorite = context.getSharedPreferences("favorite",MODE_PRIVATE);
        editor = favorite.edit();
        final User user = getItem(position);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageViewUsers);
        Picasso.with(context).load(user.getImage()).into(imageView);
        TextView textName = (TextView)convertView.findViewById(R.id.textUsersName);
        textName.setText(user.getName());
        final ImageView btn_fav = (ImageView) convertView.findViewById(R.id.btn_fav);
        final ImageView btn_details = (ImageView)convertView.findViewById(R.id.btn_details);
        if (!favorite.getString(user.getId(),"null").equals("null")) {
            btn_fav.setImageResource(R.mipmap.ic_fav_on);
        } else {
            btn_fav.setImageResource(R.mipmap.ic_fav_off);
        }
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("adapter", "onclick");
                //Toast.makeText(context, "id:" + user.getId(), Toast.LENGTH_SHORT).show();
                if(favorite.getString(user.getId(),"null").equals("null")){
                    editor.putString(user.getId(),user.getName()+"+"+user.getImage());
                    editor.commit();
                    btn_fav.setImageResource(R.mipmap.ic_fav_on);
                }else{
                    editor.remove(user.getId());
                    editor.commit();
                    btn_fav.setImageResource(R.mipmap.ic_fav_off);
                }
            }
        });

        btn_details.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v){
               Intent intent = new Intent(getContext(), ResultDetailActivity.class);
               intent.putExtra("id", user.getId());
               getContext().startActivity(intent);
           }
        });

        return convertView;
    }
}