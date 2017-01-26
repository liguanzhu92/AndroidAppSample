package com.guanzhuli.abercrombie.model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Guanzhu Li on 1/25/2017.
 */
public class Product {

    private String mTitle;
    private String mBackgroundImageURL;
    private String mPromoMessage;
    private String mTopDescription;
    private String mBottomDescription;
    private String mBottomDescriptionURL;
    private String mBottomDescriptionLink;
    private ArrayList<Content> mContentList;

    public Product() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getBackgroundImageURL() {
        return mBackgroundImageURL;
    }

    public void setBackgroundImageURL(String backgroundImageURL) {
        mBackgroundImageURL = backgroundImageURL;
    }

    public String getPromoMessage() {
        return mPromoMessage;
    }

    public void setPromoMessage(String promoMessage) {
        mPromoMessage = promoMessage;
    }

    public String getTopDescription() {
        return mTopDescription;
    }

    public void setTopDescription(String topDescription) {
        mTopDescription = topDescription;
    }

    public String getBottomDescription() {
        return mBottomDescription;
    }

    public void setBottomDescription(String bottomDescription) {
        int contentEnd = bottomDescription.indexOf("<a");
        int URLStart = bottomDescription.indexOf("http");
        int URLEnd = bottomDescription.indexOf("html") + 4;
        int linkContentStart = bottomDescription.indexOf('>');
        int linkContentEnd = bottomDescription.indexOf("</a");
        mBottomDescription = bottomDescription.substring(0,contentEnd).trim();
        setBottomDescriptionURL(bottomDescription.substring(URLStart,URLEnd).trim());
        setBottomDescriptionLink(bottomDescription.substring(linkContentStart+1,linkContentEnd).trim());
        Log.d("text", mBottomDescription);
        Log.d("text", getBottomDescriptionLink());
        Log.d("text", getBottomDescriptionURL());
    }

    public ArrayList<Content> getContentList() {
        return mContentList;
    }

    public void setContentList(ArrayList<Content> contentList) {
        mContentList = contentList;
    }

    public void setBottomDescriptionURL(String bottomDescriptionURL) {
        mBottomDescriptionURL = bottomDescriptionURL;
    }

    public String getBottomDescriptionURL() {
        return mBottomDescriptionURL;
    }

    public String getBottomDescriptionLink() {
        return mBottomDescriptionLink;
    }

    public void setBottomDescriptionLink(String bottomDescriptionLink) {
        mBottomDescriptionLink = bottomDescriptionLink;
    }
}