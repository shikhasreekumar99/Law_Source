package com.example.myfirsttest.law_source;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
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

public class Notificationadapter extends BaseAdapter
{
	Context con;

	ArrayList<String> items1;
	ArrayList<String> items2;
	ArrayList<String> items3;
	ArrayList<String> items4;
	ArrayList<String> items5;
	/*ArrayList<String> items6;
	ArrayList<String> items7;
	ArrayList<String> items8;*/
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
	ProgressDialog pdialog;
	public Notificationadapter(Context ipcsection, ArrayList<String> date,
                               ArrayList<String> time, ArrayList<String> nam, ArrayList<String> mob, ArrayList<String> mail) {
		// TODO Auto-generated constructor stub

		this.con=ipcsection;
		this.items1=date;
		this.items2=time;
		this.items3=nam;
		this.items4=mob;
		this.items5=mail;
		/*this.items6=ctitle;
		this.items7=caseid;
		this.items8=fee;*/



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
		convertView=LayoutInflater.from(con).inflate(R.layout.notificationlist, null);
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

		//li5.setText("Details :"+items6.get(position));

		li3.setText("Case Status : "+items4.get(position));
		li4.setText("Lawyers Comment :"+items5.get(position));
		//vm1=(TextView) convertView.findViewById(R.id.vm1);

		//vm2=(TextView) convertView.findViewById(R.id.vm2);



		return convertView;
	}



}
