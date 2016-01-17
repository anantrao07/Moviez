package app.com.moviez.anant.moviez;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import app.com.moviez.anant.moviez.data.MovieProvider;
import app.com.moviez.anant.moviez.data.MoviesColumns;
import app.com.moviez.anant.moviez.data.ReviewsColumns;
import app.com.moviez.anant.moviez.data.TrailersColumns;

/**
 * Created by anant on 2015-11-08.
 */
public class GetJsonDetailsTask extends AsyncTask<String , Void , Void> {

    public final String LOG_TAG = GetJsonDetailsTask.class.getSimpleName();
    JSONObject jsonObj,jsonreviewsobj;
    JSONArray jsonArray,jsonArrayreview;
    private final Context mContext;
    public final String trailer_results = "results";
    public final String movie_apiId= "id";
    protected final String trailer_id = "id";
    protected final String trailer_key = "key";
    protected final String trailer_name = "name";
    protected final String review_results = "results";
    protected final String review_author = "author";
    protected final String review_content = "content";
    protected ArrayList<TrailerDetail> trailerdetailsarray = new ArrayList<TrailerDetail>();
    protected ArrayList<ReviewsPoJo> reviewsdetailsarray = new ArrayList<ReviewsPoJo>();


    public GetJsonDetailsTask(Context context){

        mContext = context;
    }


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


        HttpURLConnection trailerUrl,reviewUrl ;
        String moviedetailjsnstr , reviewDetailJsonStr;
        BufferedReader moviedetailreader , reviewDetailReader;

        try {
            URL treviews = new URL(params[0]);
            URL review = new URL(params[1]);

            //trailer
            trailerUrl = (HttpURLConnection) treviews.openConnection();
            trailerUrl.setRequestMethod("GET");
            trailerUrl.connect();

            reviewUrl = (HttpURLConnection) review.openConnection();
            reviewUrl.setRequestMethod("GET");
            reviewUrl.connect();

            InputStream isr = trailerUrl.getInputStream();
            StringBuffer sb = new StringBuffer();
            StringBuffer rsb = new StringBuffer();

            InputStream risr = reviewUrl.getInputStream();


            if (isr == null) {
                moviedetailjsnstr = null;

            }

            if(risr == null){
                reviewDetailJsonStr = null;
            }



            moviedetailreader = new BufferedReader(new InputStreamReader(isr));

            reviewDetailReader = new BufferedReader((new InputStreamReader(risr)));

            String lineCheck;
            String reviewLineCheck;

            while ((lineCheck = moviedetailreader.readLine()) != null) {
                sb.append(lineCheck + "\n");

            }
            while ((reviewLineCheck = reviewDetailReader.readLine()) != null) {
                rsb.append(reviewLineCheck + "\n");

            }
            if (sb.length() == 0) {
                moviedetailjsnstr = null;


            }
            else{
                moviedetailjsnstr  = sb.toString();
                Log.v("****traielr list***", moviedetailjsnstr);
            }
            if (rsb.length() == 0) {
                reviewDetailJsonStr = null;
            }
            else{
                reviewDetailJsonStr  = rsb.toString();
                Log.v("****reviews list***" , reviewDetailJsonStr);
            }
            try{
                gettrailers(moviedetailjsnstr);
                getReviews(reviewDetailJsonStr);



            }catch (JSONException je){
                je.printStackTrace();
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException io) {

            io.printStackTrace();
        }

        return null;


    }



    public void insertFavourites(int movieId){

       /* Cursor c = mContext.getContentResolver().query(MovieProvider.Movies.CONTENT_URI,
                new String[]{MoviesColumns.COLUMN_MOVIE_ID,MoviesColumns.COLUMN_FAVORITE},MoviesColumns.COLUMN_FAVORITE + " = ?", // TrailersColumns.COLUMN_TMOVIE_ID + " = ?",
                new String[]{"Y"}, //   new String[]{Integer.toString(movieId)},
                null);

        if(c.moveToFirst()){

            int idx_column_id = c.getColumnIndex(MoviesColumns.COLUMN_MOVIE_ID);
            int movie_id = c.getInt(idx_column_id);
            c.

        }
        else {*/


            ContentValues favoritesCv = new ContentValues();
            favoritesCv.put(MoviesColumns.COLUMN_FAVORITE, "Y");
        /*TrailersColumns.COLUMN_TMOVIE_ID + " = ?", // TrailersColumns.COLUMN_TMOVIE_ID + " = ?",
                new String[]{Integer.toString(movieId)}, //   new String[]{Integer.toString(movieId)},
                null);*/
            mContext.getContentResolver().update(MovieProvider.Movies.CONTENT_URI,
                    favoritesCv,
                    MoviesColumns.COLUMN_MOVIE_ID + " = ? ",
                    new String[]{Integer.toString(movieId)});


        }

    public void resetFavourite(int movieId){

    ContentValues favoritesCv = new ContentValues();
    favoritesCv.put(MoviesColumns.COLUMN_FAVORITE, "N");
        /*TrailersColumns.COLUMN_TMOVIE_ID + " = ?", // TrailersColumns.COLUMN_TMOVIE_ID + " = ?",
                new String[]{Integer.toString(movieId)}, //   new String[]{Integer.toString(movieId)},
                null);*/
    mContext.getContentResolver().update(MovieProvider.Movies.CONTENT_URI,
                                         favoritesCv,
                                         MoviesColumns.COLUMN_MOVIE_ID + " = ? ",
                                         new String[]{Integer.toString(movieId)});




    }


    /*WeatherContract.LocationEntry.CONTENT_URI,
            new String[]{WeatherContract.LocationEntry._ID},
    WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + " = ?",
            new String[]{locationSetting},
            null);*/




    public void gettrailers(String moviejsonfeed) throws JSONException {

        // Log.e("MOVIEJASONSTR", "its empty");

        jsonObj = new JSONObject(moviejsonfeed);

        JSONObject movieIdObjectId = new JSONObject(moviejsonfeed);
        int mid = movieIdObjectId.getInt(movie_apiId);
        //parse the entire json string
        jsonArray = jsonObj.getJSONArray(trailer_results);

        for (int i = 0; i < jsonArray.length(); i++) {
            int id;
            String trailerId;
            String trailerSite;
            String trailerName;
            String trailerKey;

            //  float int_user_rating;

            JSONObject int_jsonObj = jsonArray.getJSONObject(i);
//parse json objects into its values
            trailerId = int_jsonObj.getString(trailer_id);
            trailerKey = int_jsonObj.getString(trailer_key);
            trailerName = int_jsonObj.getString(trailer_name);


            //create a mMovieDetail object that we will populate the json data
            TrailerDetail td = new TrailerDetail();
            td.setKey(trailerKey);
            td.setName(trailerName);
            td.setTrailer_id(trailerId);
            trailerdetailsarray.add(td);

            //Log.i("Parsedmsg", aboutmoiejsonstr);

            //Log.i("allMovies", allMovies.get(i).toString());


        }

        long movieId;
        String s = Integer.toString(mid);
        Cursor movieCursor = mContext.getContentResolver().query(
                MovieProvider.Movies.CONTENT_URI,
                new String[]{MoviesColumns._ID},
                MoviesColumns.COLUMN_MOVIE_ID + " = ?",
                new String[]{s},
                null);
                Log.i(LOG_TAG,"length of movie trailer cursor"+movieCursor.getCount());
            if(movieCursor.moveToFirst()) {
                int movieIdIndex = movieCursor.getColumnIndex(MoviesColumns._ID);
                movieId = movieCursor.getLong(movieIdIndex);

                movieCursor.close();
                ArrayList<ContentProviderOperation> trailerbatchOperations = new ArrayList<>(trailerdetailsarray.size());
                Log.v(LOG_TAG, "Movie arraylist size" + trailerdetailsarray.size());


                for (TrailerDetail movies : trailerdetailsarray) {
                    ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(
                            MovieProvider.MovieTrailers.CONTENT_URI);
                    builder.withValue(TrailersColumns.COLUMN_TMOVIE_ROW_ID, movieId);
                    builder.withValue(TrailersColumns.COLUMN_MOVIE_TRAILER_NAME, movies.getName());
                    builder.withValue(TrailersColumns.COLUMN_MOVIE_TRAILER_KEY, movies.getKey());
                    builder.withValue(TrailersColumns.COLUMN_TMOVIE_ID, mid);


                    trailerbatchOperations.add(builder.build());
                }

                try {
                    mContext.getContentResolver().applyBatch(MovieProvider.CONTENT_AUTHORITY, trailerbatchOperations);
                } catch (RemoteException | OperationApplicationException e) {
                    Log.e(LOG_TAG, "Error applying batch insert", e);
                }
            }

    }


    public void getReviews(String reviejsonfeed) throws JSONException{

        JSONObject movieIdObjectId = new JSONObject(reviejsonfeed);
        int mid = movieIdObjectId.getInt(movie_apiId);
        jsonreviewsobj = new JSONObject(reviejsonfeed);
        jsonArrayreview = jsonreviewsobj.getJSONArray(review_results);

        for(int i = 0 ; i<jsonArrayreview.length();i++){
            String reviewAuthor;
            String reviewContent;

            JSONObject reviewjsonobj = jsonArrayreview.getJSONObject(i);
            reviewAuthor = reviewjsonobj.getString(review_author);


            reviewContent = reviewjsonobj.getString(review_content);
            Log.v("----author---",reviewAuthor);
            Log.v("-----content-----",reviewContent);
            ReviewsPoJo rj = new ReviewsPoJo();
            rj.setReview_author(reviewAuthor);
            rj.setReview_content(reviewContent);
            reviewsdetailsarray.add(rj);


        }



        long movieId;
        String s = Integer.toString(mid);
        Cursor movieCursor = mContext.getContentResolver().query(
                MovieProvider.Movies.CONTENT_URI,
                new String[]{MoviesColumns._ID},
                MoviesColumns.COLUMN_MOVIE_ID + " = ?",
                new String[]{s},
                null);
        if(movieCursor.moveToFirst()) {
            int movieIdIndex = movieCursor.getColumnIndex(MoviesColumns._ID);
            movieId = movieCursor.getLong(movieIdIndex);

            movieCursor.close();
            ArrayList<ContentProviderOperation> reviewbatchOperations = new ArrayList<>(reviewsdetailsarray.size());
            Log.v(LOG_TAG, "Movie arraylist size" + reviewsdetailsarray.size());


            for (ReviewsPoJo movies : reviewsdetailsarray) {
                ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(
                        MovieProvider.MovieReviews.CONTENT_URI);
                builder.withValue(ReviewsColumns.COLUMN_RMOVIE_ID, mid);
                builder.withValue(ReviewsColumns.COLUMN_RMOVIE_ROW_ID, movieId);
                builder.withValue(ReviewsColumns.COLUMN_AUTHOR, movies.getReview_author());
                builder.withValue(ReviewsColumns.COLUMN_CONTENT, movies.getReview_content());

                reviewbatchOperations.add(builder.build());
            }

            try {
                mContext.getContentResolver().applyBatch(MovieProvider.CONTENT_AUTHORITY, reviewbatchOperations);
            } catch (RemoteException | OperationApplicationException e) {
                Log.e(LOG_TAG, "Error applying batch insert", e);
            }
        }





    }
}
