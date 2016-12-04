package com.example.android.sunshine.app;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * Test suite
 *
 * Created by jwang on 12/3/16.
 */

public class ApplicationTest extends ApplicationTestCase<Application> {
  public ApplicationTest() {
    super(Application.class);
  }
  public ApplicationTest(Class<Application> applicationClass) {
    super(applicationClass);
  }
}
