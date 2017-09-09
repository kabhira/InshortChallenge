package com.abhi.inshortchallenge.model;

import com.abhi.inshortchallenge.Network.InshortRequest;
import com.abhi.inshortchallenge.Network.VolleyNetwork;
import com.abhi.inshortchallenge.events.EventBusSingleton;
import com.abhi.inshortchallenge.utilities.CustomApplication;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *  Author: Chandra Prakash
 *  Description: Singleton File used for caching the data.
 */
public class DataManager {

    private static  DataManager instance = new DataManager();
    private ArrayList<InshortResponseElement> mainList;
    public HashMap<String, LinkedList<InshortResponseElement>> catagoryMap;
    private ArrayList<InshortResponseElement> favouriteList;

    private DataManager(){
        mainList = new ArrayList<InshortResponseElement>();
        catagoryMap = new HashMap<String, LinkedList<InshortResponseElement>>();
        favouriteList = new ArrayList<>();

        EventBusSingleton.instance().register(this);
        InshortRequest inshortRequest = new InshortRequest();
        VolleyNetwork.getInstance(CustomApplication.getmContext()).addToRequestQueue(inshortRequest);
    }

    public static DataManager instance()
    {
        return instance;
    }

    public synchronized ArrayList<InshortResponseElement> getMainList(){
        return mainList;
    }



    @Subscribe
    public void updateArray(InshortResponseElement[] data){

        mainList.clear();
        mainList.addAll(Arrays.asList(data));
    }

    public void addToFavouriteList(InshortResponseElement inshortResponseElement) {
        if(!favouriteList.contains(inshortResponseElement))
            favouriteList.add(inshortResponseElement);
    }

    public ArrayList<InshortResponseElement> getFavouriteList() {
        return favouriteList;
    }

}
