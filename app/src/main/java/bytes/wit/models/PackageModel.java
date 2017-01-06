package bytes.wit.models;

import java.util.ArrayList;

/**
 * Created by Md. Sifat-Ul Haque on 1/6/2017.
 */

public class PackageModel {

    private String package_id, product_id, package_name, price,
            quantity, unit, offer_validation_date,
            creation_date, has_offer, is_new, offer_id;

    private ArrayList<ContentImageModel> content_images;
    private ArrayList<ContentVideoModel> content_videos;

}
