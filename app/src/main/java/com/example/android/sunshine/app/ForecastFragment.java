package com.example.android.sunshine.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * forecast fragment containing a simple view.
 * <p>
 * Created by jwang on 11/29/16.
 */
public class ForecastFragment extends Fragment {

//  private static final String LOGTAG = ForecastFragment.class.getSimpleName();

  public ForecastFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    List<String> weatherData = Arrays.asList(
        "Today -- Sunny - 88/63",
        "Tomorrow -- Foggy - 70/46",
        "Wed -- Cloudy - 72/63",
        "Thur -- Rainy - 64/51",
        "Fri -- Foggy - 70/46",
        "Sat -- Sunny - 76/68"
    );



    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        getActivity(),
        R.layout.list_item_forecast,
        R.id.list_item_forecast_textview,
        weatherData
    );



    View rootView = inflater.inflate(R.layout.fragment_main, container, false);

    ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
    listView.setAdapter(adapter);

    return rootView;
  }

  public class FetchWeatherTask extends AsyncTask {
    private final String LOGTAG = FetchWeatherTask.class.getSimpleName();
    private String weatherResponse;

    @Override
    protected Object doInBackground(Object[] objects) {

      try {
        URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=cambridge,us&cnt=7");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        InputStream inputStream = conn.getInputStream();
        StringBuffer sb = new StringBuffer();

        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));

        String line;

        while((line = r.readLine()) != null) {
          sb.append(line);
        }

        Log.w(LOGTAG, "data read from openweathermap : " + sb.toString());

        weatherResponse = sb.toString();

      } catch (MalformedURLException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }

      return weatherResponse;
    }
  }
}
