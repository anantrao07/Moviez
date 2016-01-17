package app.com.moviez.anant.moviez;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ShareActionProvider;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.com.moviez.anant.moviez.data.MovieProvider;
import app.com.moviez.anant.moviez.data.MoviesColumns;
import app.com.moviez.anant.moviez.data.ReviewCursorAdapter;
import app.com.moviez.anant.moviez.data.ReviewsColumns;
import app.com.moviez.anant.moviez.data.TrailersColumns;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>  {

    private static final int T_CURSOR_LOADER_ID = 1;
    private static final int R_CURSOR_LOADER_ID = 2;

    public final String trailer_results = "results";
    public final String movie_apiId= "id";
    protected final String trailer_id = "id";
    protected final String trailer_key = "key";
    protected final String trailer_name = "name";
    protected final String review_results = "results";
    protected final String review_author = "author";
    protected final String review_content = "content";
    static final String TRAILERKEY = "app.com.moviez.anant.moviez";


    private Uri mUri;
    static final String DETAIL_URI = "URI";

    protected int movieId;
    ImageView poster_view;
    RatingBar user_rating_bar;
    TextView abt_movie;
    ListView trailerlistview;
    ListView reviewslistview;
    Switch favorite_btn;

    TrailerCursorAdapter trailerAdapter;
    ReviewCursorAdapter reviewAdapter;
    DetailsCursorAdapter detailAdapter;
    Intent intent;

    public static final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    Intent mShareIntent;
    ShareActionProvider mShareActionProvider;
    public DetailActivityFragment() {
    }

    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link //#onAttach(Activity)} and before
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

        setHasOptionsMenu(true);
        mShareIntent = new Intent();
        mShareIntent.setAction(Intent.ACTION_SEND);
        mShareIntent.setType("text/plain");
        mShareIntent.putExtra(Intent.EXTRA_TEXT, "From me to you, this text is new.");

    }


    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.  For this method
     * to be called, you must have first called {@link #setHasOptionsMenu}.  See
     * {@link //Activity#onCreateOptionsMenu(Menu) Activity.onCreateOptionsMenu}
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
        inflater.inflate(R.menu.detail_fragment_menu, menu);

        MenuItem shareItem = menu.findItem(R.id.menu_item_share);


        // Connect the dots: give the ShareActionProvider its Share Intent
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(mShareIntent);
        }

        // Return true so Android will know we want to display the menu

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootview = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle arguments;

        if(getArguments()!=null) {

             arguments = getArguments();

        }
        else {
            intent = getActivity().getIntent();
             arguments = intent.getExtras();
        }

           // mUri = arguments.getParcelable(DETAIL_URI);

       // }

        if(arguments!=null){
        favorite_btn = (Switch) rootview.findViewById(R.id.fav_btn);

       // intent = getActivity().getIntent();
       // if (intent != null && intent.hasExtra(MainActivityFragment.MOVIE_TITLE)) {
            // setting title
            String title = arguments.getString(MainActivityFragment.MOVIE_TITLE);
            // setting Movie Synopsis
//            Log.i("Movie title name ", title);
            String about = arguments.getString(MainActivityFragment.MOVIE_ABOUT);//MainActivityFragment.MOVIE_ABOUT);
            //Setting release date
            String release_date = arguments.getString(MainActivityFragment.MOVIE_RELEASE_DATE);
            //Setting Rating
            String userratingstring = arguments.getString(MainActivityFragment.MOVIE_RATING);
            //Setting Poster
            String poster_url = arguments.getString(MainActivityFragment.MOVIE_POSTER);

            //Parsing the rating in float
            float ratingfloat = Float.parseFloat(userratingstring);

            String movie_idString = arguments.getString((MainActivityFragment.MOVIE_ID));
            movieId = Integer.parseInt(movie_idString);

            user_rating_bar = ((RatingBar) rootview.findViewById(R.id.ratingBar));

            abt_movie = ((TextView) rootview.findViewById(R.id.aboutmovie));


            ((TextView) rootview.findViewById(R.id.title_textview)).setText(title);


            reviewslistview = (ListView) rootview.findViewById((R.id.review_listview));

            trailerlistview = (ListView) rootview.findViewById(R.id.trailer_listView);
            user_rating_bar.setRating(ratingfloat);

            poster_view = (ImageView) rootview.findViewById(R.id.movieposter);
            Picasso.with(getActivity()).load(poster_url).into(poster_view);
            abt_movie.setText(about);

            ((TextView) rootview.findViewById(R.id.releasedate_display)).setText("Release Date " + release_date);


            Log.v("****Movie id**** ", Integer.toString(movieId));

            trailerAdapter = new TrailerCursorAdapter(getActivity(), null, 0);
            reviewAdapter = new ReviewCursorAdapter(getActivity(), null, 0);
            //detailAdapter = new DetailsCursorAdapter(getActivity(), null, 0);



            isFavBtnChkd();
            favorite_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                /**
                 * Called when the checked state of a compound button has changed.
                 *
                 * @param buttonView The compound button view whose state has changed.
                 * @param isChecked  The new checked state of buttonView.
                 */
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    GetJsonDetailsTask gjt = new GetJsonDetailsTask(getActivity());

                    if (isChecked == true) {


                        gjt.insertFavourites(movieId);
                        //favorite_btn.isChecked();
                        // favorite_btn.setChecked(true);

                        Log.v(LOG_TAG, "Value of id " + movieId);
                    } else {

                        gjt.resetFavourite(movieId);
                    }

                }

            });


            trailerlistview.setAdapter(trailerAdapter);
            reviewslistview.setAdapter(reviewAdapter);



            trailerlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Cursor ct = getActivity().getContentResolver().query(MovieProvider.MovieTrailers.CONTENT_URI,
                            null, TrailersColumns.COLUMN_TMOVIE_ID + " = ?", // TrailersColumns.COLUMN_TMOVIE_ID + " = ?",
                            new String[]{Integer.toString(movieId)}, //   new String[]{Integer.toString(movieId)},
                            null);

                    Cursor cs = (Cursor) parent.getItemAtPosition(position);

                    ct.moveToPosition(position);


                    int idx_keycolumnindex = cs.getColumnIndex(TrailersColumns.COLUMN_MOVIE_TRAILER_KEY);
                    String urilink = "https://www.youtube.com/watch?v=" + cs.getString(idx_keycolumnindex);
                    Log.v("Movie key", urilink);
                    cs.close();
                    Intent trailerintent = new Intent(Intent.ACTION_VIEW);
                    trailerintent.setData(Uri.parse(urilink));
                    Log.v("Movie key", urilink);
                    startActivity(trailerintent);

                }
            });


            //to set the scroll view of the trailer list view
            trailerListScroll();

            //to set the scroll view of the reviews list view
            reviewListScroll();
            Cursor fav_checked = getActivity().getContentResolver().query(MovieProvider.Movies.CONTENT_URI,
                    null, MoviesColumns.COLUMN_MOVIE_ID + " = ?",
                    new String[]{Integer.toString(movieId)},//new String[]{Integer.toString(movieId)}, //   new String[]{Integer.toString(movieId)},
                    null);
            Log.v(LOG_TAG, Integer.toString(movieId));
            if (fav_checked.moveToFirst()) {
                int idx_fav_state = fav_checked.getColumnIndex(MoviesColumns.COLUMN_FAVORITE);
                String Checked = fav_checked.getString(idx_fav_state);
                if (Checked.equals("Y")) {
                    favorite_btn.setChecked(true);
                }
            }
        }
        //insertDetails();
            return rootview;
        }




    public void isFavBtnChkd(){

        Cursor fav_checked = getActivity().getContentResolver().query(MovieProvider.Movies.CONTENT_URI,
                new String[]{MoviesColumns.COLUMN_MOVIE_ID, MoviesColumns.COLUMN_FAVORITE}, MoviesColumns.COLUMN_MOVIE_ID + " = ?",
                new String[]{"Y"},//new String[]{Integer.toString(movieId)}, //   new String[]{Integer.toString(movieId)},
                null);
        Log.v(LOG_TAG,Integer.toString(movieId));
        if(fav_checked.moveToFirst()){
            int idx_fav_state = fav_checked.getColumnIndex(MoviesColumns.COLUMN_FAVORITE);
            String Checked = fav_checked.getString(idx_fav_state);
            if(Checked.equals("Y")){
                favorite_btn.setChecked(true);
            }

            fav_checked.close();
        }
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
        Cursor c = getActivity().getContentResolver().query(MovieProvider.MovieTrailers.CONTENT_URI,
                null,TrailersColumns.COLUMN_TMOVIE_ID + " = ?", // TrailersColumns.COLUMN_TMOVIE_ID + " = ?",
                new String[]{Integer.toString(movieId)}, //   new String[]{Integer.toString(movieId)},
                null);
        Cursor rc = getActivity().getContentResolver().query(MovieProvider.MovieReviews.CONTENT_URI,
                null,//     new String[]{ReviewsColumns.COLUMN_AUTHOR,ReviewsColumns.COLUMN_CONTENT,ReviewsColumns.COLUMN_RMOVIE_ID},
                ReviewsColumns.COLUMN_RMOVIE_ID + " = ?",
                new String[]{Integer.toString(movieId)}, //   new String[]{Integer.toString(movieId)},
                null);

        if(c .getCount() == 0 | rc.getCount()==0 ){

            insertDetails();
        }

        c.close();
        rc.close();




        getLoaderManager().initLoader(T_CURSOR_LOADER_ID, null, this);
        getLoaderManager().initLoader(R_CURSOR_LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to {@link //Activity#onStart() Activity.onStart} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStart() {
        super.onStart();
    }
     public void trailerListScroll() {
        trailerlistview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;


            }
        });
    }


    public void reviewListScroll(){
        reviewslistview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;


            }
        });
    }

    public void insertDetails(){
        GetJsonDetailsTask gt = new GetJsonDetailsTask(getActivity());
     /*  Cursor idCursor = getActivity().getContentResolver().query( MovieProvider.Movies.CONTENT_URI,
                new String[]{MoviesColumns.COLUMN_MOVIE_ID},
                null,
                null,
                null);
        for (int i = 0 ; i<= idCursor.getCount();i++) {

            idCursor.moveToPosition(i);
            int idx_movie_id = idCursor.getColumnIndex(MoviesColumns.COLUMN_MOVIE_ID);
            int movie_id = idCursor.getInt(idx_movie_id);*/
            gt.execute("http://api.themoviedb.org/3/movie/" +  movieId + "/videos?api_key=" + getString(R.string.api_key),
                    "http://api.themoviedb.org/3/movie/" + movieId + "/reviews?api_key=" + getString(R.string.api_key));

       // }
       // idCursor.close();
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

       /* Intent intent = getActivity().getIntent();
        if (intent == null || intent.getData() == null) {
            return null;
        }*/


       if (id == 1) {

            return new CursorLoader(getActivity(), MovieProvider.MovieTrailers.CONTENT_URI,
                    null,//new String[]{TrailersColumns.COLUMN_MOVIE_TRAILER_NAME},
                    TrailersColumns.COLUMN_TMOVIE_ID + " = ?", // TrailersColumns.COLUMN_TMOVIE_ID + " = ?",
                    new String[]{Integer.toString(movieId)}, //   new String[]{Integer.toString(movieId)},
                    null);

        } else if (id == 2) {
            return new CursorLoader(getActivity(),MovieProvider.MovieReviews.CONTENT_URI,
               null,//     new String[]{ReviewsColumns.COLUMN_AUTHOR,ReviewsColumns.COLUMN_CONTENT,ReviewsColumns.COLUMN_RMOVIE_ID},
                     ReviewsColumns.COLUMN_RMOVIE_ID + " = ?",
                    new String[]{Integer.toString(movieId)}, //   new String[]{Integer.toString(movieId)},
                    null);
        }

        return  null;
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


       if(loader.getId()==1){
//           trailerAdapter.swapCursor(data);
       }

        else if(loader.getId()==2) {
//           reviewAdapter.swapCursor(data);
       }
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

        if (loader.getId() == 1){
       //     trailerAdapter.swapCursor(null);
    }

    else if(loader.getId()==2){
//            reviewAdapter.swapCursor(null);
        }
    }

}

