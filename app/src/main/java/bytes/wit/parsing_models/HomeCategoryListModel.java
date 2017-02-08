package bytes.wit.parsing_models;

import java.util.ArrayList;

import bytes.wit.models.CategoryModel;
import bytes.wit.models.ContentModel;

/**
 * Created by Md. Sifat-Ul Haque on 2/5/2017.
 */

public class HomeCategoryListModel {

    private ArrayList<? extends ContentModel> banner;
    private ArrayList<CategoryModel> category;

    public ArrayList<? extends ContentModel> getBanner() {
        return banner;
    }

    public void setBanner(ArrayList<? extends ContentModel> banner) {
        this.banner = banner;
    }

    public ArrayList<CategoryModel> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<CategoryModel> category) {
        this.category = category;
    }
}
