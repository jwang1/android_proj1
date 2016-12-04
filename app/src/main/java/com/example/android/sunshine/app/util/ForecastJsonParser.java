package com.example.android.sunshine.app.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Util class to parse json data from OpenWeatherMap call.
 *
 * Created by jwang on 12/3/16.
 */

public class ForecastJsonParser {
  public final static String LIST = "list";
  public final static String WEATHER = "weather";
  public final static String TEMPERATURE = "temp";
  public final static String DESCRIPTION = "main";
  public final static String DATE = "dt";
  public final static String MAX = "max";
  public final static String MIN = "min";
  public final static String NUM_DAYS = "cnt";
  public final static String CITY = "city";
  public final static String CITY_NAME = "name";
  public final static String COUNTRY_NAME = "country";

  private final static String LOGTAG = ForecastJsonParser.class.getSimpleName();

  private final static String DATE_FORMAT = "EEE MMM dd";

  private static String getDate(long seconds, String dateFormat) {
    SimpleDateFormat format = new SimpleDateFormat(dateFormat);

    /* the following is a long way to get answer */
    //Calendar calendar = Calendar.getInstance();
    //calendar.setTimeInMillis(seconds * 1000);
    //return format.format(calendar.getTime());
    //Log.w(LOGTAG, "date time : " + format.format(calendar.getTime()) + " from : " + seconds);
    //Log.v(LOGTAG, "date time 2: " + calendar.get(Calendar.DAY_OF_WEEK) + " " + calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.DAY_OF_MONTH));

    return format.format(seconds * 1000);
  }

  private static String getWeatherDescription(JSONArray descriptionArray) throws JSONException {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < descriptionArray.length(); i++) {
      sb.append(descriptionArray.getJSONObject(i).getString(DESCRIPTION));
      sb.append(",");
    }

    // remove trailing comma
    if (sb.length() > 0) {
      sb.deleteCharAt(sb.length()-1);
    }

    return sb.toString();
  }

  private static String getTemperature(JSONObject tempJson) throws JSONException {
    StringBuilder sb = new StringBuilder();

    sb.append(tempJson.getDouble(MIN));
    sb.append("/");
    sb.append(tempJson.getDouble(MAX));

    return sb.toString();
  }

  public static List<String> getWeatherDataFromJson(String forecastJsonStr) throws JSONException {
    return getWeatherDataFromJson(forecastJsonStr,
        (new JSONObject(forecastJsonStr)).getInt(NUM_DAYS));
  }

  public static List<String> getWeatherDataFromJson(String forecastJsonStr, int numDays) throws JSONException {

    List<String> results = new ArrayList<>();

    JSONObject weatherData = new JSONObject(forecastJsonStr);
    JSONArray weatherArray = weatherData.getJSONArray(LIST);

    for(int i=0; i < weatherArray.length(); i++) {
      JSONObject daily = weatherArray.getJSONObject(i);
      StringBuilder forecastStr = new StringBuilder();

      // Date
      forecastStr.append(getDate(daily.getLong(DATE), DATE_FORMAT));

      forecastStr.append(" -- ");

      // Description
      forecastStr.append(getWeatherDescription(daily.getJSONArray(WEATHER)));

      forecastStr.append(" - ");

      // Min/Max
      forecastStr.append(getTemperature(daily.getJSONObject(TEMPERATURE)));

      results.add(forecastStr.toString());
    }

    return results;
  }

  public static String getCity(String weatherResponse) throws JSONException {
    JSONObject json = new JSONObject(weatherResponse);
    JSONObject city = json.getJSONObject(CITY);
    StringBuilder sb = new StringBuilder();

    sb.append(city.getString(CITY_NAME))
        .append(", ")
        .append(city.getString(COUNTRY_NAME));

    return sb.toString();
  }
}

// list -> temp | weather | dt
//         temp -> min/max
//                weather -> main
/*
{
	"city": {
		"id": 5375480,
		"name": "Mountain View",
		"coord": {
			"lon": -122.083847,
			"lat": 37.386051
		},
		"country": "US",
		"population": 0
	},
	"cod": "200",
	"message": 0.0156,

	"cnt": 7,

	"list": [
		{
			"dt": 1480618800,
			"temp": {
				"day": 9.57,
				"min": 5.74,
				"max": 9.57,
				"night": 5.74,
				"eve": 9.57,
				"morn": 9.57
			},
			"pressure": 993.4,
			"humidity": 61,
			"weather": [
				{
					"id": 800,
					"main": "Clear",
					"description": "clear sky",
					"icon": "01n"
				}
			],
			"speed": 3.07,
			"deg": 345,
			"clouds": 0
		},

		{
			"dt": 1480705200,
			"temp": {
				"day": 9.88,
				"min": 5.34,
				"max": 12.21,
				"night": 5.89,
				"eve": 11.23,
				"morn": 5.34
			},
			"pressure": 997.42,
			"humidity": 52,
			"weather": [
				{
					"id": 800,
					"main": "Clear",
					"description": "clear sky",
					"icon": "01d"
				}
			],
			"speed": 3.27,
			"deg": 3,
			"clouds": 0
		},
		{
			"dt": 1480791600,
			"temp": {
				"day": 12.07,
				"min": 0.79,
				"max": 16.82,
				"night": 3.36,
				"eve": 15.03,
				"morn": 1.41
			},
			"pressure": 1000.14,
			"humidity": 68,
			"weather": [
				{
					"id": 800,
					"main": "Clear",
					"description": "clear sky",
					"icon": "01d"
				}
			],
			"speed": 1.26,
			"deg": 14,
			"clouds": 0
		},
		{
			"dt": 1480878000,
			"temp": {
				"day": 10.94,
				"min": 7.72,
				"max": 12.55,
				"night": 10.42,
				"eve": 12.55,
				"morn": 7.72
			},
			"pressure": 1010.1,
			"humidity": 0,
			"weather": [
				{
					"id": 502,
					"main": "Rain",
					"description": "heavy intensity rain",
					"icon": "10d"
				}
			],
			"speed": 3.17,
			"deg": 168,
			"clouds": 62,
			"rain": 12.99
		},
		{
			"dt": 1480964400,
			"temp": {
				"day": 10.17,
				"min": 7.78,
				"max": 10.17,
				"night": 7.78,
				"eve": 9.27,
				"morn": 9.33
			},
			"pressure": 1004.89,
			"humidity": 0,
			"weather": [
				{
					"id": 502,
					"main": "Rain",
					"description": "heavy intensity rain",
					"icon": "10d"
				}
			],
			"speed": 2.67,
			"deg": 274,
			"clouds": 71,
			"rain": 15.42
		},
		{
			"dt": 1481054400,
			"temp": {
				"day": 8.92,
				"min": 6.13,
				"max": 10.56,
				"night": 6.13,
				"eve": 10.56,
				"morn": 7.02
			},
			"pressure": 1007.76,
			"humidity": 0,
			"weather": [
				{
					"id": 800,
					"main": "Clear",
					"description": "clear sky",
					"icon": "01d"
				}
			],
			"speed": 5.37,
			"deg": 341,
			"clouds": 0
		},
		{
			"dt": 1481140800,
			"temp": {
				"day": 8.93,
				"min": 4.49,
				"max": 10.76,
				"night": 6.58,
				"eve": 10.76,
				"morn": 4.49
			},
			"pressure": 1020.14,
			"humidity": 0,
			"weather": [
				{
					"id": 800,
					"main": "Clear",
					"description": "clear sky",
					"icon": "01d"
				}
			],
			"speed": 2.18,
			"deg": 47,
			"clouds": 11
		}
	]
}

 */
