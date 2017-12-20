package com.ocr.test.testrss.model;

import android.support.v7.widget.RecyclerView;
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
        if (_doc != null)
            return _doc.getElementsByTagName("item").getLength();
        else
            return 0;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.article_cell,parent, false);

        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Element item = (Element)_doc.getElementsByTagName("item").item(position);

        holder.setElement(item);
    }

    @Override
    public void setXMLDocument(Document doc) {

        _doc = doc;
        notifyDataSetChanged();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        private final TextView _title;

        private Element _curElt;

        public ArticleViewHolder(final View itemView) {

            super(itemView);
            _title = ((TextView) itemView.findViewById(R.id.title));
        }

        public void setElement(Element elt) {
            _curElt = elt;
            _title.setText(elt.getElementsByTagName("title").item(0).getTextContent());

        }
    }

}
