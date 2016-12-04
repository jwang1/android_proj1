package com.example.android.sunshine.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Objects;

/**
 * Forecast Detail Activity class
 *
 * Created by jwang on 12/3/16.
 */
public class DetailActivity extends ActionBarActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, new ForecastDetailFragment())
          .commit();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.detail, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

}
