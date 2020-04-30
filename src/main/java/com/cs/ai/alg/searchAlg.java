package com.cs.ai.alg;

import com.cs.ai.*;
/*
 * Created by Zhuorui Cao
 */
public interface searchAlg {

    void execute(Graph graph, int maxSize);

    boolean pathTo(int vertex);

    void printPath(int vertex, Graph graph);

    int getSteps();
}
