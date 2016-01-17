package app.com.moviez.anant.moviez;

import java.util.ArrayList;

/**
 * Created by anant on 2015-10-08.
 */
public class Wrapper {

    ArrayList<ReviewsPoJo> reviewsContainer ;
    ArrayList<TrailerDetail> trailersContainer;



    public Wrapper( ArrayList<TrailerDetail> t,ArrayList<ReviewsPoJo> r){

        this.reviewsContainer = r;
        this.trailersContainer  = t;

    }

    public Wrapper(ArrayList<ReviewsPoJo> rj){
        this.reviewsContainer = rj;

    }
}
