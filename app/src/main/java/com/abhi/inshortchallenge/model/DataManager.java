package com.abhi.inshortchallenge.model;

import com.abhi.inshortchallenge.Network.KickStartRequest;
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
    private ArrayList<KickStartResponseElement> mainList;
    public HashMap<String, LinkedList<KickStartResponseElement>> catagoryMap;

    private DataManager(){
        mainList = new ArrayList<KickStartResponseElement>();
        catagoryMap = new HashMap<String, LinkedList<KickStartResponseElement>>();

        EventBusSingleton.instance().register(this);
        KickStartRequest kickStartRequest = new KickStartRequest();
        VolleyNetwork.getInstance(CustomApplication.getmContext()).addToRequestQueue(kickStartRequest);
    }

    public static DataManager instance()
    {
        return instance;
    }

    public synchronized ArrayList<KickStartResponseElement> getMainList(){
        return mainList;
    }

    /*@Subscribe
    public void updateArray(Articles data){
        getNewsList().clear();
        getNewsList().addAll(data.getArticles());

        for(NewsData newsData : data.getArticles()){
            if(catagoryMap.containsKey(newsData.getCategory())){
                catagoryMap.get(newsData.getCategory()).add(newsData);
            }
            else {
                LinkedList<NewsData> list = new LinkedList<NewsData>();
                list.add(newsData);
                catagoryMap.put(newsData.getCategory(), list);
            }
        }

        EventBusSingleton.instance().postEvent(new ViewUpdateEvent());
    }*/



    @Subscribe
    public void updateArray(KickStartResponseElement[] data){

        mainList.clear();
        mainList.addAll(Arrays.asList(data));
    }

}
