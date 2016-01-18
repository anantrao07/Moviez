package app.com.moviez.anant.moviez.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.Map;
import java.util.Set;

/**
 * Created by anant on 2015-10-19.
 */
public class TestUtilities extends AndroidTestCase {

    static final int TEST_MOVIE = 76341;
    static final String TEST_DATE = "2015-10-02";  // December 20th, 2014

                static void validateCursor(String error, Cursor valueCursor, ContentValues expectedValues) {
               assertTrue("Empty cursor returned. " + error, valueCursor.moveToFirst());
                validateCurrentRecord(error, valueCursor, expectedValues);
                valueCursor.close();
            }

                static void validateCurrentRecord(String error, Cursor valueCursor, ContentValues expectedValues) {
                Set< Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
                for (Map.Entry<String, Object> entry : valueSet) {
                        String columnName = entry.getKey();
                        int idx = valueCursor.getColumnIndex(columnName);
                        assertFalse("Column '" + columnName + "' not found. " + error, idx == -1);
                        String expectedValue = entry.getValue().toString();
                        assertEquals("Value '" + entry.getValue().toString() +
                                        "' did not match the expected value '" +
                                        expectedValue + "'. " + error, expectedValue, valueCursor.getString(idx));
                    }
           }



    static ContentValues createMovieDetailsValues(long movieRowId) {
        // Create a new map of values, where column names are the keys
        ContentValues testValues = new ContentValues();

        testValues.put(MovieContract.DetailsEntry.COLOUMN_MOVIE_LOC_KEY ,movieRowId);
        testValues.put(MovieContract.DetailsEntry.COLUMN_MOVIE_ID, TEST_MOVIE);
        testValues.put(MovieContract.DetailsEntry.COLUMN_AUTHOR, "Phileas Fogg");
        testValues.put(MovieContract.DetailsEntry.COLUMN_CONTENT, "Fabulous action movie. Lots of interesting characters. They don't make many movies like this. The whole movie from start to finish was entertaining I'm looking forward to seeing it again. I definitely recommend seeing it.");
        testValues.put(MovieContract.DetailsEntry.COLUMN_MOVIE_TRAILER_KEY, "YWNWi-ZWL3c");
        testValues.put(MovieContract.DetailsEntry.COLUMN_MOVIE_TRAILER_NAME, "Official Trailer #1");



        return testValues;
    }

    static ContentValues createMovieValues() {
        ContentValues movieValues = new ContentValues();


        movieValues.put(MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE, "Mad Max: Fury Road");
        movieValues.put(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH, "/tbhdm8UJAb4ViCTsulYFL3lxMCd.jpg");
        movieValues.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, "2015-05-15");
        movieValues.put(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE, 7.6);
        movieValues.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, "An apocalyptic story set in the furthest reaches of our planet, in a stark desert landscape where humanity is broken, and most everyone is crazed fighting for the necessities of life. Within this world exist two rebels on the run who just might be able to restore order. There's Max, a man of action and a man of few words, who seeks peace of mind following the loss of his wife and child in the aftermath of the chaos. And Furiosa, a woman of action and a woman who believes her path to survival may be achieved if she can make it across the desert back to her childhood homeland.");
        movieValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID, "76341");
      //  movieValues.put(MovieContract.MovieEntry.C, 5.5);
      //  movieValues.put(MovieContract.MovieEntry.COLUMN_WEATHER_ID, 321);


        return movieValues;
    }
}
