package app.com.moviez.anant.moviez;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by anant on 2015-09-30.
 */
public class ReviewAdapter extends ArrayAdapter<ReviewsPoJo> {



    public ReviewAdapter(Activity context, ArrayList<ReviewsPoJo> review) {

        super(context,0, review);

    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ReviewsPoJo review = getItem(position);

        //convertview is  the view returned by the arrayadapter which will be asked by the listview
        if(convertView==null){
           // convertView = LayoutInflater.from(getContext()).inflate(R.layout.reviews_list_item,parent,false);


        }
      //  TextView review_Author = (TextView)convertView.findViewById(R.id.author_textView);

      //  TextView display_review = (TextView)convertView.findViewById(R.id.review_textview);


      //  display_review.setText(review.getReview_content());
       // review_Author.setText(review.getReview_author());
       // Log.v("@@@@@@content#######" ,review.getReview_content());



        return convertView;
    }
}


