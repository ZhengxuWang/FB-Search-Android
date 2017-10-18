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

import com.example.finalhwnine.Adapter.UsersListAdapter;
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

public class GroupsFragment extends Fragment {

    ArrayList<User> arrayList;
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_groups, container, false);
        arrayList = new ArrayList<>();
        lv = (ListView) view.findViewById(R.id.list);
        final String keyword = getArguments().getString("keyword");
        //Log.d("userfragment", "keyword" + keyword);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Log.d("userfragment", "1");
                new GroupsFragment.ReadJSON().execute("http://sample-env-1.fdxvbppf2e.us-west-2.elasticbeanstalk" +
                        ".com/h8.php?submit_search=" + keyword +"&type=group");
            }
        });
        return view;
    }

    String con;
    private Button btnnext;
    private Button btnprev;
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnnext = (Button) getActivity().findViewById(R.id.nextGroups);

        btnnext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    JSONObject jsonObjectnext = new JSONObject(con);
                    JSONObject jsonnextpage = jsonObjectnext.getJSONObject("paging");
                    String next  = jsonnextpage.getString("next");
                    Log.d("userfragment",con);
                    con = next;
                    arrayList = new ArrayList<>();
                    new GroupsFragment.ReadJSON().execute(next);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        btnprev = (Button) getActivity().findViewById(R.id.prevGroups);

        btnprev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    //Log.d("userfragment",con);
                    JSONObject jsonObjectprev = new JSONObject(con);
                    JSONObject jsonprevpage = jsonObjectprev.getJSONObject("paging");
                    String prev  = jsonprevpage.getString("previous");
                    //Log.d("userfragment","succ");
                    con = prev;
                    arrayList = new ArrayList<>();
                    new GroupsFragment.ReadJSON().execute(prev);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
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
                con = content;
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject usersObject = jsonArray.getJSONObject(i);
                    JSONObject pictureObject = usersObject.getJSONObject("picture");
                    JSONObject dataObject = pictureObject.getJSONObject("data");
                    arrayList.add(new User(dataObject.getString("url"),usersObject.getString("name"),usersObject.getString("id")));
                    //Log.d("userfragment", arrayList + "");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Log.d("userfragment", "arrayList" + arrayList);
            UsersListAdapter adapter = new UsersListAdapter(
                    getActivity(), R.layout.content_users, arrayList
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
