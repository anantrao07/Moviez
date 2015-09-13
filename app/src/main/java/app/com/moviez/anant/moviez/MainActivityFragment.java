package app.com.moviez.anant.moviez;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {



    static final String MOVIE_TITLE = "app.com.moviez.anant.moviez.MOVIE_TITLE";
    static final String MOVIE_POSTER = "app.com.moviez.anant.moviez.MOVIE_POSTER";
    static final String MOVIE_ABOUT = "app.com.moviez.anant.moviez.MOVIE_ABOUT";
    static final String MOVIE_RATING = "app.com.moviez.anant.moviez.MOVIE_RATING";
    static final String MOVIE_RELEASE_DATE = "app.com.moviez.anant.moviez.MOVIE_RELEASE_DATE";
    static final String Error_MSG = "Sorry, something went wrong with network connection. :(";


    public ArrayList<MoviesDetail> mvd ;

    ArrayList<MoviesDetail> allMovies = new ArrayList<MoviesDetail>();
    ArrayList<MoviesDetail> sortedList;

    GridView gridView;

    public  ImageAdapter movieDetailAdapter;

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

        if(savedInstanceState==null || !savedInstanceState.containsKey("moviedetails")){
            mvd = new ArrayList<>(allMovies);
        }
        else{
            mvd = savedInstanceState.getParcelableArrayList("moviedetails");
        }
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

            if (isNetworkAvailable() == true) {
            NetworkCall nwc = new NetworkCall();

            String urlPopular = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + NetworkCall.PERSONAL_API_KEY;

            movieDetailAdapter.clear();
            nwc.execute(urlPopular);
            movieDetailAdapter.addAll(allMovies);
            movieDetailAdapter.notifyDataSetChanged();
        }

        else {

                Toast.makeText(getActivity(), Error_MSG, Toast.LENGTH_SHORT).show();
        }

            return true;


        }

        if (id == R.id.action_sortrated) {


            if(allMovies==null){
                Toast.makeText(getActivity(), Error_MSG, Toast.LENGTH_SHORT).show();
            }
            else {
                sortedList = new ArrayList<MoviesDetail>(allMovies);
                Collections.sort(sortedList);
                movieDetailAdapter.clear();
                movieDetailAdapter.addAll(sortedList);

            }
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState (Bundle outState) {

       super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("Moviedetails", mvd);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        NetworkCall nw = new NetworkCall();
        if (isNetworkAvailable() == false||NetworkCall.PERSONAL_API_KEY.length()==0) {
            Toast.makeText(getActivity(), Error_MSG, Toast.LENGTH_SHORT).show();

        } else {

            nw.execute("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + NetworkCall.PERSONAL_API_KEY);
        }
        movieDetailAdapter = new ImageAdapter(getActivity(), allMovies);

        gridView = (GridView) rootView.findViewById(R.id.movies_gridview);
        gridView.setAdapter(movieDetailAdapter);
        movieDetailAdapter.notifyDataSetChanged();


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MoviesDetail msg = movieDetailAdapter.getItem(position);

                String poster = msg.getJsf_poster();
                StringBuffer sb = new StringBuffer();
                sb.append(ImageAdapter.IMAGE_BASE_URL);
                sb.append(ImageAdapter.POSTER_SIZE);
                sb.append(poster);
                String url = sb.toString();
                Intent detailActivity = new Intent(getActivity(), DetailActivity.class);
                float usr = (msg.getJsf_user_rating());
                String user_ratingstring = Float.toString(usr);


                detailActivity.putExtra(MOVIE_TITLE, msg.getJsf_original_title());
                detailActivity.putExtra(MOVIE_ABOUT, msg.getJsf_plot_synopsis());
                detailActivity.putExtra(MOVIE_RELEASE_DATE, msg.getJsf_release_date());
                detailActivity.putExtra(MOVIE_RATING, user_ratingstring);
                detailActivity.putExtra(MOVIE_POSTER, url);
                startActivity(detailActivity);


            }

        });
        return rootView;

    }

    //****network check method*******

    private boolean isNetworkAvailable() {

        ConnectivityManager connMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    //****background async task class method*******

    public  class NetworkCall extends AsyncTask<String , Void, List> {


        public final String LOG_TAG = NetworkCall.class.getSimpleName();

        //Write your API in the below String variable PERSONAL_API_KEY

        public final static String PERSONAL_API_KEY = "";


        protected final String jsf_original_title = "original_title";
        protected final String jsf_poster = "poster_path";
        protected final String jsf_plot_synopsis = "overview";
        protected final String jsf_user_rating = "vote_average";
        protected final String jsf_release_date = "release_date";
        protected final String jsf_results = "results";
        protected final String jsf_id = "id";


        JSONObject jsonObj;
        JSONArray jsonArray;

        // Will contain the raw JSON response as a string.

        HttpURLConnection urlConnection;
        BufferedReader reader;


        String aboutmoiejsonstr = null;


        @Override
        protected ArrayList<MoviesDetail> doInBackground(String... params) {
            // Will contain the raw JSON response as a arraylist.

            try {

                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                int response = urlConnection.getResponseCode();
                // Log.d("LOG_TAG", "The response is: " + response);
                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    aboutmoiejsonstr = null;

                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // for making debug easy :)
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    aboutmoiejsonstr = null;

                } else {
        //       Log.i("StringParsed", aboutmoiejsonstr);

                    aboutmoiejsonstr = buffer.toString();

                    Log.i(LOG_TAG, aboutmoiejsonstr);

                }
            } catch (IOException e) {
                Log.e("LOG_TAG", "Error ", e);

                // If the code didn't successfully get the movie data, there's no point in attempting
                // to parse it.
                aboutmoiejsonstr = null;
            } finally {

//            Log.i(LOG_TAG , aboutmoiejsonstr);
                if (urlConnection != null) {

                    urlConnection.disconnect();
                }
                if (reader != null) {

                    try {

                        reader.close();

                    } catch (final IOException e) {

                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }

            try {


                    return getMovieDetails(aboutmoiejsonstr);


            } catch (JSONException e) {

                e.printStackTrace();

                Log.e(LOG_TAG, "JSON failed,pls correct me ");
            }
            return null;
        }

        public ArrayList<MoviesDetail> getMovieDetails(String jsonfeed) throws JSONException {

            // Log.e("MOVIEJASONSTR", "its empty");

            jsonObj = new JSONObject(jsonfeed);
            //parse the entire json string
            jsonArray = jsonObj.getJSONArray(jsf_results);

            for (int i = 0; i < jsonArray.length(); i++) {
                int id;
                String int_title;
                String int_poster_path;
                String int_overview;
                String int_release_date;
                float int_user_rating;

                JSONObject int_jsonObj = jsonArray.getJSONObject(i);
//parse json objects into its values
                id = int_jsonObj.getInt(jsf_id);
                int_title = int_jsonObj.getString(jsf_original_title);

                int_poster_path = int_jsonObj.getString(jsf_poster);
                int_overview = int_jsonObj.getString(jsf_plot_synopsis);
                int_release_date = int_jsonObj.getString(jsf_release_date);
                int_user_rating = (float) int_jsonObj.getDouble(jsf_user_rating);

                //create a mMovieDetail object that we will populate the json data
                MoviesDetail mvd = new MoviesDetail();
                mvd.setJsf_id(id);
                mvd.setJsf_original_title(int_title);
                mvd.setJsf_poster(int_poster_path);
                mvd.setJsf_plot_synopsis(int_overview);
                mvd.setJsf_release_date(int_release_date);
                mvd.setJsf_user_rating(int_user_rating);

                //add newly create movie detail to our collection
                allMovies.add(mvd);
                //Log.i("Parsedmsg", aboutmoiejsonstr);

                //Log.i("allMovies", allMovies.get(i).toString());

            }

            return allMovies;
        }

        @Override
        protected void onPostExecute(List movies) {

            movieDetailAdapter.notifyDataSetChanged();

        }
    }

}
