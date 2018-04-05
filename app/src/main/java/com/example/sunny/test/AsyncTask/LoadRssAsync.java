package com.example.sunny.test.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;

import com.example.sunny.test.Common.HTTPDataHandler;

/**
 * Created by Sunny on 15/03/2018.
 */

public class LoadRssAsync extends  AsyncTask<String, String, String>{

    private Context context;
    private static final int SLEEPING_TIME = 2000;

    public LoadRssAsync(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result;
        // let the UIThread sleep
        SystemClock.sleep(SLEEPING_TIME);
        // create HTTPDataHandler object
        HTTPDataHandler http = new HTTPDataHandler();
        // start the GetHTTPData method and take the result
        result = http.GetHTTPData(strings[0]);
        return result;
    }

    @Override
    protected void onPostExecute(String s) {

    }
}
