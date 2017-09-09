package com.abhi.inshortchallenge.utilities;

import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhi.inshortchallenge.R;
import com.abhi.inshortchallenge.model.InshortResponseElement;
import com.abhi.inshortchallenge.view.InshortDetailFragment;

import java.util.List;

/**
 *  Author: Chandra Prakash
 *  Description: Adapter to display lists.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<InshortResponseElement> mDataset;
    private FragmentActivity mActivity;
    private CustomAlertDialog customAlertDialog;
    private int loadCount = 20;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTextViewTitle, cellPublisher, cellUrl;
        public ViewHolder(View v) {
            super(v);
            mTextViewTitle = (TextView) v.findViewById(R.id.cell_title);
            cellPublisher = (TextView) v.findViewById(R.id.cell_publisher);
            cellUrl = (TextView) v.findViewById(R.id.cell_url);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            InshortDetailFragment baseFragment = InshortDetailFragment.newInstance();
            FragmentTransactionHelper.addFragmentWithModelObject(mActivity, R.id.content, baseFragment, mDataset.get(getAdapterPosition()) );
        }
    }

    public ListAdapter(List<InshortResponseElement> myDataset, FragmentActivity activity) {
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

        holder.mTextViewTitle.setText(mDataset.get(position).getTITLE());
        holder.cellPublisher.setText(mDataset.get(position).getPUBLISHER());
        holder.cellUrl.setText(mDataset.get(position).getURL());

        if( (position == getItemCount()-1) && mDataset.size()>20) {
            // Last Element.
            customAlertDialog = new CustomAlertDialog(mActivity);
            customAlertDialog.createProgressDialog("Loading..");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    customAlertDialog.dismissProgressDialog();
                    ListAdapter.this.notifyDataSetChanged();
                }
            }, 1000);
            loadNextPage();
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(loadCount > mDataset.size())
            return mDataset.size();

        return loadCount;
    }

    private void loadNextPage() {
        loadCount = loadCount+20;
    }
}
