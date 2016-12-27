package bytes.wit.models;

import java.util.ArrayList;

/**
 * Created by Md. Sifat-Ul Haque on 12/26/2016.
 */

public class ProductModel {

    private String product_id, category_id, product_name, validity, ingredient, description, offer_validation_date;
    private ArrayList<ContentImageModel> content_images;
    private ArrayList<ContentVideoModel> content_videos;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOffer_validation_date() {
        return offer_validation_date;
    }

    public void setOffer_validation_date(String offer_validation_date) {
        this.offer_validation_date = offer_validation_date;
    }

    public ArrayList<ContentImageModel> getContent_images() {
        return content_images;
    }

    public void setContent_images(ArrayList<ContentImageModel> content_images) {
        this.content_images = content_images;
    }

    public ArrayList<ContentVideoModel> getContent_videos() {
        return content_videos;
    }

    public void setContent_videos(ArrayList<ContentVideoModel> content_videos) {
        this.content_videos = content_videos;
    }
}
