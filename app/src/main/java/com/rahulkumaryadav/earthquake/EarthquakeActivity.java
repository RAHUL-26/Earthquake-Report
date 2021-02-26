package com.rahulkumaryadav.earthquake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {
    private static final String USGS_REQUEST_URL =
           "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    //"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    EarthquakeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.earthquake_activity);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // Create a fake list of earthquake locations.
        ListView earthquakeListView = findViewById(R.id.list);
        adapter = new EarthquakeAdapter(this,new ArrayList<>());
        earthquakeListView.setAdapter(adapter);

        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);
    }


//private void updateUi(List<Earthquake> earthquakes){
//
//        // Find a reference to the {@link ListView} in the layout
//        ListView earthquakeListView = (ListView) findViewById(R.id.list);
//
//        // Create a new {@link ArrayAdapter} of earthquakes
//        EarthquakeAdapter adapter = new EarthquakeAdapter(this,earthquakes);
//
//        // Set the adapter on the {@link ListView}
//        // so the list can be populated in the user interface
//        earthquakeListView.setAdapter(adapter);
//
//        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Earthquake currentEarthquake = adapter.getItem(position);
//                Intent url = new Intent(Intent.ACTION_VIEW);
//                url.setData(Uri.parse(currentEarthquake.getUrl()));
//                startActivity(url);
//            }
//        });
//    }

    private class EarthquakeAsyncTask extends AsyncTask<String,Void, List<Earthquake>>{

        @Override
        protected List<Earthquake> doInBackground(String... urls) {
            if (urls.length<1|| urls[0] ==null){
                return null;
            }
            List<Earthquake> earthquakes= QueryUtils.fetchEarthquakeData(urls[0]);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            super.onPostExecute(earthquakes);
            if (earthquakes==null){
                return;
            }
            adapter.clear();
            if (earthquakes!=null&& !earthquakes.isEmpty()){
                adapter.addAll(earthquakes);
            }
           // updateUi(earthquakes);
        }
    }
    }
