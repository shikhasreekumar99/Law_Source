package com.example.myfirsttest.lawyer;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Payadaptor extends BaseAdapter
{
	Context con;

	ArrayList<String> items1;
	ArrayList<String> items2;
	ArrayList<String> items3;
	ArrayList<String> items4;
	ArrayList<String> items5;
	ArrayList<String> items6;
	ArrayList<String> items7;
	Animation fade_in, fade_out;
	ViewFlipper viewFlipper;
	TextView vm;
	TextView vm1,vm2,vm4,vm5;
	LinearLayout vm3;
	EditText nameText,age;
	RadioGroup radioGroup;
	RadioButton radioButton;
	String web_response,resp="";
	SharedPreferences sp;
	SharedPreferences.Editor ed;
	Dialog dialog;
	String comment="",gender="",comment1="";
	Button subb;
	ProgressDialog pdialog;
	public Payadaptor(Context ipcsection, ArrayList<String> date,
                      ArrayList<String> time, ArrayList<String> nam, ArrayList<String> mob, ArrayList<String> mail, ArrayList<String> ctitle, ArrayList<String> caseid) {
		// TODO Auto-generated constructor stub

		this.con=ipcsection;
		this.items1=date;
		this.items2=time;
		this.items3=nam;
		this.items4=mob;
		this.items5=mail;
		this.items6=ctitle;
		this.items7=caseid;



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
		convertView=LayoutInflater.from(con).inflate(R.layout.paylist, null);
		sp = PreferenceManager.getDefaultSharedPreferences(con);
		ed = sp.edit();

TextView li=(TextView) convertView.findViewById(R.id.q1);
		
		TextView li1=(TextView) convertView.findViewById(R.id.q2);
		TextView li2=(TextView) convertView.findViewById(R.id.q3);
		TextView li3=(TextView) convertView.findViewById(R.id.q4);
		TextView li4=(TextView) convertView.findViewById(R.id.q5);

		Button submit=(Button)convertView.findViewById(R.id.sub);

		li.setText("Case Title : "+items1.get(position));
		li1.setText("Case Type : "+items2.get(position));
		li2.setText("Client Name :"+items3.get(position));



		li3.setText("Details : "+items4.get(position));
		li4.setText("Payment Status :"+items5.get(position));
		//vm1=(TextView) convertView.findViewById(R.id.vm1);

		//vm2=(TextView) convertView.findViewById(R.id.vm2);
		/*vm3=(LinearLayout) convertView.findViewById(R.id.vm3);
		vm4=(TextView) convertView.findViewById(R.id.vm4);
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
				Toast.makeText(con, "Updated Successfully", Toast.LENGTH_SHORT).show();
				dialog.dismiss();
                Casestatus pro_details = new Casestatus();
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
