package com.guanzhuli.abercrombie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.guanzhuli.abercrombie.R;
import com.guanzhuli.abercrombie.model.ProductList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private int mPposition;
    private int mButtonPosition;
    private String mStringURL;
    private WebView mWebView;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mPposition = getArguments().getInt("Position");
        if (getArguments().getBoolean("Term")) {
            mStringURL = ProductList.getInstance().get(mPposition).getBottomDescriptionURL();
        } else {
            mButtonPosition = getArguments().getInt("Button");
            mStringURL = ProductList.getInstance().get(mPposition).getContentList().get(mButtonPosition).getTargetURL();
        }
        mWebView = (WebView) view.findViewById(R.id.search_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(mStringURL);
        return view;
    }
}
