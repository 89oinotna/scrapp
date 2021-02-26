package com.oinotna.scrapp.core;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.oinotna.scrapp.service.MyAccessibilityService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Node {
    private AccessibilityNodeInfo root;
    private List<Node> lst;
    //private static String PKGID = "com.instagram.android";

    public AccessibilityNodeInfo getRoot(){return root;}

    public Node(AccessibilityNodeInfo node){
        root=node;
        lst=new ArrayList<>();

    }
    /*public Node(){
        root= MyAccessibilityService.getRoot();
        lst=new ArrayList<>();
    }*/

    /*
    public Node byId(String id){
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
    public Node byId(String id, String pkg){
        //TODO: fix
        if(id==null) return this;
        for (int i=0; i<lst.size(); i++){
            if(!lst.get(i).getViewIdResourceName().equals(pkg+":id/"+id)){
                lst.remove(i);
            }
        }
        return this;
    }
    public Node byText(String text){
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
    public Node byDesc(String desc){
        if(desc==null) return this;
        for (int i=0; i<lst.size(); i++){
            if(!lst.get(i).getContentDescription().toString().contains(desc)){
                lst.remove(i);
            }
        }
        return this;
    }
    public Node byClass(String _class){
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
    }*/

    public List<Node> find(FindableNode fn){
        lst.clear();
        for(int i=0; i<root.getChildCount(); i++){
            if(root.getChild(i)!=null) {
                Node child=new Node(root.getChild(i));
                if (fn.equality(child)) {
                    lst.add(child);
                }
                lst.addAll(child.find(fn));
            }
        }

        return lst;
    }


  /*
    public Node findByClassName(String className){
        ////lst.clear();
        Stream<String> s;
       // s.
       // AccessibilityNodeInfo root =MyAccessibilityService.getRoot();
        for(int i=0; i<root.getChildCount(); i++){
            if(root.getChild(i)!=null) {
                if (root.getChild(i).getClassName() != null) {
                    if (root.getChild(i).getClassName().equals(className)) {
                        lst.add(root.getChild(i));
                    }
                }
            }
            Node child=new Node(root.getChild(i));
            child.findByClassName(className);
            List<AccessibilityNodeInfo> l=child.getList();
            lst.addAll(l);
        }
        return this;
    }
    public Node findByText(String text){
        //lst.clear();
        for(int i=0; i<root.getChildCount(); i++){
            Node c=new Node(root.getChild(i));
            c.findByClassName(text);
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

    public Node findById(String id, String pkg){
        //lst.clear();

        if(root!=null){
         lst=root.findAccessibilityNodeInfosByViewId(pkg+":id/"+id);
        }

        return this;
    }

    public Node findById(String id){
        //lst.clear();

        lst=root.findAccessibilityNodeInfosByViewId(PKGID+":id/"+id);

        return this;
    }

    public Node findByDesc(String desc){
        //lst.clear();

        for(int i=0; i<root.getChildCount(); i++){
            Node c=new Node(root.getChild(i));
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
*/  public boolean waitForNode(FindableNode fn, long timeout){
        return !waitAndReturnNode(fn,timeout).isEmpty();
    }
    public List<Node> waitAndReturnNode(FindableNode fn, long timeout){
        long start = System.currentTimeMillis();
        long last=System.currentTimeMillis();
        List<Node> lst;
        while((lst=find(fn)).isEmpty() && (last-start)<timeout){

            last=System.currentTimeMillis();

        }

        return lst;

    }
    /*
    public static boolean waitForNode(AccessibilityNodeInfo root, FindableNode n, long timeout){
        Node o=new Node(root);
        Long start = System.currentTimeMillis();
        Long last=System.currentTimeMillis();
        if(n.getPkg()!=null){

        }
        while(o.getList().isEmpty() && (last-start)<timeout){
            if(n.getId()!=null){

                o.findById(n.getId(), n.getPkg()).byText(n.getText()).byDesc(n.getDesc()).byClass(n.getClassName());

            }
            else if(n.getClassName()!=null){

                o.findByClassName(n.getClassName()).byText(n.getText()).byDesc(n.getDesc()).byId(n.getId(), n.getPkg());
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
    public static List<AccessibilityNodeInfo> waitForNode(FindableNode n, long timeout){
        Node o=new Node(MyAccessibilityService.getRoot());
        Long start = System.currentTimeMillis();
        Long last=System.currentTimeMillis();

        while(o.getList().isEmpty()&& (last-start)<timeout){
            if(n.getId()!=null){

                o.findById(n.getId(), n.getPkg()).byText(n.getText()).byDesc(n.getDesc()).byClass(n.getClassName());

            }
            else if(n.getClassName()!=null){

                o.findByClassName(n.getClassName()).byText(n.getText()).byDesc(n.getDesc()).byId(n.getId(), n.getPkg());
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
    public static List<AccessibilityNodeInfo> waitForNode(FindableNode n){
        Node o=new Node(MyAccessibilityService.getRoot());
        Long start = System.currentTimeMillis();
        Long last=System.currentTimeMillis();

        while(o.getList().isEmpty()){
            o=new Node(MyAccessibilityService.getRoot());
            if(n.getId()!=null){

                o.findById(n.getId(), n.getPkg()).byText(n.getText()).byDesc(n.getDesc()).byClass(n.getClassName());

            }
            else if(n.getClassName()!=null){

                o.findByClassName(n.getClassName()).byText(n.getText()).byDesc(n.getDesc()).byId(n.getId(), n.getPkg());
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
    }*/

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
                lst.addAll(Node.getAllChildren(n.getChild(i)));
            }

        }
        return lst;
    }
}
