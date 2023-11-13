package com.baidu.hugegraph.api.traverser;

import com.baidu.hugegraph.api.BaseApiTest;
import com.baidu.hugegraph.structure.graph.Edge;
import com.baidu.hugegraph.testutil.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class EdgeExistenceAPITest extends TraverserApiTest{

    @BeforeClass
    public static void prepareSchemaAndGraph() {
        BaseApiTest.initPropertyKey();
        BaseApiTest.initVertexLabel();
        BaseApiTest.initEdgeLabel();
        BaseApiTest.initIndexLabel();
        BaseApiTest.initVertex();
        BaseApiTest.initEdge();
    }

    @Test
    public void testEdgeExistenceGet() {
        Object markoId = getVertexId("person", "name", "marko");
        Object vadasId = getVertexId("person", "name", "vadas");

        List<Edge> edges = edgeExistenceAPI.get(markoId, vadasId,
                                      "knows", "", 100);

        Assert.assertEquals(1, edges.size());
        String id = edges.get(0).id();

        Assert.assertTrue(id.contains("marko"));
        Assert.assertTrue(id.contains("vadas"));

        Object lopId = getVertexId("software", "name", "lop");

        edges = edgeExistenceAPI.get(lopId, vadasId,
                           "knows", "", 100);
        Assert.assertEquals(0, edges.size());

        Object joshId = getVertexId("person", "name", "josh");
        Object rippleId = getVertexId("person", "name", "ripple");

        edges = edgeExistenceAPI.get(joshId, rippleId,
                                    "created", "", 100);
        Assert.assertEquals(1, edges.size());
    }
}
