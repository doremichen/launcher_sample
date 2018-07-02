/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: AppDetailInfo.java
 * Brief: 
 * 
 * Author: AdamChen
 * Create Date: 2018/6/29
 */

package com.adam.app.launchersample.model;

import java.text.Collator;
import java.util.Comparator;

import android.graphics.drawable.Drawable;

/**
 * <h1>AppDetailInfo</h1>
 * 
 * @autor AdamChen
 * @since 2018/6/29
 */
public final class AppInfo {
    
    private CharSequence mLabel;
    private CharSequence mName;
    private Drawable mIcon;
    
    public void setLabel(CharSequence label) {
        this.mLabel = label;
    }
    
    public void setName(CharSequence name) {
        this.mName = name;
    }
    
    public void setIcon(Drawable icon) {
        this.mIcon = icon;
    }
    
    public CharSequence getLabel() {
        return this.mLabel;
    }
    
    public CharSequence getName() {
        return this.mName;
    }
    
    public Drawable getIcon() {
        return this.mIcon;
    }

    @Override
    public String toString() {
        return "[" + this.mLabel + "]: " + this.mName;
    }
    
    /**
     * Perform alphabetical comparison of application entry objects.
     */
    public static final Comparator<AppInfo> ALPHA_COMPARATOR = new Comparator<AppInfo>() {
        private final Collator sCollator = Collator.getInstance();
        @Override
        public int compare(AppInfo object1, AppInfo object2) {
            return sCollator.compare(object1.getLabel(), object2.getLabel());
        }
    };

}
