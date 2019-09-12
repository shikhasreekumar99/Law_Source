package com.example.myfirsttest.law_source;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Courtadaptor extends BaseAdapter {
	Context con;

	ArrayList<String> items1;
	ArrayList<String> items2;
	ArrayList<String> items3;
	ArrayList<String> items4;

	public Courtadaptor(Context ipcsection, ArrayList<String> name, ArrayList<String> address,
						ArrayList<String> mob) {
		// TODO Auto-generated constructor stub

		this.con = ipcsection;
		this.items1 = name;
		this.items2 = address;
		this.items3 = mob;


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
		convertView = LayoutInflater.from(con).inflate(R.layout.courtlist, null);

		TextView li = (TextView) convertView.findViewById(R.id.t1);

		TextView li1 = (TextView) convertView.findViewById(R.id.t2);
		TextView li2 = (TextView) convertView.findViewById(R.id.t3);
		ImageView call = (ImageView) convertView.findViewById(R.id.call);

		li.setText(items1.get(position));
		li1.setText("Address : " + items2.get(position));
		li2.setText("Contact No: " + items3.get(position));

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

		
		
		return convertView;
	}

}
