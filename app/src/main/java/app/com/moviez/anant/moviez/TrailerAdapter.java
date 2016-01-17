package app.com.moviez.anant.moviez;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by anant on 2015-09-27.
 */
public class TrailerAdapter extends ArrayAdapter<TrailerDetail> {



   public  TrailerAdapter(Activity context, ArrayList<TrailerDetail> td){

       super(context,0,td);

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
        TrailerDetail td = getItem(position);

        //convertview is  the view returned by the arrayadapter which will be asked by the listview
        if(convertView == null)
        {

          //  convertView = LayoutInflater.from(getContext()).inflate(R.layout.trailer_list_item,parent,false);

        }
      //  ImageView playbutton = (ImageView)convertView.findViewById(R.id.imageplayButton);
       // TextView trailername  = (TextView)convertView.findViewById(R.id.trailer_name);

        //trailername.setText(td.getName());


       // Log.v("&^%$#@@%#^$name#######", td.getName());

        return null;
    }
}
