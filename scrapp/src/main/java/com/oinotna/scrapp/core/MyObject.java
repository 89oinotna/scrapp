package com.oinotna.scrapp.core;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.List;

public class MyObject {
    private AccessibilityNodeInfo root;
    private List<AccessibilityNodeInfo> lst;
    private static String PKGID = "com.instagram.android";

    public MyObject(AccessibilityNodeInfo _node){
        root=_node;
        lst=new ArrayList<>();

    }
    public MyObject(){
        root= com.example.acessinstabot.MyAccessibilityService.getRoot();
        lst=new ArrayList<>();

    }


    public com.example.acessinstabot.MyObject byId(String id){
        //TODO: fix
        if(id==null) return this;
        for (int i=0; i<lst.size(); i++){
            if(lst.get(i).getViewIdResourceName()!=null) {
                if (!lst.get(i).getViewIdResourceName().equals(PKGID + ":id/" + id)) {
                    lst.remove(i);
                }
            }
        }
        return this;
    }
    public com.example.acessinstabot.MyObject byId(String id, String pkg){
        //TODO: fix
        if(id==null) return this;
        for (int i=0; i<lst.size(); i++){
            if(!lst.get(i).getViewIdResourceName().equals(pkg+":id/"+id)){
                lst.remove(i);
            }
        }
        return this;
    }
    public com.example.acessinstabot.MyObject byText(String text){
        if(text==null) return this;
        for (int i=0; i<lst.size(); i++){
            if(lst.get(i).getText()!=null) {
                if (!lst.get(i).getText().equals(text)) {
                    lst.remove(i);
                }
            }
        }
        return this;
    }
    public com.example.acessinstabot.MyObject byDesc(String desc){
        if(desc==null) return this;
        for (int i=0; i<lst.size(); i++){
            if(!lst.get(i).getContentDescription().toString().contains(desc)){
                lst.remove(i);
            }
        }
        return this;
    }
    public com.example.acessinstabot.MyObject byClass(String _class){
        if(_class==null) return this;
        for (int i=0; i<lst.size(); i++){
            if(!lst.get(i).getClassName().equals(_class)) {
                lst.remove(i);
            }
        }
        return this;
    }
    public AccessibilityNodeInfo get(int i){
        if(lst.size()>i)
            return lst.get(i);
        return null;
    }
    public com.example.acessinstabot.MyObject findByClass(String _class){
        lst.clear();
       // AccessibilityNodeInfo root =MyAccessibilityService.getRoot();
        for(int i=0; i<root.getChildCount(); i++){
            com.example.acessinstabot.MyObject c=new com.example.acessinstabot.MyObject(root.getChild(i));
            c.findByClass(_class);
            List<AccessibilityNodeInfo> l=c.getList();
            lst.addAll(l);
            if(root.getChild(i)!=null) {
                if (root.getChild(i).getClassName() != null) {
                    if (root.getChild(i).getClassName().equals(_class)) {
                        lst.add(root.getChild(i));
                    }
                }
            }
        }
        return this;
    }
    public com.example.acessinstabot.MyObject findByText(String text){
        lst.clear();
        for(int i=0; i<root.getChildCount(); i++){
            com.example.acessinstabot.MyObject c=new com.example.acessinstabot.MyObject(root.getChild(i));
            c.findByClass(text);
            List<AccessibilityNodeInfo> l=c.getList();
            lst.addAll(l);
            if(root.getChild(i)!=null) {
                if (root.getChild(i).getText() != null) {
                    if (root.getChild(i).getText().equals(text)) {
                        lst.add(root.getChild(i));
                    }
                }
            }
        }
        return this;
    }

    public com.example.acessinstabot.MyObject findById(String id, String pkg){
        lst.clear();

        if(root!=null){
        lst=root.findAccessibilityNodeInfosByViewId(pkg+":id/"+id);}

        return this;
    }

    public com.example.acessinstabot.MyObject findById(String id){
        lst.clear();

        lst=root.findAccessibilityNodeInfosByViewId(PKGID+":id/"+id);

        return this;
    }

    public com.example.acessinstabot.MyObject findByDesc(String desc){
        lst.clear();

        for(int i=0; i<root.getChildCount(); i++){
            com.example.acessinstabot.MyObject c=new com.example.acessinstabot.MyObject(root.getChild(i));
            c.findByDesc(desc);
            List<AccessibilityNodeInfo> l=c.getList();
            lst.addAll(l);
            if(root.getChild(i)!=null) {
                if (root.getChild(i).getContentDescription() != null) {
                    Log.v("ciao",root.getChild(i).getContentDescription().toString());
                    if (root.getChild(i).getContentDescription().toString().contains(desc)) {
                        lst.add(root.getChild(i));
                    }
                }
            }
        }
        return this;
    }
    public List<AccessibilityNodeInfo> getList(){

        return lst;
    }

    public static boolean waitForNode(AccessibilityNodeInfo _root, com.example.acessinstabot.FindableNode n, int timeout){
        com.example.acessinstabot.MyObject o=new com.example.acessinstabot.MyObject(_root);
        Long start = System.currentTimeMillis();
        Long last=System.currentTimeMillis();
        if(n.getPkg()!=null){

        }
        while(o.getList().isEmpty() && (last-start)<timeout){
            if(n.getId()!=null){

                o.findById(n.getId(), n.getPkg()).byText(n.getText()).byDesc(n.getDesc()).byClass(n.getClassName());

            }
            else if(n.getClassName()!=null){

                o.findByClass(n.getClassName()).byText(n.getText()).byDesc(n.getDesc()).byId(n.getId(), n.getPkg());
            }

            else if(n.getText()!=null){
                o.findByText(n.getText()).byDesc(n.getDesc()).byId(n.getId(), n.getPkg()).byClass(n.getClassName());
            }
            else if(n.getDesc()!=null){
                o.findByDesc(n.getDesc()).byText(n.getText()).byId(n.getId(), n.getPkg()).byClass(n.getClassName());
            }
            else{
                return false;
            }
            last=System.currentTimeMillis();

        }
        if(o.getList().isEmpty())
        {
            return false;
        }
        return true;
    }
    public static List<AccessibilityNodeInfo> waitForNode(com.example.acessinstabot.FindableNode n, int timeout){
        com.example.acessinstabot.MyObject o=new com.example.acessinstabot.MyObject(com.example.acessinstabot.MyAccessibilityService.getRoot());
        Long start = System.currentTimeMillis();
        Long last=System.currentTimeMillis();

        while(o.getList().isEmpty()&& (last-start)<timeout){
            if(n.getId()!=null){

                o.findById(n.getId(), n.getPkg()).byText(n.getText()).byDesc(n.getDesc()).byClass(n.getClassName());

            }
            else if(n.getClassName()!=null){

                o.findByClass(n.getClassName()).byText(n.getText()).byDesc(n.getDesc()).byId(n.getId(), n.getPkg());
            }

            else if(n.getText()!=null){
                o.findByText(n.getText()).byDesc(n.getDesc()).byId(n.getId(), n.getPkg()).byClass(n.getClassName());
            }
            else if(n.getDesc()!=null){
                o.findByDesc(n.getDesc()).byText(n.getText()).byId(n.getId(), n.getPkg()).byClass(n.getClassName());
            }
            else{
                return null;
            }
            last=System.currentTimeMillis();

        }
        if(o.getList().isEmpty())
        {
            return null;
        }
        return o.getList();
    }
    public static List<AccessibilityNodeInfo> waitForNode(com.example.acessinstabot.FindableNode n){
        com.example.acessinstabot.MyObject o=new com.example.acessinstabot.MyObject(com.example.acessinstabot.MyAccessibilityService.getRoot());
        Long start = System.currentTimeMillis();
        Long last=System.currentTimeMillis();

        while(o.getList().isEmpty()){
            o=new com.example.acessinstabot.MyObject(com.example.acessinstabot.MyAccessibilityService.getRoot());
            if(n.getId()!=null){

                o.findById(n.getId(), n.getPkg()).byText(n.getText()).byDesc(n.getDesc()).byClass(n.getClassName());

            }
            else if(n.getClassName()!=null){

                o.findByClass(n.getClassName()).byText(n.getText()).byDesc(n.getDesc()).byId(n.getId(), n.getPkg());
            }

            else if(n.getText()!=null){
                o.findByText(n.getText()).byDesc(n.getDesc()).byId(n.getId(), n.getPkg()).byClass(n.getClassName());
            }
            else if(n.getDesc()!=null){
                o.findByDesc(n.getDesc()).byText(n.getText()).byId(n.getId(), n.getPkg()).byClass(n.getClassName());
            }
            else{
                return null;
            }
            last=System.currentTimeMillis();

        }
        if(o.getList().isEmpty())
        {
            return null;
        }
        return o.getList();
    }

    public static List<AccessibilityNodeInfo> getChildren(AccessibilityNodeInfo n){
        List<AccessibilityNodeInfo> lst=new ArrayList<>();
        for (int i=0; i<n.getChildCount(); i++){
            lst.add(n.getChild(i));
        }
        return lst;
    }
    public static List<AccessibilityNodeInfo> getAllChildren(AccessibilityNodeInfo n){
        List<AccessibilityNodeInfo> lst=new ArrayList<>();
        for (int i=0; i<n.getChildCount(); i++){
            if(n.getChild(i)!=null) {
                lst.add(n.getChild(i));
                lst.addAll(com.example.acessinstabot.MyObject.getAllChildren(n.getChild(i)));
            }

        }
        return lst;
    }
}
