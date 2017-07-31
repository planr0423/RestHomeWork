package com.yshomework.controller;

import com.yshomework.Models.DataList;
import com.yshomework.Models.Node;
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
    private TreeModel treeModel;
    private Formatter formatter;
    @RequestMapping(value = "/Nodes",method = {RequestMethod.GET})
    public String totalXml() throws Exception {
        Node rootNode = treeModel.getRootNode();
        String totalXml = formatter.format(rootNode);
        return totalXml;
    }
    @RequestMapping(value = "/Nodes", method = {RequestMethod.POST})
    public void postData(@RequestBody DataList dataList){
        Node node;
        Node parentNode = treeModel.getRootNode();
        for(String name:dataList.getPath().split("[+]")){
            node = nodeSearcher.findNodeByName(name);
            if(node == null){
                node=nodeFactory.creatNodeByPath(dataList);
                treeModel.addChildNode(node,parentNode);
            }
            parentNode = node;
        }
    }
    @RequestMapping(value = "Nodes/{NodeName}",method = {RequestMethod.GET})
    public String findNodeByName(Node node) throws Exception {
        Node thisNode = nodeSearcher.findNodeByName(node.getName());
        String pathForXml = formatter.format(thisNode);
        return pathForXml;
    }

}

