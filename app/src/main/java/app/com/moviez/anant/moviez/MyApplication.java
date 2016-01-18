package app.com.moviez.anant.moviez;


import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by anant on 2015-10-25.
 */
public class MyApplication extends Application {

    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     * Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.
     * If you override this method, be sure to call super.onCreate().
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);





        //enable chrome dev tools
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));

        // Enable command line interface
        Context context = this.getApplicationContext();
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(context)
        );

// Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

// Initialize Stetho with the Initializer
        Stetho.initialize(initializer);


    }
}
