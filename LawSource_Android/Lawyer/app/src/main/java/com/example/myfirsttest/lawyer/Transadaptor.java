package com.example.myfirsttest.lawyer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class Transadaptor extends BaseAdapter
{
	Context con;
	
	ArrayList<String> items1;
	ArrayList<String> items2;
	ArrayList<String> items3;
	ArrayList<String> items4;
	ArrayList<String> items5;
	Animation fade_in, fade_out;
	ViewFlipper viewFlipper;
	TextView vm;
	TextView vm1,vm2,vm4,vm5;
	LinearLayout vm3;
	public Transadaptor(Context ipcsection, ArrayList<String> date,
						ArrayList<String> time, ArrayList<String> nam, ArrayList<String> mob, ArrayList<String> mail) {
		// TODO Auto-generated constructor stub

		this.con=ipcsection;
		this.items1=date;
		this.items2=time;
		this.items3=nam;
		this.items4=mob;
		this.items5=mail;



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
		convertView=LayoutInflater.from(con).inflate(R.layout.clientlist, null);
		
TextView li=(TextView) convertView.findViewById(R.id.q1);
		
		TextView li1=(TextView) convertView.findViewById(R.id.q2);
		TextView li2=(TextView) convertView.findViewById(R.id.q3);
		TextView li3=(TextView) convertView.findViewById(R.id.q4);
		TextView li4=(TextView) convertView.findViewById(R.id.q5);

		li.setText("Case Title : "+items1.get(position));
		li1.setText("Case Type : "+items2.get(position));
		li2.setText("Client Name : "+items3.get(position));
		li3.setText("Contact No: "+items4.get(position));
		li4.setText("Email : "+items5.get(position));

        ImageView call = (ImageView) convertView.findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = items4.get(position);
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
		//vm1=(TextView) convertView.findViewById(R.id.vm1);

	//	vm2=(TextView) convertView.findViewById(R.id.vm2);
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

}
