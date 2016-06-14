package io.lazluiz.onyochallenge.screen.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import io.lazluiz.onyochallenge.R;
import io.lazluiz.onyochallenge.model.Company;
import io.lazluiz.onyochallenge.model.Image;

/**
 * Created by Luiz F. Lazzarin on 13/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class CompaniesAdapter extends AbstractListAdapter<Company, CompaniesAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;
    private OnMapClickListener mOnMapClickListener;

    public CompaniesAdapter(Context c) {
        mContext = c;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(mInflater
                .inflate(R.layout.list_item_restaurants, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.bind(mData.get(position), position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View mRoot;
        private final ImageView mImageMain;
        private final ImageButton mButtonGeo;
        private final TextView mTextName;
        private final TextView mTextAddress;

        private Company mCompany;

        public ViewHolder(View v) {
            super(v);
            mRoot = v;
            mImageMain = (ImageView) v.findViewById(R.id.item_restaurant_picture);
            mButtonGeo = (ImageButton) v.findViewById(R.id.item_restaurant_geo_button);
            mTextName = (TextView) v.findViewById(R.id.item_restaurant_name);
            mTextAddress = (TextView) v.findViewById(R.id.item_restaurant_address);

            mImageMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onItemClick(mCompany);
                    }
                }
            });

            mButtonGeo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnMapClickListener != null) {
                        mOnMapClickListener.onItemClick(mCompany);
                    }
                }
            });
        }

        public void bind(Company r, int position) {
            mCompany = r;
            mTextName.setText(r.getDisplayName());
            mTextAddress.setText(r.getAddress());

            Image image = r.getImage().where().equalTo("context", "company-main").findFirst();
            Glide.with(mContext).load(image.getUrl()).into(mImageMain);

            mRoot.setBackgroundColor(ContextCompat.getColor(mContext,
                    position % 2 != 0 ? R.color.gray : android.R.color.white));
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnMapClickListener(OnMapClickListener listener) {
        mOnMapClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Company c);
    }

    public interface OnMapClickListener {
        void onItemClick(Company c);
    }
}
