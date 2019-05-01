package com.example.dynamo;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class statsfragment extends Fragment {
    ListView lists;
    TextView text;
    private PackageManager packageManager;

   private UsageStatsManager usage;
   private List<UsageStats> usageStats;
   private List<UsageStats> newusagestats;
   private UsageStats usagestats;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.list,container,false);
        lists=view.findViewById(R.id.list);
        packageManager=getContext().getPackageManager();
        usage=(UsageStatsManager)getContext().getSystemService(Context.USAGE_STATS_SERVICE);

        usageStats=usage.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,System.currentTimeMillis()-43200000,System.currentTimeMillis());
        Iterator<UsageStats> iterator=usageStats.iterator();
        usagestats=null;
      newusagestats=new ArrayList<>();
        while(iterator.hasNext()){
            usagestats=iterator.next();
            if(packageManager.getLaunchIntentForPackage(usagestats.getPackageName())!=null){
                newusagestats.add(usagestats);
            }
        }



       CustomListAdapter itemsAdapter= new CustomListAdapter(getContext(),newusagestats);

        lists.setAdapter(itemsAdapter);

        return view;



    }

}




