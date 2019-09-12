package com.example.myfirsttest.law_source;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/*import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;*/


/**
 * Created by adhoc on 12/10/16.
 */
public class Home extends Fragment {

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
    String id,uname;
    ImageView prof;
    ListView listitem;
    String n1,n2,n3,n4,n5,n6,n7,n8,n9,n10;
    ArrayList<String> ctitle;
    ArrayList<String> fee;
    TextView use0r_profile_name;
    ArrayList<String> ctype;
    ArrayList<String> nam;
    ArrayList<String> mob;
    ArrayList<String> mail;
    ArrayList<String> desc;
    ArrayList<String> caseid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.home, container, false);



    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        ViewFlipper flipper = (ViewFlipper)getView().findViewById(R.id.flipper1);


        flipper.startFlipping();

        user_profile_name=getView().findViewById(R.id.user_profile_name);

        listitem=(ListView)getView().findViewById(R.id.list1);
        /*listitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Casestatus pro_details = new Casestatus();
                FragmentTransaction fm =((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                fm.addToBackStack("Product_Details");
                fm.replace(R.id.content_frame,pro_details );
                fm.commit();
            }
        });*/
        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ed = sp.edit();

        //  Toast.makeText(Travel.this, "Available", Toast.LENGTH_LONG).show();
        userid=sp.getString("user_id","");
        uname=sp.getString("uname","");
        user_profile_name.setText("Welcome "+uname);
        new AsyncHttpTask().execute("http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/notification.jsp?id="+userid);
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
            Notificationadapter bp=new Notificationadapter(getContext(),ctitle,ctype,nam,mob,mail);
            listitem.setAdapter(bp);





        }
    }
    private void parseResult(String response_string) {


        try
        {
            ctitle=new ArrayList<String>() ;

            ctype =new ArrayList<String>();
            nam=new ArrayList<String>() ;

            mob =new ArrayList<String>();
            mail=new ArrayList<String>() ;
            desc=new ArrayList<String>() ;
            caseid=new ArrayList<String>() ;
            fee=new ArrayList<String>() ;


            JSONArray ja=new JSONArray(response_string);
            JSONObject jo;



            // resp=jo.getString("resp");
            int i=0;
            while(i<ja.length()) {
                jo = ja.getJSONObject(i);
                n1 = jo.getString("casetitle");
                n2 = jo.getString("casetype");
                n3 = jo.getString("name");
                n4 = jo.getString("status");
                n5 = jo.getString("comment");
                /*n6 = jo.getString("desc");
                n7= jo.getString("caseid");
                n8= jo.getString("fee");*/

                // Toast.makeText(MainActivity.this,"hwww"+n1,Toast.LENGTH_LONG).show();
                //Log.e("resp",""+resp);
                ctype.add(n2);
                ctitle.add(n1);
                nam.add(n3);
                mob.add(n4);
                mail.add(n5);
                /*desc.add(n6);
                caseid.add(n7);
                fee.add(n8);*/
                i++;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}