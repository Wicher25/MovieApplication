package com.example.movieapplication.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;


import javax.annotation.Generated;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Dates extends BaseObservable implements Parcelable {


    @SerializedName("maximum")
    @Expose
    private String maximum;
    @SerializedName("minimum")
    @Expose
    private String minimum;

    // Parcel
    public final static Parcelable.Creator<Dates> CREATOR = new Creator<Dates>() {
        @Override
        public Dates createFromParcel(Parcel parcel) {
            return new Dates(parcel);
        }

        @Override
        public Dates[] newArray(int i) {
            return (new Dates[i]);
        }
    };


    @Bindable
    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
        notifyPropertyChanged(BR.maximum);
    }

    @Bindable
    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
        notifyPropertyChanged(BR.minimum);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel , int i) {

        parcel.writeValue(maximum);
        parcel.writeValue(minimum);

    }


    public Dates() {
    }

    public Dates(Parcel in) {
        this.maximum = ((String) in.readValue((String.class.getClassLoader())));
        this.minimum = ((String) in.readValue((String.class.getClassLoader())));
    }
}
