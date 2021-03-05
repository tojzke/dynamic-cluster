package ru.xim;

import java.util.ArrayList;
import java.util.List;

public class Node {

    List<Integer> deployedIds = new ArrayList<>();

    public void addInstance(int instanceId) {
        deployedIds.add(instanceId);
    }

    public List<Integer> getDeployedIds() {
        return deployedIds;
    }

    @Override
    public String toString() {
        return "Node{" +
                "deployedIds=" + deployedIds +
                '}';
    }
}
