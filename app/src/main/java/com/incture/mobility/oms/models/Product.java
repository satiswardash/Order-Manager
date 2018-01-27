package com.incture.mobility.oms.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by satiswardash on 27/01/18.
 */

public class Product implements Parcelable {

    private String id;
    private String categoryId;
    private String subCategoryId;
    private String name;
    private String shortDescription;
    private String description;
    private String technicalSpecification;
    private String physicalSpecification;
    private String price;
    private String coupon;
    private String discount;
    private String image;

    public Product(String id, String categoryId, String subCategoryId, String name, String shortDescription, String description, String technicalSpecification, String physicalSpecification, String price, String coupon, String discount, String image) {
        this.id = id;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.technicalSpecification = technicalSpecification;
        this.physicalSpecification = physicalSpecification;
        this.price = price;
        this.coupon = coupon;
        this.discount = discount;
        this.image = image;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnicalSpecification() {
        return technicalSpecification;
    }

    public void setTechnicalSpecification(String technicalSpecification) {
        this.technicalSpecification = technicalSpecification;
    }

    public String getPhysicalSpecification() {
        return physicalSpecification;
    }

    public void setPhysicalSpecification(String physicalSpecification) {
        this.physicalSpecification = physicalSpecification;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.categoryId);
        dest.writeString(this.subCategoryId);
        dest.writeString(this.name);
        dest.writeString(this.shortDescription);
        dest.writeString(this.description);
        dest.writeString(this.technicalSpecification);
        dest.writeString(this.physicalSpecification);
        dest.writeString(this.price);
        dest.writeString(this.coupon);
        dest.writeString(this.discount);
        dest.writeString(this.image);
    }

    protected Product(Parcel in) {
        this.id = in.readString();
        this.categoryId = in.readString();
        this.subCategoryId = in.readString();
        this.name = in.readString();
        this.shortDescription = in.readString();
        this.description = in.readString();
        this.technicalSpecification = in.readString();
        this.physicalSpecification = in.readString();
        this.price = in.readString();
        this.coupon = in.readString();
        this.discount = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
