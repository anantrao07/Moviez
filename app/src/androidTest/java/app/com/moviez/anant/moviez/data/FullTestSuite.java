package app.com.moviez.anant.moviez.data;

import android.test.AndroidTestCase;
import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;

/**
 * Created by anant on 2015-10-21.
 */
public class FullTestSuite extends AndroidTestCase{


    public static Test suite(){

        return  new TestSuiteBuilder(FullTestSuite.class)
                .includeAllPackagesUnderHere().build();

    }

    public FullTestSuite(){
        super();
    }

}
