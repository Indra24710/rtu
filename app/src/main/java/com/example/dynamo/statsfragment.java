package com.example.dynamo;


import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.R.layout.simple_list_item_1;

public class profilefragment extends Fragment {
    ListView lists;
    TextView text;
    PackageManager packageManager;


       private UsageStatsManager usage;
    private Object Context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


View view=inflater.inflate(R.layout.list,container,false);
    lists=(ListView)view.findViewById(R.id.list);
    /*MainActivity main=new MainActivity();
        packageManager=main.packageManager;
        usage=main.usage;
        List<UsageStats> usageStats=usage.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,System.currentTimeMillis()-3600000,System.currentTimeMillis());
        //  Map<String,UsageStats> usagestats=usage.queryAndAggregateUsageStats(System.currentTimeMillis()-3600000,System.currentTimeMillis());
      //  List<UsageStats> appusage= new ArrayList<UsageStats>();
        //appusage.addAll(usagestats.values());
        List<String> appusage=new ArrayList<String>();
        Iterator<UsageStats> iterator=usageStats.iterator();
        List<String> packs=new ArrayList<String>();
        while(iterator.hasNext()){
            appusage.add(iterator.next().getPackageName());}
        Iterator<String> it=appusage.iterator();
        while(it.hasNext()) {
            try {
                packs.add(packageManager.getApplicationLabel(packageManager.getApplicationInfo(it.next(),PackageManager.GET_META_DATA)).toString());
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        /*while(iterator.hasNext()){
            try {
                packs.add(packageManager.getApplicationLabel(packageManager.getApplicationInfo(iterator.next().getPackageName(),PackageManager.GET_META_DATA)).toString());
            } catch (PackageManager.NameNotFoundException e) {


            }*/
Bundle b=new Bundle();
b.getStringArrayList("sherlock");

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, b.getStringArrayList("sherlock"));
                lists.setAdapter(itemsAdapter);
                return  view;
    }}




