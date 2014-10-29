package com.jonathankau.worldly.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.jonathankau.worldly.R;
import com.jonathankau.worldly.model.ArticleEntry;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ArticleActivity extends Activity {
    @InjectView(R.id.article_banner) ImageView banner;
    @InjectView(R.id.article_content) TextView content;

    private ArticleEntry entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // Obtain bundle args
        entry = getIntent().getExtras().getParcelable("ARTICLE_ENTRY");

        // Generate typefaces
        final Typeface tisaPro=Typeface.createFromAsset(getAssets(),
                "fonts/TisaPro-Regular.otf");

        // Inject views
        ButterKnife.inject(this);

        // Set fonts
        content.setTypeface(tisaPro);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.article, menu);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Set picasso load image
        Picasso.with(this)
                .load(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .into(banner);

        Picasso.with(this)
                .load(entry.getImageUrl())
                .fit()
                .centerCrop()
                .into(banner);

        // Load content
        content.setText(entry.getBody());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
