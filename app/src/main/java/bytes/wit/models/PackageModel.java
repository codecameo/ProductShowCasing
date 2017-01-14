package bytes.wit.models;

import java.util.ArrayList;

/**
 * Created by Md. Sifat-Ul Haque on 1/6/2017.
 */

public class PackageModel {

    private String package_id, product_id, package_name, price,
            quantity, unit, offer_validation_date,
            creation_date, has_offer, is_new, offer_id;
    private ContentModel poster;
    private ArrayList<ContentImageModel> content_images;
    private ArrayList<ContentVideoModel> content_videos;

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOffer_validation_date() {
        return offer_validation_date;
    }

    public void setOffer_validation_date(String offer_validation_date) {
        this.offer_validation_date = offer_validation_date;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getHas_offer() {
        return has_offer;
    }

    public void setHas_offer(String has_offer) {
        this.has_offer = has_offer;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public ContentModel getPoster() {
        return poster;
    }

    public void setPoster(ContentModel poster) {
        this.poster = poster;
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
