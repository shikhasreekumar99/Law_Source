package com.example.myfirsttest.law_source;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.ArrayList;

public class Lawyeradaptor extends BaseAdapter
{
	Context con;
	TextView vm;
	ArrayList<String> items1;
	ArrayList<String> items2;
	ArrayList<String> items3;
	ArrayList<String> items4;
	ArrayList<String> items5;
	ArrayList<String> items6;
	LinearLayout vm3;
	Dialog dialog;
	EditText Title,Number;
	EditText Description;
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
	SharedPreferences sp;
	SharedPreferences.Editor ed;

	String tit,num,desc;
	String tit1,num1,desc1,userid,lawid;
	public Lawyeradaptor(Context ipcsection, ArrayList<String> email, ArrayList<String> fees, ArrayList<String> name, ArrayList<String> address,
						 ArrayList<String> mob, ArrayList<String> id) {
		// TODO Auto-generated constructor stub

		this.con=ipcsection;
		this.items1=name;
		this.items2=address;
		this.items3=mob;

		this.items4=email;
		this.items5=fees;

		this.items6=id;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items1.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView=LayoutInflater.from(con).inflate(R.layout.lawyerlist, null);
		
TextView li=(TextView) convertView.findViewById(R.id.t1);
		
		TextView li1=(TextView) convertView.findViewById(R.id.t2);
		TextView li2=(TextView) convertView.findViewById(R.id.t3);

		TextView li3=(TextView) convertView.findViewById(R.id.t4);
		TextView li4=(TextView) convertView.findViewById(R.id.t5);

		li.setText(items4.get(position));
		li1.setText(items5.get(position));
		li2.setText(items1.get(position));
		li3.setText(items2.get(position));
		li4.setText(items3.get(position));

		ImageView call = (ImageView) convertView.findViewById(R.id.call);
		call.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String number = items3.get(position);
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + number));
				if (ActivityCompat.checkSelfPermission(con, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
					// TODO: Consider calling
					//    ActivityCompat#requestPermissions
					// here to request the missing permissions, and then overriding
					//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
					//                                          int[] grantResults)
					// to handle the case where the user grants the permission. See the documentation
					// for ActivityCompat#requestPermissions for more details.
					return;
				}
				con.startActivity(callIntent);
			}
		});
		TextView vm5=(TextView) convertView.findViewById(R.id.vm5);
		//TextView vm1=(TextView) convertView.findViewById(R.id.vm1);
		/*TextView vm5=(TextView) convertView.findViewById(R.id.vm5);
		//TextView vm2=(TextView) convertView.findViewById(R.id.vm2);
		 vm3=(LinearLayout) convertView.findViewById(R.id.vm3);
		TextView vm4=(TextView) convertView.findViewById(R.id.vm4);
		 vm=(TextView) convertView.findViewById(R.id.vm);
		vm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Toast.makeText(getActivity(), "clickz....", Toast.LENGTH_LONG).show();
				ViewGroup.LayoutParams params = vm3.getLayoutParams();
				params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
				vm3.setLayoutParams(params);


				vm.setVisibility(View.INVISIBLE);
			}
		});
		vm4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//  Toast.makeText(getActivity(), "clickz....", Toast.LENGTH_LONG).show();
				ViewGroup.LayoutParams params = vm3.getLayoutParams();
				params.height = 0;
				vm3.setLayoutParams(params);

				vm.setVisibility(View.VISIBLE);
			}
		});*/
		vm5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				 dialog = new Dialog(con);
				dialog.setContentView(R.layout.casedetail);
				dialog.setTitle("Case Details");
				dialog.show();

				sp = PreferenceManager.getDefaultSharedPreferences(con);
				ed = sp.edit();





				Title=(EditText) dialog.findViewById(R.id.input_name) ;
				radioGroup = (RadioGroup)dialog. findViewById(R.id.radioGender);
				radioButton = (RadioButton) dialog.findViewById(R.id.radioMale);
				Number=(EditText) dialog.findViewById(R.id.input_name1) ;
				Description=(EditText)dialog. findViewById(R.id.input_address);

				signupButton=(Button) dialog.findViewById(R.id.btn_signup) ;



				signupButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// Finish the registration screen and return to the Login activity



						tit1 = Title.getText().toString();
						num1 = Number.getText().toString();
						desc1 = Description.getText().toString();

						int selectedId = radioGroup.getCheckedRadioButtonId();
						if(selectedId == radioButton.getId())
						{
							//yo stirng database ma store gara hai ani sabai huncha

							gender = "civil";
						}
						else
						{
							gender = "criminal";
						}
						// find the radiobutton by returned id
						// radioButton = (RadioButton) findViewById(selectedId);

						try {
							tit = URLEncoder.encode(tit1, "UTF-8");
							num = URLEncoder.encode(num1, "UTF-8");
							desc = URLEncoder.encode(desc1, "UTF-8");



						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						//Toast.makeText(con ,"clickz...."+items6.get(position), Toast.LENGTH_LONG).show();
						userid=sp.getString("user_id","");
						lawid=items6.get(position);
						new AsyncHttpTask().execute("http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/caserequest.jsp?userid="+userid+"&lawid="+lawid+"&tit="+tit+"&type="+gender+"&num="+num+"&desc="+desc);

					}
				});

			}
		});

		return convertView;
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

			pdialog = new ProgressDialog(con);
			pdialog.setMessage("Requesting Appoiinment");
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
				dialog.dismiss();
				Lawyers pro_details = new Lawyers();
				FragmentTransaction fm =((FragmentActivity)con).getSupportFragmentManager().beginTransaction();
				fm.addToBackStack("Product_Details");
				fm.replace(R.id.content_frame,pro_details );
				fm.commit();
			}

			else
			{
				Toast.makeText(con, "You clicked on NO", Toast.LENGTH_SHORT).show();
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
}
