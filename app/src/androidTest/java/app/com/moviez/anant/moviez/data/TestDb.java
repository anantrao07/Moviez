package app.com.moviez.anant.moviez.data;

import android.test.AndroidTestCase;

/**
 * Created by anant on 2015-10-18.
 */
public class TestDb extends AndroidTestCase {


    public static final String LOG_CAT = TestDb.class.getName();
    long movieRowId;
    //since we want each test case to start on a clean slate we use the below method

    public void testDeleteDb() {
        mContext.deleteDatabase(MovieDb.DATABASE_NAME);

    }

    public void testMovieTable() {


        //insertMovie();
    }


    //public long insertMovie() {

    public void testDatabase() {
        testDeleteDb();
        mContext.deleteDatabase(MovieDb.DATABASE_NAME);
        // First step: Get reference to writable database
        // If there's an error in those massive SQL table creation Strings,
        // errors will be thrown here when you try to get a writable database.
        MovieDb dbHelper = new MovieDb();
        // SQLiteDatabase db = mContext.getWritableDatabase(dbHelper);
        //
        //  assertEquals("Database is open",true, db.isOpen());
        // Second Step: Create ContentValues of what you want to insert
        // (you can use the createNorthPoleLocationValues if you wish)
        //ContentValues testValues = TestUtilities.createMovieValues();

    }
}
/*
        movieRowId = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, testValues);

        // Verify we got a row back.
        assertTrue("Error : Movie Row id is not returned as -1 " , movieRowId != -1);

        Cursor cursor = db.query(
                MovieContract.MovieEntry.TABLE_NAME,  // Table to Query
                null, // all columns
                null, // Columns for the "where" clause
                null, // Values for the "where" clause
                null, // columns to group by
                null, // columns to filter by row groups
                null // sort order
        );

        // Move the cursor to a valid database row and check to see if we got any records back
        // from the query
        assertTrue("Error: No Records returned from Movie Entry  query", cursor.moveToFirst());

        // Fifth Step: Validate data in resulting Cursor with the original ContentValues
        // (you can use the validateCurrentRecord function in TestUtilities to validate the
        // query if you like)

        TestUtilities.validateCurrentRecord("Error: Movie Entry Query Validation Failed",
                cursor, testValues);

        // Move the cursor to demonstrate that there is only one record in the database
        assertFalse("Error: More than one record returned from Movie Entry query",
                cursor.moveToNext());

        cursor.close();
        db.close();
        return movieRowId;
    }

*/

/*
    public void testDetailEntryTable() {

        long locationMovieId = insertMovie();


        MovieDb dbHelper = new MovieDb(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Second Step (Weather): Create weather values
        ContentValues weatherValues = TestUtilities.createMovieDetailsValues(locationMovieId);
        // Third Step (Weather): Insert ContentValues into database and get a row ID back

        long weatherRowId = db.insert(MovieContract.DetailsEntry.TABLE_NAME, null, weatherValues);

        // Make sure we have a valid row ID.

         assertFalse("Error: Location Not Inserted Correctly", weatherRowId == -1L);

//        assertTrue(weatherRowId != -1);
        // Fourth Step: Query the database and receive a Cursor back
        // A cursor is your primary interface to the query results.
        Cursor weatherCursor = db.query(MovieContract.DetailsEntry.TABLE_NAME,  // Table to Query
                null, // leaving "columns" null just returns all the columns.
                null, // cols for "where" clause
                null, // values for "where" clause
                null, // columns to group by
                null, // columns to filter by row groups
                null  // sort order
        );

        // Move the cursor to the first valid database row and check to see if we have any rows
        assertTrue("Error: No Records returned from Detail Entry query", weatherCursor.moveToFirst());

        // Fifth Step: Validate the location Query
        TestUtilities.validateCurrentRecord("testInsertReadDb Detail Entry failed to validate",
                weatherCursor, weatherValues);

        // Move the cursor to demonstrate that there is only one record in the database
        assertFalse("Error: More than one record returned from Movie detail Entry query",
                weatherCursor.moveToNext());

    }

}
  /*  public void testCreateDb() throws Throwable {


        mContext.deleteDatabase(MovieDb.DATABSE_NAME);

        MovieDb dbHelper = new MovieDb(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();



        assertEquals(true, db.isOpen());
        assertEquals(true,dbDetails.isOpen());

        ContentValues testValues = TestUtilities.createMovieValues();

        long movieRowId;
        movieRowId = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, testValues);
        ContentValues testValuesDetails = TestUtilities.createMovieDetailsValues(movieRowId);

        assertTrue(movieRowId != -1);
       //db.insert(MovieContract.DetailsEntry.TABLE_NAME,null,testValuesDetails);

        assertFalse("Error: Location Not Inserted Correctly", movieRowId == -1L);

        // Verify we got a row back.
               assertTrue(movieRowId != -1);

        // Fourth Step: Query the database and receive a Cursor back
        // A cursor is your primary interface to the query results.
                Cursor cursor = db.query(
                MovieContract.MovieEntry.TABLE_NAME,  // Table to Query
                null, // all columns
                null, // Columns for the "where" clause
                null, // Values for the "where" clause
                null, // columns to group by
                null, // columns to filter by row groups
                null // sort order
                );


        Cursor cursor1 = dbDetails.query(MovieContract.DetailsEntry.TABLE_NAME,  // Table to Query
                null, // all columns
                null, // Columns for the "where" clause
                null, // Values for the "where" clause
                null, // columns to group by
                null, // columns to filter by row groups
                null // sort order
        );
        // Move the cursor to a valid database row and check to see if we got any records back
        // from the query
        assertTrue( "Error: No Records returned from movie query", cursor.moveToFirst() );

        assertTrue( "Error: No Records returned from movie details query", cursor1.moveToFirst() );

        // Fifth Step: Validate data in resulting Cursor with the original ContentValues
                // (you can use the validateCurrentRecord function in TestUtilities to validate the
                // query if you like)
        TestUtilities.validateCurrentRecord("Error: Movie Query Validation Failed",
                cursor, testValues);

        TestUtilities.validateCurrentRecord("Error: MovieDetails Query Validation Failed",
                cursor1, testValuesDetails);

        // Move the cursor to demonstrate that there is only one record in the database
        assertFalse( "Error: More than one record returned from location query",
                cursor.moveToNext() );

        assertFalse("Error: More than one record returned from location query",
                cursor1.moveToNext());
        // Sixth Step: Close Cursor and Database
        cursor.close();
        cursor1.close();
        db.close();
        dbDetails.close();
*/















/*
        // have we created the tables we want?
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        assertTrue("Error: This means that the database has not been created correctly",
                c.moveToFirst());


*/

       /* public void setUp(){
            deleteDatabse();
        }


        public void testMovieDetailTable(){

    }

  /*  long movieDetailRowId = insertMovieDetail();

    // Make sure we have a valid row ID.
            assertFalse("Error: Location Not Inserted Correctly", movieDetailRowId == -1L);


    // First step: Get reference to writable database
    // If there's an error in those massive SQL table creation Strings,
    // errors will be thrown here when you try to get a writable database.

        MovieDb dbHelper = new MovieDb(mContext);

    SQLiteDatabase db = dbHelper.getWritableDatabase();

    ContentValues movieValues =  TestUtilities.createMovieValues(movieDetailRowId);

    long rowId =
            db.insert(MovieContract.MovieEntry.TABLE_NAME,null,movieValues);
    assertTrue(movieDetailRowId != 1);



    // Fourth Step: Query the database and receive a Cursor back
          // A cursor is your primary interface to the query results.

    Cursor movieCursor = db.query(MovieContract.MovieEntry,null,
            null,
            null,
            null,
            null);

    assertTrue( "Error: No Records returned from location query", movieCursor.moveToFirst() );

    // Fifth Step: Validate the location Query
          TestUtilities.validateCurrentRecord("testInsertReadDb weatherEntry failed to validate",
                           movieCursor, movieValues);

    // Move the cursor to demonstrate that there is only one record in the database

    assertFalse( "Error: More than one record returned from weather query",weatherCursor.moveToNext() );


    /*
        This function gets called before each test is executed to delete the database.  This makes
        sure that we always have a clean test.
     */


        // Sixth Step: Close cursor and database*/
        //  dbHelper.close();


        //}
//}*/


