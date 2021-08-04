package com.gnani.project2;

import android.os.Parcel;
import android.os.Parcelable;

public class Music implements Parcelable {

    // Declaration of variables
    public String songName;
    public String songLink;
    public String SingerName;
    public String SingerDetails;
    public String SongDetails;
    public int image;

    protected Music(Parcel in) {
        songName = in.readString();
        songLink = in.readString();
        SingerName = in.readString();
        SingerDetails = in.readString();
        SongDetails = in.readString();
        image = in.readInt();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    // Getters and setters
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public String getSingerName() {
        return SingerName;
    }

    public void setSingerName(String singerName) {
        SingerName = singerName;
    }

    public String getSingerDetails() {
        return SingerDetails;
    }

    public void setSingerDetails(String singerDetails) {
        SingerDetails = singerDetails;
    }

    public String getSongDetails() {
        return SongDetails;
    }

    public void setSongDetails(String songDetails) {
        SongDetails = songDetails;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    // To string method
    public String toString() {
        return "Music{" +
                "songName='" + songName + '\'' +
                ", songLink='" + songLink + '\'' +
                ", SingerName='" + SingerName + '\'' +
                ", SingerDetails='" + SingerDetails + '\'' +
                ", SongDetails='" + SongDetails + '\'' +
                ", image=" + image +
                '}';
    }

    //Constructor to set values .
    public Music(String songName, String songLink, String singerName, String singerDetails, String songDetails, int image) {
        this.songName = songName;
        this.songLink = songLink;
        SingerName = singerName;
        SingerDetails = singerDetails;
        SongDetails = songDetails;
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    // write to parcel method . To allow class instances to be sent as a Parcel we must implement the Parcelable .
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(songName);
        dest.writeString(songLink);
        dest.writeString(SingerName);
        dest.writeString(SingerDetails);
        dest.writeString(SongDetails);
        dest.writeInt(image);
    }
}
