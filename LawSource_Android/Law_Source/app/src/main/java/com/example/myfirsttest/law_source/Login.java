package com.example.myfirsttest.law_source;

/**
 * Created by adhocadmin on 23/8/17.
 */

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirsttest.law_source.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*import butterknife.ButterKnife;
import butterknife.InjectView;*/

public class Login extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    String web_response,resp="";
    ProgressDialog pdialog;
    EditText emailText;
    EditText passwordText;
    Button loginButton;
    TextView signupLink;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    AlertDialog.Builder alertDialog;
    AlertDialog.Builder alertDialog1;
    String email="",userid="";
    String password = "",uname="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        // ButterKnife.inject(this);
        sp = PreferenceManager.getDefaultSharedPreferences(Login.this);
        ed = sp.edit();
        emailText=(EditText) findViewById(R.id.input_email) ;
        passwordText=(EditText) findViewById(R.id.input_password);
        loginButton=(Button) findViewById(R.id.btn_login) ;
        signupLink=(TextView) findViewById(R.id.link_signup) ;

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        email = emailText.getText().toString();
        password = passwordText.getText().toString();
        if (isNetworkAvailable()) {
            // Toast.makeText(Login.this, "Available", Toast.LENGTH_LONG).show();
            new AsyncHttpTask().execute("http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/login.jsp?username="+email+"&password="+password);
            Log.d(TAG,"http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/login.jsp?username="+email+"&password="+password);
        } else {
            Toast.makeText(Login.this, "Not Available", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }



    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        /*if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }*/

        return valid;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(1, 1, 1, "SET  IP");
        //menu.add(1, 2, 2, "SET  Interval");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Specify IP");
        dialog.setContentView(R.layout.rsu_ip);
        dialog.show();
        Button b1 = (Button) dialog.findViewById(R.id.ipsubmitButton);

        final EditText ipEdit = (EditText) dialog
                .findViewById(R.id.ipaddressET);
        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                String ipaddrress = ipEdit.getText().toString();
                ipaddrress = ipaddrress + ":8051";
                ed.putString("ipaddrress", ipaddrress);
                ed.commit();
                dialog.dismiss();
            }
        });
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        alertDialog1 = new AlertDialog.Builder(Login.this);

        // Setting Dialog Title
        alertDialog1.setTitle("Confirm Exit...");

        // Setting Dialog Message
        alertDialog1.setMessage("Are you sure to Exit LawSource App.?");

        // Setting Icon to Dialog
        alertDialog1.setIcon(R.drawable.unnamed);

        // Setting Positive "Yes" Button
        alertDialog1.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                // Write your code here to invoke YES event
                finish();
            }
        });

        // Setting Negative "NO" Button
        alertDialog1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event

                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog1.show();

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

            pdialog = new ProgressDialog(Login.this);
            pdialog.setMessage("Authenticating");
            pdialog.setCancelable(false);
            pdialog.show();

        }

        @Override
        protected void onPostExecute (String s){
            super.onPostExecute(s);
            pdialog.dismiss();
            Log.d(TAG,resp);
            if(resp.equals("success"))
            {
               // Toast.makeText(Login.this,"hwww"+userid,Toast.LENGTH_LONG).show();
                ed.putString("user_id", userid);
                ed.putString("uname", uname);
                ed.commit();
                Intent in = new Intent(Login.this, MainActivity.class);
                //in.putExtra("key1",n1);
                startActivity(in);
            }
            else
            {
                alertDialog = new AlertDialog.Builder(Login.this);

                // Setting Dialog Title
                alertDialog.setTitle("Authentication Failed...");

                // Setting Dialog Message
                alertDialog.setMessage("Username or Password Mismatch Found");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.unnamed);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        // Write your code here to invoke YES event
                        dialog.cancel();
                        Intent in = new Intent(Login.this, Login.class);
                        //in.putExtra("key1",n1);
                        startActivity(in);
                    }
                });

                // Setting Negative "NO" Button
				/*alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to invoke NO event
						Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
						dialog.cancel();
					}
				});*/

                // Showing Alert Message
                alertDialog.show();




            }
        }
    }
    private void parseResult(String response_string) {


        try
        {
            JSONArray ja=new JSONArray(response_string);
            JSONObject jo=ja.getJSONObject(0);


            userid=jo.getString("userid");
            resp=jo.getString("response");
uname=jo.getString("name");
            Log.e("resp",""+resp);



        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }



    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}