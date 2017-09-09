package com.abhi.inshortchallenge.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhi.inshortchallenge.Network.VolleyNetwork;
import com.abhi.inshortchallenge.R;
import com.abhi.inshortchallenge.model.KickStartResponseElement;
import com.abhi.inshortchallenge.utilities.CustomApplication;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 *  Author: Chandra Prakash
 *  Description: Fragment to show kickstarter detail.
 */

public class KickDetailFragment extends BaseFragment {

    public TextView mTextViewTitle, mTextViewBackers, mTextViewPleadge, mTextViewnoDays, mTextViewDescription;
    public NetworkImageView mImageView;


    public KickDetailFragment() {
        // Required empty public constructor
    }

    public static KickDetailFragment newInstance() {
        KickDetailFragment fragment = new KickDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        mTextViewTitle = (TextView) rootView.findViewById(R.id.cell_title);
        mTextViewBackers = (TextView) rootView.findViewById(R.id.cell_backers);
        mTextViewPleadge = (TextView) rootView.findViewById(R.id.cell_pleadge);
        mTextViewnoDays = (TextView) rootView.findViewById(R.id.cell_no_days);
        mTextViewDescription = (TextView) rootView.findViewById(R.id.cell_description);
        mImageView = (NetworkImageView) rootView.findViewById(R.id.detail_imageView);

        if(getModelObject() != null) {
            KickStartResponseElement element = (KickStartResponseElement) getModelObject();

            mTextViewTitle.setText(element.getTitle());
            mTextViewBackers.setText("Pleadge :"+element.getCurrency()+" "+element.getAmtpledged());
            mTextViewPleadge.setText("Backers :"+element.getNumbackers());
            mTextViewnoDays.setText("Funded :"+element.getPercentagefunded()+"%");
            mTextViewDescription.setText(element.getBlurb());

            mImageView.setDefaultImageResId(R.drawable.kick);
            if(element.getImageURL() == null) {
                Thread thread = new Thread(new Task(element));
                thread.start();
            }
            else {
                ImageLoader imageLoader = VolleyNetwork.getInstance(CustomApplication.getmContext()).getImageLoader();
                mImageView.setImageUrl(element.getImageURL(), imageLoader);
            }

        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity)getActivity() ).setActionBarHome(true);
    }

    /**
     * Thread to fetch Image Url on the HTML page using Jsoup library
     */
    class Task implements Runnable{
        private KickStartResponseElement element;

        public Task(KickStartResponseElement element){
            this.element = element;
        }

        @Override
        public void run() {
            Document doc;
            try {

                String urL = "https://www.kickstarter.com"+element.getUrl();
                doc = Jsoup.connect(urL).get();

                Element imageElement = doc.select("img.js-feature-image").first();
                String absoluteUrl = imageElement.absUrl("src");
                element.setImageURL(absoluteUrl);

                KickDetailFragment.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageLoader imageLoader = VolleyNetwork.getInstance(CustomApplication.getmContext()).getImageLoader();
                        mImageView.setImageUrl(element.getImageURL(), imageLoader);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
