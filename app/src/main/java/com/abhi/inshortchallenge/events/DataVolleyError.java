package com.abhi.inshortchallenge.events;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * interface for getting vlley library error.
 */
public class DataVolleyError implements Response.ErrorListener{

    private static String TAG = "DataVolleyError";

    @Override
    public void onErrorResponse(VolleyError error) {
        //Log.e(TAG, error.toString()+"");
        EventBusSingleton.instance().postEvent(error);
    }
}
