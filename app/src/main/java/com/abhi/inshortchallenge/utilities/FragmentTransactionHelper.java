package com.abhi.inshortchallenge.utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.abhi.inshortchallenge.R;
import com.abhi.inshortchallenge.view.BaseFragment;

/**
 *  Author: Chandra Prakash
 *  Description: Class responsible to handle fragment transition and animations
 */

public class FragmentTransactionHelper {

    public static void switchFragment(FragmentActivity fragmentActivity, int resID, Fragment fragment) {

        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(resID, fragment).commit();

    }

    public static void addFragment(FragmentActivity fragmentActivity, int resID, Fragment fragment) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(resID, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void addFragmentWithModelObject(FragmentActivity fragmentActivity, int resID, BaseFragment fragment, Object o) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        fragment.setModelObject(o);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit);
        fragmentTransaction.add(resID, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
