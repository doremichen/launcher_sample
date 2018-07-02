/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: AppListActivity.java
 * Brief: 
 * 
 * Author: AdamChen
 * Create Date: 2018/6/29
 */

package com.adam.app.launchersample;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.ProgressDialog;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.adam.app.launchersample.model.AppInfo;
import com.adam.app.launchersample.model.AppListAdapter;
import com.adam.app.launchersample.model.AppLoader;
import com.adam.app.launchersample.util.Helper;

/**
 * <h1>AppListActivity</h1>
 * 
 * @autor AdamChen
 * @since 2018/6/29
 */
public class AppListActivity extends ListActivity implements LoaderCallbacks<ArrayList<AppInfo>>{

    // App list adapter
    private AppListAdapter mAdapter;
    
    // App loader
    private AppLoader mLoader;
    
    // Progress bar dialog
    private ProgressDialog mProgressDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Helper.Info(this, "[onCreate] enter");
        this.setContentView(R.layout.activity_list_app);
        
        // Build list view
        mAdapter = new AppListAdapter(this);
        this.setListAdapter(mAdapter);
        
        // Show progress bar
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        
        // Initial loader to load app in list in the background
        this.getLoaderManager().initLoader(0, null, this);
        
        
    }
    
   
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Helper.Info(this, "[onDestroy] enter");
        
        Helper.releaseData();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Helper.Info(this, "[onListItemClick] enter");
        // Launch the app by the specified item
        Helper.cliskListItem(this, position);
    }


    @Override
    public Loader<ArrayList<AppInfo>> onCreateLoader(int id, Bundle args) {
        Helper.Info(this, "[onCreateLoader] enter");
        
        mLoader = new AppLoader(this);
        
        Helper.setLoader(mLoader);
        
        return mLoader;
    }


    @Override
    public void onLoadFinished(Loader<ArrayList<AppInfo>> loader,
            ArrayList<AppInfo> apps) {
        Helper.Info(this, "[onLoadFinished] enter");
        
        // Close the propress bar
        mProgressDialog.dismiss();
        
        this.mAdapter.setAppData(apps);
    }


    @Override
    public void onLoaderReset(Loader<ArrayList<AppInfo>> loader) {
        Helper.Info(this, "[onLoaderReset] enter");
        
        this.mAdapter.setAppData(null);
    }


}
