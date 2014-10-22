package com.jonathankau.worldly.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ArticleEntry implements Parcelable {
    String header;
    String imageUrl;
    String body;

    public ArticleEntry(String header, String imageUrl, String body) {
        this.header = header;
        this.imageUrl = imageUrl;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public String getHeader() {

        return header;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.header);
        dest.writeString(this.imageUrl);
        dest.writeString(this.body);
    }

    private ArticleEntry(Parcel in) {
        this.header = in.readString();
        this.imageUrl = in.readString();
        this.body = in.readString();
    }

    public static final Creator<ArticleEntry> CREATOR = new Creator<ArticleEntry>() {
        public ArticleEntry createFromParcel(Parcel source) {
            return new ArticleEntry(source);
        }

        public ArticleEntry[] newArray(int size) {
            return new ArticleEntry[size];
        }
    };
}
