package com.oinotna.scrapp.core;

public class FindableNode {
    private String _class=null;
    private String text=null;
    private String desc=null;
    private String id=null;
    private String pkg="com.instagram.android";
    //private static String PKGID = "com.instagram.android";
    public FindableNode(){

    }
    public com.example.acessinstabot.FindableNode setClassName(String _class){
        this._class=_class;
        return this;
    }
    public com.example.acessinstabot.FindableNode setId(String id){
        this.id=id;

        return this;
    }
    public com.example.acessinstabot.FindableNode setPkg(String pkg){
        this.pkg=pkg;
        return this;
    }
    public com.example.acessinstabot.FindableNode setText(String text){
        this.text=text;
        return this;
    }
    public com.example.acessinstabot.FindableNode setDesc(String desc){
        this.desc=desc;
        return this;
    }
    public String getClassName(){

        return this._class;
    }
    public  String getId(){

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
