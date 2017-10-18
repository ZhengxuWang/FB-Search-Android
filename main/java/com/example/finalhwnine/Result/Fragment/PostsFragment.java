package com.example.finalhwnine.Result.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.finalhwnine.Adapter.PostsListAdapter;
import com.example.finalhwnine.Adapter.UsersListAdapter;
import com.example.finalhwnine.Model.Post;
import com.example.finalhwnine.Model.User;
import com.example.finalhwnine.R;

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

public class PostsFragment extends Fragment {

    ArrayList<Post> arrayList;
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_albums, container, false);
        arrayList = new ArrayList<>();
        lv = (ListView) view.findViewById(R.id.list);
        final String id = getArguments().getString("id");
       // Log.d("userfragment", "keyword" + keyword);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Log.d("userfragment", "1");
                new ReadJSON().execute("http://sample-env-1.fdxvbppf2e.us-west-2.elasticbeanstalk" +
                        ".com/h8.php?user_id="+id);
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
                JSONObject jsonObject = new JSONObject(content);
                //Log.d("userfragment", "content"+content);
                JSONObject jsonPost = jsonObject.getJSONObject("posts");
                JSONArray jsonArray = jsonPost.getJSONArray("data");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject usersObject = jsonArray.getJSONObject(i);
                    arrayList.add(new Post(usersObject.getString("message")));
                    //Log.d("userfragment", arrayList + "");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Log.d("userfragment", "arrayList" + arrayList);
            PostsListAdapter adapter = new PostsListAdapter(
                    getActivity(), R.layout.content_posts, arrayList
            );
            lv.setAdapter(adapter);
        }
    }


    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
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
}



