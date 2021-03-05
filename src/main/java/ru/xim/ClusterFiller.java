package ru.xim;

import java.util.List;

public class ClusterFiller {


    public void fillCluster(List<Integer> instanceIds, List<Node> namenodes, List<Node> datanodes) {

        int namenodeCount = namenodes.size();
        int datanodeCount = datanodes.size();
        int allNodesCount = namenodeCount + datanodeCount;

        // in first iteration filling namenode hosts have higher priority
        for (int i = 0; i < instanceIds.size() && i < allNodesCount; ++i) {
            boolean deployOnNamenode = i % allNodesCount < namenodeCount;
            if (deployOnNamenode) {
                namenodes.get(i % namenodeCount).addInstance(instanceIds.get(i));
            } else {
                datanodes.get((i - namenodeCount) % datanodeCount).addInstance(instanceIds.get(i));
            }
        }

        // now fill datanode hosts at first
        for (int i = allNodesCount; i < instanceIds.size(); ++i) {
            boolean deployOnDatanode = i % allNodesCount < datanodeCount;
            int newNodeIndex = (i - allNodesCount) % allNodesCount;
            if (deployOnDatanode) {
                datanodes.get(newNodeIndex % datanodeCount).addInstance(instanceIds.get(i));
            } else {
                namenodes.get((newNodeIndex - datanodeCount) % namenodeCount).addInstance(instanceIds.get(i));
            }
        }


    }

}
