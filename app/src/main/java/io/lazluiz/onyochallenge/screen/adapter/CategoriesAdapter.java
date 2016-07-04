package io.lazluiz.onyochallenge.screen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import io.lazluiz.onyochallenge.R;
import io.lazluiz.onyochallenge.model.Category;
import io.lazluiz.onyochallenge.model.Image;

/**
 * Created by Luiz F. Lazzarin on 29/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class CategoriesAdapter extends AbstractListAdapter<Category, CategoriesAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mInflater;

    public CategoriesAdapter(Context c) {
        mContext = c;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(mInflater
                .inflate(R.layout.list_item_categories, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.bind(mData.get(position), position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageMain;
        private final TextView mTextTitle;

        public ViewHolder(View v) {
            super(v);
            mImageMain = (ImageView) v.findViewById(R.id.item_categories_picture);
            mTextTitle = (TextView) v.findViewById(R.id.item_categories_title);
        }

        public void bind(Category data, int position) {
            mTextTitle.setText(data.getName());
            mImageMain.setContentDescription(data.getName());

            Image image = data.getImage().where().equalTo("context", "category-background").findFirst();
            Glide.with(mContext).load(image.getUrl()).into(mImageMain);

            // Image resize
            mImageMain.getLayoutParams().height = (int) mContext.getResources().getDimension(
                    (position == 0 || position % 4 == 0 || (position+1) % 4 == 0) ?
                                R.dimen.list_item_category_image_height_min :
                                R.dimen.list_item_category_image_height);
        }
    }
}
