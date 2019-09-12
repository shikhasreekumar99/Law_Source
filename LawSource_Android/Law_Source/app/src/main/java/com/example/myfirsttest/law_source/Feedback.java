package com.example.myfirsttest.law_source;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
/*import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;*/


/**
 * Created by adhoc on 12/10/16.
 */
public class Feedback extends Fragment implements AdapterView.OnItemSelectedListener {
    ViewFlipper flipper;
    private View thisFragment;
    View myView;
    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;
    Spinner spinnerOsversions;
    Button submit;
    String web_response,resp="",userid="",statuss="";
    ProgressDialog pdialog;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    String id,rating;
    ImageView prof;
    ListView listitem;
    String[] state;
    String n1,n2,n3,n4,n5,n6,n7,n8,n9,n10;
    ArrayList<String> ctitle;
    ArrayList<String> fee;
    ArrayAdapter<String> adapter_state;
    ArrayList<String> ctype;
    ArrayList<String> nam;
    ArrayList<String> mob;
    ArrayList<String> mail;
    ArrayList<String> desc;
    ArrayList<String> caseid;
    String lawid;
    TextView li;
    RatingBar ratingbar1;
    String feedback1=""; String feedback="";
    String lawyernam,lawyernam1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.feedstatus, container, false);



    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        listitem=(ListView)getView().findViewById(R.id.list1);
        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ed = sp.edit();

         li=(TextView) getView().findViewById(R.id.feed);
        spinnerOsversions = (Spinner) getView().findViewById(R.id.spinner);


        spinnerOsversions.setOnItemSelectedListener(this);
        //  Toast.makeText(Travel.this, "Available", Toast.LENGTH_LONG).show();
        userid=sp.getString("user_id","");
        new AsyncHttpTask().execute("http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/mylawyers.jsp?id="+userid);


        //Log.d("","http://"+sp.getString("ipaddrress","")+"/Digitalbus/conductorwebservice/gethistory.jsp?userid="+userid);



        ratingbar1=(RatingBar)getView().findViewById(R.id.ratingBar1);
        // li1=(EditText) convertView.findViewById(R.id.feed);
         submit=(Button)getView().findViewById(R.id.ipsubmitButton);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                userid=sp.getString("user_id","");
                //String feedback1=li1.getText().toString();
                rating=String.valueOf(ratingbar1.getRating());

                 feedback1=li.getText().toString();
               // lawid=items2.get(position);
				try {
					feedback = URLEncoder.encode(feedback1, "UTF-8");
lawyernam1=URLEncoder.encode(lawyernam, "UTF-8");


				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
                //+"&feedback="+feedback
                Toast.makeText(getActivity(), "Rating"+rating, Toast.LENGTH_SHORT).show();
                if(lawid.equals("0"))
                {
                    Toast.makeText(getActivity(), "Choose your lawyer", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new AsyncHttpTask1().execute("http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/feedback.jsp?userid="+userid+"&lawyerid="+lawid+"&rating="+rating+"&feedback="+feedback);


                }


            }






        });



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerOsversions.setSelection(i);
         lawid=mail.get(i);
         lawyernam = (String) spinnerOsversions.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
            pdialog.setMessage("Rating Updating");
            pdialog.setCancelable(false);
            pdialog.show();

        }

        @Override
        protected void onPostExecute (String s){
            super.onPostExecute(s);
            pdialog.dismiss();
            if(resp.equals("success"))
            {


                // Setting Negative "NO" Button
				/*alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to invoke NO event
						Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
						dialog.cancel();
					}
				});*/

                // Showing Alert Message

                Home pro_details = new Home();
                FragmentTransaction fm =((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                fm.addToBackStack("Product_Details");
                fm.replace(R.id.content_frame,pro_details );
                fm.commit();

            }

            else
            {
                Toast.makeText(getActivity(), "You clicked on NO", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void parseResult1(String response_string) {


        try
        {
            JSONArray ja=new JSONArray(response_string);
            JSONObject jo=ja.getJSONObject(0);



            resp=jo.getString("response");
            // Toast.makeText(MainActivity.this,"hwww"+n1,Toast.LENGTH_LONG).show();
            Log.e("resp",""+resp);



        }
        catch (Exception e)
        {
            System.out.println(e);
        }

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
            adapter_state = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, nam);
            adapter_state
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerOsversions.setAdapter(adapter_state);





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

            nam.add("Select Lawyer");
mail.add("0");
            // resp=jo.getString("resp");
            int i=0;
            while(i<ja.length()) {
                jo = ja.getJSONObject(i);

                n3 = jo.getString("name");

n4=jo.getString("lawyerid");
                // Toast.makeText(getContext(),"hwww"+ja.length(), Toast.LENGTH_LONG).show();
                //Log.e("resp",""+resp);

                nam.add(n3);
                mail.add(n4);


                i++;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}


