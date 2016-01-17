package app.com.moviez.anant.moviez.data;

import android.test.AndroidTestCase;

/**
 * Created by anant on 2015-10-30.
 */
public class TestProvider extends AndroidTestCase {


    public void testGetType() {
        // content://com.example.android.sunshine.app/weather/
        String type = mContext.getContentResolver().getType(MovieContract.MovieEntry.CONTENT_URI);
        // vnd.android.cursor.dir/com.example.android.sunshine.app/weather
        assertEquals("Error: the WeatherEntry CONTENT_URI should return WeatherEntry.CONTENT_TYPE",
                MovieContract.MovieEntry.CONTENT_TYPE, type);
    }
    // content://com.example.android.sunshine.app/weather/94074

      /*  // vnd.android.cursor.dir/com.example.android.sunshine.app/weather
        assertEquals("Error: the WeatherEntry CONTENT_URI with location should return WeatherEntry.CONTENT_TYPE",
                MovieContract.MovieEntry.CONTENT_TYPE, type);
        long testDate = 1419120000L; // December 21st, 2014
        // content://com.example.android.sunshine.app/weather/94074/20140612
            type = mContext.getContentResolver().getType(
                 //   MovieContract.MovieEntry.buildWeatherLocationWithDate(testLocation, testDate));
        // vnd.android.cursor.item/com.example.android.sunshine.app/weather/1419120000
        //assertEquals("Error: the WeatherEntry CONTENT_URI with location and date should return WeatherEntry.CONTENT_ITEM_TYPE",
                //MovieContract.MovieEntry.CONTENT_ITEM_TYPE, type);
        // content://com.example.android.sunshine.app/location/
        //type = mContext.getContentResolver().getType(MovieContract.DetailsEntry.CONTENT_URI);
        // vnd.android.cursor.dir/com.example.android.sunshine.app/location
     //   assertEquals("Error: the LocationEntry CONTENT_URI should return LocationEntry.CONTENT_TYPE",
               // LocationEntry.CONTENT_TYPE, type);


}*/
}