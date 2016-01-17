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

import com.squareup.picasso.Picasso;

import app.com.moviez.anant.moviez.data.MoviesColumns;

/**
 * Created by anant on 2015-11-04.
 */
public class MoviesAdapter extends CursorAdapter {
    /**
     * Recommended constructor.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     * @param flags   Flags used to determine the behavior of the adapter; may
     *                be any combination of {@link #FLAG_AUTO_REQUERY} and
     *                {@link #FLAG_REGISTER_CONTENT_OBSERVER}.
     */
    protected static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    protected static final String POSTER_SIZE = "w342";
    public MoviesAdapter(Context context, Cursor c , int parent)
    {
        super(context, c,false);
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


    private String convertCursorRowTitle(Cursor cursor) {
        // get row indices for our cursor
        int idx_title = cursor.getColumnIndex(MoviesColumns.COLUMN_ORIGINAL_TITLE);

        String movie_title = cursor.getString(idx_title);
        Log.v("cursor title", movie_title);
        return  movie_title;

    }

    private String convertCursorRowimage(Cursor cursor) {
        // get row indices for our cursor

        int idx_image = cursor.getColumnIndex(MoviesColumns.COLUMN_BACKDROP_PATH);


        String movie_image = cursor.getString(idx_image);
        Log.v("cursor image", movie_image);
        return  movie_image;

    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {


        View view  = LayoutInflater.from(context).inflate(R.layout.activity_image_detail,parent,false);



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

        TextView tv = (TextView)view.findViewById(R.id.image_textview);
        ImageView iv = (ImageView)view.findViewById(R.id.detail_imageview);

        StringBuffer sb = new StringBuffer();
        sb.append(IMAGE_BASE_URL);
        sb.append(POSTER_SIZE);
        sb.append(convertCursorRowimage(cursor));
        String url = sb.toString();
        Picasso.with(context).load(url).into(iv);
        tv.setText(convertCursorRowTitle(cursor));

    }



}
