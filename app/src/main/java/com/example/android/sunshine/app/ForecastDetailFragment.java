package com.example.android.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
//import android.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Detailed Forecast fragment class.
 *
 * Created by jwang on 12/3/16.
 */
public class ForecastDetailFragment extends Fragment {

  private final static String FORECAST_SHARE_HASHTAG = "#SunshineApp";
  private final static String LOGTAG = ForecastDetailFragment.class.getSimpleName();

  private String forecastString;

  private ShareActionProvider shareActionProvider;


  public ForecastDetailFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.forecastdetailfragment, menu);

    MenuItem item = menu.findItem(R.id.menu_item_share);

    shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

    if (shareActionProvider != null) {
      shareActionProvider.setShareIntent(createShareForecastIntent());
    } else {
      String msg = "Share Action Provider is Null";
      Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG)
          .show();
      Log.d(LOGTAG, msg);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInBundle) {
    // View rootView = inflater.inflate(R.layout.activity_detail, container, false);  // culprit for following textView NULL pointer
    View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

    Intent intent = getActivity().getIntent();
    if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
      forecastString = intent.getStringExtra(Intent.EXTRA_TEXT);

      TextView textView = (TextView) rootView.findViewById(R.id.detail_textview);
      textView.setText(forecastString);
    }

    return rootView;
  }

  private Intent createShareForecastIntent() {
    Intent shareIntent = new Intent(Intent.ACTION_SEND);
    shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
    shareIntent.setType("text/plain");
    shareIntent.putExtra(Intent.EXTRA_TEXT, forecastString + FORECAST_SHARE_HASHTAG);
    return shareIntent;
  }

}
