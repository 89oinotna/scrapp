package com.oinotna.scrapp.actions;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;

import com.oinotna.scrapp.core.Node;
import com.oinotna.scrapp.service.MyAccessibilityService;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Class that let you performs action on the gui.
 * Such an action can be performed at any moment regardless of the current
 * application or user location in that application.
 * For example going back, click, swipe, etc
 */
public class UIActions {

    /**
     * Method that let you perform the click action on a specific Node object of the view.
     * @param node require the node to be cliked.
     */
    public static void click(Node node){
        Rect rect = new Rect();
        node.getRoot().getBoundsInScreen(rect);
        boolean result = MyAccessibilityService.Instance.dispatchGesture(createClick(rect.centerX(), rect.centerY()), null, null);
    }

    /**
     * Method that perform the click action on a specific coordinate
     * of the screen.
     * @param x x-axis
     * @param y y-axis
     * @return
     */
    public static GestureDescription createClick(float x, float y) {
        // for a single tap a duration of 1 ms is enough
        final int DURATION = 1;
        Path clickPath = new Path();
        clickPath.moveTo(x, y);
        GestureDescription.StrokeDescription clickStroke =
                new GestureDescription.StrokeDescription(clickPath, 0, DURATION);
        GestureDescription.Builder clickBuilder = new GestureDescription.Builder();
        clickBuilder.addStroke(clickStroke);
        return clickBuilder.build();
    }

    /**
     * Let you perform the swipe action, from a starting coordinate point to a
     * target point in a specific ammount of time t.
     * @param x1 starting x-axis
     * @param y1 starting y-axis
     * @param x2 ending x-axis
     * @param y2 ending y-axis
     * @param time ammount of time for the action
     */
    private static void swipe(int x1,int y1,int x2,int y2,int time){
        GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(x1,y1);
        path.lineTo(x2,y2);
        gestureBuilder.addStroke(new GestureDescription.StrokeDescription(path, 0, time));
        MyAccessibilityService.Instance.dispatchGesture(gestureBuilder.build(), new AccessibilityService.GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
            }

        }, null);
    }

    /**
     * Method that let you, set a specific Text, inside Node.
     * @param node
     * @param text
     */
    public static void setText(Node node, String text){
        Bundle t=new Bundle();
        t.putCharSequence(AccessibilityNodeInfo
                .ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
        node.getRoot().performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, t);
    }

    /**
     * Let you perform the drag action, from a starting coordinate point to a
     * target point in a specific step size.
     * @param startX starting x-axis
     * @param startY starting y-axis
     * @param endX ending x-axis
     * @param endY ending y-axis
     * @param steps step size
     */
    public static void drag(int startX, int startY, int endX, int endY, int steps){}

    public static void openNotification(){
        MyAccessibilityService.Instance.performGlobalAction(MyAccessibilityService.GLOBAL_ACTION_NOTIFICATIONS);
    }

    /**
     *
     */
    public static void openQuickSettings(){
        MyAccessibilityService.Instance.performGlobalAction(MyAccessibilityService.GLOBAL_ACTION_QUICK_SETTINGS);
    }

    public static void pressBack(){
        MyAccessibilityService.Instance.performGlobalAction(MyAccessibilityService.GLOBAL_ACTION_BACK);
    }

    public static void pressHome(){
        MyAccessibilityService.Instance.performGlobalAction(MyAccessibilityService.GLOBAL_ACTION_HOME);
    }

    public static void pressKeycode(){}

    public static void pressMenu(){

    }

    public static void pressRecentApps(){
        MyAccessibilityService.Instance.performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
    }

    public static void pressRecentApps(){}

    public static void pressSearch(){}

    public static void sleep(){
    }

    public static void takeScreenshot(){
        MyAccessibilityService.Instance.performGlobalAction(MyAccessibilityService.GLOBAL_ACTION_TAKE_SCREENSHOT);
    }
    /*
    public static void scrollDown()
    {
        Swipe(500,1000,500,600,500);
    }
    public static void scrollDownFast()
    {
        //UIActions.wait(1000);
        Swipe(500,1300,500,200,10);
    }
    public static void scrollDownMedium()
    {
        UIActions.wait(1000);
        Swipe(500,1300,500,200,20);
        UIActions.wait(1000);
    }
    public static void scrollUp()
    {
        Swipe(500,800,500,1000,10);
    }*/

}
