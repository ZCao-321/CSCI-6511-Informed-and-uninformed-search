package com.cs.ai;

import com.sun.javafx.geom.Edge;

import java.util.*;

/*
 * Created by Zhuorui Cao
 */
public class Graph {

    private final int size;
    private int eSize;
    private HashSet<Edges>[] adj;

    public Graph(int size) {
        this.size = size;
        this.eSize = 0;
        adj = new HashSet[size];
        for (int i = 0; i < size; i++) {
            adj[i] = new HashSet<>();
        }
    }

    public int getSize() {
        return size;
    }

    public int getESize() {
        return eSize;
    }

    public void addEdge(int start, int end, int weight) {
        this.eSize++;
        adj[start].add(new Edges(start, end, weight));
        adj[end].add(new Edges(end, start, weight));
    }

    public Iterable<Edges> getAdj(int node) {
        return adj[node];
    }

    public int getWeight(int start, int end) {
        for (Edges e : getAdj(start)) {
            if (e.getEndVertex() == end) {
                return e.getWeight();
            }
        }
        return 0;
    }
}
