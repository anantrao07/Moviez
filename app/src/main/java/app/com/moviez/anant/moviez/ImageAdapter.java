package app.com.moviez.anant.moviez;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by anant on 2015-08-14.
 */
public class ImageAdapter extends ArrayAdapter<MoviesDetail> {

    private static final String LOG_TAG = MoviesDetail.class.getSimpleName();
    protected static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    protected static final String POSTER_SIZE = "w342";//"w342";//"w185";


    Context context;

    public ImageAdapter(Activity context  , ArrayList<MoviesDetail> mvd){
        //passing the context and datalist into the internal implementation of the adapter.constructor is called the
        //is done with the below line in super class.
        super(context , 0 ,mvd);

        this.context = getContext();
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {




        MoviesDetail moviedetail = getItem(position);

    //convertview is the view returned by arrayadapter which will be asked by the grid view,to inflate the ui
       if(convertView==null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_image_detail,parent,false);


       }
        ImageView iv = (ImageView)convertView.findViewById(R.id.detail_imageview);
        TextView tv = (TextView)convertView.findViewById(R.id.image_textview);

        String poster = moviedetail.getJsf_poster();
        StringBuffer sb = new StringBuffer();
        sb.append(IMAGE_BASE_URL);
        sb.append(POSTER_SIZE);
        sb.append(poster);
        String url = sb.toString();
        Picasso.with(context).load(url).into(iv);
        tv.setText(moviedetail.getJsf_original_title());


        return  convertView;
    }
}
