package com.example.myfirsttest.law_source;

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

public class Caseadaptor extends BaseAdapter
{
	Context con;

	ArrayList<String> items1;
	ArrayList<String> items2;
	ArrayList<String> items3;
	ArrayList<String> items4;
	ArrayList<String> items5;
	ArrayList<String> items6;
	ArrayList<String> items7;
	ArrayList<String> items8;
	Spinner spinnerOsversions;
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
	String comment="",gender="",comment1="",userid="";
	Button subb;
	String[] state;
	ProgressDialog pdialog;
	public Caseadaptor(Context ipcsection, ArrayList<String> date,
					   ArrayList<String> time, ArrayList<String> nam, ArrayList<String> mob, ArrayList<String> mail, ArrayList<String> ctitle, ArrayList<String> caseid, ArrayList<String> fee) {
		// TODO Auto-generated constructor stub

		this.con=ipcsection;
		this.items1=date;
		this.items2=time;
		this.items3=nam;
		this.items4=mob;
		this.items5=mail;
		this.items6=ctitle;
		this.items7=caseid;
		this.items8=fee;



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
		convertView=LayoutInflater.from(con).inflate(R.layout.historylist, null);
		sp = PreferenceManager.getDefaultSharedPreferences(con);
		ed = sp.edit();

TextView li=(TextView) convertView.findViewById(R.id.q1);
		
		TextView li1=(TextView) convertView.findViewById(R.id.q2);
		TextView li2=(TextView) convertView.findViewById(R.id.q3);
		TextView li3=(TextView) convertView.findViewById(R.id.q4);
		TextView li4=(TextView) convertView.findViewById(R.id.q5);
		TextView li5=(TextView) convertView.findViewById(R.id.q6);
		Button submit=(Button)convertView.findViewById(R.id.sub);

		li.setText("Case Title : "+items1.get(position));
		li1.setText("Case Type : "+items2.get(position));
		li2.setText("Lawyers Name :"+items3.get(position));

		li5.setText("Details :"+items6.get(position));

		li3.setText("Current Status : "+items4.get(position));
		li4.setText("Payment Status :"+items5.get(position));
		//vm1=(TextView) convertView.findViewById(R.id.vm1);

		//vm2=(TextView) convertView.findViewById(R.id.vm2);
	/*	vm3=(LinearLayout) convertView.findViewById(R.id.vm3);
		vm4=(TextView) convertView.findViewById(R.id.vm4);
		vm=(TextView) convertView.findViewById(R.id.vm);*/
		/*vm.setOnClickListener(new View.OnClickListener() {
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
if(items5.get(position).equals("Payment Requested"))
{
	submit.setVisibility(View.VISIBLE);
}
submit.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View view) {

		 dialog = new Dialog(con);
		dialog.setContentView(R.layout.payment);

		dialog.setTitle("Payment");
		dialog.show();
		Button pay,payments;
		LinearLayout payment;
		final EditText cardno,mm,yy,cvv;
		TextView amt;

		amt=(TextView) dialog.findViewById(R.id.park_charge);


			amt.setText("You Have to Pay Rs."+items8.get(position));

		pay=(Button) dialog.findViewById(R.id.paynow);

		cardno=(EditText)dialog.findViewById(R.id.cardno);

		mm=(EditText)dialog.findViewById(R.id.mm);
		yy=(EditText)dialog.findViewById(R.id.yy);
		cvv=(EditText)dialog.findViewById(R.id.cvv);

		// set the custom dialog components - text, image and button

		pay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String card=cardno.getText().toString();
				String m=mm.getText().toString();
				String y=yy.getText().toString();
				String c=cvv.getText().toString();

				if(card==""||m==""||y==""||c=="")
				{
					Toast.makeText(con, "Enter Card Details Properly", Toast.LENGTH_SHORT).show();
				}

				else if (card.length() < 16)
				{
					Toast.makeText(con, "Enter valid 16 Digit Card Number", Toast.LENGTH_SHORT)
							.show();
					cardno.setError("Invalid Card Number");
				}
				else if (m.length() < 2)
				{
					Toast.makeText(con, "Enter valid Month in MM Format", Toast.LENGTH_SHORT)
							.show();
					mm.setError("Invalid Month Format");
				}
				else if (y.length() < 4)
				{
					Toast.makeText(con, "Enter valid Year in YYYY Format", Toast.LENGTH_SHORT)
							.show();
					yy.setError("Invalid Year Format");
				}
				else if (c.length() < 3)
				{
					Toast.makeText(con, "Enter valid CVV", Toast.LENGTH_SHORT)
							.show();
					cvv.setError("Invalid CVV Format");
				}
				else
				{
					dialog.dismiss();
					userid=sp.getString("user_id","");
					new AsyncHttpTask().execute("http://"+sp.getString("ipaddrress","")+"/LawSource/clientwebservice/payment.jsp?caseid="+items7.get(position)+"&id="+userid);

				}
			}
		});



		dialog.setCancelable(true);



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
			pdialog.setMessage("Payment Processing");
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
				Toast.makeText(con, "Payment Successfull", Toast.LENGTH_SHORT).show();
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
