package com.erdemcamlioglu;

import com.sun.source.tree.Scope;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {


    public static void main(String[] args) {
        List<Character> tempList = new ArrayList<>();
        try {
            Scanner reader = new Scanner(new FileInputStream(args[0]));
            System.out.println("Input başarılı dosyanızdaki veriler : ");
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                var temp = data
                    .replaceAll("\\s", "")
                    .split("->");
                for (int i = 0; i < temp.length; i++) {
                    tempList.add(temp[i].charAt(0));
                }
                System.out.println(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if (tempList.size() <= 0 || tempList.size() % 2 != 0) {
            System.out.println("Input dosyası tanınamadı tekrar çalıştırınız!");
            return;
        }

        int max = 0;
        for (int i = 0; i < tempList.size(); i++) {
            var temp = Integer.parseInt(tempList.get(i).toString());
            if (temp > max) {
                max = temp;
            }
        }

        //Check Topology:
        CheckTopology.n = max;  // n = liste genişliği
        CheckTopology.m = tempList.size() / 2; // m = edge sayısı

        CheckTopology.s = new Stack<>();
        CheckTopology.adj = new ArrayList<>();
        CheckTopology.tsort = new ArrayList<>();

        for (int i = 0; i < CheckTopology.n + 1; i++)
            CheckTopology.adj.add(new ArrayList<>());

        for (int i = 0; i < CheckTopology.m; i++) {
            CheckTopology.addEdge(Integer.parseInt(tempList.get(i).toString()),
                    Integer.parseInt(tempList.get(i + 1).toString()));
        }

        for (int i = 0; i < CheckTopology.n; i++) {
            if (CheckTopology.visited[i] == 0) {
                CheckTopology.checkSort(i);
            }
        }

        // dönebilirse
        System.out.println("Topology Sıralanabilir Mi?");
        if (CheckTopology.cycleCalc())
            System.out.println("Evet");
        else {
            System.out.println("Hayır");
            return;
        }
        System.out.println("------------------------------");

        //Algorithm 1: Adjacency List
        AdjList algorithm1 = new AdjList(max + 1);
        for (int i = 0; i < tempList.size(); i++) {
            algorithm1.addEdge(Integer.parseInt(tempList.get(i).toString()),
                    Integer.parseInt(tempList.get(i + 1).toString()));
            i++;
        }
        System.out.println("Adjacency List Algorithm Result");
        algorithm1.AdjSort();
        System.out.println("------------------------------");

        //Algorithm 2: Neighbor List

        Graph g = new Graph();
        List<Node> nodeList = new ArrayList<Node>();
        for (int i = 0; i < max + 1; i++) {
            nodeList.add(new Node(i));
        }
        for (int i = 0; i < tempList.size(); i++) {
            nodeList.get(Integer.parseInt(tempList.get(i).toString()))
                    .addNeighbor(Integer.parseInt(tempList.get(i + 1).toString()));
            i++;
        }
        for (int i = 0; i < nodeList.size(); i++) {
            g.addNode(nodeList.get(i));
        }

        System.out.println("Neighbor List Algorithm Result");
        NeighborList.GeoSort(g);
        System.out.println("------------------------------");

        //Algorithm 3: Adjacency Matrix
        GraphMatrix theGraph = new GraphMatrix();
        for (int i = 0; i < max + 1; i++) {
            theGraph.addVertex(i);
        }
        for (int i = 0; i < tempList.size(); i++) {
            theGraph.addEdge(Integer.parseInt(tempList.get(i).toString()),
                    Integer.parseInt(tempList.get(i + 1).toString()));
            i++;
        }
        System.out.println("Adjacency Matrix Algorithm Result");
        theGraph.topo();
        System.out.println("------------------------------");
    }
}