package ru.xim;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ClusterFillerTest {

    private static List<Node> createNodes(int nodesCount) {
        List<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < nodesCount; ++i) {
            nodes.add(new Node());
        }
        return nodes;
    }

    @Test
    public void mustFillNamenodesOnFirstIteration() {

        List<Integer> instanceIds_0 = Arrays.asList(5);
        List<Node> namenodes_0 = createNodes(1);
        List<Node> datanodes_0 = createNodes(2);
        new ClusterFiller().fillCluster(instanceIds_0, namenodes_0, datanodes_0);

        assertEquals(Arrays.asList(5), namenodes_0.get(0).deployedIds);
        assertEquals(0, datanodes_0.get(0).deployedIds.size());
        assertEquals(0, datanodes_0.get(1).deployedIds.size());

        // ---

        List<Integer> instanceIds_1 = Arrays.asList(1, 3, 4);
        List<Node> namenodes_1 = createNodes(1);
        List<Node> datanodes_1 = createNodes(2);
        new ClusterFiller().fillCluster(instanceIds_1, namenodes_1, datanodes_1);

        assertEquals(Arrays.asList(1), namenodes_1.get(0).deployedIds);
        assertEquals(Arrays.asList(3), datanodes_1.get(0).deployedIds);
        assertEquals(Arrays.asList(4), datanodes_1.get(1).deployedIds);

        // ---

        List<Integer> instanceIds_2 = Arrays.asList(1, 2, 3, 4, 8);
        List<Node> namenodes_2 = createNodes(2);
        List<Node> datanodes_2 = createNodes(3);
        new ClusterFiller().fillCluster(instanceIds_2, namenodes_2, datanodes_2);

        assertEquals(Arrays.asList(1), namenodes_2.get(0).deployedIds);
        assertEquals(Arrays.asList(2), namenodes_2.get(1).deployedIds);
        assertEquals(Arrays.asList(3), datanodes_2.get(0).deployedIds);
        assertEquals(Arrays.asList(4), datanodes_2.get(1).deployedIds);
        assertEquals(Arrays.asList(8), datanodes_2.get(2).deployedIds);
    }


    @Test
    public void mustFillDatanodesToTheFullest() {
        List<Integer> instanceIds_0 = Arrays.asList(1, 2, 5, 7, 8, 10, 11);
        List<Node> namenodes_0 = createNodes(2);
        List<Node> datanodes_0 = createNodes(3);
        new ClusterFiller().fillCluster(instanceIds_0, namenodes_0, datanodes_0);

        assertEquals(Arrays.asList(1), namenodes_0.get(0).deployedIds);
        assertEquals(Arrays.asList(2), namenodes_0.get(1).deployedIds);
        assertEquals(Arrays.asList(5, 10), datanodes_0.get(0).deployedIds);
        assertEquals(Arrays.asList(7, 11), datanodes_0.get(1).deployedIds);
        assertEquals(Arrays.asList(8), datanodes_0.get(2).deployedIds);

        // ---

        List<Integer> instanceIds_1 = Arrays.asList(3, 4, 6, 7, 8, 10, 11, 15, 16, 90, 110, 120);
        List<Node> namenodes_1 = createNodes(3);
        List<Node> datanodes_1 = createNodes(5);
        new ClusterFiller().fillCluster(instanceIds_1, namenodes_1, datanodes_1);

        assertEquals(Arrays.asList(3), namenodes_1.get(0).deployedIds);
        assertEquals(Arrays.asList(4), namenodes_1.get(1).deployedIds);
        assertEquals(Arrays.asList(6), namenodes_1.get(2).deployedIds);

        assertEquals(Arrays.asList(7, 16), datanodes_1.get(0).deployedIds);
        assertEquals(Arrays.asList(8, 90), datanodes_1.get(1).deployedIds);
        assertEquals(Arrays.asList(10, 110), datanodes_1.get(2).deployedIds);
        assertEquals(Arrays.asList(11, 120), datanodes_1.get(3).deployedIds);
        assertEquals(Arrays.asList(15), datanodes_1.get(4).deployedIds);

        // ---

        List<Integer> instanceIds_2 = Arrays.asList(2, 4, 5, 7, 8, 10, 11, 13, 15);
        List<Node> namenodes_2 = createNodes(2);
        List<Node> datanodes_2 = createNodes(3);
        new ClusterFiller().fillCluster(instanceIds_2, namenodes_2, datanodes_2);

        assertEquals(Arrays.asList(2, 15), namenodes_2.get(0).deployedIds);
        assertEquals(Arrays.asList(4), namenodes_2.get(1).deployedIds);
        assertEquals(Arrays.asList(5, 10), datanodes_2.get(0).deployedIds);
        assertEquals(Arrays.asList(7, 11), datanodes_2.get(1).deployedIds);
        assertEquals(Arrays.asList(8, 13), datanodes_2.get(2).deployedIds);

    }

}