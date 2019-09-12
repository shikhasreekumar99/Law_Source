package com.example.myfirsttest.law_source;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.ArrayList;

/*import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;*/


/**
 * Created by adhoc on 12/10/16.
 */
public class Court extends Fragment {
    ViewFlipper flipper;
    private View thisFragment;
    View myView;
    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;
    String web_response,resp="",userid="",n1,n2,n3,n4,n5;
    ProgressDialog pdialog;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    ListView listitem;
    AutoCompleteTextView textView;
    ArrayList<String> hospital;
    ArrayList<String> name;

    ArrayList<String> address;
    ArrayList<String> mob;
    ArrayAdapter<String> adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.courtsearch, container, false);



    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ed = sp.edit();
        listitem=(ListView)getView().findViewById(R.id.list1);
        textView = (AutoCompleteTextView)getView().findViewById(R.id.autoCompleteTextView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
            new AsyncHttpTask().execute("http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/getdistrict.jsp");



        super.onActivityCreated(savedInstanceState);
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
            pdialog.setMessage("Processing....");
            pdialog.setCancelable(false);
            pdialog.show();

        }

        @Override
        protected void onPostExecute (String s){
            super.onPostExecute(s);
            pdialog.dismiss();
            Log.d("'",resp);

            adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line, hospital);

            textView.setAdapter(adapter);
            textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    textView.setSelection(i);
                    String selState1 = adapterView.getItemAtPosition(i).toString();
                    String selState="";
                    try {
                        selState = URLEncoder.encode(selState1, "UTF-8");



                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    new AsyncHttpTask1().execute("http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/court.jsp?district="+selState);

                }
            });
        }
    }
    private void parseResult(String response_string) {


        try
        {
            hospital=new ArrayList<String>() ;
            JSONArray ja=new JSONArray(response_string);
            JSONObject jo;



            // resp=jo.getString("resp");
            int i=0;
            while(i<ja.length()) {
                jo = ja.getJSONObject(i);
                n1 = jo.getString("district");
                //n5 = jo.getString("court");
                hospital.add(n1);
                //hospital.add(n5);
                i++;
            }


        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
    public class AsyncHttpTask1 extends AsyncTask<String, Void, String> {


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

                    parseResult1(response.toString());
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
            Courtadaptor bp=new Courtadaptor(getContext(),name,address,mob);
            listitem.setAdapter(bp);





        }
    }
    private void parseResult1(String response_string) {


        try
        {

            name=new ArrayList<String>() ;

            address =new ArrayList<String>();
            mob =new ArrayList<String>();

            JSONArray ja=new JSONArray(response_string);
            JSONObject jo;



            // resp=jo.getString("resp");
            int i=0;
            while(i<ja.length()) {
                jo = ja.getJSONObject(i);
                n2 = jo.getString("name");
                n3 = jo.getString("address");

                n4 = jo.getString("mob");

                // Toast.makeText(MainActivity.this,"hwww"+n1,Toast.LENGTH_LONG).show();
                //Log.e("resp",""+resp);
                name.add(n2);
                address.add(n3);
                mob.add(n4);

                i++;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}


