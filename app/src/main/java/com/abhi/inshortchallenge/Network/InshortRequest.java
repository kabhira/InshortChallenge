package com.abhi.inshortchallenge.Network;

import com.abhi.inshortchallenge.model.InshortResponseElement;

/**
 *  Author: Chandra Prakash
 *  Description: Network request to be executed by Volley to fetch json.
 */

public class InshortRequest extends VolleyRequest<InshortResponseElement[]> {

    private static String url = "http://starlord.hackerearth.com/newsjson";
    public InshortRequest(){
        super(Method.GET, url, InshortResponseElement[].class, null, null, null);
    }
}