package com.abhi.inshortchallenge.view;

import android.support.v4.app.Fragment;

/**
 *  Author: Chandra Prakash
 *  Description: Base class for all fragment to store data object.
 */

public class BaseFragment extends Fragment {

    private Object modelObject;

    public Object getModelObject() {
        return modelObject;
    }

    public void setModelObject(Object modelObject) {
        this.modelObject = modelObject;
    }
}
