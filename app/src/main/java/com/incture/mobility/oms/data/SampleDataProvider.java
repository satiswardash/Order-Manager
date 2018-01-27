package com.incture.mobility.oms.data;

import com.incture.mobility.oms.models.PromotionBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by satiswardash on 24/01/18.
 */

public class SampleDataProvider {

    private List<String> categories = Arrays.asList(new String[]{"Home, Garden & Tools", "Home Appliances", "Clothing", "Books & Audible", "Food & Grocery", "Automotive & Industrial", "Beauty & Health", "Movies, Music & Games", "Shoes & Jewelry", "Home Services"});

    public List<PromotionBanner> getPromotionBanners() {
        List<PromotionBanner> banners = new ArrayList<>();
        banners.add(new PromotionBanner("B101", "40% OFF", "On Top Brands", "Shop Now", "https://firebasestorage.googleapis.com/v0/b/upsc-c98c0.appspot.com/o/cityscapes_old_Czech_history_flags_town_Prague_rivers_1920x1271.jpg?alt=media&token=8e8fd8af-3dfb-40f2-94b2-a4acc6ed000c"));
        banners.add(new PromotionBanner("B102", "50% OFF", "On All Brands", "Shop Now", "https://firebasestorage.googleapis.com/v0/b/upsc-c98c0.appspot.com/o/J8Luoe4%20-%20Imgur.jpg?alt=media&token=97462d1b-9af0-45c0-81a2-3ec0a96660b5"));
        banners.add(new PromotionBanner("B103", "40% OFF", "On Selected Restaurants", "Visit Now", "https://firebasestorage.googleapis.com/v0/b/upsc-c98c0.appspot.com/o/Maler_der_Grabkammer_des_Sennudem_001.jpg?alt=media&token=e81d3b60-460c-47e0-9a6a-b025b75b62fa"));
        banners.add(new PromotionBanner("B104", "40% OFF", "On Top Brands", "Buy Now", "https://firebasestorage.googleapis.com/v0/b/kortain-ecommerce-application.appspot.com/o/images%2Fbanners%2Fbanner_00005.jpeg?alt=media&token=0916158f-bc87-4213-8fee-a39d0bd4dd00"));
        banners.add(new PromotionBanner("B105", "40% OFF", "On All Menus", "See More", "https://firebasestorage.googleapis.com/v0/b/upsc-c98c0.appspot.com/o/monument-valley-5k.jpg?alt=media&token=fdb65bef-5fc2-4d57-b795-c06f0c7a5278"));
        return banners;
    }

    public Map<String, String> getCategoryFeeds() {
        Map<String, String> feeds = new HashMap<>();
        int key = 101;
        for (String value :
                categories) {
            feeds.put(value, "C" + key);
            key++;
        }

        return feeds;
    }

    public Map<String, String[]> getSubCategory(String id) {
        Map<String, String[]> result = new HashMap<>();
        String[] values = null;
        switch (id) {

            case "C101": {
                values = new String[]{"Kitchen & Dining", "Furniture", "Bed & Bath", "Appliances", "Garden & Outdoor", "Fine Art", "Arts, Crafts & Sewing", "Pet Supplies", "Wedding Registry", "Event & Party Supplies"};
                result.put("CS101", values);
                break;
            }

            case "C102": {
                values = new String[]{"Air Conditioners", "Air Purifiers", "Beer Keg Refrigerators", "Beverage Refrigerators", "Ceiling Fans & Accessories", "Fans"};
                result.put("CS102", values);
                break;
            }

            case "C103": {
                values = new String[]{"Men", "Women", "Kids"};
                result.put("CS103", values);
                break;
            }

            case "C104": {
                values = new String[]{"Books", "Children's Books", "Magazines", "Textbooks", "Kindle Books", "Audible Audiobooks"};
                result.put("CS104", values);
                break;
            }

            case "C105": {
                values = new String[]{"Baby Foods", "Beverages", "Breakfast Foods", "Candy & Chocolate", "Canned, Jarred & Packaged Foods", "Condiments & Salad Dressings", "Cooking & Baking", "Dairy, Cheese & Eggs", "Deli", "Frozen", "Herbs, Spices & Seasonings", "Meat & Seafood"};
                result.put("CS105", values);
                break;
            }

            case "C106": {
                values = new String[]{"Automotive Parts & Accessories", "Automotive Tools & Equipment", "Car/Vehicle Electronics & GPS", "Tires & Wheels", "Motorcycle & Powersports", "Vehicles", "Industrial Supplies", "Janitorial", "Safety"};
                result.put("CS106", values);
                break;
            }

            case "C107": {
                values = new String[]{"Luxury Beauty", "Professional Skin Care", "Salon & Spa", "Men’s Grooming", "Health, Household & Baby Care", "Vitamins & Dietary Supplements", "Subscribe & Save"};
                result.put("CS107", values);
                break;
            }

            case "C108": {
                values = new String[]{"Movies & TV", "Blu-ray", "Video Shorts", "CDs & Vinyl", "Digital Music", "Musical Instruments", "Headphones", "Video Games", "PC Gaming"};
                result.put("CS108", values);
                break;
            }

            case "C109": {
                values = new String[]{"Men", "Women", "Baby & Kids"};
                result.put("CS109", values);
                break;
            }

            case "C110": {
                values = new String[]{"Computer & Electronics", "Home Improvement & Repair", "Smart Home Services", "Assembly", "Cleaning", "Plumbing", "Electrical", "Home Theater"};
                result.put("CS110", values);
                break;
            }

            default: {
                values = new String[]{};
                result.put("CS000", values);
                break;
            }
        }

        return result;
    }
}
