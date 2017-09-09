package com.abhi.inshortchallenge.Network;

import com.abhi.inshortchallenge.model.KickStartResponseElement;

/**
 *  Author: Chandra Prakash
 *  Description: Network request to be executed by Volley to fetch json.
 */

public class KickStartRequest  extends VolleyRequest<KickStartResponseElement[]> {

    private static String url = "http://starlord.hackerearth.com/kickstarter";
    public KickStartRequest(){
        super(Method.GET, url, KickStartResponseElement[].class, null, null, null);
    }
}