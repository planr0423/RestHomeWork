package com.yshomework.nodeTools;

import com.yshomework.DatatoxmlApplication;
import com.yshomework.Models.DataList;
import com.yshomework.Models.Node;
import com.yshomework.nodeTools.toolesInterface.NodeFactory;
import com.yshomework.nodeTools.toolesInterface.NodeSearcher;
import com.yshomework.nodeTools.toolesInterface.TreeModel;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DatatoxmlApplication.class)
@WebAppConfiguration
public class NodeFactoryTest {
    private NodeSearcher nodeSearcher;
    private NodeFactory subject;

    @Before
    public void setup() {
        nodeSearcher = EasyMock.createMock(NodeSearcher.class);
        subject = new NodeFactoryImpl();
        subject.setNodeSearcher(nodeSearcher);
    }

    @Test
    public void testIfNodeFactoryCanCreateNonExistingNodes() {
        TreeModel treeModel = new TreeModelImpl();
        DataList path = new DataList();
        path.setPath("A+B");
        subject.setTreeModel(treeModel);
        EasyMock.expect(nodeSearcher.findNodeByName("A")).andReturn(null).once();
        EasyMock.expect(nodeSearcher.findNodeByName("B")).andReturn(null).once();
        EasyMock.replay(nodeSearcher);
        subject.createNodeByPath(path);
        EasyMock.verify(nodeSearcher);

        Assert.assertTrue(treeModel.getRootNode().getChildNode().containsKey("A"));
        Assert.assertTrue(treeModel.getRootNode().getChildNode().get("A").getChildNode().containsKey("B"));
    }

    @Test
    public void testIfNodeFactoryCanCreateOneNonExistingNodes() {
        TreeModel treeModel = new TreeModelImpl();
        DataList path = new DataList();
        path.setPath("A+B");
        subject.setTreeModel(treeModel);
        Node nodeA = new Node();
        nodeA.setName("A");
        treeModel.getRootNode().getChildNode().put(nodeA.getName(), nodeA);
        EasyMock.expect(nodeSearcher.findNodeByName("A")).andReturn(nodeA).once();
        EasyMock.expect(nodeSearcher.findNodeByName("B")).andReturn(null).once();
        EasyMock.replay(nodeSearcher);
        subject.createNodeByPath(path);
        EasyMock.verify(nodeSearcher);

        Assert.assertTrue(treeModel.getRootNode().getChildNode().containsKey("A"));
        Assert.assertTrue(treeModel.getRootNode().getChildNode().get("A").getChildNode().containsKey("B"));
    }
}
