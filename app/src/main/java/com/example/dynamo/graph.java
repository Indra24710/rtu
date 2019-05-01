package com.example.dynamo;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Iterator;
import java.util.List;

public class graph extends AppCompatActivity {
private PackageManager packageManager;
UsageStatsManager usageStatsManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);
Bundle b=getIntent().getExtras();
UsageStats usageStats=null;
packageManager=getPackageManager();
        usageStatsManager=(UsageStatsManager)getSystemService(Context.USAGE_STATS_SERVICE);

int i=0,j=1;
long [] a=new long[5];
while(i<5){
List<UsageStats> usage=usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_WEEKLY,System.currentTimeMillis()-(43200000*j),System.currentTimeMillis());
        Iterator<UsageStats> iterator=usage.iterator();
        UsageStats tempusage=null;
        while(iterator.hasNext()){
            tempusage=iterator.next();
            if((tempusage.getPackageName())==b.getString("name")){
                usageStats=tempusage;
                break;
            }
        }



        j++;
        a[i]= (usageStats.getTotalTimeInForeground()/60000);
i++;
}
        GraphView graph = (GraphView)findViewById(R.id.appgraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, a[0]),
                new DataPoint(1,a[1]),
                new DataPoint(2, a[2]),
                new DataPoint(3, a[3]),
                new DataPoint(4,a[4]),
        });}

}

