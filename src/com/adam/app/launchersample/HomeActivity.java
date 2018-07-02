/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: HomeActivity.java
 * Brief: 
 * 
 * Author: AdamChen
 * Create Date: 2018/6/29
 */

package com.adam.app.launchersample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void onListApp(View v) {
        Intent it = new Intent(this, AppListActivity.class);
        this.startActivity(it);
    }
   
}
