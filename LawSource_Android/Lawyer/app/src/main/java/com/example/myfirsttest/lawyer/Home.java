package com.example.myfirsttest.lawyer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.ViewFlipper;

/*import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;*/


/**
 * Created by adhoc on 12/10/16.
 */
public class Home extends Fragment {
    ViewFlipper flipper;
    private View thisFragment;
    View myView;
    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;
    SharedPreferences sp;
    TextView user_profile_name;
    SharedPreferences.Editor ed;
    String uname;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.homes, container, false);



    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        ViewFlipper flipper = (ViewFlipper)getView().findViewById(R.id.flipper1);


        flipper.startFlipping();
        user_profile_name=getView().findViewById(R.id.user_profile_name);
        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ed = sp.edit();

        //  Toast.makeText(Travel.this, "Available", Toast.LENGTH_LONG).show();
        uname=sp.getString("uname","");
        user_profile_name.setText("Welcome Adv."+uname);
    }
}


