package com.ocr.test.testrss.model;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocr.test.testrss.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


/**
 * Created by bob on 20/12/17.
 */

public class RSS3Adapter extends RecyclerView.Adapter<RSS3Adapter.ArticleViewHolder> implements XMLAsyncTask.DocumentConsumer {


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

        /* TODO : Add elements to doc with the submitted one - if possible order by date
        * and change color depending on source of RSS :*/
        try {
            if (_doc != null)
                _doc = concatXmlDocuments(_doc,doc);
            else _doc = doc;
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            Log.e("RSS3setXMLDocument", "Exception while concatening document" , e);
        }
        notifyDataSetChanged();

        Log.i("RSS3Adapter","setXMLDocument from interface / XMLAsyncTask - notify the changes to the adpater.");
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
            _title.setText(elt.getElementsByTagName("title").item(0).getTextContent() +
                            elt.getElementsByTagName("pubDate").item(0).getTextContent());

            _title.setBackgroundColor(Color.CYAN);
            Log.i("Adapter_AViewHolder","we set the current element");

        }
    }

    // Method to concatenate 2 or more document
    public Document concatXmlDocuments(Document... xmlDoc)
            throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document result = builder.newDocument();

        // root element from rss feed : channel :
        Element rootElement = result.createElement("channel");
        result.appendChild(rootElement);

        for(Document is : xmlDoc) {
            org.w3c.dom.Element root = is.getDocumentElement();
            NodeList childNodes = root.getChildNodes();
            for(int i = 0; i < childNodes.getLength(); i++) {
                Node importNode = result.importNode(childNodes.item(i), true);
                rootElement.appendChild(importNode);
            }
        }
        return result;
    }

}
