package com.example.myfirsttest.law_source;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
/*import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;*/


/**
 * Created by adhoc on 12/10/16.
 */
public class Ipcsection extends Fragment {
    ViewFlipper flipper;
    private View thisFragment;
    View myView;
    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;
    Button submit;
    String web_response,resp="",userid="",statuss="";
    ProgressDialog pdialog;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    String id;
    ImageView prof;
    ListView listitem;
    String n1,n2,n3,n4,n5,n6,n7,n8,n9,n10;
    ArrayList<String> date;

    ArrayList<String> time;
    ArrayList<String> type;
    ArrayList<String> conc;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.ipcsection, container, false);



    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        listitem=(ListView)getView().findViewById(R.id.list1);
        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ed = sp.edit();

            //  Toast.makeText(Travel.this, "Available", Toast.LENGTH_LONG).show();
            userid=sp.getString("user_id","");
            new AsyncHttpTask().execute("http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/ipc.jsp");
            //Log.d("","http://"+sp.getString("ipaddrress","")+"/Digitalbus/conductorwebservice/gethistory.jsp?userid="+userid);

    }
    public class AsyncHttpTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {


            String result = "0";
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setConnectTimeout(2 * 60 * 1000);

                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200)
                {
                    BufferedReader r = new BufferedReader(
                            new InputStreamReader(
                                    urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line).append("\n");
                    }

                    parseResult(response.toString());
                    web_response=response.toString();
                    result = "1"; // Successful
                } else
                {
                    result = "0"; // "Failed to fetch data!";
                }
            }

            catch (Exception e) {

                e.printStackTrace();
                result = "3";
            }
            return result; // "Failed to fetch data!";
        }
        @Override
        protected void onPreExecute () {

            pdialog = new ProgressDialog(getActivity());
            pdialog.setMessage("Updating....");
            pdialog.setCancelable(false);
            pdialog.show();

        }

        @Override
        protected void onPostExecute (String s){
            super.onPostExecute(s);
            pdialog.dismiss();
            Log.d("'",resp);
            Transadaptor bp=new Transadaptor(getContext(),date,time,type);
            listitem.setAdapter(bp);





        }
    }
    private void parseResult(String response_string) {


        try
        {

            date=new ArrayList<String>() ;

            time =new ArrayList<String>();
            type=new ArrayList<String>();


            JSONArray ja=new JSONArray(response_string);
            JSONObject jo;

            int i=0;
            while(i<ja.length()) {
                jo = ja.getJSONObject(i);
                n1 = jo.getString("section");
                n2 = jo.getString("description");
                n3=jo.getString("expanssion");

                date.add(n1);
                time.add(n2);
                type.add(n3);
                i++;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}


