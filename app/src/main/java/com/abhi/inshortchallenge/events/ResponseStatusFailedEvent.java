package com.abhi.inshortchallenge.events;

import android.util.Log;

/**
 * Event fired when states code is other than 200
 */
public class ResponseStatusFailedEvent {
    public ResponseStatusFailedEvent(){
        Log.i("Event", "ResponseStatusFailedEvent");
    }
}
