package com.yshomework.Models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lubeeplant on 17-7-30.
 */
@XmlRootElement (name = "path")
public class Node {
    private String name;
    private Map<String,Node> childNode = new HashMap();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Node> getChildNode() {
        return childNode;
    }

    public void setChildNode(Map<String, Node> childNode) {
        this.childNode = childNode;
    }
}
