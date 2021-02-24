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
        root=getRootInActiveWindow();
        //PackageManager pm = getPackageManager();
        /*Intent intent = pm.getLaunchIntentForPackage("com.instagram.android");
        startActivity(intent);*/
    }

    public Object getClipboardService(){
        return getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    public void onAccessibilityEvent(final AccessibilityEvent event){
        //
        //if(!com.example.acessinstabot.MainActivity.run) return;
        if(!running){
           return;
        }
        if (event.getEventType() == TYPES_ALL_MASK) {
            root = getRootInActiveWindow();
        }

        event.getEventType();
        Log.v("ciao", "***** onAccessibilityEvent");
        Toast.makeText(getApplicationContext(), "Got event from: " + event.getPackageName(), Toast.LENGTH_LONG).show();
        //root=getRootInActiveWindow();
        Toast.makeText(getApplicationContext(), root.getClassName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInterrupt() {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            disableSelf();
        }*/
    }


    public AccessibilityNodeInfo getRoot(){

        return root =  getRootInActiveWindow();
    }



    @Override
    protected void onServiceConnected() {
        running = true;
        System.out.println("onServiceConnected");
        AccessibilityServiceInfo info = getServiceInfo();
        //info.eventTypes = AccessibilityEvent.TYPE_WINDOWS_CHANGED;
        info.eventTypes=AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        info.flags = AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS;
        info.notificationTimeout = 100;
        info.packageNames = null;//new String[]{"com.facebook.katana"};
        setServiceInfo(info);
        super.onServiceConnected();
    }



}
