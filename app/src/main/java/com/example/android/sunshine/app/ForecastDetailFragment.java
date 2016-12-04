package com.example.android.sunshine.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Detailed Forecast fragment class.
 *
 * Created by jwang on 12/3/16.
 */
public class ForecastDetailFragment extends Fragment {
  public ForecastDetailFragment() {

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInBundle) {
    View rootView = inflater.inflate(R.layout.activity_detail, container, false);
    return rootView;
  }
}
