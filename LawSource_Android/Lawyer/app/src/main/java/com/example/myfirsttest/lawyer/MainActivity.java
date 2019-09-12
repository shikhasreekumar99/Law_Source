package com.example.myfirsttest.lawyer;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    String block;
    Dialog dialog;
    ViewFlipper flipper;
    AlertDialog.Builder alertDialog;
    protected DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        sp= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        ed=sp.edit();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        /*toolbar.setTitleTextColor(getResources().getColor(R.color.orange));*/
        setSupportActionBar(toolbar);



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        Home fr=new Home();
       FragmentTransaction fm=getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.content_frame,fr);

        fm.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.dashboard) {





            Clients fr=new Clients();
            FragmentTransaction fm=getSupportFragmentManager().beginTransaction();
            clearBackStack();

            fm.replace(R.id.content_frame,fr);
            fm.addToBackStack("DashBoard");
            Log.e("backstckcount",""+getSupportFragmentManager().getBackStackEntryCount());
            fm.commit();



        }
        else if (id == R.id.product) {


            Newclients fr=new Newclients();
            FragmentTransaction fm=getSupportFragmentManager().beginTransaction();
            clearBackStack();

            fm.replace(R.id.content_frame,fr);

            fm.addToBackStack("profile");
            fm.commit();



        }
       else if (id == R.id.orders) {


            Casestatus fr=new Casestatus();
            FragmentTransaction fm=getSupportFragmentManager().beginTransaction();
            clearBackStack();

            fm.replace(R.id.content_frame,fr);

            fm.addToBackStack("cart");
            fm.commit();



        }
        else if (id == R.id.logout) {


            alertDialog = new AlertDialog.Builder(MainActivity.this);

            // Setting Dialog Title
            alertDialog.setTitle("Confirm Sign Out...");

            // Setting Dialog Message
            alertDialog.setMessage("Are you sure you want Logout?");

            // Setting Icon to Dialog
            alertDialog.setIcon(R.drawable.advocate);

            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    // Write your code here to invoke YES event
                    Intent in=new Intent(MainActivity.this,Login.class);
                    finish();
                    startActivity(in);
                }
            });

            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to invoke NO event

                    dialog.cancel();
                }
            });

            // Showing Alert Message
            alertDialog.show();



        }
         else if (id == R.id.payment) {


            Paystatus fr=new Paystatus();
            FragmentTransaction fm=getSupportFragmentManager().beginTransaction();
            clearBackStack();

            fm.replace(R.id.content_frame,fr);

            fm.addToBackStack("orders");
            fm.commit();



        }
      else if (id == R.id.cart) {


            Profile fr=new Profile();
            FragmentTransaction fm=getSupportFragmentManager().beginTransaction();
            clearBackStack();

            fm.replace(R.id.content_frame,fr);

            fm.addToBackStack("orders");
            fm.commit();



        }
       else if (id == R.id.chat) {


            Chat fr=new Chat();
            FragmentTransaction fm=getSupportFragmentManager().beginTransaction();
            clearBackStack();

            fm.replace(R.id.content_frame,fr);

            fm.addToBackStack("orders");
            fm.commit();



        }
         /* else if (id == R.id.refer) {


            Casehistory fr=new Casehistory();
            FragmentTransaction fm=getSupportFragmentManager().beginTransaction();
            clearBackStack();

            fm.replace(R.id.content_frame,fr);

            fm.addToBackStack("orders");
            fm.commit();



        }
       */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void clearBackStack() {

        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            Log.e("cleared","backstack");
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}
