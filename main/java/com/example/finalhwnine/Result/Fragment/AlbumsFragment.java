package com.example.finalhwnine.Result.Fragment;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalhwnine.Model.Album;
import com.example.finalhwnine.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by User on 4/25/2017.
 */

public class AlbumsFragment extends Fragment {

    ArrayList<Album> arrayList;
    ListView lv;
    private int selectPosition = -1;
    AlbumsListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_albums, container, false);
        arrayList = new ArrayList<>();

        lv = (ListView) view.findViewById(R.id.list);
        final String id = getArguments().getString("id");
        Log.d("adapter","id="+id);
       // Log.d("userfragment", "keyword" + keyword);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Log.d("userfragment", "1");

                new ReadJSON().execute("http://sample-env-1.fdxvbppf2e.us-west-2.elasticbeanstalk" +
                        ".com/h8.php?user_id="+id);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectPosition = position;
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }



    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
           // Log.d("userfragment", "readURL");
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                //Log.d("adapter","okok");
                JSONObject jsonObject = new JSONObject(content);
                //Log.d("adapter","1");
                //Log.d("userfragment", "content"+content);
                JSONObject jsonAlbum = jsonObject.getJSONObject("albums");
                //Log.d("adapter","2");
                JSONArray jsonArray = jsonAlbum.getJSONArray("data");
                //Log.d("adapter","3");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject usersObject = jsonArray.getJSONObject(i);
                    JSONObject photoObject = usersObject.getJSONObject("photos");
                    JSONArray photoJson = photoObject.getJSONArray("data");
                    JSONObject imageone = photoJson.getJSONObject(0);
                    JSONObject imagetwo = photoJson.getJSONObject(1);
                    //JSONObject dataObject = pictureObject.getJSONObject("data");
                    //Log.d("userfragment", arrayList + "");
                    //Log.d("adapter",usersObject.getString("name"));
                    //Log.d("adapter",imageone.getString("picture"));
                    //Log.d("adapter",imagetwo.getString("picture"));

                    arrayList.add(new Album(usersObject.getString("name"),imageone.getString("picture"),imagetwo.getString("picture")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Log.d("userfragment", "arrayList" + arrayList);
            adapter = new AlbumsListAdapter(
                    getActivity(), R.layout.content_albums, arrayList
            );
            lv.setAdapter(adapter);
        }
    }


    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            Log.d("adapter","url="+url);
            //Log.d("userfragment", "url" + url);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            //Log.d("userfragment", "BufferedReader" + bufferedReader);
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public class AlbumsListAdapter extends ArrayAdapter<Album> {
        ArrayList<Album> albums;
        Context context;
        int resource;
        public AlbumsListAdapter(Context context, int resource, ArrayList<Album> albums) {
            super(context, resource, albums);
            this.albums = albums;
            this.context = context;
            this.resource = resource;
            //Log.d("debug111","success");
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){

            if(convertView==null){
                LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.content_albums,null,true);
            }
            final Album album = getItem(position);
            ImageView imageView_one = (ImageView)convertView.findViewById(R.id.detail_photo_one);
            Picasso.with(context).load(album.getImage_one()).into(imageView_one);
            ImageView imageView_two = (ImageView)convertView.findViewById(R.id.detail_photo_two);
            Picasso.with(context).load(album.getImage_two()).into(imageView_two);
            if(selectPosition == position){
                if (imageView_one.getVisibility() == View.GONE) {
                    imageView_one.setVisibility(View.VISIBLE);
                    imageView_two.setVisibility(View.VISIBLE);
                } else {
                    imageView_one.setVisibility(View.GONE);
                    imageView_two.setVisibility(View.GONE);
                }

            }
            TextView textName = (TextView)convertView.findViewById(R.id.textAlbumsName);
            textName.setText(album.getName());
            return convertView;
        }
    }
}



