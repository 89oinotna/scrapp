package com.oinotna.scrapp.core;

import android.view.accessibility.AccessibilityNodeInfo;

import java.util.Optional;

import static java.util.Optional.of;

/**
 * Object that rappresent a Node that can be initialized to be search inside the view.
 */
public class FindableNode {
    private String className=null;
    private String text=null;
    private String desc=null;
    private String id=null;
    private String pkg=null;

    public FindableNode setClassName(String className){
        this.className=className;
        return this;
    }
    public FindableNode setId(String id){
        this.id=id;
        return this;
    }
    public FindableNode setPkg(String pkg){
        this.pkg=pkg;
        return this;
    }
    public FindableNode setText(String text){
        this.text=text;
        return this;
    }
    public FindableNode setDesc(String desc){
        this.desc=desc;
        return this;
    }
    public String getClassName(){
        return this.className;
    }
    public String getId(){
        return this.id;
    }
    public  String getText(){
        return this.text;
    }
    public  String getDesc(){
        return this.desc;
    }
    public String getPkg(){
        return this.pkg;
    }


    /**
     * Compare if the Node has the same attributes of the FindableNode
     * @param n Require the node to be compared
     * @return true if the Node is equals, false otherwise
     */
    public boolean equality(Node n){
        AccessibilityNodeInfo ani=n.getRoot();
        if(className!=null && !className.contentEquals(ani.getClassName())){
            return false;
        }
        if( text!=null && !text.contentEquals(ani.getText())){ return false;}
        if(id!=null && !id.equals(ani.getViewIdResourceName())){return false;}
        if(pkg!=null && !pkg.contentEquals(ani.getPackageName())){return false;}
        if(desc!=null && !desc.contentEquals(ani.getContentDescription())){return false;}
        return true;
    }

}
