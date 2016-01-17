package app.com.moviez.anant.moviez;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import app.com.moviez.anant.moviez.data.MovieProvider;
import app.com.moviez.anant.moviez.data.MoviesColumns;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    private static final int CURSOR_LOADER_ID = 0;
   // private MovieCursorAdapter mCursorAdapter;
    static final String MOVIE_TITLE = "app.com.moviez.anant.moviez.MOVIE_TITLE";
    static final String MOVIE_POSTER = "app.com.moviez.anant.moviez.MOVIE_POSTER";
    static final String MOVIE_ABOUT = "app.com.moviez.anant.moviez.MOVIE_ABOUT";
    static final String MOVIE_RATING = "app.com.moviez.anant.moviez.MOVIE_RATING";
    static final String MOVIE_RELEASE_DATE = "app.com.moviez.anant.moviez.MOVIE_RELEASE_DATE";
    static final String MOVIE_ID = "app.com.moviez.anant.moviez.MOVIE_ID";
    static final String Error_MSG = "Sorry, something went wrong with network connection. :(";


    public ArrayList<MoviesDetail> mvd ;
    private MoviesAdapter mmovieAdapter;
    //ArrayList<MoviesDetail> allMovies = new ArrayList<MoviesDetail>();
    //ArrayList<MoviesDetail> sortedList;
    public  static final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    GridView gridView;

    public interface Callback{

        public void onItemSelected(Uri data  , int pos);
    }

  /*  public static MainActivityFragment newInstance(int index ) {
        MainActivityFragment f = new MainActivityFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("movieindex", index);
      //  args.putInt("movie id" , idx_movie_id);

        Log.e("imhereValue of movieid", String.valueOf(index));

        f.setArguments(args);

        return f;
    }

   /* public int getShownIndex(){

        int i = getArguments().getInt("movieindex");
        Log.e("Value is ", "value is "+ i);
        return  getArguments().getInt("movieindex" , 0);

    }*/
    public MainActivityFragment() {

    }

    /**
     * Called to do initial creation of a fragment.  This is called after
     * { onAttach(Activity)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p/>
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */


        @Override
    public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }
        /**
         * Initialize the contents of the Activity's standard options menu.  You
         * should place your menu items in to <var>menu</var>.  For this method
         * to be called, you must have first called {@link #setHasOptionsMenu}.  See
         * { Activity#onCreateOptionsMenu(Menu) Activity.onCreateOptionsMenu}
         * for more information.
         *
         * @param menu     The options menu in which you place your items.
         * @param inflater
         * @see #setHasOptionsMenu
         * @see #onPrepareOptionsMenu
         * @see #onOptionsItemSelected
         */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflating the Mainactivity fragment with its own menu items , via movie_fragment_menu.xml
        inflater.inflate(R.menu.movie_fragment_menu, menu);
    }
    /**
     * Called to ask the fragment to save its current dynamic state, so it
     * can later be reconstructed in a new instance of its process is
     * restarted.  If a new instance of the fragment later needs to be
     * created, the data you place in the Bundle here will be available
     * in the Bundle given to {@link #onCreate(Bundle)},
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}, and
     * {@link #onActivityCreated(Bundle)}.
     * <p/>
     * <p>This corresponds to {ActivityonSaveInstanceState(Bundle)
     * Activity.onSaveInstanceState(Bundle)} and most of the discussion there
     * applies here as well.  Note however: <em>this method may be called
     * at any time before {@link #onDestroy()}</em>.  There are many situations
     * where a fragment may be mostly torn down (such as when placed on the
     * back stack with no UI showing), but its state will not be saved until
     * its owning activity actually needs to save its state.
     *
     * @param outState Bundle in which to place your saved state.
     */

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p/>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       int id = item.getItemId();


        if (id == R.id.action_sort) {

            Cursor sortC = getActivity().getContentResolver().query(MovieProvider.Movies.CONTENT_URI,//URI
                    null,//project
                    null,//selection
                    null, //selection argument
                    MoviesColumns.COLUMN_VOTE_AVERAGE+" DESC");//sort order

            //mmovieAdapter.getCursor().close();
            mmovieAdapter.changeCursor(sortC);
            mmovieAdapter.notifyDataSetChanged();
        }


        if (id == R.id.action_sortrated) {

            Cursor ratedC = getActivity().getContentResolver().query(MovieProvider.Movies.CONTENT_URI,//URI
                    null,//project
                    null,//selection
                    null, //selection argument
                    MoviesColumns.COLUMN_POPULARITY+" DESC");//sort order


            Log.i(LOG_TAG, "cursor count: " + ratedC.getCount());
            if(ratedC==null){
                Toast.makeText(getActivity(), Error_MSG, Toast.LENGTH_SHORT).show();
            }
            else {
              //  sortedList = new ArrayList<MoviesDetail>(allMovies);
              //  Collections.sort(sortedList);
                //allMovies.
               // movieDetailAdapter.clear();  onLoadFinished( loader, c);
               // mmovieAdapter.getCursor().close();
                mmovieAdapter.changeCursor(ratedC);
                mmovieAdapter.notifyDataSetChanged();
               // mmovieAdapter.swapCursor(c);


            }
           // c.close();
           // return true;

        }
        if (id == R.id.action_favorites){

            Cursor fav_list = getActivity().getContentResolver().query(MovieProvider.Movies.CONTENT_URI,
                   null,MoviesColumns.COLUMN_FAVORITE + " = ? ",
                    new String[]{"Y"},//new String[]{Integer.toString(movieId)}, //   new String[]{Integer.toString(movieId)},
                    null);

            int count = fav_list.getCount();
            if(fav_list == null || fav_list.getCount()==0){
                Toast.makeText(getActivity(),"Sorry no favourites made yet",Toast.LENGTH_SHORT).show();
            }else {

                //mmovieAdapter.getCursor().close();
                mmovieAdapter.changeCursor(fav_list);


                mmovieAdapter.notifyDataSetChanged();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    fav_list.getNotificationUri();

                if(fav_list.getCount()!=count) {

                    mmovieAdapter.notifyDataSetChanged();
                }
                }

            }

        }
            return super.onOptionsItemSelected(item);
        }


    @Override
    public void onSaveInstanceState (Bundle outState) {

        super.onSaveInstanceState(outState);
        //outState.putParcelableArrayList("Moviedetails", mvd);

    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to {@link //Activity#onResume() Activity.onResume} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onResume() {


        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      /*  Cursor cur = getActivity().getContentResolver().query(MovieProvider.Movies.CONTENT_URI,
                null, null, null, null);
        Log.v("cursor value ","cursor value " + cur.getCount());
*/

        // The CursorAdapter will take data from our cursor and populate the ListView
        // However, we cannot use FLAG_AUTO_REQUERY since it is deprecated, so we will end
        // up with an empty list the first time we run.
        mmovieAdapter = new MoviesAdapter(getActivity(), null , 0);


        View rootView = inflater.inflate(R.layout.fragment_main, container, false);



       /* GetJsonTask gt = new GetJsonTask(getActivity());
        Cursor c = getActivity().getContentResolver().query(MovieProvider.Movies.CONTENT_URI,
                null, null, null, null);
        Log.i(LOG_TAG, "cursor count: " + c.getCount());*/

       // getShownIndex();
            gridView = (GridView) rootView.findViewById(R.id.movies_gridview);

            gridView.setAdapter(mmovieAdapter);

       /* Cursor c = getActivity().getContentResolver().query(MovieProvider.Movies.CONTENT_URI,
                new String[]{MoviesColumns.COLUMN_MOVIE_ID},
                null,
                null,//new String[]{Integer.toString(movieId)}, //   new String[]{Integer.toString(movieId)},
                null);
*/

      //  c.close();

       // Toast.makeText(this, "value of movie id is "+ f.ge , )
       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               //  MoviesDetail msg = movieDetailAdapter.getItem(position);

               getActivity().getContentResolver().query(MovieProvider.Movies.CONTENT_URI,
                       new String[]{MoviesColumns.COLUMN_MOVIE_ID},
                       null,
                       null,//new String[]{Integer.toString(movieId)}, //   new String[]{Integer.toString(movieId)},
                       null);
              Cursor c = (Cursor) adapterView.getItemAtPosition(position);

               ((Callback) getActivity()).onItemSelected(MovieProvider.Movies.CONTENT_URI , adapterView.getSelectedItemPosition());


               //( (Callback)getActivity()).onItemSelected((Cursor)adapterView.getItemAtPosition(position));



           /*    if (c != null) {

                   int movie_id;
                   //  int idx_movie_id = c.getColumnIndex(MoviesColumns.COLUMN_MOVIE_ID);
                   ((Callback) getActivity()).onItemSelected(MovieProvider.Movies.CONTENT_URI);

               }*/


             /*  Cursor c = getActivity().getContentResolver().query(MovieProvider.Movies.CONTENT_URI,
                     null,
                   null,//MoviesColumns.COLUMN_MOVIE_ID + " = ? ",
                 null,//new String[]{Integer.toString()},//new String[]{Integer.toString(movieId)}, //   new String[]{Integer.toString(movieId)},
               null);*/
             //  mmovieAdapter.getItem(position);
             //  Cursor c = mmovieAdapter.getCursor();

              //    c.moveToPosition(position);

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


               String poster = c.getString(idx_poster);
               Log.i(LOG_TAG, "value of movie id fetched " + poster);
               StringBuffer sb = new StringBuffer();
               sb.append(ImageAdapter.IMAGE_BASE_URL);
               sb.append(ImageAdapter.POSTER_SIZE);
               sb.append(poster);
               String url = sb.toString();
               Intent detailActivity = new Intent(getActivity(), DetailActivity.class);
               // ((Callback) getActivity()).onItemSelected(WeatherContract.WeatherEntry.buildWeatherLocationWithDate(
               //        locationSetting, cursor.getLong(COL_WEATHER_DATE)
               //));

               int movieIdInt = c.getInt(idx_movie_id);
               Log.i(LOG_TAG, "value of movie id fetched " + movieIdInt);
               String movie_dString = Integer.toString(movieIdInt);
               Log.i(LOG_TAG, "value of movie id fetched " + movie_dString);

               String user_ratingstring = Float.toString(c.getFloat(idx_rating));

               Bundle bundle = new Bundle();
               bundle.putString(MOVIE_TITLE, c.getString(idx_title));
               bundle.putString(MOVIE_ABOUT, c.getString(idx_about));
               bundle.putString(MOVIE_RELEASE_DATE, c.getString(idx_release_date));
               bundle.putString(MOVIE_RATING, user_ratingstring);
               bundle.putString(MOVIE_POSTER, url);
               bundle.putString(MOVIE_ID, movie_dString);

               detailActivity.putExtras(bundle);
                detailActivity.putExtra(MOVIE_ABOUT, c.getString(idx_about));
                detailActivity.putExtra(MOVIE_RELEASE_DATE, c.getString(idx_release_date));
                detailActivity.putExtra(MOVIE_RATING, user_ratingstring);
                detailActivity.putExtra(MOVIE_POSTER, url);
                detailActivity.putExtra(MOVIE_ID, movie_dString);
               startActivity(detailActivity);

               //c.close();
           }


       });
            return rootView;

        }


    /**
     * Called when the fragment's activity has been created and this
     * fragment's view hierarchy instantiated.  It can be used to do final
     * initialization once these pieces are in place, such as retrieving
     * views or restoring state.  It is also useful for fragments that use
     * {@link #setRetainInstance(boolean)} to retain their instance,
     * as this callback tells the fragment when it is fully associated with
     * the new activity instance.  This is called after {@link #onCreateView}
     * and before {@link #onViewStateRestored(Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        Cursor c = getActivity().getContentResolver().query(MovieProvider.Movies.CONTENT_URI,
                null, null, null, null);
        Log.i(LOG_TAG, "cursor count: " + c.getCount());
        if (c == null || c.getCount() == 0){
            updateMovie();
        }
        else if (isNetworkAvailable() == false || getString(R.string.api_key).length() == 0) {
            Toast.makeText(getActivity(), Error_MSG, Toast.LENGTH_SHORT).show();

        }

        getLoaderManager().initLoader(CURSOR_LOADER_ID,null,this);

        super.onActivityCreated(savedInstanceState);
        c.close();
    }

    private void updateMovie() {
        GetJsonTask weatherTask = new GetJsonTask(getActivity());


                weatherTask.execute("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + getString(R.string.api_key));

    }

    @Override
    public void onStart() {
        super.onStart();
        //updateMovie();
    }
    //****network check method*******

    private boolean isNetworkAvailable() {

        ConnectivityManager connMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {


        return new CursorLoader(getActivity(), MovieProvider.Movies.CONTENT_URI,
                null,
                null,
                null,
                null);

    }

    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is <em>not</em> allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See {@link //FragmentManager#beginTransaction()
     * FragmentManager.openTransaction()} for further discussion on this.
     * <p/>
     * <p>This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     * <p/>
     * <ul>
     * <li> <p>The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a {@link Cursor}
     * and you place it in a {@link CursorAdapter}, use
     * the {@link CursorAdapter#CursorAdapter(Context,
     * Cursor, int)} constructor <em>without</em> passing
     * in either {@link CursorAdapter#FLAG_AUTO_REQUERY}
     * or {@link CursorAdapter#FLAG_REGISTER_CONTENT_OBSERVER}
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     * <li> The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a {@link Cursor} from a {@link android.content.CursorLoader},
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * {@link CursorAdapter}, you should use the
     * {@link CursorAdapter#swapCursor(Cursor)}
     * method so that the old Cursor is not closed.
     * </ul>
     *
     * @param loader The Loader that has finished.
     * @param data   The data generated by the Loader.
     */
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mmovieAdapter.swapCursor(data);
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mmovieAdapter.swapCursor(null);
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */

}
