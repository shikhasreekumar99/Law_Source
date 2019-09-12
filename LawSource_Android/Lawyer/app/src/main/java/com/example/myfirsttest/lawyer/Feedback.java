package com.example.myfirsttest.lawyer;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
/*import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;*/


/**
 * Created by adhoc on 12/10/16.
 */
public class Feedback extends Fragment {
    ViewFlipper flipper;
    private View thisFragment;
    View myView;
    Animation fade_in, fade_out;
    Button sendid;
    EditText comp;
    String compliant,compliant1,userid;
    SharedPreferences sp;
    LinearLayout famii;
    SharedPreferences.Editor ed;
    ProgressDialog pdialog;
    String web_response;
    String response;
    ViewFlipper viewFlipper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.compliant, container, false);



    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ed = sp.edit();
        comp=(EditText)getView().findViewById(R.id.etTweetReview);
        sendid=(Button) getView().findViewById(R.id.sendid);
        sendid.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setMessage("Send This Message to Authority??");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                                compliant1=comp.getText().toString();

                                try {
                                    compliant = URLEncoder.encode(compliant1, "UTF-8");



                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }



                                userid=sp.getString("user_id","");
                                    new AsyncHttpTask().execute("http://"+sp.getString("ipaddrress","")+"/LawSource/lawyerwebservice/feedback.jsp?userid="+userid+"&feedback="+compliant);
                                //    Toast.makeText(getActivity(),"http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/feedback.jsp?userid="+userid+"&feedback="+compliant, Toast.LENGTH_LONG).show();


                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

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
            pdialog.setCancelable(false);
            pdialog.setMessage("Loading");
            pdialog.show();

        }

        @Override
        protected void onPostExecute (String s){
            super.onPostExecute(s);
            pdialog.dismiss();
            if(response.equals("success")) {

                Intent in = new Intent(getActivity(), MainActivity.class);

                startActivity(in);
            }
           /* else  {

                Intent in = new Intent(Compliant.this, Changepassword.class);
                finish();
                startActivity(in);
            }*/

        }
    }
    private void parseResult(String response_string) {


        try
        {



            JSONArray ja=new JSONArray(response_string);
            JSONObject jo;



            jo=ja.getJSONObject(0);
            response= jo.getString("response");




        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }





}


