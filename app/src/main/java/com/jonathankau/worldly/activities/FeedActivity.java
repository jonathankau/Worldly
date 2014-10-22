package com.jonathankau.worldly.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.contentful.java.api.CDACallback;
import com.contentful.java.api.CDAClient;
import com.contentful.java.model.CDAArray;
import com.contentful.java.model.CDAAsset;
import com.contentful.java.model.CDAEntry;
import com.contentful.java.model.CDAResource;
import com.jonathankau.worldly.R;
import com.jonathankau.worldly.fragments.FeedItemFragment;
import com.jonathankau.worldly.model.ArticleEntry;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class FeedActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        if (savedInstanceState == null) {
            CDAClient client = new CDAClient.Builder()
                    .setSpaceKey("zhvvoct54c0z")
                    .setAccessToken("d76a8349541f51bfd3ae2d29f24d339d04576cc9d453c989e95ddae258b1664e")
                    .build();

            client.fetchEntries(new CDACallback<CDAArray>() {
                @Override
                protected void onSuccess(CDAArray array, Response response) {
                    getFragmentManager().beginTransaction()
                            .add(R.id.container, FeedItemFragment.newInstance(parseEntries(array.getItems())))
                            .commit();
                }

                @Override
                protected void onFailure(RetrofitError retrofitError) {
                    // failure
                    Toast.makeText(FeedActivity.this, retrofitError.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private ArrayList<ArticleEntry> parseEntries(ArrayList<CDAResource> resources) {
        ArrayList<ArticleEntry> entries = new ArrayList<ArticleEntry>(resources.size());

        for (CDAResource entry : resources) {
            ArrayList<CDAAsset> assets = (ArrayList<CDAAsset>) ((CDAEntry) entry).getFields().get("pictures");
            String imageUrl = assets.get(0).getUrl().toString();
            String headline = (String) ((CDAEntry) entry).getFields().get("headline");
            String content = (String) ((CDAEntry) entry).getFields().get("content");

            ArticleEntry article = new ArticleEntry(headline, imageUrl, content);
            entries.add(article);
        }

        return entries;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feed, menu);
        return true;
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
