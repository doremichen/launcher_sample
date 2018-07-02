/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: Utils.java
 * Brief: 
 * 
 * Author: AdamChen
 * Create Date: 2018/6/29
 */

package com.adam.app.launchersample.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.adam.app.launchersample.model.AppLoader;

/**
 * <h1>Utils</h1>
 * 
 * @autor AdamChen
 * @since 2018/6/29
 */
public final class Helper {
    
    //Debug tag
    private static final String TAG = "Demo";
    
    // App loader 
    private static AppLoader sLoader;
    
    //Prevent to instance
    private Helper() {}
    
    public static void Info(Object obj, String str) {
        Log.i(TAG, obj.getClass().getSimpleName() + ": " + str);
    }
     
    public static void Info(Class<?> clazz, String str) {
        Log.i(TAG, clazz.getSimpleName() + ": " + str);
    }
    
    /**
     * Set the app loader
     */
    public static void setLoader(AppLoader loader) {
        if (loader == null) {
            throw new RuntimeException("Please input the valid loader");
        }
        
        sLoader = loader;
    }
    
    /**
     * This function is called when the list item is pressed
     */
    public static void cliskListItem(Context context, int position) {
        
        if (sLoader == null) {
            throw new RuntimeException("Please set app loader first by Helper.setLoader");
        }
        
        Intent i = sLoader.getIntentBy(position);
        context.startActivity(i);
    }
    
    /**
     * Release static data
     */
    public static void releaseData() {
        sLoader = null;
    }

}
