package com.abhi.inshortchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 *  Author: Chandra Prakash
 *  Description: Model Object to hold values form JSON object which are fetched from server.
 *              http://starlord.hackerearth.com/newsjson
 */
public class InshortResponseElement implements Parcelable {

    @SerializedName("ID")
    private int ID;

    @SerializedName("TITLE")
    private String TITLE;

    @SerializedName("URL")
    private String URL;

    @SerializedName("PUBLISHER")
    private String PUBLISHER;

    @SerializedName("CATEGORY")
    private String CATEGORY;

    @SerializedName("HOSTNAME")
    private String HOSTNAME;

    @SerializedName("TIMESTAMP")
    private long TIMESTAMP;



    public InshortResponseElement(Parcel source){
        readFromParcel(source);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(TITLE);
        dest.writeString(URL);
        dest.writeString(PUBLISHER);
        dest.writeString(CATEGORY);
        dest.writeString(HOSTNAME);
        dest.writeLong(TIMESTAMP);
    }

    private void readFromParcel(Parcel msource){
        ID = msource.readInt();
        TITLE = msource.readString();
        URL = msource.readString();
        PUBLISHER = msource.readString();
        CATEGORY = msource.readString();
        HOSTNAME = msource.readString();
        TIMESTAMP = msource.readLong();
    }

    public int describeContents(){
        return 0;
    }

    public static final Creator CREATOR = new Creator<InshortResponseElement>(){

        public InshortResponseElement createFromParcel(Parcel source){
            return new InshortResponseElement(source);
        }

        public InshortResponseElement[] newArray(int size){
            return new InshortResponseElement[size];
        }
    };

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getPUBLISHER() {
        return PUBLISHER;
    }

    public void setPUBLISHER(String PUBLISHER) {
        this.PUBLISHER = PUBLISHER;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public String getHOSTNAME() {
        return HOSTNAME;
    }

    public void setHOSTNAME(String HOSTNAME) {
        this.HOSTNAME = HOSTNAME;
    }

    public long getTIMESTAMP() {
        return TIMESTAMP;
    }

    public void setTIMESTAMP(long TIMESTAMP) {
        this.TIMESTAMP = TIMESTAMP;
    }
}
