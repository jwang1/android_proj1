package com.example.android.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    // View rootView = inflater.inflate(R.layout.activity_detail, container, false);  // culprit for following textView NULL pointer
    View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

    Intent intent = getActivity().getIntent();
    if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
      String msg = intent.getStringExtra(Intent.EXTRA_TEXT);

      TextView textView = (TextView) rootView.findViewById(R.id.detail_textview);
      textView.setText(msg);
    }

    return rootView;
  }
}
