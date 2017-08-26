package com.wallpaper.app.utils;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.wallpaper.app.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Navdeep on 8/19/2017.
 */

public class Util {

    public static void setAsWallpaper(Context context, Bitmap bitmap) {
        try {
            WallpaperManager wm = WallpaperManager.getInstance(context);

            wm.setBitmap(bitmap);
            Toast.makeText(context,
                    context.getString(R.string.toast_wallpaper_set),
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context,context.getString(R.string.toast_wallpaper_set_failed),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public static void shareApp(Activity activity) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out amazing wallpapers app at: https://play.google.com/store/apps/details?id=" + activity.getPackageName());
        sendIntent.setType("text/plain");
        activity.startActivity(sendIntent);
    }

    public static void shareImage(Context context, Bitmap img) {

        Uri bmpUri = getLocalBitmapUri(img,context);

        Intent shareIntent = new Intent();

        shareIntent.setAction(Intent.ACTION_SEND);

        shareIntent.putExtra(Intent.EXTRA_TEXT,
                "\nHey check out amazing wallpapers app at: https://play.google.com/store/apps/details?id="
                        + context.getPackageName());
        shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(shareIntent, "Share Opportunity"));


        /*sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out amazing wallpapers app at: https://play.google.com/store/apps/details?id=" + context.getPackageName());
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri );
        context.startActivity(sendIntent);*/
    }

    private static Uri getLocalBitmapUri(Bitmap bmp, Context context) {
        Uri bmpUri = null;
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bmpUri = Uri.fromFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }


    public static void launchMarket(Activity activity) {
        Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            activity.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + activity.getPackageName())));
        }
    }
}
