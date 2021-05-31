package com.erdemcamlioglu;

import java.util.*;

class Node {
    private int id;
    private List<Integer> neighbors;

    public Node(int id) {
        this.id = id;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(int e) {
        this.neighbors.add(e);
    }

    public int getId() {
        return id;
    }

    public List<Integer> getNeighbors() {
        return neighbors;
    }

}

class Graph {
    private List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node e) {
        this.nodes.add(e);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Node getNode(int searchId) {
        for (Node node : this.getNodes()) {
            if (node.getId() == searchId) {
                return node;
            }
        }
        return null;
    }

    public int getSize() {
        return this.nodes.size();
    }
}

class NeighborList {
    public static void GeoSort(Graph g) {
        int V = g.getSize();
        List<Integer> order = new ArrayList<>();

        Map<Integer, Boolean> visited = new HashMap<>();
        for (Node tmp : g.getNodes())
            visited.put(tmp.getId(), false);
        for (Node tmp : g.getNodes()) {
            if (!visited.get(tmp.getId()))
                GeoSortUtil(g, tmp.getId(), visited, order);
        }
        Collections.reverse(order);
        System.out.println(order);
    }

    public static void GeoSortUtil(Graph g, int v, Map<Integer, Boolean> visited, List<Integer> order) {
        visited.replace(v, true);
        Integer i;
        for (Integer neighborId : g.getNode(v).getNeighbors()) {
            if (!visited.get(neighborId))
                GeoSortUtil(g, neighborId, visited, order);
        }
        order.add(v);
    }
}