package com.yshomework.nodeTools;

import com.yshomework.Models.DataList;
import com.yshomework.Models.Node;
import com.yshomework.nodeTools.toolesInterface.NodeFactory;
import com.yshomework.nodeTools.toolesInterface.NodeSearcher;
import com.yshomework.nodeTools.toolesInterface.TreeModel;

public class NodeFactoryImpl implements NodeFactory {

    private TreeModel treeModel;

    private NodeSearcher nodeSearcher;

    @Override
    public Node createNodeByPath(DataList dataList) {
        Node node = null;
        Node parentNode = treeModel.getRootNode();
        for (String name : dataList.getPath().split("[+]")) {
            node = nodeSearcher.findNodeByName(name);
            if (node == null) {
                node = new Node();
                node.setName(name);
                treeModel.addChildNode(node, parentNode);
            }
            parentNode = node;
        }
        return node;
    }

    @Override
    public void setTreeModel(TreeModel model) {
        treeModel = model;
    }

    @Override
    public void setNodeSearcher(NodeSearcher searcher) {
        nodeSearcher = searcher;
    }
}

