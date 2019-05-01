package com.example.dynamo;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

public Intent stats;
LockScreenStateReciever lockScreenStateReciever;
public int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //bottomnav initializer
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);

        //get user permission for accessing app usage

       Intent i=new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
       startActivity(i);

       //intent for the app usage section
       stats=new Intent(this,statsfragment.class);
       //intent-filter for unlock activity
        lockScreenStateReciever=new LockScreenStateReciever();
        IntentFilter lock=new IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(lockScreenStateReciever,lock);

//initializing count variable
count=0;

//default fragment initializer
        Fragment fragment=new homefragment();
        Bundle b=new Bundle();
        b.putString("unlocks",Integer.toString(count));
        fragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentcontainer,fragment).commit();


        }





    private BottomNavigationView.OnNavigationItemSelectedListener navlistener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragments = null;

                    switch (menuItem.getItemId()) {
                        case R.id.home:
                           fragments=new homefragment();
                            Bundle b=new Bundle();
                            b.putString("unlocks",Integer.toString(count));
                            fragments.setArguments(b);
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, fragments).commit();
                            break;

                        case R.id.Friends:
                            fragments=new friendsfragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,fragments).commit();
                            break;
                        case R.id.profile:
                            fragments=new profilesfragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,fragments).commit();

                            break;
                        case R.id.stats:
                          //  startActivity(stats);
                            fragments=new statsfragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,fragments).commit();
                            break;
                        case R.id.settings:
                            fragments=new settingsfragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,fragments).commit();}



                            return true;
                    }
                };

    public class LockScreenStateReciever extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                // screen is turn off


            } else {
                //Handle resuming events if user is present/screen is unlocked
                if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                    count++;
                    Fragment fragments;
                    fragments = new homefragment();
                    Bundle b = new Bundle();
                    b.putString("unlocks", Integer.toString(count));
                    fragments.setArguments(b);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, fragments).commit();
                }
                //("Screen unlocked");
            }
        }

    }
}
