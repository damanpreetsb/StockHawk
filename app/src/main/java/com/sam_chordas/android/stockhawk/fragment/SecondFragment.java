package com.sam_chordas.android.stockhawk.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.sam_chordas.android.stockhawk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    WebView webView;
    String stock;


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        Bundle symbol = getArguments();
        stock = symbol.getString(getResources().getString(R.string.symbol_bundle));
        webView = (WebView) rootView.findViewById(R.id.web_chart);
        webView.getSettings().setJavaScriptEnabled(true);
        String s = "http://empyrean-aurora-455.appspot.com/charts.php?symbol="+stock;
        webView.loadUrl(s);
        return rootView;
    }
}
