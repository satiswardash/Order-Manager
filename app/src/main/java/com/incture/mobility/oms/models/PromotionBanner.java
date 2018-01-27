package com.incture.mobility.oms.models;

/**
 * Created by satiswardash on 24/01/18.
 */

public class PromotionBanner {

    private String id;
    private String title;
    private String description;
    private String action;
    private String image;

    public PromotionBanner() {
    }

    public PromotionBanner(String id, String title, String description, String action, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.action = action;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
