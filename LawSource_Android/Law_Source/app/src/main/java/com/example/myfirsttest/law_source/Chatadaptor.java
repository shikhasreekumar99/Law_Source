package com.example.myfirsttest.law_source;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Chatadaptor extends BaseAdapter
{
	Context con;
	SharedPreferences sp;
	SharedPreferences.Editor ed;
	ArrayList<String> items1;
	ArrayList<String> items2;
	ArrayList<String> items3;
	public Chatadaptor(Context chat_user, ArrayList<String> id,
                       ArrayList<String> usr) {
		// TODO Auto-generated constructor stub
		
		this.con=chat_user;
		this.items1=id;
		this.items2=usr;
		
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


		sp = PreferenceManager.getDefaultSharedPreferences(con);
		ed = sp.edit();
		convertView= LayoutInflater.from(con).inflate(R.layout.chat, null);
		
TextView li=(TextView) convertView.findViewById(R.id.cdateid);
		
		
		li.setText(items2.get(position));
		
		li.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(con, "User Id"+items1.get(position), Toast.LENGTH_LONG).show();
				//Intent in=new Intent(con,Chatting.class);
				//in.putExtra("TON_ID", items1.get(position));
				
				//con.startActivity(in);
				Chatting pro_details = new Chatting();
				ed.putString("TON_ID",items1.get(position));
				ed.commit();
				FragmentTransaction fm =((FragmentActivity)con).getSupportFragmentManager().beginTransaction();
				fm.addToBackStack("Product_Details");
				fm.replace(R.id.content_frame,pro_details );
				fm.commit();
			}
		});
		
		
		return convertView;
	}

}
