package app.com.moviez.anant.moviez.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.OnCreate;
import net.simonvt.schematic.annotation.OnUpgrade;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by anant on 2015-10-18.
 */

@Database(version = MovieDb.DATABASE_VERSION)

public class MovieDb {


    public static final String[] MIGRATIONS = {};

//@ExecOnCreate PRAGMA foreign_keys = ON;

    public static final int DATABASE_VERSION = 3;

   // static final String DATABASE_NAME = "movie.db";





    @Table(MoviesColumns.class)
    public static final String movies = "movies";


    @Table(ReviewsColumns.class)
    public static final String reviews = "reviews";

    @Table(TrailersColumns.class)
    public static final String trailers = "trailers";


    @OnCreate
    public static void onCreate(SQLiteDatabase database) {
       // database.execSQL(DATABASE_CREATE);
    }
    @OnUpgrade
    public static void onUpgrade(Context context, SQLiteDatabase db, int oldVersion,
                                            int newVersion) {
;




}

    //"archived_planets";
   /* public MovieDb (Context context){

        super(context,DATABSE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        final String SQL_CREATE_MOVIE_DETAIL_TABLE = "CREATE TABLE "+ MovieContract.DetailsEntry.TABLE_NAME

                +"("+ MovieContract.DetailsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                MovieContract.DetailsEntry.COLOUMN_MOVIE_LOC_KEY + " INTEGER NOT NULL, " +
                MovieContract.DetailsEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                MovieContract.DetailsEntry.COLUMN_AUTHOR + " TEXT NOT NULL, " +
                MovieContract.DetailsEntry.COLUMN_CONTENT + " TEXT NOT NULL," +
                MovieContract.DetailsEntry.COLUMN_MOVIE_TRAILER_KEY + " TEXT NOT NULL, "+
                MovieContract.DetailsEntry.COLUMN_MOVIE_TRAILER_NAME + " TEXT NOT NULL, "+
                //MovieContract.DetailsEntry.COLUMN_URL + " TEXT NOT NULL, " +
                " FOREIGN KEY (" + MovieContract.DetailsEntry.COLOUMN_MOVIE_LOC_KEY +") REFERENCES "+
                MovieContract.MovieEntry.TABLE_NAME + " ("+ MovieContract.MovieEntry._ID + " )"+" )";








        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME +"( " +

                MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY, "+


                MovieContract.MovieEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_BACKDROP_PATH + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE + " REAL NOT NULL "+
                " )";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_DETAIL_TABLE);

    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param //db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
   /* @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ MovieContract.DetailsEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ MovieContract.MovieEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);

    }*/
}
