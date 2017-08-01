package com.yshomework.nodeTools;

import com.yshomework.Models.DataList;
import com.yshomework.Models.Node;
import com.yshomework.nodeTools.toolesInterface.NodeFactory;
import com.yshomework.nodeTools.toolesInterface.NodeSearcher;
import com.yshomework.nodeTools.toolesInterface.TreeModel;

public class NodeFactoryImpl implements NodeFactory {
    TreeModel treeModel = new TreeModelImpl();
    @Override
    public Node creatNodeByPath(DataList dataList) {
        NodeSearcher nodeSearcher = new NodeSearcherImpl();
        Node node = null;
        Node parentNode = treeModel.getRootNode();
        for (String name : dataList.getPath().split("[+]")) {
            node = nodeSearcher.findNodeByName(name);
            if (node == null) {
                node.setName(name);
                treeModel.addChildNode(node, parentNode);
            }
            parentNode = node;
        }
        return node;
    }
}

