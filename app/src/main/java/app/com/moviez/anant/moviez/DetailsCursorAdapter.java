package app.com.moviez.anant.moviez;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.com.moviez.anant.moviez.data.MoviesColumns;

/**
 * Created by anant on 2016-01-07.
 */
public class DetailsCursorAdapter extends CursorAdapter {

    public static final String LOG_TAG = DetailsCursorAdapter.class.getSimpleName();
    protected static final String DETAILACTIVITY_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    protected static final String POSTER_SIZE = "w342";
    /**
     * Recommended constructor.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     * @param flags   Flags used to determine the behavior of the adapter; may
     *                be any combination of {@link #FLAG_AUTO_REQUERY} and
     *                {@link #FLAG_REGISTER_CONTENT_OBSERVER}.
     */
    public DetailsCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }


    private String setTitle(Cursor cursor){
        // get row indices for our cursor
        int idx_movie_name = cursor.getColumnIndex(MoviesColumns.COLUMN_ORIGINAL_TITLE);

        String movie_movie_title = cursor.getString(idx_movie_name);
        Log.v("cursor title", movie_movie_title);
        return  movie_movie_title;


    }
    private String setImage(Cursor cursor){
// get row indices for our cursor
        int idx_movie_image = cursor.getColumnIndex(MoviesColumns.COLUMN_BACKDROP_PATH);

        String movie_movie_image = cursor.getString(idx_movie_image);

        Log.i(LOG_TAG, "value of movie id fetched " +movie_movie_image);
        StringBuffer sb = new StringBuffer();
        sb.append(ImageAdapter.IMAGE_BASE_URL);
        sb.append(ImageAdapter.POSTER_SIZE);
        sb.append(movie_movie_image);
        String url = sb.toString();
        Log.v("cursor title", movie_movie_image);
        return  url;



    }
    private float setRating(Cursor cursor){

// get row indices for our cursor
        int idx_movie_rating = cursor.getColumnIndex(MoviesColumns.COLUMN_VOTE_AVERAGE);

        String movie_movie_rating = cursor.getString(idx_movie_rating);
        Log.v("cursor title", movie_movie_rating);
        float ratingfloat = Float.parseFloat(movie_movie_rating);
        return  ratingfloat;



    }
    private String setReleaseDate(Cursor cursor){

        int idx_movie_releasedate = cursor.getColumnIndex(MoviesColumns.COLUMN_RELEASE_DATE);

        String movie_movie_raelease = cursor.getString(idx_movie_releasedate);
        Log.v("cursor title", movie_movie_raelease);
        return  movie_movie_raelease;

    }
    private String setAboutMovie(Cursor cursor){


        int idx_movie_about = cursor.getColumnIndex(MoviesColumns.COLUMN_OVERVIEW);

        String movie_movie_release = cursor.getString(idx_movie_about);
        Log.v("cursor title", movie_movie_release);
        return  movie_movie_release;


    }


    /**
     * Makes a new view to hold the data pointed to by cursor.
     *
     * @param context Interface to application's global information
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {


        View view = LayoutInflater.from(context).inflate(R.layout.activity_detail,parent,false);

        return view;
    }

    /**
     * Bind an existing view to the data pointed to by cursor
     *
     * @param view    Existing view, returned earlier by newView
     * @param context Interface to application's global information
     * @param cursor  The cursor from which to get the data. The cursor is already
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView abt_movie  = ((TextView) view.findViewById(R.id.aboutmovie));
        abt_movie.setText(setAboutMovie(cursor));

        TextView title_movie = (TextView) view.findViewById(R.id.title_textview);
        title_movie.setText(setTitle(cursor));

        TextView movie_releasedate = (TextView)view.findViewById(R.id.releasedate_display);
        movie_releasedate.setText(setReleaseDate(cursor));
        ImageView poster_view = (ImageView) view.findViewById(R.id.movieposter);

        Picasso.with(context).load(setImage(cursor)).into(poster_view);
        RatingBar user_rating_bar = ((RatingBar) view.findViewById(R.id.ratingBar));

        user_rating_bar.setRating(setRating(cursor));

    }
}
