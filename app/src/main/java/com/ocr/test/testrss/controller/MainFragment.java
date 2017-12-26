package com.ocr.test.testrss.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocr.test.testrss.R;

public class MainFragment extends Fragment {


    // static factory method ici pour passer les arg aux fragments
    public static MainFragment create(String tagname) {

        // Good to share parameters :
        Bundle args = new Bundle();
        args.putString("saveTag", tagname);
        // singleton :
        MainFragment mainfrg = new MainFragment();
        mainfrg.setArguments(args);
        return mainfrg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        // update the textView with our new text from the bundle of  args :
        final TextView mTxt = (TextView) v.findViewById(R.id.myFragTxt);

        mTxt.setText(getArguments().getString("saveTag"));

        // Inflate the layout for this fragment
        return v;
    }



}
