package com.yshomework.nodeTools;

import com.yshomework.Models.DataList;
import com.yshomework.Models.Node;
import com.yshomework.nodeTools.toolesInterface.NodeFactory;
import com.yshomework.nodeTools.toolesInterface.NodeSearcher;
import com.yshomework.nodeTools.toolesInterface.TreeModel;

/**
 * Created by lubeeplant on 17-7-31.
 */
public class NodeFactoryImpl implements NodeFactory {
    @Override
    public Node creatNodeByPath(DataList dataList) {
        TreeModel treeModel = new TreeModelImpl();
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

