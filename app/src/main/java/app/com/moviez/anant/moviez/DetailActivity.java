package app.com.moviez.anant.moviez;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class DetailActivity extends AppCompatActivity {

    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);


        setContentView(R.layout.activity_detail);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        if (savedInstanceState == null) {


            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
             //Bundle arguments = new Bundle();
           // arguments = getIntent().getExtras();


          //  DetailActivityFragment fragment = new DetailActivityFragment();
            //fragment.setArguments(arguments);

          //  getSupportFragmentManager().beginTransaction().add(R.id.movie_detail_panel, fragment, DETAILFRAGMENT_TAG)
            //       .commit();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         //Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_detail, menu);



        return true;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public Intent getParentActivityIntent(){
        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
