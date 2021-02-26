package com.oinotna.scrapp.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.oinotna.scrapp.actions.*;

import static android.view.accessibility.AccessibilityEvent.TYPES_ALL_MASK;

public class MyAccessibilityService extends AccessibilityService {
    public static boolean running=false;
    private static AccessibilityNodeInfo root;
    public static MyAccessibilityService Instance;
    @Override
    public void onCreate() {
        super.onCreate();
        Instance=this;
        Log.d("MyAccessibilityService", "onCreate");
        refreshRoot();
        //Launch application by package name
        /*PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("com.instagram.android");
        startActivity(intent);*/
    }

    @Override
    public void onAccessibilityEvent(final AccessibilityEvent event){
        if(!running){
           return;
        }
        if (event.getEventType() == TYPES_ALL_MASK) {
            refreshRoot();
        }
    }

    @Override
    public void onInterrupt() {
        running=false;
    }

    private void refreshRoot(){
         root =  getRootInActiveWindow();
    }

    @Override
    protected void onServiceConnected() {
        running = true;
        System.out.println("Service started...");
        super.onServiceConnected();
    }



}
