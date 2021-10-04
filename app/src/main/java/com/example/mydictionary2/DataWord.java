package com.example.mydictionary2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class DataWord implements Parcelable {
    private String word;
    private List<DataDefinition> definitions;

    public String getDataWord() {
        return word;
    }

    public void setDataWord(String word) {
        this.word = word;
    }

    public List<DataDefinition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<DataDefinition> definitions) {
        this.definitions = definitions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.word);
        dest.writeList(this.definitions);
    }

    public void readFromParcel(Parcel source) {
        this.word = source.readString();
        this.definitions = new ArrayList<DataDefinition>();
        source.readList(this.definitions, DataDefinition.class.getClassLoader());
    }

    public DataWord() {
    }

    protected DataWord(Parcel in) {
        this.word = in.readString();
        this.definitions = new ArrayList<DataDefinition>();
        in.readList(this.definitions, DataDefinition.class.getClassLoader());
    }

    public static final Parcelable.Creator<DataWord> CREATOR = new Parcelable.Creator<DataWord>() {
        @Override
        public DataWord createFromParcel(Parcel source) {
            return new DataWord(source);
        }

        @Override
        public DataWord[] newArray(int size) {
            return new DataWord[size];
        }
    };
}
