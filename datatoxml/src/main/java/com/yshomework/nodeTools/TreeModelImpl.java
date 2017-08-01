package com.yshomework.nodeTools;

import com.yshomework.Models.Node;
import com.yshomework.nodeTools.toolesInterface.TreeModel;

/**
 * Created by lubeeplant on 17-7-31.
 */
public class TreeModelImpl implements TreeModel {

    private Node rootNode = new Node();

    @Override
    public Node getRootNode() {
        return rootNode;
    }

    @Override
    public void addChildNode(Node childNode, Node parentNode) {
        parentNode.getChildNode().put(childNode.getName(), childNode);
    }
}

