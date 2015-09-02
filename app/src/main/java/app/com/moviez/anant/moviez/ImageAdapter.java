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
    protected String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    protected String POSTER_SIZE = "w185";






    Context context;

    public ImageAdapter(Activity context  , ArrayList<MoviesDetail> mvd){
        super(context , 0 ,mvd);
        this.context = getContext();
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MoviesDetail moviedetail = getItem(position);


        if(convertView==null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_image_detail,parent,false);
            notifyDataSetChanged();

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
        notifyDataSetChanged();




        return  convertView;
    }
}
