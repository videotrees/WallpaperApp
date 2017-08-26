package com.wallpaper.app;

import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Navdeep on 8/22/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
    }
}
