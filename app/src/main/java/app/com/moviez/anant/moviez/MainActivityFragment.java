package app.com.moviez.anant.moviez;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public  ImageAdapter movieDetailAdapter ;

    public ArrayList<MoviesDetail> mvd ;

    NetworkCall nw = new NetworkCall();
    GridView gridView;


    public MainActivityFragment() {
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
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("moviedetails",mvd);

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

        if(savedInstanceState == null || !savedInstanceState.containsKey("moviedetails")){
           new ArrayList<>(nw.allMovies);

        }
        else
        {
           savedInstanceState.getParcelableArrayList("moviesdetails");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main,container,false);


        nw.execute();

       // movieDetailAdapter = new ImageAdapter(getActivity(),nw.allMovies);
        movieDetailAdapter = new ImageAdapter(getActivity(),nw.allMovies);

        gridView = (GridView)rootView.findViewById(R.id.movies_gridview);
        gridView.setAdapter(movieDetailAdapter);

        movieDetailAdapter.notifyDataSetChanged();



        return rootView;


    }
}
