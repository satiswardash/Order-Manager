package com.incture.mobility.oms.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by satiswardash on 29/01/18.
 */

public class Cart implements Parcelable {

    private String id;
    private Product product;
    private int quantity;
    private float totalPrice;

    public Cart() {
    }

    public Cart(String id, Product product, int quantity, float totalPrice) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.product, flags);
        dest.writeInt(this.quantity);
        dest.writeFloat(this.totalPrice);
    }

    protected Cart(Parcel in) {
        this.id = in.readString();
        this.product = in.readParcelable(Product.class.getClassLoader());
        this.quantity = in.readInt();
        this.totalPrice = in.readFloat();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel source) {
            return new Cart(source);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };
}
