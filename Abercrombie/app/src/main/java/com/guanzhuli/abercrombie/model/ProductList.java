package com.guanzhuli.abercrombie.model;

import java.util.ArrayList;

/**
 * Created by Guanzhu Li on 1/25/2017.
 */
public class ProductList extends ArrayList<Product> {
    private static ProductList mInstance = null;

    public static ProductList getInstance() {
        if (mInstance == null) {
            mInstance = new ProductList();
        }
        return mInstance;
    }

    private ProductList() {
    }
}
