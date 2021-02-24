package com.oinotna.scrapp.core;

import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.List;


public class UIFinder {
    private static String PKGID = "com.facebook.katana";
    public static AccessibilityNodeInfo findByClassWDesc(String _class, String desc, AccessibilityNodeInfo node){

        for(int i=0; i<node.getChildCount(); i++){
            if(node.getChild(i).getClassName().equals(_class) && node.getChild(i).getContentDescription().equals(desc))
                return node.getChild(i);

        }
        return null;
    }
    public static List<AccessibilityNodeInfo> findByClassWDesc(String _class, String desc){
        List<AccessibilityNodeInfo> lst=new ArrayList<>();
        for(int i = 0; i< com.example.acessinstabot.MyAccessibilityService.getRoot().getChildCount(); i++){
            if(com.example.acessinstabot.MyAccessibilityService.getRoot().getChild(i).getClassName().equals(_class) && com.example.acessinstabot.MyAccessibilityService.getRoot().getChild(i).getContentDescription().equals(desc))
                lst.add(com.example.acessinstabot.MyAccessibilityService.getRoot().getChild(i));
        }
        return lst;
    }edc4rv
    public static List<AccessibilityNodeInfo> findByid(String id)
    {
        return com.example.acessinstabot.MyAccessibilityService.getRoot().findAccessibilityNodeInfosByViewId(PKGID+":id/"+id);
    }
    public static AccessibilityNodeInfo findByid(String id,int index)
    {
        return com.example.acessinstabot.MyAccessibilityService.getRoot().findAccessibilityNodeInfosByViewId(PKGID+":id/"+id).get(index);
    }
    public static AccessibilityNodeInfo findByid(AccessibilityNodeInfo node,String id,int index)
    {
        return node.findAccessibilityNodeInfosByViewId(PKGID+":id/"+id).get(index);
    }
    public static List<AccessibilityNodeInfo> findByid(AccessibilityNodeInfo node,String id)
    {
        return node.findAccessibilityNodeInfosByViewId(PKGID+":id/"+id);
    }
    public static AccessibilityNodeInfo findByClass(List<AccessibilityNodeInfo> nodes,String _class,int index)
    {
        ArrayList<AccessibilityNodeInfo> l = new ArrayList<AccessibilityNodeInfo>();
        for(int i=0;i<nodes.size();i++)
        {
            if(nodes.get(i).getClassName().equals(_class))
            {
                l.add(nodes.get(i));
            }
        }
        return l.get(index);
    }
    public static List<AccessibilityNodeInfo> findByClass(List<AccessibilityNodeInfo> nodes,String _class)
    {
        ArrayList<AccessibilityNodeInfo> l = new ArrayList<AccessibilityNodeInfo>();
        for(int i=0;i<nodes.size();i++)
        {
            if(nodes.get(i).getClassName().equals(_class))
            {
                l.add(nodes.get(i));
            }
        }
        return l;
    }
    public static AccessibilityNodeInfo findByText(String testo,int index)
    {
        return com.example.acessinstabot.MyAccessibilityService.getRoot().findAccessibilityNodeInfosByText(testo).get(index);
    }
    public static List<AccessibilityNodeInfo> findByText(String testo)
    {
        return com.example.acessinstabot.MyAccessibilityService.getRoot().findAccessibilityNodeInfosByText(testo);
    }
    public static List<AccessibilityNodeInfo> findByText(AccessibilityNodeInfo node,String testo)
    {
        return node.findAccessibilityNodeInfosByText(testo);
    }
    public static List<AccessibilityNodeInfo> findByText(List<AccessibilityNodeInfo> nodes,String testo)
    {
        ArrayList<AccessibilityNodeInfo> l = new ArrayList<AccessibilityNodeInfo>();
        for(int i=0;i<nodes.size();i++)
        {
            if(nodes.get(i).getText().equals(testo))
            {
                l.add(nodes.get(i));
            }
        }
        return l;
    }

    public static AccessibilityNodeInfo findByText(AccessibilityNodeInfo node,String testo,int index)
    {
        return node.findAccessibilityNodeInfosByText(testo).get(index);
    }
    public static List<AccessibilityNodeInfo> findByClass(String _class)
    {
        ArrayList<AccessibilityNodeInfo> l = new ArrayList<AccessibilityNodeInfo>();
        for(int i = 0; i< com.example.acessinstabot.MyAccessibilityService.getRoot().getChildCount(); i++)
        {
            if(com.example.acessinstabot.MyAccessibilityService.getRoot().getChild(i).getClassName().equals(_class))
            {
                l.add(com.example.acessinstabot.MyAccessibilityService.getRoot().getChild(i));
            }
        }
        return l;
    }
    public static AccessibilityNodeInfo findByClass(String _class,int index)
    {
        ArrayList<AccessibilityNodeInfo> l = new ArrayList<AccessibilityNodeInfo>();
        for(int i = 0; i< com.example.acessinstabot.MyAccessibilityService.getRoot().getChildCount(); i++)
        {
            if(com.example.acessinstabot.MyAccessibilityService.getRoot().getChild(i).getClassName().equals(_class))
            {
                l.add(com.example.acessinstabot.MyAccessibilityService.getRoot().getChild(i));
            }
        }
        return l.get(index);
    }
    public static AccessibilityNodeInfo findByClass(AccessibilityNodeInfo node,String _class,int index)
    {
        ArrayList<AccessibilityNodeInfo> l = new ArrayList<AccessibilityNodeInfo>();
        for(int i=0;i<node.getChildCount();i++)
        {
            if(node.getChild(i).getClassName().equals(_class))
            {
                l.add(node.getChild(i));
            }
        }
        return l.get(index);
    }
    public static List<AccessibilityNodeInfo> findByClass(AccessibilityNodeInfo node,String _class)
    {
        ArrayList<AccessibilityNodeInfo> l = new ArrayList<AccessibilityNodeInfo>();
        for(int i=0;i<node.getChildCount();i++)
        {
            if(node.getChild(i).getClassName().equals(_class))
            {
                l.add(node.getChild(i));
            }
        }
        return l;
    }
    public static List<AccessibilityNodeInfo> findByIdWClass(String _class, String _id)
    {
        return findByClass(findByid(_id),_class);
    }
    public static List<AccessibilityNodeInfo> findByIdWClass(AccessibilityNodeInfo node,String _class, String _id)
    {
        return findByClass(findByid(node,_id),_class);
    }
    public static AccessibilityNodeInfo findByIdWClass(AccessibilityNodeInfo node,String _class, String _id,int index)
    {
        return findByClass(findByid(node,_id),_class).get(index);
    }
    public static AccessibilityNodeInfo findByClassWText(String _class, String testo,int index)
    {
        return findByClass(findByText(com.example.acessinstabot.MyAccessibilityService.getRoot(),testo),_class).get(index);
    }
    public static List<AccessibilityNodeInfo> findByClassWText(String _class, String testo)
    {
        return findByClass(findByText(com.example.acessinstabot.MyAccessibilityService.getRoot(),testo),_class);
    }
    public static AccessibilityNodeInfo findByClassWText(AccessibilityNodeInfo node,String _class, String testo,int index)
    {
        return findByClass(findByText(node,testo),_class).get(index);
    }
    public static List<AccessibilityNodeInfo> findByClassWText(AccessibilityNodeInfo node,String _class, String testo)
    {
        return findByClass(findByText(node,testo),_class);
    }
    public static AccessibilityNodeInfo findByIdWText(AccessibilityNodeInfo node,String id, String testo,int index)
    {
        return findByText(findByid(node,id),testo).get(index);
    }
    public static List<AccessibilityNodeInfo> findByIdWText(AccessibilityNodeInfo node,String id, String testo)
    {
        return findByText(findByid(node,id),testo);
    }
    public static List<AccessibilityNodeInfo> findByIdWText(List<AccessibilityNodeInfo> l,String id, String testo)
    {
        return findByText(findByid(id),testo);
    }



}
