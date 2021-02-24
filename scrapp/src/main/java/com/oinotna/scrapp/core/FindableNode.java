package com.oinotna.scrapp.core;

import java.util.Optional;

import static java.util.Optional.of;

public class FindableNode {
    private String className=null;
    private String text=null;
    private String desc=null;
    private String id=null;
    private String pkg=null;
    //private static String PKGID = "com.instagram.android";

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

}
