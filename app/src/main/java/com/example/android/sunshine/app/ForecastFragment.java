package com.example.android.sunshine.app;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.sunshine.app.util.ForecastJsonParser;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * forecast fragment containing a simple view.
 * <p>
 * Created by jwang on 11/29/16.
 */
public class ForecastFragment extends Fragment {

  //private static final String LOGTAG = ForecastFragment.class.getSimpleName();
  private ListView forecastListView;

  private String city;

  public ForecastFragment() {
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // this fragment handling events
    setHasOptionsMenu(true);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.forecastfragment, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();


    if (id == R.id.action_refresh) {
      Toast.makeText(getActivity(), "You just click Refresh menu", Toast.LENGTH_SHORT).show();
      FetchWeatherTask task = new FetchWeatherTask();
      task.execute(city);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_main, container, false);

    city = "25588";

    forecastListView = (ListView) rootView.findViewById(R.id.listview_forecast);

    FetchWeatherTask task = new FetchWeatherTask();
    task.execute(city);

    return rootView;
  }

  public class FetchWeatherTask extends AsyncTask<String, Void, String> {
    private final String LOGTAG = FetchWeatherTask.class.getSimpleName();
    private String weatherResponse;

    @Override
    protected void onProgressUpdate(Void... progress) {
      Toast.makeText(getActivity(), "calling OpenWeatherMap ...", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostExecute(String result) {
      // calling Json parser to handle results
      // Toast.makeText(getActivity(), "COMPLETE http call ...", Toast.LENGTH_LONG).show();


      // debugging message on Toast
      //Toast.makeText(getActivity(), weatherResponse.toString(), Toast.LENGTH_LONG).show();

      // update Adapter data
      try {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview,
            ForecastJsonParser.getWeatherDataFromJson(weatherResponse));
        forecastListView.setAdapter(arrayAdapter);
      } catch (JSONException e) {
        e.printStackTrace();
        Log.e(LOGTAG, e.getMessage());
      }
    }


    @Override
    protected String doInBackground(String... params) {

      try {
        String city = "94043";
        int numDays = 7;
        String urlBase = "http://api.openweathermap.org/data/2.5/forecast/daily?";

        // OPEN_WEATHER_MAP_API_KEY defined in build.gradle ;  points to the value in $HOME/.gradle/gradle.properties
        // http://stackoverflow.com/questions/27382236/buildconfig-file-in-android-purpose-and-possibilities
        // need to build first, otherwise not able to see the intended BuildConfig class.
        //String apiKey = "&APPID=" + BuildConfig.OPEN_WEATHER_MAP_API_KEY;

        Uri builtUri = Uri.parse(urlBase).buildUpon()
            .appendQueryParameter("q", params[0])
            .appendQueryParameter("mode", "json")
            .appendQueryParameter("units", "metric")
            .appendQueryParameter("cnt", Integer.toString(numDays))
            .appendQueryParameter("APPID", BuildConfig.OPEN_WEATHER_MAP_API_KEY)
            .build();

        URL url = new URL(builtUri.toString());

        Log.v(LOGTAG, "built URI : " + builtUri.toString());

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
        Log.e(LOGTAG, e.getMessage());
      } catch (Exception e) {
        e.printStackTrace();
        Log.e(LOGTAG, e.getMessage());
      }


      Log.i(LOGTAG, "response: " + weatherResponse);

      return weatherResponse;
    }
  }
}
