package com.example.myfirsttest.law_source;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
/*import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;*/


/**
 * Created by adhoc on 12/10/16.
 */
public class Profile extends Fragment {
    ViewFlipper flipper;
    Button edit,done;
    private View thisFragment;
    View myView;
    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    String web_response;
    ProgressDialog pdialog;
    String userid;
    String resp;
    String n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12;
    String p1,p2,p3,p4,p5,p6,p7,p8,p9,p10;
    String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;

    TextView input_name;
    TextView age;
    TextView address,district;
    TextView input_email;
    TextView input_mobile;
    TextView input_username,input_password,q1,q2,q3,q4,q5,q6,q7,q8,q9;
    RadioGroup radioGroup;
    RadioButton radioButton,radioButton1;
    Button signup,signup1;
    LinearLayout edits;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.profileupdates, container, false);



    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
       /* edit=(Button) getView().findViewById(R.id.btn_signup);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent in = new Intent(Profileview.this, Editprofile.class);
                //in.putExtra("key1",n1);
                //startActivity(in);
            }
        });*/

        q1=(TextView)getView().findViewById(R.id.q1);
        q2=(TextView)getView().findViewById(R.id.q2);
        q3=(TextView)getView().findViewById(R.id.q3);
        q4=(TextView)getView().findViewById(R.id.q4);
        q5=(TextView)getView().findViewById(R.id.q5);
        q6=(TextView)getView().findViewById(R.id.q6);
        q7=(TextView)getView().findViewById(R.id.q7);
        q8=(TextView)getView().findViewById(R.id.q8);
        q9=(TextView)getView().findViewById(R.id.q9);
        edits=(LinearLayout) getView().findViewById(R.id.editpro);

        signup=(Button) getView().findViewById(R.id.btn_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edits.setVisibility(View.VISIBLE);
            }
        });
        input_name=(TextView)getView().findViewById(R.id.input_name);
        age=(TextView)getView().findViewById(R.id.age);
        input_password=(TextView)getView().findViewById(R.id.input_password);
        // radioGender=(TextView)findViewById(R.id.radioGender);
        input_email=(TextView)getView().findViewById(R.id.input_email);
        input_mobile=(TextView)getView().findViewById(R.id.input_mobile);
        input_username=(TextView)getView().findViewById(R.id.input_username);
        address=(TextView)getView().findViewById(R.id.input_address);
        district=(TextView)getView().findViewById(R.id.input_district);
        radioGroup = (RadioGroup) getView().findViewById(R.id.radioGender);
        radioButton = (RadioButton)getView(). findViewById(R.id.radioMale);
        radioButton1 = (RadioButton) getView().findViewById(R.id.radioFemale);

        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ed = sp.edit();

        //Toast.makeText(getActivity(), "Available", Toast.LENGTH_LONG).show();
        userid=sp.getString("user_id","");
        Log.e("","http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/profile.jsp?id="+userid);
        new AsyncHttpTask().execute("http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/profile.jsp?id="+userid);
        // Toast.makeText(getActivity(),"http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/profile.jsp?id="+userid, Toast.LENGTH_LONG).show();
        /*} else {
            Toast.makeText(getActivity(), "Not Available", Toast.LENGTH_LONG).show();
        }
*/
        signup1=(Button) getView().findViewById(R.id.btn_signup1);

        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p1 = input_name.getText().toString();
                p2 = age.getText().toString();
                p3 = input_password.getText().toString();
                //p4 = age.getText().toString();
                p5 = input_mobile.getText().toString();
                p6 = input_username.getText().toString();
                //p7 = passwordText1.getText().toString();
                p8= address.getText().toString();
                p9=district.getText().toString();

                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId == radioButton.getId())
                {
                    //yo stirng database ma store gara hai ani sabai huncha

                    p10 = "male";
                }
                else
                {
                    p10 = "female";
                }
                // find the radiobutton by returned id
                // radioButton = (RadioButton) findViewById(selectedId);

                try {
                    s1 = URLEncoder.encode(p1, "UTF-8");
                    s2 = URLEncoder.encode(p2, "UTF-8");
                    s3 = URLEncoder.encode(p3, "UTF-8");
//                    s4 = URLEncoder.encode(p4, "UTF-8");
                    s5 = URLEncoder.encode(p5, "UTF-8");
                    s6 = URLEncoder.encode(p6, "UTF-8");
                    s7 = URLEncoder.encode(p8, "UTF-8");
                    s8 = URLEncoder.encode(p9, "UTF-8");
                    s9 = URLEncoder.encode(p10, "UTF-8");


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }




                userid=sp.getString("user_id","");
                //Toast.makeText(Login.this, "Available", Toast.LENGTH_LONG).show();
                new AsyncHttpTask1().execute("http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/editprofile.jsp?id="+userid+"&name="+s1+"&age="+s2+"&gender="+s9+"&mobile="+s5+"&username="+s6+"&password="+s3+"&address="+s7+"&district="+s8);
                // Toast.makeText(Signup.this,"http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/register.jsp?name="+name+"&age="+age1+"&gender="+gender+"&email="+email+"&mobile="+mob+"&username="+user+"&password="+password1+"&address="+address+"&district="+district, Toast.LENGTH_LONG).show();

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




            input_name.setText(n1);
            age.setText(n2);
            // radioGender.setText(n3);

            if(n3.equalsIgnoreCase("male"))
            {
                radioButton.setChecked(true);
                // radioButton1.setEnabled(false);
            }
            else if(n3.equalsIgnoreCase("female")){

                radioButton1.setChecked(true);
                //radioButton.setEnabled(false);
            }
            address.setText(n4);
            district.setText(n5);
            input_email.setText(n6);
            input_mobile.setText(n7);
            input_username.setText(n8);
            input_password.setText(n9);




            q1.setText(": "+n1);
            q2.setText(": "+n2);
            q3.setText(": "+n3);
            q4.setText(": "+n4);
            q5.setText(": "+n5);
            q6.setText(": "+n6);
            q7.setText(": "+n7);
            q8.setText(": "+n8);
            q9.setText(": "+n9);




        }
    }
    private void parseResult(String response_string) {


        try
        {
            JSONArray ja=new JSONArray(response_string);
            JSONObject jo=ja.getJSONObject(0);

            n1=jo.getString("k_name");
            n2=jo.getString("k_age");
            n3=jo.getString("k_gender");

            n4=jo.getString("k_address");
            n5=jo.getString("k_district");
            n6=jo.getString("k_email");

            n7=jo.getString("k_mobile");
            n8=jo.getString("k_username");
            n9=jo.getString("k_password");


        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }




    /* public boolean isNetworkAvailable() {
         ConnectivityManager cm = (ConnectivityManager) getSystemService(getActivity().CONNECTIVITY_SERVICE);
         NetworkInfo networkInfo = cm.getActiveNetworkInfo();
         if (networkInfo != null && networkInfo.isConnected()) {
             return true;
         }
         return false;
     }*/
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
            pdialog.setMessage("Creating Account");
            pdialog.setCancelable(false);
            pdialog.show();

        }

        @Override
        protected void onPostExecute (String s){
            super.onPostExecute(s);
            pdialog.dismiss();
            if(resp.equals("success"))
            {
                Profile pro_details = new Profile();
                FragmentTransaction fm =((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                fm.addToBackStack("Product_Details");
                fm.replace(R.id.content_frame,pro_details );
                fm.commit();



                // Setting Negative "NO" Button
				/*alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to invoke NO event
						Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
						dialog.cancel();
					}
				});*/

                // Showing Alert Message


            }

            else
            {
                Profile pro_details = new Profile();
                FragmentTransaction fm =((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                fm.addToBackStack("Product_Details");
                fm.replace(R.id.content_frame,pro_details );
                fm.commit();
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

}


