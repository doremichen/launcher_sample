/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: ApplistAdapter.java
 * Brief: 
 * 
 * Author: AdamChen
 * Create Date: 2018/6/29
 */

package com.adam.app.launchersample.model;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adam.app.launchersample.R;
import com.adam.app.launchersample.util.Helper;


/**
 * <h1>ApplistAdapter</h1>
 * @param <E>
 * 
 * @autor AdamChen
 * @since 2018/6/29
 */
public class AppListAdapter extends ArrayAdapter<AppInfo> {

    // serialVersionUID: Field description
    private static final long serialVersionUID = 3948528009709098334L;
    
    private final LayoutInflater mLayoutinflater;

    public AppListAdapter(Context context) {
        super(context, R.layout.list_item);
        Helper.Info(this, "[AppListAdapter] enter");
        
        mLayoutinflater = LayoutInflater.from(context);
    }
    
    public void setAppData(ArrayList<AppInfo> apps) {
        Helper.Info(this, "[setAppData] enter");
        this.clear();
        
        if (apps != null) {
            // add data in list
            addAll(apps);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Helper.Info(this, "[getView] enter");
        AppInfo appModel = this.getItem(position);
        View view = null;
        
        if (convertView == null) {
            view = mLayoutinflater.inflate(R.layout.list_item, parent, false);
        } else {
            view  = convertView;
        }
        
        ImageView icon = (ImageView)view.findViewById(R.id.item_icon);
        TextView label = (TextView)view.findViewById(R.id.item_label);
        TextView name = (TextView)view.findViewById(R.id.item_name);
        
        icon.setImageDrawable(appModel.getIcon());
        label.setText(appModel.getLabel());
        name.setText(appModel.getName());

        
        return view;
    }

    
    

}
