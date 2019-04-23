package com.example.dynamo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
    private LockScreenStateReceiver lock;
    public int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
//bottomnav initializer
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);
//unlock_count initializer
        count=0;
        lock = new LockScreenStateReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);


        registerReceiver(lock, filter);

 //default fragment initializer
      Fragment fragment=new homefragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentcontainer,custom()).commit();



    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragments = null;

                    switch (menuItem.getItemId()) {
                        case R.id.home:

                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, custom()).commit();
                            break;

                        case R.id.Friends:
                            fragments=new friendsfragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,fragments).commit();
                            break;
                        case R.id.stats:
                            fragments=new statsfragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,fragments).commit();
                            break;
                        case R.id.profile:
                            fragments=new profilefragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,fragments).commit();
                            break;
                        case R.id.settings:
                            fragments=new settingsfragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,fragments).commit();}



                            return true;
                    }
                };
private Fragment custom(){

    Bundle b=new Bundle();
    b.putString("unlock",Integer.toString(count));
    Fragment fragment=new homefragment();
    fragment.setArguments(b);
    return fragment;
}
    public class LockScreenStateReceiver extends BroadcastReceiver {
        // TextView textView;

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                // screen is turn off
                //("Screen locked");
                Bundle b=new Bundle();
                b.putString("unlock",Integer.toString(count));
                Fragment frag=new homefragment();
                frag.setArguments(b);

                //getSupportFragmentManager().beginTransaction().add(R.id.fragmentcontainer,custom()).commit();

            } else {
                //Handle resuming events if user is present/screen is unlocked
                count++;
              //  getSupportFragmentManager().beginTransaction().add(R.id.fragmentcontainer,custom()).commit();
                Bundle b=new Bundle();
                b.putString("unlock",Integer.toString(count));
                Fragment frag=new homefragment();
                frag.setArguments(b);
              // getSupportFragmentManager().beginTransaction().add(R.id.fragmentcontainer,custom()).commit();
                //("Screen unlocked");
            }
        }


        void onDestroy() {
            unregisterReceiver(lock);
            lock.onDestroy();
        }

    }
}
