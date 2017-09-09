package com.abhi.inshortchallenge.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abhi.inshortchallenge.R;
import com.abhi.inshortchallenge.events.EventBusSingleton;
import com.abhi.inshortchallenge.model.DataManager;
import com.abhi.inshortchallenge.model.InshortResponseElement;
import com.abhi.inshortchallenge.utilities.ListAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *  Author: Chandra Prakash
 *  Description: Fragment to show list
 */

public class InshortListFragment extends Fragment implements View.OnClickListener {

    private List<InshortResponseElement> mDataset;
    private ListAdapter mAdapter;
    private Button favouriteButton, oldNewButton, newOldButton;
    private boolean isFavourite;

    public InshortListFragment() {
        // Required empty public constructor
    }

    public static InshortListFragment newInstance() {
        InshortListFragment fragment = new InshortListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kick_list, container, false);
        favouriteButton = (Button) rootView.findViewById(R.id.list_favourite);
        oldNewButton = (Button) rootView.findViewById(R.id.old_new);
        newOldButton = (Button) rootView.findViewById(R.id.new_old);

        favouriteButton.setOnClickListener(this);
        oldNewButton.setOnClickListener(this);
        newOldButton.setOnClickListener(this);

        mDataset = new ArrayList<>();
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.item_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListAdapter(mDataset, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateArray(null);
        EventBusSingleton.instance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBusSingleton.instance().unregister(this);
    }

    @Subscribe
    public void updateArray(InshortResponseElement[] data){
        refreshList(DataManager.instance().getMainList());
    }

    private void refreshList(ArrayList<InshortResponseElement> responseElements) {
        mDataset.clear();
        mDataset.addAll(responseElements);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.list_favourite:
                if(isFavourite) {
                    isFavourite = false;
                    refreshList(DataManager.instance().getMainList());
                }
                else {
                    isFavourite = true;
                    refreshList(DataManager.instance().getFavouriteList());
                }
                break;
            case R.id.old_new:
                Collections.sort(mDataset, new Comparator<InshortResponseElement>() {
                    @Override
                    public int compare(InshortResponseElement t1, InshortResponseElement t2) {
                        return (int) (t1.getTIMESTAMP() - t2.getTIMESTAMP());
                    }
                });
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.new_old:
                Collections.sort(mDataset, new Comparator<InshortResponseElement>() {
                    @Override
                    public int compare(InshortResponseElement t1, InshortResponseElement t2) {
                        return (int) (t2.getTIMESTAMP() - t1.getTIMESTAMP());
                    }
                });
                mAdapter.notifyDataSetChanged();
                break;
        }
    }
}
