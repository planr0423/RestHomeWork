package com.yshomework.controller;

import com.yshomework.Models.DataList;
import com.yshomework.Models.Node;
import com.yshomework.nodeTools.TreeModelImpl;
import com.yshomework.nodeTools.toolesInterface.Formatter;
import com.yshomework.nodeTools.toolesInterface.NodeFactory;
import com.yshomework.nodeTools.toolesInterface.NodeSearcher;
import com.yshomework.nodeTools.toolesInterface.TreeModel;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/api"})
@EnableAutoConfiguration
public class ApiController {

    private NodeFactory nodeFactory;
    private NodeSearcher nodeSearcher;
    private TreeModel treeModel = new TreeModelImpl();
    private Formatter formatter;

    @RequestMapping(value = "/Nodes", method = {RequestMethod.GET})
    public String totalXml() throws Exception {
        Node rootNode = treeModel.getRootNode();
        String totalXml = formatter.format(rootNode);
        return totalXml;
    }

    @RequestMapping(value = "/Nodes", method = {RequestMethod.POST})
    public void postData(@RequestBody DataList dataList) {
        nodeFactory.setTreeModel(treeModel);
        nodeFactory.createNodeByPath(dataList);
    }

    @RequestMapping(value = "Nodes/{NodeName}", method = {RequestMethod.GET})
    public String findNodeByName(Node node) throws Exception {
        Node thisNode = nodeSearcher.findNodeByName(node.getName());
        String pathForXml = formatter.format(thisNode);
        return pathForXml;
    }

}


