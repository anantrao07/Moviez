package app.com.moviez.anant.moviez;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.util.Log;

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

import app.com.moviez.anant.moviez.data.MovieProvider;
import app.com.moviez.anant.moviez.data.MoviesColumns;

/**
 * Created by anant on 2015-11-03.
 */
public class GetJsonTask extends AsyncTask<String , Void, Void> {

    private MoviesAdapter mmovieAdapter;
    private final Context mContext;

    ArrayList<MoviesDetail> allMovies = new ArrayList<MoviesDetail>();

    public GetJsonTask(Context context) {
        mContext = context;
    }


    public final String LOG_TAG = GetJsonTask.class.getSimpleName();


    private boolean DEBUG = true;


    protected final String jsf_original_title = "original_title";
    protected final String jsf_poster = "poster_path";
    protected final String jsf_plot_synopsis = "overview";
    protected final String jsf_user_rating = "vote_average";
    protected final String jsf_release_date = "release_date";
    protected final String jsf_results = "results";
    protected final String jsf_id = "id";
    protected final String jsf_popularity = "popularity";


    JSONObject jsonObj;
    JSONArray jsonArray;

    // Will contain the raw JSON response as a string.

    HttpURLConnection urlConnection;
    BufferedReader reader;


    String aboutmoiejsonstr = null;

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */

    @Override
    protected Void doInBackground(String... params) {


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


                getMovieDetails(aboutmoiejsonstr);


            } catch (JSONException e) {

                e.printStackTrace();

                Log.e(LOG_TAG, "JSON failed,pls correct me ");
            }

        return null;
    }

    public void getMovieDetails(String jsonfeed) throws JSONException {

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
            float int_popularity;

            JSONObject int_jsonObj = jsonArray.getJSONObject(i);

            ContentValues movieValues = new ContentValues();

//parse json objects into its values
            id = int_jsonObj.getInt(jsf_id);
            int_title = int_jsonObj.getString(jsf_original_title);

            int_poster_path = int_jsonObj.getString(jsf_poster);
            int_overview = int_jsonObj.getString(jsf_plot_synopsis);
            int_release_date = int_jsonObj.getString(jsf_release_date);
            int_user_rating = (float) int_jsonObj.getDouble(jsf_user_rating);
            int_popularity = (float) int_jsonObj.getDouble(jsf_popularity);

            //create a mMovieDetail object that we will populate the json data
            MoviesDetail mvd = new MoviesDetail();
            mvd.setJsf_id(id);
            mvd.setJsf_original_title(int_title);
            mvd.setJsf_poster(int_poster_path);
            mvd.setJsf_plot_synopsis(int_overview);
            mvd.setJsf_release_date(int_release_date);
            mvd.setJsf_user_rating(int_user_rating);
            mvd.setJsf_popularity(int_popularity);

            //add newly create movie detail to our collection
            allMovies.add(mvd);
            //Log.i("Parsedmsg", aboutmoiejsonstr);

            //Log.i("allMovies", allMovies.get(i).toString());
            // add to database


        }

        ArrayList<ContentProviderOperation> batchOperations = new ArrayList<>(allMovies.size());
        Log.v(LOG_TAG,"Movie arraylist size"+allMovies.size());

        for (MoviesDetail movies : allMovies) {
            ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(
                    MovieProvider.Movies.CONTENT_URI);
            builder.withValue(MoviesColumns.COLUMN_MOVIE_ID, movies.getJsf_id());
            builder.withValue(MoviesColumns.COLUMN_ORIGINAL_TITLE, movies.getJsf_original_title());
            builder.withValue(MoviesColumns.COLUMN_RELEASE_DATE, movies.getJsf_release_date());
            builder.withValue(MoviesColumns.COLUMN_BACKDROP_PATH, movies.getJsf_poster());
            builder.withValue(MoviesColumns.COLUMN_OVERVIEW, movies.getJsf_plot_synopsis());
            builder.withValue(MoviesColumns.COLUMN_RELEASE_DATE, movies.getJsf_release_date());
            builder.withValue(MoviesColumns.COLUMN_VOTE_AVERAGE, movies.getJsf_user_rating());
            builder.withValue(MoviesColumns.COLUMN_POPULARITY, movies.getJsf_popularity());
            builder.withValue(MoviesColumns.COLUMN_FAVORITE,"N");


            batchOperations.add(builder.build());
        }


        try {
            mContext.getContentResolver().applyBatch(MovieProvider.CONTENT_AUTHORITY, batchOperations);
        } catch (RemoteException | OperationApplicationException e) {
            Log.e(LOG_TAG, "Error applying batch insert", e);
        }



    }




    }




