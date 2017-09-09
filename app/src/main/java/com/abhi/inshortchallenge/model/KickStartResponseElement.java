package com.abhi.inshortchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 *  Author: Chandra Prakash
 *  Description: Model Object to hold values form JSON object which are fetched from server.
 *              http://starlord.hackerearth.com/kickstarter
 */
public class KickStartResponseElement implements Parcelable {

    @SerializedName("s.no")
    private String sno;

    @SerializedName("amt.pledged")
    private String amtpledged;

    @SerializedName("blurb")
    private String blurb;

    @SerializedName("by")
    private String by;

    @SerializedName("country")
    private String country;

    @SerializedName("currency")
    private String currency;

    @SerializedName("end.time")
    private String endtime;

    @SerializedName("location")
    private String location;

    @SerializedName("percentage.funded")
    private int percentagefunded;

    @SerializedName("num.backers")
    private String numbackers;

    @SerializedName("state")
    private String state;

    @SerializedName("title")
    private String title;

    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    private String imageURL;



    public KickStartResponseElement(Parcel source){
        readFromParcel(source);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sno);
        dest.writeString(amtpledged);
        dest.writeString(blurb);
        dest.writeString(by);
        dest.writeString(country);
        dest.writeString(currency);
        dest.writeString(endtime);
        dest.writeString(location);
        dest.writeInt(percentagefunded);
        dest.writeString(numbackers);
        dest.writeString(state);
        dest.writeString(title);
        dest.writeString(type);
        dest.writeString(url);
        dest.writeString(imageURL);
    }

    private void readFromParcel(Parcel msource){
        sno = msource.readString();
        amtpledged = msource.readString();
        blurb = msource.readString();
        by = msource.readString();
        country = msource.readString();
        currency = msource.readString();
        endtime = msource.readString();
        location = msource.readString();
        percentagefunded = msource.readInt();
        numbackers = msource.readString();
        state = msource.readString();
        title = msource.readString();
        type = msource.readString();
        url = msource.readString();
        imageURL = msource.readString();
    }

    public int describeContents(){
        return 0;
    }

    public static final Creator CREATOR = new Creator<KickStartResponseElement>(){

        public KickStartResponseElement createFromParcel(Parcel source){
            return new KickStartResponseElement(source);
        }

        public KickStartResponseElement[] newArray(int size){
            return new KickStartResponseElement[size];
        }
    };

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getAmtpledged() {
        return amtpledged;
    }

    public void setAmtpledged(String amtpledged) {
        this.amtpledged = amtpledged;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPercentagefunded() {
        return percentagefunded;
    }

    public void setPercentagefunded(int percentagefunded) {
        this.percentagefunded = percentagefunded;
    }

    public String getNumbackers() {
        return numbackers;
    }

    public void setNumbackers(String numbackers) {
        this.numbackers = numbackers;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
