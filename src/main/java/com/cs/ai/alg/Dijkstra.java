package com.cs.ai.alg;

import com.cs.ai.*;

import java.util.*;

/*
 * Created by Zhuorui Cao
 */
public class Dijkstra implements searchAlg {

    private int startVertex;
    private int endVertex;
    private ArrayList<Integer> visited;
    private ArrayList<Integer> parent;
    private int Steps;

    public Dijkstra(Graph graph, int startVertex, int endVertex) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        visited = new ArrayList<>();
        for (int i = 0; i < graph.getSize(); i++) visited.add(0);
        parent = new ArrayList<>();
        for (int i = 0; i < graph.getSize(); i++) parent.add(0);
        Steps = 0;
    }

    public void execute(Graph graph, int size) {
        Vertice.getVertice(startVertex).setCost(0);
        PriorityQueue<Vertice> queue = new PriorityQueue<>(size, new Comparator<Vertice>(){
            @Override
            public int compare(Vertice o1, Vertice o2) {
                return o1.getCost() - o2.getCost();
            }
        });
        queue.add(Vertice.getVertice(startVertex));
        for (;!queue.isEmpty();) {
            Steps++;
            Vertice current = queue.poll();
            visited.remove(current.getNodeID());
            visited.add(current.getNodeID(), 1);
            if (current.getNodeID() == endVertex) return;
            for (Edges edge : graph.getAdj(current.getNodeID())) {
                int next = edge.getEndVertex();
                int weight = edge.getWeight();
                Vertice nextV = Vertice.getVertice(next);
                int newCost = current.getCost() + weight;
                if (nextV.getCost() > current.getCost() + weight && queue.contains(nextV)){
                    nextV.setCost(newCost);
                    parent.remove(next);
                    parent.add(next, current.getNodeID());
                    queue.remove(nextV);
                    queue.add(nextV);
                } else if (!queue.contains(nextV) && visited.get(next) != 1) {
                    nextV.setCost(newCost);
                    parent.remove(next);
                    parent.add(next, current.getNodeID());
                    queue.add(nextV);
                }
            }
        }
    }

    public int getSteps() { return this.Steps;}

    public boolean pathTo(int node) { return visited.get(node) == 1;}

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
