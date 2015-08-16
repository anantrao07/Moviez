package app.com.moviez.anant.moviez;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by anant on 2015-08-14.
 */
public class ImageAdapter extends BaseAdapter {

    public static int image_id[]  = {R.drawable.image1 , R.drawable.image2, R.drawable.image3,R.drawable.image4,R.drawable.image5};

    private Context context;

    public ImageAdapter(Context applicationContext){
        this.context = applicationContext;
    }

    @Override
    public int getCount() {
        return image_id.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView iv;
        if(convertView==null){

            iv = new ImageView(context);
            iv.setLayoutParams(new GridView.LayoutParams(500, 500));
            iv.setScaleType(ImageView.ScaleType.CENTER);
            iv.setPadding(10,10,10,10);

        }

        else{
            iv = (ImageView)convertView;
        }
        
        iv.setImageResource(image_id[position]);
        return iv;
    }
}
