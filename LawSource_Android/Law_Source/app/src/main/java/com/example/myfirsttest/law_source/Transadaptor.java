package com.example.myfirsttest.law_source;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Transadaptor extends BaseAdapter
{
	Context con;
	
	ArrayList<String> items1;
	ArrayList<String> items2;
	ArrayList<String> items3;
	AlertDialog.Builder alertDialog;
	ArrayList<String> items4;
	public Transadaptor(Context ipcsection, ArrayList<String> date,
						ArrayList<String> time,ArrayList<String> type) {
		// TODO Auto-generated constructor stub

		this.con=ipcsection;
		this.items1=date;
		this.items2=time;

		this.items3=type;
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
		convertView=LayoutInflater.from(con).inflate(R.layout.ipclayout, null);
		
TextView li=(TextView) convertView.findViewById(R.id.s1);
		LinearLayout viewmore=(LinearLayout) convertView.findViewById(R.id.viewmore);
		
		TextView li1=(TextView) convertView.findViewById(R.id.s2);

		li.setText("Section No: "+items1.get(position));
		li1.setText(items2.get(position));

		viewmore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				alertDialog = new AlertDialog.Builder(con);

				// Setting Dialog Title
				alertDialog.setTitle("Expansion of Section "+items1.get(position));

				// Setting Dialog Message
				alertDialog.setMessage(items3.get(position));

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
		});
		
		return convertView;
	}

}
