package com.yshomework.nodeTools.toolesInterface;


import com.yshomework.Models.DataList;
import com.yshomework.Models.Node;

public interface TreeModel {
    public Node getRootNode();
    public void addChildNode(Node childNode, Node parentNode);
}
