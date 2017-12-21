package com.ocr.test.testrss.model;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocr.test.testrss.R;
import com.ocr.test.testrss.RecycleActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * Created by bob on 20/12/17.
 */

public class RSSAdapter extends RecyclerView.Adapter<RSSAdapter.ArticleViewHolder> implements XMLAsyncTask.DocumentConsumer {


    private Document _doc = null;

    @Override
    public int getItemCount() {
       // Log.i("RSSAdapter","Retrieving onItemCount()");


        if (_doc != null) {
            Log.i("RSSAdapter","Retrieving onItemCount() " + _doc.getElementsByTagName("item").getLength());
            return _doc.getElementsByTagName("item").getLength();

        }
        else
            return 0;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.i("RSSAdapter","Calling onCreateViewHolder");

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.article_cell,parent, false);

        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {

        Log.i("RSSAdapter","Calling onBindViewHolder");

        Element item = (Element)_doc.getElementsByTagName("item").item(position);

        holder.setElement(item);
    }

    @Override
    public void setXMLDocument(Document doc) {

        _doc = doc;
        notifyDataSetChanged();

        Log.i("RSSAdapter","setXMLDocument from interface / XMLAsyncTask - notify the changes to the adpater.");
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        private final TextView _title;

        private Element _curElt;

        public ArticleViewHolder(final View itemView) {

            super(itemView);
            _title = ((TextView) itemView.findViewById(R.id.title));

            Log.i("Adapter_AViewHolder","Article View Holder call passing the view in parameters - setting the title");
        }

        public void setElement(Element elt) {
            _curElt = elt;
            _title.setText(elt.getElementsByTagName("title").item(0).getTextContent());

            Log.i("Adapter_AViewHolder","we set the current element");

        }
    }

}
