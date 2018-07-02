/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: AppLoader.java
 * Brief: 
 * 
 * Author: AdamChen
 * Create Date: 2018/6/29
 */

package com.adam.app.launchersample.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.adam.app.launchersample.util.Helper;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

/**
 * <h1>AppLoader</h1>
 *   reference: http://developer.android.com/reference/android/content/AsyncTaskLoader.html
 * @autor AdamChen
 * @since 2018/6/29
 */
public class AppLoader extends AsyncTaskLoader<ArrayList<AppInfo>> {
    
    private PackageManager mManager;

    private ArrayList<AppInfo> mApps;
    
    public AppLoader(Context context) {
        super(context);
        Helper.Info(this, "[AppLoader] enter");
        
        mManager = context.getPackageManager();
    }
    
    /**
     * 
     * <h1>getIntentBy</h1>
     * Retunr intent by the specified position in list
     * @param postion
     * @return
     * @return Intent
     *
     */
    public Intent getIntentBy(int postion) {
        Helper.Info(this, "[getIntentBy] enter");
        Intent it = this.mManager.getLaunchIntentForPackage(mApps.get(postion).getName().toString());
        return it;
    }
    
    
    /**
     * This is where the bulk of our work is done.  This function is
     * called in a background thread and should generate a new set of
     * data to be published by the loader.
     */
    @Override
    public ArrayList<AppInfo> loadInBackground() {
        Helper.Info(this, "[loadInBackground] enter");
//        final Context context = this.getContext();
        
        // Get the number of the installed apps
        List<ApplicationInfo> apps = mManager.getInstalledApplications(0);
        
        if (apps == null) {
            apps = new ArrayList<ApplicationInfo>();
        }
        
        ArrayList<AppInfo> appList = new ArrayList<AppInfo>(apps.size());
        
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
         
        List<ResolveInfo> availableActivities = mManager.queryIntentActivities(i, 0);
        for(ResolveInfo ri:availableActivities){
            AppInfo model = new AppInfo();
            model.setLabel(ri.loadLabel(mManager));
            model.setName(ri.activityInfo.packageName);
            model.setIcon(ri.loadIcon(mManager));
            
            appList.add(model);
        
        }
        
        // Sort list
        Collections.sort(appList, AppInfo.ALPHA_COMPARATOR);
        
        return appList;
    }

    /**
     * Called when there is new data to deliver to the client.  The
     * super class will take care of delivering it; the implementation
     * here just adds a little more logic.
     */
    @Override
    public void deliverResult(ArrayList<AppInfo> apps) {
        Helper.Info(this, "[deliverResult] enter");
        if (isReset()) {
            // An async query came in while the loader is stopped.  We
            // don't need the result.
            if (apps != null) {
                onReleaseResources(apps);
            }
        }
        List<AppInfo> oldApps = mApps;
        mApps = apps;

        if (isStarted()) {
            // If the Loader is currently started, we can immediately
            // deliver its results.
            super.deliverResult(apps);
        }

        // At this point we can release the resources associated with
        // 'oldApps' if needed; now that the new result is delivered we
        // know that it is no longer in use.
        if (oldApps != null) {
            onReleaseResources(oldApps);
        }
    }

    
    
    
    @Override
    protected void onStartLoading() {
        Helper.Info(this, "[onStartLoading] enter");
        if (mApps != null) {
            // If we currently have a result available, deliver it
            // immediately.
            deliverResult(mApps);
        }
        
        if (takeContentChanged() || mApps == null ) {
            // If the data has changed since the last time it was loaded
            // or is not currently available, start a load.
            forceLoad();
        }
    }

    
    @Override
    protected void onStopLoading() {
        Helper.Info(this, "[onStopLoading] enter");
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    
    
    
    @Override
    public void onCanceled(ArrayList<AppInfo> apps) {
        super.onCanceled(apps);
        Helper.Info(this, "[onCanceled] enter");
        
        onReleaseResources(apps);
    }

    
    
    @Override
    protected void onReset() {
        super.onReset();
        Helper.Info(this, "[onReset] enter");
        
        // Ensure the loader is stopped
        onStopLoading();
        
        // At this point we can release the resources associated with 'apps'
        // if needed.
        if (mApps != null) {
            onReleaseResources(mApps);
            mApps = null;
        }

    }

    /**
     * Helper function to take care of releasing resources associated
     * with an actively loaded data set.
     */
    protected void onReleaseResources(List<AppInfo> apps) {
        Helper.Info(this, "[onReleaseResources] enter");
        // For a simple List<> there is nothing to do.  For something
        // like a Cursor, we would close it here.
    }
    
    
}
