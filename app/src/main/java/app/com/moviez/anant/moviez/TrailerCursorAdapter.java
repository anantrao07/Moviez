package app.com.moviez.anant.moviez;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import app.com.moviez.anant.moviez.data.TrailersColumns;

/**
 * Created by anant on 2015-11-09.
 */
public class TrailerCursorAdapter extends CursorAdapter {

    protected static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    protected static final String POSTER_SIZE = "w342";
    public TrailerCursorAdapter(Context context, Cursor c, int flags){

        super(context, c, flags);
    }


    /**
     * Makes a new view to hold the data pointed to by cursor.
     *
     * @param //context Interface to application's global information
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param //parent  The parent to which the new view is attached to
     * @return the newly created view.
     */

    private String convertCursorRowTrailerName(Cursor cursor) {
        // get row indices for our cursor
        int idx_trailer_name = cursor.getColumnIndex(TrailersColumns.COLUMN_MOVIE_TRAILER_NAME);

        String movie_trailer_title = cursor.getString(idx_trailer_name);
        Log.v("cursor title", movie_trailer_title);
        return  movie_trailer_title;

    }

    private String convertCursorRowTrailerKey(Cursor cursor) {
        // get row indices for our cursor

        int idx_trailer_key = cursor.getColumnIndex(TrailersColumns.COLUMN_MOVIE_TRAILER_KEY);


        String movie_trailer_key = cursor.getString(idx_trailer_key);
        Log.v("cursor image", movie_trailer_key);
        return  movie_trailer_key;

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view  = LayoutInflater.from(context).inflate(R.layout.trailer_list_item,parent,false);


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

        Log.v("&^%$#@@%#^$name#######", convertCursorRowTrailerName(cursor));
        TextView trailername  = (TextView)view.findViewById(R.id.trailer_name);

        trailername.setText(convertCursorRowTrailerName(cursor));
        ImageView playbutton = (ImageView)view.findViewById(R.id.imageplayButton);
        Log.v("&^%$#@@%#^$name#######", convertCursorRowTrailerName(cursor));

    }
}
