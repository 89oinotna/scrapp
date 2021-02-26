package com.oinotna.scrapp.core;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.oinotna.scrapp.service.MyAccessibilityService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Object that rappresent the Node inside the Views-Tree. Each Node may contains different children nodes.
 * This class provide a list of functions to let you search inside the sub-tree radicated in our node.
 */
public class Node {
    private AccessibilityNodeInfo root;
    private List<Node> lst;

    public AccessibilityNodeInfo getRoot(){return root;}

    public Node(AccessibilityNodeInfo node){
        root=node;
        lst=new ArrayList<>();

    }

    /**
     * Method that search in the nodes in the sub-tree radicated in our node
     * that are equals to the specific attributes of the FindableNode parameter.
     * @param fn require the FindableNode to filter the children nodes.
     * @return list of nodes.
     */
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

    public boolean waitForNode(FindableNode fn, long timeout){
        return !waitAndReturnNode(fn,timeout).isEmpty();
    }

    /**
     * Method tha let you wait a specific node to be present on the view-tree.
     * @param fn Require the findableNode to filter the children of the root node.
     * @param timeout The timeout let you wait until the time is ended
     * @return the list of nodes.
     */
    public List<Node> waitAndReturnNode(FindableNode fn, long timeout){
        long start = System.currentTimeMillis();
        long last=System.currentTimeMillis();
        List<Node> lst;
        while((lst=find(fn)).isEmpty() && (last-start)<timeout){
            last=System.currentTimeMillis();
        }
        return lst;
    }

    /**
     * Get the list of the first level of the children nodes.
     * @param n Require the root node to search for the children
     * @return return the first childrens layer.
     */
    public static List<AccessibilityNodeInfo> getChildren(AccessibilityNodeInfo n){
        List<AccessibilityNodeInfo> lst=new ArrayList<>();
        for (int i=0; i<n.getChildCount(); i++){
            lst.add(n.getChild(i));
        }
        return lst;
    }

    /**
     * Get the list of all childrens nodes of the root node.
     * @param n Require the root node to get the children.
     * @return all the childrens of the rooted node.
     */
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
