package com.ocr.test.testrss.model;


import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oc.rss.fake.FakeNews;
import com.oc.rss.fake.FakeNewsList;
import com.ocr.test.testrss.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyFakeAdapter extends RecyclerView.Adapter<MyFakeAdapter.MyViewHolder> {

    // Fake news retrieving
    private final List<FakeNews> list = FakeNewsList.all;


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_fake, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FakeNews pair = list.get(position);
        holder.display(pair);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;

        private FakeNews currentPair;

        public MyViewHolder(final View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.fake_title));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // trigger new activity with webview
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentPair.title)
                            .setMessage(currentPair.htmlContent)
                            .show();
                }
            });
        }

        public void display(FakeNews pair) {
            currentPair = pair;
            name.setText(pair.title);
        }
    }

}