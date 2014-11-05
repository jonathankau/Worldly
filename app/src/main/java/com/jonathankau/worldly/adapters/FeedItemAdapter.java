package com.jonathankau.worldly.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jonathankau.worldly.R;
import com.jonathankau.worldly.model.ArticleEntry;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by jkau on 10/19/14.
 */
public class FeedItemAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    Context context;
    ArrayList<ArticleEntry> data;
    Typeface tisaPro;

    public FeedItemAdapter(Context context, ArrayList<ArticleEntry> data) {
        // Generate typefaces
        tisaPro = Typeface.createFromAsset(context.getAssets(),
                "fonts/SourceSansPro-Light.otf");

        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ArticleEntry getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.layout_feeditem, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        // Load text and image
        ArticleEntry entry = getItem(i);

        holder.header.setText(entry.getHeader());
        holder.header.setTypeface(tisaPro);

        holder.number.setText(Integer.toString(i + 1));
        holder.number.setTypeface(tisaPro);

        Picasso.with(context)
                .load(entry.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.image);

        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.article_image) ImageView image;
        @InjectView(R.id.article_header) TextView header;
        @InjectView(R.id.article_number) TextView number;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
