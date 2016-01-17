package app.com.moviez.anant.moviez;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import app.com.moviez.anant.moviez.data.MovieProvider;
import app.com.moviez.anant.moviez.data.MoviesColumns;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.Callback {

    boolean mTwoPane;
    int count = R.id.action_sort;
    private static final String DETAILFRAGMENT_TAG = "DFTAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ((findViewById(R.id.movie_detail_panel) != null)) {
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_panel, new DetailActivityFragment(), DETAILFRAGMENT_TAG)
                    .commit();
        } else {
            mTwoPane = false;


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;
        }


        return super.onOptionsItemSelected(item);
    }


   // @Override
    //public void onItemSelected(Cursor c) {
   @Override
   public void onItemSelected(Uri data , int pos) {
        if (mTwoPane) {

            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle bundle = new Bundle();

            Cursor c = getContentResolver().query(MovieProvider.Movies.CONTENT_URI,
                    new String[]{MoviesColumns.COLUMN_MOVIE_ID},
                    null,
                    null,//new String[]{Integer.toString(movieId)}, //   new String[]{Integer.toString(movieId)},
                    null);
            c.moveToPosition(pos);



            int idx_movie_id = c.getColumnIndex(MoviesColumns.COLUMN_MOVIE_ID);
            int idx_title = c.getColumnIndex(MoviesColumns.COLUMN_ORIGINAL_TITLE);
            ;
            int idx_about = c.getColumnIndex(MoviesColumns.COLUMN_OVERVIEW);
            ;
            int idx_release_date = c.getColumnIndex(MoviesColumns.COLUMN_RELEASE_DATE);
            ;
            int idx_rating = c.getColumnIndex(MoviesColumns.COLUMN_VOTE_AVERAGE);
            ;
            int idx_poster = c.getColumnIndex(MoviesColumns.COLUMN_BACKDROP_PATH);
            ;
            bundle.putParcelable(DetailActivityFragment.DETAIL_URI, (Parcelable) c);

            int movieIdInt = c.getInt(idx_movie_id);

            String poster = c.getString(idx_poster);
           // Log.i(LOG_TAG, "value of movie id fetched " + poster);
            StringBuffer sb = new StringBuffer();
            sb.append(ImageAdapter.IMAGE_BASE_URL);
            sb.append(ImageAdapter.POSTER_SIZE);
            sb.append(poster);
            String url = sb.toString();
            String user_ratingstring = Float.toString(c.getFloat(idx_rating));
            String movie_dString = Integer.toString(movieIdInt);
            bundle.putString("MOVIE_TITLE", c.getString(idx_title));
            bundle.putString("MOVIE_ABOUT", c.getString(idx_about));
            bundle.putString("MOVIE_RELEASE_DATE", c.getString(idx_release_date));
            bundle.putString("MOVIE_RATING", user_ratingstring);
            bundle.putString("MOVIE_POSTER", url);
            bundle.putString("MOVIE_ID", movie_dString);

            Log.e("Values is ", c.toString());

            DetailActivityFragment fragment = new DetailActivityFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_panel, fragment, DETAILFRAGMENT_TAG).commit();

        } else {

               Intent intent = new Intent(this, DetailActivity.class);

               startActivity(intent);
        }
    }
}





//}


