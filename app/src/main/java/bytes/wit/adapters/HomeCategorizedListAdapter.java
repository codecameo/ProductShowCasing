package bytes.wit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import bytes.wit.models.CategoryModel;
import bytes.wit.showcasing.R;
import bytes.wit.viewholder.CategoryViewHolder;

/**
 * Created by Md. Sifat-Ul Haque on 12/27/2016.
 */

public class HomeCategorizedListAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private ArrayList<CategoryModel> mCategoryModels;

    public HomeCategorizedListAdapter() {
        mCategoryModels = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        //if (position>0)
            return R.layout.item_home_list;
        /*else
            return R.layout.slider_layout;*/
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //if (viewType == R.layout.item_home_list)
            return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false));
        /*else
            return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false));*/
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.bindTo(mCategoryModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategoryModels.size();
    }


    public void updateData(ArrayList<CategoryModel> categoryModels) {
        mCategoryModels.clear();
        mCategoryModels.addAll(categoryModels);
        notifyDataSetChanged();
    }




    /*@Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder instanceof SliderViewHolder){
            SliderViewHolder sliderViewHolder =(SliderViewHolder) holder;
            sliderViewHolder.startSlider();
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (holder instanceof SliderViewHolder){
            SliderViewHolder sliderViewHolder =(SliderViewHolder) holder;
            sliderViewHolder.stopSlider();
        }
    }*/
}
