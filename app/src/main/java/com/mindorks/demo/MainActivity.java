package com.mindorks.demo;

import android.os.Bundle;

import android.view.View;

import com.mindorks.butterknifelite.ButterKnifeLite;
import com.mindorks.butterknifelite.annotations.BindView;
import com.mindorks.demo.drawer.DrawerHeader;
import com.mindorks.demo.drawer.DrawerMenuItem;
import com.mindorks.demo.gallery.ImageTypeBig;
import com.mindorks.demo.gallery.ImageTypeSmallPlaceHolder;
import com.mindorks.placeholderview.PlaceHolderView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawerView)
    private PlaceHolderView mDrawerView;

    @BindView(R.id.drawerLayout)
    private DrawerLayout mDrawer;

    @BindView(R.id.toolbar)
    private Toolbar mToolbar;

    @BindView(R.id.galleryView)
    private PlaceHolderView mGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnifeLite.bind(this);
        setupDrawer();
        setupGallery();
    }

    private void setupDrawer(){
        mDrawerView
                .addView(new DrawerHeader())
                .addView(new DrawerMenuItem(this.getApplicationContext()))
                .addView(new DrawerMenuItem(this.getApplicationContext()))
                .addView(new DrawerMenuItem(this.getApplicationContext()))
                .addView(new DrawerMenuItem(this.getApplicationContext()))
                .addView(new DrawerMenuItem(this.getApplicationContext()))
                .addView(new DrawerMenuItem(this.getApplicationContext()))
                .addView(new DrawerMenuItem(this.getApplicationContext()))
                .addView(new DrawerMenuItem(this.getApplicationContext()));

        ActionBarDrawerToggle  drawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void setupGallery(){
        List<Image> imageList = Utils.loadImages(this.getApplicationContext());
        List<Image> newImageList = new ArrayList<>();
        for (int i = 0; i <  (imageList.size() > 10 ? 10 : imageList.size()); i++) {
            newImageList.add(imageList.get(i));
        }
        mGalleryView.addView(new ImageTypeSmallPlaceHolder(this.getApplicationContext(), newImageList));

        for (int i = imageList.size() - 1; i >= 0; i--) {
            mGalleryView.addView(new ImageTypeBig(this.getApplicationContext(), mGalleryView, imageList.get(i).getUrl()));
        }
    }
}
