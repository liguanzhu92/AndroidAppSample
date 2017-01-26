package com.guanzhuli.abercrombie.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.*;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.guanzhuli.abercrombie.fragment.HomeFragment;
import com.guanzhuli.abercrombie.MainActivity;
import com.guanzhuli.abercrombie.R;
import com.guanzhuli.abercrombie.fragment.SearchFragment;
import com.guanzhuli.abercrombie.model.Product;
import com.guanzhuli.abercrombie.model.ProductList;
import com.squareup.picasso.Picasso;

/**
 * Created by Guanzhu Li on 1/25/2017.
 */
public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemHolder>{
    private Context mContext;
    private ProductList mProductList = ProductList.getInstance();

    public HomeItemAdapter(Context context) {
        mContext = context;
    }

    @Override
    public HomeItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_home_item,parent,false);
        return new HomeItemHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeItemHolder holder, final int position) {
        Product product = mProductList.get(position);
        if (product.getTitle() != null) {
            holder.mTextTitle.setVisibility(View.VISIBLE);
            holder.mTextTitle.setText(product.getTitle());
        } else {
            holder.mTextTitle.setVisibility(View.GONE);
        }
        if (product.getTopDescription() != null) {
            holder.mTextTopDescription.setVisibility(View.VISIBLE);
            holder.mTextTopDescription.setText(product.getTopDescription());
        } else {
            holder.mTextTopDescription.setVisibility(View.GONE);
        }
        if (product.getPromoMessage() != null) {
            holder.mTextPromo.setVisibility(View.VISIBLE);
            holder.mTextPromo.setText(product.getPromoMessage());
        } else {
            holder.mTextPromo.setVisibility(View.GONE);
        }
        if (product.getBottomDescription() != null) {
            holder.mTextBottomDescription.setVisibility(View.VISIBLE);
            holder.mTextBottomDescription.setText(product.getBottomDescription());
        } else {
            holder.mTextBottomDescription.setVisibility(View.GONE);
        }
        //holder.mTextBottomLink.setText(product.getBottomDescriptionLink());
        if (product.getBottomDescriptionLink() != null) {
            SpannableString ss = new SpannableString(product.getBottomDescriptionLink());
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    SearchFragment searchFragment = new SearchFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("Position", position);
                    bundle.putBoolean("Term", true);
                    searchFragment.setArguments(bundle);
                    ((MainActivity)mContext).getSupportFragmentManager().
                            beginTransaction().replace(R.id.fragment_container_home_activity, searchFragment)
                            .addToBackStack(HomeFragment.class.getName())
                            .commit();
                }
            };
            ss.setSpan(clickableSpan, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, ss.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            ss.setSpan(new UnderlineSpan(), 0, ss.length(), 0);
            holder.mTextBottomDescription.setMovementMethod(LinkMovementMethod.getInstance());
            holder.mTextBottomDescription.append(ss);
        }
        Picasso.with(mContext).load(product.getBackgroundImageURL()).into(holder.mImageBackground);
        if (product.getContentList() != null){
            holder.mLinearLayout.removeAllViews();
            for (int i = 0; i < product.getContentList().size(); i++) {
                Log.d("text", "position:" + position + " " + "button:" + i);
                Button button = new Button(mContext);
                button.setBackgroundResource(R.drawable.background_content_text);
                button.setText(product.getContentList().get(i).getTitle());
                final int finalI = i;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SearchFragment searchFragment = new SearchFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("Position", position);
                        bundle.putInt("Button", finalI);
                        bundle.putBoolean("Term", false);
                        searchFragment.setArguments(bundle);
                        ((MainActivity)mContext).getSupportFragmentManager().
                                beginTransaction().replace(R.id.fragment_container_home_activity, searchFragment)
                                .addToBackStack(HomeFragment.class.getName())
                                .commit();
                    }
                });
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(15,15,15,15);
                holder.mLinearLayout.addView(button, lp);
            }
        }
    }


    @Override
    public int getItemCount() {
        return mProductList.size();
    }
}

class HomeItemHolder extends RecyclerView.ViewHolder {
    ImageView mImageBackground;
    TextView mTextTitle, mTextTopDescription, mTextPromo, mTextBottomDescription, mTextBottomLink;
    LinearLayout mLinearLayout;

    public HomeItemHolder(View itemView) {
        super(itemView);
        mImageBackground = (ImageView) itemView.findViewById(R.id.home_background);
        mTextTitle = (TextView) itemView.findViewById(R.id.home_title);
        mTextPromo = (TextView) itemView.findViewById(R.id.home_promo_message);
        mTextTopDescription = (TextView) itemView.findViewById(R.id.home_top_desccription);
        mTextBottomDescription = (TextView) itemView.findViewById(R.id.home_bottom_description);
        //mTextBottomLink = (TextView) itemView.findViewById(R.id.home_bottom_description_link);
        mLinearLayout = (LinearLayout) itemView.findViewById(R.id.add_button_linear);

    }
}
