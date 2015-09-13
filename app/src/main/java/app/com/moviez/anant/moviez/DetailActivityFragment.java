package app.com.moviez.anant.moviez;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {


    ImageView poster_view;
    RatingBar user_rating_bar;
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootview =  inflater.inflate(R.layout.fragment_detail, container, false);


        Intent intent = getActivity().getIntent();

        if(intent != null && intent.hasExtra(MainActivityFragment.MOVIE_TITLE)){
            //setting title
            String title = intent.getStringExtra(MainActivityFragment.MOVIE_TITLE);
            //setting Movie Synopsis
            String about  = intent.getStringExtra(MainActivityFragment.MOVIE_ABOUT);
            //Setting release date
            String release_date = intent.getStringExtra(MainActivityFragment.MOVIE_RELEASE_DATE);
            //Setting Rating
            String userratingstring  = intent.getStringExtra(MainActivityFragment.MOVIE_RATING);
            //Settimg Poster
            String poster_url = intent.getStringExtra(MainActivityFragment.MOVIE_POSTER);
            //Parsing the rating in float
            float ratingfloat = Float.parseFloat(userratingstring);


            user_rating_bar = ((RatingBar)rootview.findViewById(R.id.ratingBar));

            TextView abt_movie = ((TextView) rootview.findViewById(R.id.aboutmovie));
            ((TextView) rootview.findViewById(R.id.title_textview)).setText(title);

            user_rating_bar.setRating(ratingfloat);

            poster_view = (ImageView)rootview.findViewById(R.id.movieposter);
            Picasso.with(getActivity()).load(poster_url).into(poster_view);

            abt_movie.setText(about);
            abt_movie.setMovementMethod(new ScrollingMovementMethod());

            ((TextView)rootview.findViewById(R.id.releasedate_display)).setText(release_date);


        }
        return rootview;
    }
}
