package com.cs.ai;

import java.util.*;

/*
 * Created by Zhuorui Cao
 */
public class Vertice {

    private int nodeID;
    private int X_coordinate;
    private int Y_coordinate;

    private int Cost;
    private double G_function;
    private double F_function;
    private static List<Vertice> vertices = new ArrayList<>();

    private Vertice(int nodeID, int X_coordinate, int Y_coordinate) {
        this.nodeID = nodeID;
        this.X_coordinate = X_coordinate;
        this.Y_coordinate = Y_coordinate;
    }

    public static void addNode(int nodeID, int X_coordinate, int Y_coordinate) {
        vertices.add(new Vertice(nodeID, X_coordinate, Y_coordinate));
    }

    public void setCost(int Cost) {
        this.Cost = Cost;
    }

    public double getF_function() {
        return F_function;
    }

    public void setF_function(double F_function) {
        this.F_function = F_function;
    }

    public double getG_function() {
        return G_function;
    }

    public void setG_function(double G_function) {
        this.G_function = G_function;
    }

    public int getNodeID() {
        return nodeID;
    }

    public int getCost() {
        return Cost;
    }

    public int getX_coordinate() {
        return X_coordinate;
    }

    public int getY_coordinate() {
        return Y_coordinate;
    }

    public static Vertice getVertice(int nodeNum) {
        return vertices.get(nodeNum);
    }
}
