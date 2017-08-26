package com.wallpaper.app.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.wallpaper.app.R;
import com.wallpaper.app.bean.APIResponse;

/**
 * Created by Navdeep on 8/22/2017.
 */

public class FullScreenDialog {
    public static void showDialog(final Context activityContex, final APIResponse.ImageObj imageObj){

        final Dialog dialog = new Dialog(activityContex,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_full_screen);

        final Bitmap[] bitmapImage = new Bitmap[1];

        final ImageView imageView = (ImageView) dialog.findViewById(R.id.fullScreenImageView);
        ImageView backButton = (ImageView)dialog.findViewById(R.id.backButton);
        ImageView shareButton = (ImageView)dialog.findViewById(R.id.shareButton);
        TextView setTextView = (TextView)dialog.findViewById(R.id.setTextView);

        if(imageObj.getWebformatURL()!=null && !imageObj.getWebformatURL().isEmpty()){
            Picasso
                    .with(activityContex)
                    .load(imageObj.getWebformatURL())
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            imageView.setImageBitmap(bitmap);
                            bitmapImage[0] = bitmap;

                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {
                        }
                    });
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        setTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bitmapImage[0]!=null)
                    Util.setAsWallpaper(activityContex,bitmapImage[0]);
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bitmapImage[0]!=null)
                Util.shareImage(activityContex,bitmapImage[0]);
            }
        });

        dialog.show();

    }
}
