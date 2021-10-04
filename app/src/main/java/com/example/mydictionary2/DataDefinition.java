package com.example.mydictionary2;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class DataDefinition implements Parcelable, Serializable {
    public String image_url;
    public String type;
    public String definition;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image_url);
        dest.writeString(this.type);
        dest.writeString(this.definition);
    }

    public void readFromParcel(Parcel source) {
        this.image_url = source.readString();
        this.type = source.readString();
        this.definition = source.readString();
    }

    public DataDefinition() {
    }

    protected DataDefinition(Parcel in) {
        this.image_url = in.readString();
        this.type = in.readString();
        this.definition = in.readString();
    }

    public static final Parcelable.Creator<DataDefinition> CREATOR = new Parcelable.Creator<DataDefinition>() {
        @Override
        public DataDefinition createFromParcel(Parcel source) {
            return new DataDefinition(source);
        }

        @Override
        public DataDefinition[] newArray(int size) {
            return new DataDefinition[size];
        }
    };
}
