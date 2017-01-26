package com.guanzhuli.abercrombie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.guanzhuli.abercrombie.fragment.HomeFragment;
import com.guanzhuli.abercrombie.model.Content;
import com.guanzhuli.abercrombie.model.Product;
import com.guanzhuli.abercrombie.model.ProductList;
import com.guanzhuli.abercrombie.utils.VolleyController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL
            = "https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json";
    private static final String TAG = "Volley";
    private ProductList mProductList = ProductList.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        fetchData();
    }

    private void fetchData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(BASE_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                mProductList.clear();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Product product = new Product();
                    try {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                        try {
                            product.setBackgroundImageURL(jsonObject.getString("backgroundImage"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            product.setPromoMessage(jsonObject.getString("promoMessage"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            product.setTopDescription(jsonObject.getString("topDescription"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            product.setBottomDescription(jsonObject.getString("bottomDescription"));
                            Log.d("text", product.getBottomDescription());
                            Log.d("text", product.getBottomDescriptionLink());
                            Log.d("text", product.getBottomDescriptionURL());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            product.setTitle(jsonObject.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            JSONArray subJSONArray = jsonObject.getJSONArray("content");
                            ArrayList<Content> arrayList = new ArrayList<>();
                            for (int j = 0; j < subJSONArray.length(); j++) {
                                Log.d("text", "create list");
                                JSONObject subJsonObject = (JSONObject) subJSONArray.get(j);
                                Content content = new Content();
                                try {
                                    content.setTitle(subJsonObject.getString("title"));
                                    content.setTargetURL(subJsonObject.getString("target"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                arrayList.add(content);
                            }
                            product.setContentList(arrayList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mProductList.add(product);
                }
                if(findViewById(R.id.fragment_container_home_activity) != null) {
                    HomeFragment homeFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_home_activity, homeFragment).commit();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(TAG, "Error: " + volleyError.getMessage());
            }
        });
        VolleyController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}
