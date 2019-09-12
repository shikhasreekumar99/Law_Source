package com.example.myfirsttest.lawyer;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Chatviewadaptor extends BaseAdapter
{
	Context con;
	
	ArrayList<String> items1;
	ArrayList<String> items2;
	ArrayList<String> items3;
	ArrayList<String> items4;
	
	public Chatviewadaptor(Context chatting, ArrayList<String> message,
                           ArrayList<String> datetime, ArrayList<String> wh) {
		// TODO Auto-generated constructor stub
		
		
	
		
		this.con=chatting;
		this.items1=message;
		this.items2=datetime;
		this.items3=wh;
		
		
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
		convertView= LayoutInflater.from(con).inflate(R.layout.chathistory, null);
		
TextView li=(TextView) convertView.findViewById(R.id.chathist);
TextView li1=(TextView) convertView.findViewById(R.id.chattime);
TextView li2=(TextView) convertView.findViewById(R.id.stat);
LinearLayout rightlay=(LinearLayout) convertView.findViewById(R.id.rightlinear);


if(items3.get(position).equals("client"))
{
	rightlay.setGravity(Gravity.START);
	li.setText(items2.get(position));
	li.setBackgroundResource(R.drawable.rounded_corner);
	li1.setText("Replied on ");
	li2.setText(items1.get(position));
	
	}
else
{



	rightlay.setGravity(Gravity.END);
	li.setText(items2.get(position));
	li1.setText(items1.get(position));
	li2.setText("  You messaged");
	li2.setGravity(Gravity.END);
	li.setBackgroundResource(R.drawable.rounded_corner1);
}
		
		
		
		
		
		
		return convertView;
	}

}
