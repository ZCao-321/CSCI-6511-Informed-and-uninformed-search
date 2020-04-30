package com.cs.ai;

/*
 * Created by Zhuorui Cao
 */
public class Edges {

    private int startVertex;
    private int endVertex;
    private int weight;

    public Edges(int startVertex, int endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    public int getEndVertex() {
        return endVertex;
    }

    public int getWeight() {
        return weight;
    }
}
