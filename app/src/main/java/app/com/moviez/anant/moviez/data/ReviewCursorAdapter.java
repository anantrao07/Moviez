package app.com.moviez.anant.moviez.data;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import app.com.moviez.anant.moviez.R;

/**
 * Created by anant on 2015-11-09.
 */
public class ReviewCursorAdapter extends CursorAdapter {

    public ReviewCursorAdapter(Context context, Cursor c, int flags)
    {
        super(context, c, flags);
    }




    private String convertCursorRowReviewName(Cursor cursor) {
        // get row indices for our cursor
        int idx_review_name = cursor.getColumnIndex(ReviewsColumns.COLUMN_AUTHOR);

        String movie_review_name = cursor.getString(idx_review_name);
        Log.v("cursor title", movie_review_name);

        if(movie_review_name.isEmpty()){
            return null;
        }
        else {


            return movie_review_name;

        }
    }

    private String convertCursorRowReviewContent(Cursor cursor) {
        // get row indices for our cursor

        int idx_review_content = cursor.getColumnIndex(ReviewsColumns.COLUMN_CONTENT);


        String movie_review_content = cursor.getString(idx_review_content);
        Log.v("cursor image", movie_review_content);
        if(movie_review_content.isEmpty()){
            return null;
        }
        else {
            return movie_review_content;
        }

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
        View view  = LayoutInflater.from(context).inflate(R.layout.reviews_list_item,parent,false);

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


        TextView review_Author = (TextView)view.findViewById(R.id.author_textView);

        TextView display_review = (TextView)view.findViewById(R.id.review_textview);


        display_review.setText(convertCursorRowReviewContent(cursor));
        review_Author.setText(convertCursorRowReviewName(cursor));
        Log.v("@@@@@@content#######" ,convertCursorRowReviewContent(cursor));
    }

}
