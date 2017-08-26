package com.wallpaper.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wallpaper.app.R;
import com.wallpaper.app.bean.APIResponse;

import java.util.List;

/**
 * Created by Navdeep on 8/19/2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.HyViewHolder> {

    public interface AdapeterListners{
        void getList(APIResponse.ImageObj imageObj);
    }

    private List<APIResponse.ImageObj> imageObjs;
    private Context context;
    private AdapeterListners listners;

    public ImageAdapter(List<APIResponse.ImageObj> imageObjs, Context context,AdapeterListners listners){
        this.imageObjs = imageObjs;
        this.context = context;
        this.listners = listners;
    }

    @Override
    public HyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid_image, parent, false);

        return new HyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HyViewHolder holder, final int position) {

        if(imageObjs.get(position).getWebformatURL()!=null && !imageObjs.get(position).getWebformatURL().isEmpty()){
            Picasso
                    .with(context)
                    .load(imageObjs.get(position).getWebformatURL())
                    .into(holder.imageView);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listners.getList(imageObjs.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageObjs.size();
    }

    class HyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public HyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}
