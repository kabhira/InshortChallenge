package com.abhi.inshortchallenge.utilities;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abhi.inshortchallenge.Network.VolleyNetwork;
import com.abhi.inshortchallenge.R;
import com.abhi.inshortchallenge.model.KickStartResponseElement;
import com.abhi.inshortchallenge.view.KickDetailFragment;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 *  Author: Chandra Prakash
 *  Description: Adapter to display lists.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<KickStartResponseElement> mDataset;
    private FragmentActivity mActivity;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTextViewTitle, mTextViewBackers, mTextViewPleadge, mTextViewnoDays;
        public NetworkImageView mImageView;
        public ProgressBar percentageProgress;
        public ViewHolder(View v) {
            super(v);
            mTextViewTitle = (TextView) v.findViewById(R.id.cell_title);
            percentageProgress = (ProgressBar) v.findViewById(R.id.percentage_progress);
            mTextViewBackers = (TextView) v.findViewById(R.id.cell_backers);
            mTextViewPleadge = (TextView) v.findViewById(R.id.cell_pleadge);
            mTextViewnoDays = (TextView) v.findViewById(R.id.cell_no_days);
            mImageView = (NetworkImageView) v.findViewById(R.id.cell_imageView);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            KickDetailFragment baseFragment = KickDetailFragment.newInstance();
            FragmentTransactionHelper.addFragmentWithModelObject(mActivity, R.id.content, baseFragment, mDataset.get(getAdapterPosition()) );
        }
    }

    public ListAdapter(List<KickStartResponseElement> myDataset, FragmentActivity activity) {
        mDataset = myDataset;
        mActivity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextViewTitle.setText(mDataset.get(position).getTitle());
        holder.mTextViewBackers.setText(mDataset.get(position).getCurrency()+" "+mDataset.get(position).getAmtpledged());
        holder.mTextViewPleadge.setText(mDataset.get(position).getNumbackers());
        holder.mTextViewnoDays.setText(mDataset.get(position).getPercentagefunded()+"%");
        holder.percentageProgress.setProgress(mDataset.get(position).getPercentagefunded());

        holder.mImageView.setDefaultImageResId(R.drawable.kick);

        if(mDataset.get(position).getImageURL() == null) {
            Thread thread = new Thread(new Task(mDataset.get(position), holder));
            thread.start();
        }
        else {
            ImageLoader imageLoader = VolleyNetwork.getInstance(CustomApplication.getmContext()).getImageLoader();
            holder.mImageView.setImageUrl(mDataset.get(position).getImageURL(), imageLoader);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }



    /**
     * Thread to fetch Image Url on the HTML page using Jsoup library
     */

    class Task implements Runnable{
        private KickStartResponseElement element;
        private ViewHolder holder;

        public Task(KickStartResponseElement element, ViewHolder holder){
            this.element = element;
            this.holder = holder;
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

                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageLoader imageLoader = VolleyNetwork.getInstance(mActivity).getImageLoader();
                        holder.mImageView.setImageUrl(element.getImageURL(), imageLoader);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
