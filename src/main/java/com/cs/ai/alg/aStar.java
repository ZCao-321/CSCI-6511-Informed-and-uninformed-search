package com.cs.ai.alg;

import com.cs.ai.*;

import java.util.*;

/*
 * Created by Zhuorui Cao
 */
public class aStar implements searchAlg {

    private int startVertex;
    private int endVertex;
    private ArrayList<Integer> visited;
    private ArrayList<Integer> parent;
    private int Steps;

    public aStar(Graph graph, int startVertex, int endVertex) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        visited = new ArrayList<>();
        for (int i = 0; i < graph.getSize(); i++) visited.add(0);
        parent = new ArrayList<>();
        for (int i = 0; i < graph.getSize(); i++) parent.add(0);
        Steps = 0;
    }

    public void execute(Graph graph, int size) {
        Vertice.getVertice(startVertex).setF_function(costSoFar(startVertex, endVertex));
        Vertice.getVertice(startVertex).setG_function(0.0);
        PriorityQueue<Vertice> queue = new PriorityQueue<>(size, new Comparator<Vertice>(){
            @Override
            public int compare(Vertice o1, Vertice o2) {
                return (int)(o1.getF_function() - o2.getF_function());
            }
        });
        queue.add(Vertice.getVertice(startVertex));
        for (;!queue.isEmpty();) {
            Steps++;
            Vertice current = queue.poll();
            visited.remove(current.getNodeID());
            visited.add(current.getNodeID(), 1);
            if (current.getNodeID() == endVertex) return;
            for (Edges e : graph.getAdj(current.getNodeID())) {
                int next = e.getEndVertex();
                if (visited.get(next) != 1) {
                    Vertice nextV = Vertice.getVertice(next);
                    double tGValue = current.getG_function() + e.getWeight();
                    double eFScore = tGValue + costSoFar(next, endVertex);
                    if (eFScore < nextV.getF_function() && queue.contains(nextV)) {
                        parent.remove(next);
                        parent.add(next, current.getNodeID());
                        nextV.setF_function(eFScore);
                        nextV.setG_function(tGValue);
                        queue.remove(nextV);
                        queue.add(nextV);
                    } else if (!queue.contains(nextV) && visited.get(next) != 1) {
                        parent.remove(next);
                        parent.add(next, current.getNodeID());
                        nextV.setF_function(eFScore);
                        nextV.setG_function(tGValue);
                        queue.add(nextV);
                    }
                }
            }
        }
    }

    private double costSoFar(int vertex1, int vertex2) {
        Vertice v1 = Vertice.getVertice(vertex1);
        Vertice v2 = Vertice.getVertice(vertex2);
        double x = Math.abs(v1.getX_coordinate() - v2.getX_coordinate());
        double y = Math.abs(v1.getY_coordinate() - v2.getY_coordinate());
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public int getSteps() { return this.Steps;}

    public boolean pathTo(int node) {
        return visited.get(node) == 1;
    }

    public void printPath(int node, Graph graph) {
        StringBuffer str = new StringBuffer();
        int x = node;
        while (x != startVertex) {
            int w = graph.getWeight(parent.get(x), x);
            if (w == -1) {
                System.out.println("Can not find the edge from vertex " + parent.get(x) + " to vertex " + x);
                return;
            }
            str.insert(0, " -> " + x);
            x = parent.get(x);
        }
        str.insert(0, startVertex);
        System.out.println(str);
    }
}
