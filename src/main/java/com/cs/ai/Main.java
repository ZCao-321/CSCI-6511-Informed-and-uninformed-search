package com.cs.ai;

import com.cs.ai.alg.*;

import java.io.*;
import java.util.*;

/*
 * Created by Zhuorui Cao
 */
public class Main {

    private static Graph mainGraph;
    private static searchAlg algorithm;
    private static int startVertex;
    private static int endVertex;
    private static boolean isRandom;

    public static void main(String[] args) throws IOException {

        Initialization(args);

        File fileV = new File(args[0]);
        File fileE = new File(args[1]);
        BufferedReader checkNum = new BufferedReader(new InputStreamReader(new FileInputStream(fileV)));
        BufferedReader Vin = new BufferedReader(new InputStreamReader(new FileInputStream(fileV)));
        BufferedReader Ein = new BufferedReader(new InputStreamReader(new FileInputStream(fileE)));

        int graphSize = getVNum(checkNum);
        createGraph(Vin, Ein, graphSize);
        setV(args, graphSize);

        setAlgorithm(new Dijkstra(mainGraph, startVertex, endVertex));
        runAlg(graphSize, algorithm, endVertex);
        setAlgorithm(new aStar(mainGraph, startVertex, endVertex));
        runAlg(graphSize, algorithm, endVertex);

        Vin.close();
        Ein.close();
    }

    private static void Initialization(String[] args) {
        if (args.length == 2 && !args[0].equals(args[1])) isRandom = true;
        else if (args.length == 4 && !args[0].equals(args[1]) && !args[2].equals(args[3])) {
            isRandom = false;
        } else {
            inputAlert();
            System.exit(0);
        }
    }

    private static void setV(String[] args, int graphSize) {
        System.out.println();
        if (isRandom) {
            while (startVertex == endVertex) {
                startVertex = (int)(Math.random()*graphSize);
                endVertex = (int)(Math.random()*graphSize);
            }
            System.out.println("Enter source point: " + startVertex);
            System.out.println("Enter destination point: " + endVertex);
        } else {
            startVertex = Integer.valueOf(args[2]).intValue();
            endVertex = Integer.valueOf(args[3]).intValue();
            System.out.println("Enter source point: " + startVertex);
            System.out.println("Enter destination point: " + endVertex);
        }
    }

    private static int getVNum(BufferedReader Vin) throws IOException {
        int count = 0;
        for (String readline = Vin.readLine(); readline != null; readline = Vin.readLine()) {
            if (readline.startsWith("#")) continue;
            count++;
        }
        return count;
    }

    private static void createGraph(BufferedReader Vin, BufferedReader Ein, int graphSize) throws IOException {
        mainGraph = new Graph(graphSize);
        String Vread = Vin.readLine();
        while (Vread != null) {
            if (!Vread.startsWith("#")) {
                String[] words = Vread.split(",");
                int vID = Integer.valueOf(words[0]).intValue();
                int vLO = Integer.valueOf(words[1]).intValue();
                Vertice.addNode(vID, vLO % 10, vLO / 10);
            }
            Vread = Vin.readLine();
        }
        String Eread = Ein.readLine();
        while (Eread != null) {
            if (!Eread.startsWith("#")) {
                String[] words = Eread.split(",");
                int eStart = Integer.valueOf(words[0]).intValue();
                int eEnd = Integer.valueOf(words[1]).intValue();
                int eWeight = Integer.valueOf(words[2]).intValue();
                mainGraph.addEdge(eStart, eEnd, eWeight);
            }
            Eread = Ein.readLine();
        }
    }

    private static void runAlg(int size, searchAlg algorithm, int endVertex) {
        long start = System.currentTimeMillis();
        algorithm.execute(mainGraph, size);
        System.out.println();
        if (algorithm.pathTo(endVertex)) {
            if (algorithm instanceof Dijkstra) {
                System.out.println("Distance to destination(Dijkstra): ");
            } else {
                System.out.println("Distance to destination(A*): ");
            }
            System.out.print("Traversed path: ");
            algorithm.printPath(endVertex, mainGraph);
        } else {
            System.out.println();
            System.out.println("No path to vertex " + endVertex);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time Taken: " + (end - start) + " ms");
        System.out.println("Number of Steps Taken: " + algorithm.getSteps());
    }

    private static void inputAlert() {
        String alert = "Please invoke it as follows:" + System.lineSeparator() +
                "java -jar xxx.jar <vertex file> <edge file> <start vertex> <end vertex>" + System.lineSeparator() +
                "or invoke it without indicating specific vertices:" + System.lineSeparator() +
                "java -jar xxx.jar <vertex file> <edge file>" + System.lineSeparator() +
                "Example:" + System.lineSeparator() +
                "java -jar AI_YourName_P1.jar ～/Desktop/v.txt ~/Desktop/e.txt 1 3" + System.lineSeparator() +
                "Start Vertex must be different from end Vertex.";
        System.out.println(alert);
    }

    private static void setAlgorithm(searchAlg algo) {
        algorithm = algo;
    }
}
