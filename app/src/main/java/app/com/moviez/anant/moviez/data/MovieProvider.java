package app.com.moviez.anant.moviez.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;
/**
 * Created by anant on 2015-10-22.
 */
@ContentProvider(authority = MovieProvider.CONTENT_AUTHORITY,
        database = MovieDb.class)

public final  class MovieProvider {

    private MovieProvider() {
    }

    public static final String CONTENT_AUTHORITY = "com.moviez.anant.moviez.data.MovieProvider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    interface Path {
        String MOVIES = "movies";
        String MOVIES_TRAILERS = "movies_trailers";
        String MOVIES_REVIEWS = "movies_reviews";
    }

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }

    @TableEndpoint(table = MovieDb.movies)
    public static class Movies {
        @ContentUri(
                path = Path.MOVIES,
                type = "vnd.android.cursor.dir/movies",
                defaultSort = MoviesColumns.COLUMN_VOTE_AVERAGE + " ASC")
        public static final Uri CONTENT_URI = buildUri(Path.MOVIES);

        @InexactContentUri(
                name = "MOVIE_ID",
                path = Path.MOVIES + "/#",
                type = "vnd.android.cursor.item/movies",
                whereColumn = MoviesColumns._ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return buildUri(Path.MOVIES, String.valueOf(id));

        }
    }

    @TableEndpoint(table = MovieDb.trailers)
    public static class MovieTrailers {
        @ContentUri(
                path = Path.MOVIES_TRAILERS,
                type = "vnd.android.cursor.dir/movies_trailers",
                defaultSort = TrailersColumns.COLUMN_TMOVIE_ID + " ASC"
        )
        public static final Uri CONTENT_URI = buildUri(Path.MOVIES_TRAILERS);

        @InexactContentUri(
                name = "MOVIE_ID",
                path = Path.MOVIES_TRAILERS + "/#",
                type = "vnd.android.cursor.item/movies_trailers",
                whereColumn = TrailersColumns._ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return buildUri(Path.MOVIES_TRAILERS, String.valueOf(id));

        }

    }

    @TableEndpoint(table = MovieDb.reviews)
    public static class MovieReviews {
        @ContentUri(
                path = Path.MOVIES_REVIEWS,
                type = "vnd.android.cursor.dir/movies_reviews",
                defaultSort = ReviewsColumns.COLUMN_RMOVIE_ROW_ID + " ASC"
        )
        public static final Uri CONTENT_URI = buildUri(Path.MOVIES_REVIEWS);

        @InexactContentUri(
                name = "MOVIE_ID",
                path = Path.MOVIES_REVIEWS + "/#",
                type = "vnd.android.cursor.item/movies_reviews",
                whereColumn = ReviewsColumns._ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return buildUri(Path.MOVIES_REVIEWS, String.valueOf(id));


        }

    }
        //static final int MOVIE = 100;
        // static final int MOVIE_WITH_DETAILS = 101;
        //static final int WEATHER_WITH_LOCATION_AND_DATE = 102;
        //   static final int LOCATION = 300;
        // private static final SQLiteQueryBuilder smovieByMovieSettingQueryBuilder;

   /* static UriMatcher buildUriMatcher() {
        // 1) The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case. Add the constructor below.


        // 2) Use the addURI function to match each of the types.  Use the constants from
        // WeatherContract to help define the types to the UriMatcher.


        // 3) Return the new matcher!

        // All paths added to the UriMatcher have a corresponding code to return when a match is
        // found.  The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case.
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MovieContract.CONTENT_AUTHORITY;

        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, MovieContract.PATH_MOVIE, MOVIE);
        matcher.addURI(authority, MovieContract.PATH_DETAIL + "/*", MOVIE_WITH_DETAILS);
        return matcher;
    }

*/
    }

