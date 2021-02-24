package com.oinotna.scrapp.actions;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;

import com.oinotna.scrapp.service.MyAccessibilityService;

import java.lang.reflect.Method;
import java.util.List;

public class UIActions {


    public static Object getObject()
    {
        return Instance.getObject();
    }


    public static void click(AccessibilityNodeInfo node)
    {

        Rect rect = new Rect();
        node.getBoundsInScreen(rect);
        boolean result = MyAccessibilityService.Instance.dispatchGesture(createClick(rect.centerX(), rect.centerY()), null, null);
    }
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
    public static void setText(AccessibilityNodeInfo node, String testo)
    {
        Bundle t=new Bundle();
        t.putCharSequence(AccessibilityNodeInfo
                .ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,testo);
        node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, t);
    }
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
    }
    private static void Swipe(int x1,int y1,int x2,int y2,int time){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
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
    }
    public static void wait(int time)
    {
        try{
            Thread.sleep(time);
        }
        catch (InterruptedException e)
        {

        }
    }

    public static List<AccessibilityNodeInfo> waitObjects(Method method, String message) throws Exception {
        Object[] parameters = new Object[1];
        parameters[0] = message;
        List<AccessibilityNodeInfo> objects=(List<AccessibilityNodeInfo>)method.invoke(getObject(), parameters);
        return objects;
    }

}
