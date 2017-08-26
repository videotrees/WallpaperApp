package com.wallpaper.app.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Navdeep on 8/19/2017.
 */

public class APIResponse {

    @SerializedName("hits")
    @Expose
    private List<ImageObj> imageObjs = null;

    public List<ImageObj> getImageObjs() {
        return imageObjs;
    }

    public void setImageObjs(List<ImageObj> imageObjs) {
        this.imageObjs = imageObjs;
    }

    public class ImageObj {
        @SerializedName("webformatURL")
        @Expose
        private String webformatURL;

        public String getWebformatURL() {
            return webformatURL;
        }

        public void setWebformatURL(String webformatURL) {
            this.webformatURL = webformatURL;
        }
    }
}
