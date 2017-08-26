package com.wallpaper.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.wallpaper.app.adapter.ImageAdapter;
import com.wallpaper.app.bean.APIResponse;
import com.wallpaper.app.rest.APIUtils;
import com.wallpaper.app.utils.AppListners;
import com.wallpaper.app.utils.AppPref;
import com.wallpaper.app.utils.FullScreenDialog;
import com.wallpaper.app.utils.Util;

import java.util.List;

public class DashboardActivity extends AppCompatActivity
        implements View.OnClickListener, SearchView.OnQueryTextListener {


    private RecyclerView imageGrid;
    private TextView sportCategory,natureCategory,artCategory,earthCategory,landscapesCategory,
            cityScapesCategory,lifeCategory,texturesCategory;
    private ProgressBar progressBar;
    private ImageView shareImageView,fbImageView;

    private AppPref appPref;
    private AdView mAdView;
    InterstitialAd mInterstitialAd;
    private boolean canBack;
    private String testDeviceId = "A33F0A3396C8F4627EC2484A729A3D9E";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appPref = new AppPref(this);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(testDeviceId)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        AdRequest adRequest2 = new AdRequest.Builder()
                .addTestDevice(testDeviceId)
                .build();
        mInterstitialAd.loadAd(adRequest2);



        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        sportCategory = (TextView)findViewById(R.id.sportCategory);
        natureCategory = (TextView)findViewById(R.id.natureCategory);
        artCategory = (TextView)findViewById(R.id.artCategory);
        earthCategory = (TextView)findViewById(R.id.earthCategory);
        landscapesCategory = (TextView)findViewById(R.id.landscapesCategory);
        cityScapesCategory = (TextView)findViewById(R.id.cityScapesCategory);
        lifeCategory = (TextView)findViewById(R.id.lifeCategory);
        texturesCategory = (TextView)findViewById(R.id.texturesCategory);
        shareImageView = (ImageView)findViewById(R.id.shareImageView);
        fbImageView = (ImageView)findViewById(R.id.fbImageView);

        sportCategory.setOnClickListener(this);
        natureCategory.setOnClickListener(this);
        artCategory.setOnClickListener(this);
        earthCategory.setOnClickListener(this);
        landscapesCategory.setOnClickListener(this);
        cityScapesCategory.setOnClickListener(this);
        lifeCategory.setOnClickListener(this);
        texturesCategory.setOnClickListener(this);
        shareImageView.setOnClickListener(this);
        fbImageView.setOnClickListener(this);


        imageGrid = (RecyclerView)findViewById(R.id.imageGrid);
        imageGrid.setLayoutManager(new GridLayoutManager(this,2));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        setImagesList("wallpaper hd");
    }

    /*private void showInterstitial() {

       *//* AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(testDeviceId)
                .build();
                mInterstitialAd.loadAd(adRequest);*//*

                mInterstitialAd.setAdListener(new AdListener() {
                    public void onAdLoaded() {
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                    }
                });

    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (!canBack){
            canBack = true;
            mInterstitialAd.show();
        }
        else {
            canBack = false;
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    private void setImagesList(String query){
        progressBar.setVisibility(View.VISIBLE);

        APIUtils.getImageListAPI(query, new AppListners() {
            @Override
            public void getList(List<APIResponse.ImageObj> imageObjs) {
                if(imageObjs !=null && imageObjs.size()>0){

                    ImageAdapter adapter = new ImageAdapter(imageObjs, DashboardActivity.this, new ImageAdapter.AdapeterListners() {
                        @Override
                        public void getList(APIResponse.ImageObj imageObj) {
                            FullScreenDialog.showDialog(DashboardActivity.this,imageObj);
                        }
                    });
                    imageGrid.setAdapter(adapter);
                }else{
                    Toast.makeText(DashboardActivity.this,getString(R.string.no_result),
                            Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public void onClick(View view) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if (view.getId()==R.id.sportCategory){
            setImagesList("sport");
        }else if (view.getId()==R.id.natureCategory){
            setImagesList("nature");
        }else if (view.getId()==R.id.artCategory){
            setImagesList("art");
        }else if (view.getId()==R.id.earthCategory){
            setImagesList("Earth");
        }else if (view.getId()==R.id.landscapesCategory){
            setImagesList("landscapes");
        }else if (view.getId()==R.id.cityScapesCategory){
            setImagesList("city");
        }else if (view.getId()==R.id.lifeCategory){
            setImagesList("life");
        }else if (view.getId()==R.id.texturesCategory){
            setImagesList("Textures");
        }
        else if (view.getId()==R.id.shareImageView){
           Util.shareApp(DashboardActivity.this);
        }
        else if (view.getId()==R.id.fbImageView){
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        setImagesList(query);
        hideKeyBoard();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    private void hideKeyBoard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
