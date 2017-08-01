package com.yshomework.nodeTools;

import com.yshomework.DatatoxmlApplication;
import com.yshomework.Models.DataList;
import com.yshomework.Models.Node;
import com.yshomework.nodeTools.toolesInterface.NodeSearcher;
import com.yshomework.nodeTools.toolesInterface.TreeModel;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * Created by plant on 17-8-1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DatatoxmlApplication.class)
@WebAppConfiguration
public class NodeFactoryTest {
    private static Node rootNode;
    private NodeSearcher nodeSearcher;
    private TreeModel treeModel;
    @Before
    public void setup(){
        nodeSearcher= EasyMock.createMock(NodeSearcher.class);
        treeModel = EasyMock.createMock(TreeModel.class);
    }
    @Test
    public void NodeFactoryShouldCreateNewNode(){
        DataList path =new DataList();
        path.setPath("A+B");
        EasyMock.expect(treeModel.getRootNode()).andReturn(rootNode).once();
        EasyMock.expect(nodeSearcher.findNodeByName("A")).andReturn(null).once();
        EasyMock.expect(nodeSearcher.findNodeByName("B")).andReturn(null).once();
    }

}
