package com.example.android.sunshine.app.util;

import com.example.android.sunshine.app.util.ForecastJsonParser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

/**
 * Tests for ForecastJsonParser
 *
 * Created by jwang on 12/3/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ForecastJsonParserTest {
  private String jsonData;

  @Test
  public void getWeatherDataFromJson() throws Exception {

    List<String> forecastString = ForecastJsonParser.getWeatherDataFromJson(jsonData);

    System.out.println(forecastString);
  }

  @Before
  public void setUp() throws Exception {
    // beautified Json data is in ForecastJsonParer.java class
    jsonData = "{\"city\":{\"id\":5375480,\"name\":\"Mountain View\",\"coord\":{\"lon\":-122.083847,\"lat\":37.386051},\"country\":\"US\",\"population\":0},\"cod\":\"200\",\"message\":0.3799,\"cnt\":7,\"list\":[{\"dt\":1480791600,\"temp\":{\"day\":15.58,\"min\":3.83,\"max\":20.29,\"night\":3.83,\"eve\":17.35,\"morn\":4.38},\"pressure\":999.65,\"humidity\":66,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"speed\":1.21,\"deg\":26,\"clouds\":0},{\"dt\":1480878000,\"temp\":{\"day\":11.54,\"min\":1.17,\"max\":15.82,\"night\":7.92,\"eve\":14.54,\"morn\":1.5},\"pressure\":996.51,\"humidity\":63,\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}],\"speed\":0.58,\"deg\":37,\"clouds\":24},{\"dt\":1480964400,\"temp\":{\"day\":10.96,\"min\":4.88,\"max\":12.63,\"night\":4.88,\"eve\":11.38,\"morn\":9.26},\"pressure\":994.45,\"humidity\":74,\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}],\"speed\":2.17,\"deg\":310,\"clouds\":12},{\"dt\":1481054400,\"temp\":{\"day\":9.99,\"min\":6.8,\"max\":10.49,\"night\":6.8,\"eve\":10.49,\"morn\":8.53},\"pressure\":1009.65,\"humidity\":0,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":6.57,\"deg\":324,\"clouds\":30,\"rain\":3.24},{\"dt\":1481140800,\"temp\":{\"day\":9.2,\"min\":5.06,\"max\":10.64,\"night\":7.03,\"eve\":10.64,\"morn\":5.06},\"pressure\":1020.3,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":1.92,\"deg\":25,\"clouds\":7,\"rain\":0.37},{\"dt\":1481227200,\"temp\":{\"day\":8.44,\"min\":5.24,\"max\":10.95,\"night\":5.24,\"eve\":10.95,\"morn\":6.56},\"pressure\":1026.55,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"speed\":2.37,\"deg\":93,\"clouds\":63},{\"dt\":1481313600,\"temp\":{\"day\":8.62,\"min\":3.79,\"max\":12.81,\"night\":6.5,\"eve\":12.81,\"morn\":3.79},\"pressure\":1023.39,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"speed\":2.64,\"deg\":60,\"clouds\":9}]}\n";
  }

}

