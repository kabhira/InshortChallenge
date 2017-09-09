package com.abhi.inshortchallenge.view;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.abhi.inshortchallenge.R;
import com.abhi.inshortchallenge.model.DataManager;
import com.abhi.inshortchallenge.model.InshortResponseElement;

/**
 *  Author: Chandra Prakash
 *  Description: Fragment to show detail page.
 */

public class InshortDetailFragment extends BaseFragment implements View.OnClickListener {

    public WebView articleWebview;
    private ProgressBar progressBar;

    public InshortDetailFragment() {
        // Required empty public constructor
    }

    public static InshortDetailFragment newInstance() {
        InshortDetailFragment fragment = new InshortDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        articleWebview = (WebView) rootView.findViewById(R.id.article_webview);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        rootView.findViewById(R.id.add_to_favourite).setOnClickListener(this);

        if(getModelObject() != null) {
            InshortResponseElement element = (InshortResponseElement) getModelObject();
            articleWebview.loadUrl(element.getURL());
            articleWebview.setWebViewClient(webViewClient);
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity)getActivity() ).setActionBarHome(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_to_favourite:
                if(getModelObject() != null) {
                    InshortResponseElement element = (InshortResponseElement) getModelObject();
                    DataManager.instance().addToFavouriteList(element);
                }
                break;
        }
    }

    private WebViewClient webViewClient = new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.INVISIBLE);
        }
    };

}
