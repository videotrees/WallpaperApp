package com.wallpaper.app.utils;

import com.wallpaper.app.bean.APIResponse;

import java.util.List;

/**
 * Created by Navdeep on 8/19/2017.
 */

public interface AppListners{
   void getList(List<APIResponse.ImageObj> imageObjs);
}
