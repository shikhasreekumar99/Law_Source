package com.example.myfirsttest.law_source;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/*import butterknife.ButterKnife;
import butterknife.InjectView;*/

public class Signup extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    EditText nameText,age;
    EditText emailText,input_mobile,input_username,input_address,inputdistrict;
    Button signupButton;
    TextView loginLink;
    EditText passwordText,passwordText1;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String web_response,resp="";
    ProgressDialog pdialog;
    String name,age1,mob,user,gender;
    String email,address,district;
    String password,password1;
    AlertDialog.Builder alertDialog;
    AlertDialog.Builder alertDialog1,alertDialog3;

    String name2,age2,mob2,user2;
    String email2,address2,district2;
    String password2;
    Calendar myCalendar;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        sp = PreferenceManager.getDefaultSharedPreferences(Signup.this);
        ed = sp.edit();
        nameText=(EditText) findViewById(R.id.input_name) ;
        radioGroup = (RadioGroup) findViewById(R.id.radioGender);
        radioButton = (RadioButton) findViewById(R.id.radioMale);
        age=(EditText) findViewById(R.id.age) ;

         myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        age.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Signup.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        emailText=(EditText) findViewById(R.id.input_email);
        input_mobile=(EditText) findViewById(R.id.input_mobile);
        input_username=(EditText) findViewById(R.id.input_username);
        input_address=(EditText) findViewById(R.id.input_address);
        inputdistrict=(EditText) findViewById(R.id.input_district);

        signupButton=(Button) findViewById(R.id.btn_signup) ;
        loginLink=(TextView) findViewById(R.id.link_login) ;
        passwordText=(EditText) findViewById(R.id.input_password);
        passwordText1=(EditText) findViewById(R.id.input_password1);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

       /* if (!validate()) {
            onSignupFailed();
            return;
        }

    signupButton.setEnabled(false);*/



        name2 = nameText.getText().toString();
        email2 = emailText.getText().toString();
        password2 = passwordText.getText().toString();
        age2 = age.getText().toString();
        mob2 = input_mobile.getText().toString();
        user2 = input_username.getText().toString();
        password2 = passwordText1.getText().toString();
        address2= input_address.getText().toString();
        district2=inputdistrict.getText().toString();
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if(selectedId == radioButton.getId())
        {
            //yo stirng database ma store gara hai ani sabai huncha

            gender = "male";
        }
        else
        {
            gender = "female";
        }
        // find the radiobutton by returned id
        // radioButton = (RadioButton) findViewById(selectedId);

        try {
            name = URLEncoder.encode(name2, "UTF-8");
            email = URLEncoder.encode(email2, "UTF-8");
            password = URLEncoder.encode(password2, "UTF-8");
            age1 = URLEncoder.encode(age2, "UTF-8");
            mob = URLEncoder.encode(mob2, "UTF-8");
            user = URLEncoder.encode(user2, "UTF-8");
            password1 = URLEncoder.encode(password2, "UTF-8");
            address = URLEncoder.encode(address2, "UTF-8");
            district = URLEncoder.encode(district2, "UTF-8");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }




        if (isNetworkAvailable()) {
            //Toast.makeText(Login.this, "Available", Toast.LENGTH_LONG).show();
            new AsyncHttpTask().execute("http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/register.jsp?name="+name+"&age="+age1+"&gender="+gender+"&email="+email+"&mobile="+mob+"&username="+user+"&password="+password1+"&address="+address+"&district="+district);
           // Toast.makeText(Signup.this,"http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/register.jsp?name="+name+"&age="+age1+"&gender="+gender+"&email="+email+"&mobile="+mob+"&username="+user+"&password="+password1+"&address="+address+"&district="+district, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Signup.this, "Not Available", Toast.LENGTH_LONG).show();
        }

    }


   /* public void onSignupSuccess() {
        signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }*/

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        name = nameText.getText().toString();
        email = emailText.getText().toString();
        password = passwordText.getText().toString();
        password1 = passwordText1.getText().toString();
        mob = input_mobile.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("at least 3 characters");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
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
        }

        if (!password.equals(password1)) {
            passwordText1.setError("Password Mismatch");
            valid = false;
        } else {
            passwordText1.setError(null);
        }
        if (mob.length()<10) {
            input_mobile.setError("Mobile No: Not Valid");
            valid = false;
        } else {
            input_mobile.setError(null);
        }

        return valid;
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

            pdialog = new ProgressDialog(Signup.this);
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
                alertDialog3 = new AlertDialog.Builder(Signup.this);

                // Setting Dialog Title
                alertDialog3.setTitle("Confirmation...");

                // Setting Dialog Message
                alertDialog3.setMessage("Registered Sucessfully");

                // Setting Icon to Dialog
                alertDialog3.setIcon(R.drawable.unnamed);

                // Setting Positive "Yes" Button
                alertDialog3.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        // Write your code here to invoke YES event
                        dialog.cancel();
                        Intent in = new Intent(Signup.this, Login.class);
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
                alertDialog3.show();

            }
            else if(resp.equals("username"))
            {


                alertDialog = new AlertDialog.Builder(Signup.this);

                // Setting Dialog Title
                alertDialog.setTitle("Username Not Available...");

                // Setting Dialog Message
                alertDialog.setMessage("Please Choose another Username");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.unnamed);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        // Write your code here to invoke YES event
                        dialog.cancel();

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
            else if(resp.equals("email"))
            {


                alertDialog1 = new AlertDialog.Builder(Signup.this);

                // Setting Dialog Title
                alertDialog1.setTitle("Email Already Exist");

                // Setting Dialog Message
                alertDialog1.setMessage("This Email ID is already used...");

                // Setting Icon to Dialog
                alertDialog1.setIcon(R.drawable.unnamed);

                // Setting Positive "Yes" Button
                alertDialog1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        // Write your code here to invoke YES event
                        dialog.cancel();

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
                alertDialog1.show();

            }
            else
            {
                Intent in = new Intent(Signup.this, Signup.class);
                //in.putExtra("key1",n1);
                startActivity(in);
            }
        }
    }
    private void parseResult(String response_string) {


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



    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }


    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        age.setText(sdf.format(myCalendar.getTime()));
    }
}