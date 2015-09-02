package app.com.moviez.anant.moviez;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {


    int count = R.id.action_sort;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

          }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        boolean decide_url;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;
        }
        if (id == R.id.action_sort){

            count = 2;
           // networkCall.urlPopular = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=";
            return true;

        }
        if (id == R.id.action_sortrated){

            count = R.id.action_sortrated;
            //networkCall.urlRated = "http://api.themoviedb.org/3/discover/movie?desc&api_key=";
            return true;

        }

        return super.onOptionsItemSelected(item);
    }






}
