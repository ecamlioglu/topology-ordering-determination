package com.erdemcamlioglu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class AdjList {
    private int V;

    // Adjacency List
    private ArrayList<ArrayList<Integer>> adj;

    AdjList(int v)
    {
        V = v;
        adj = new ArrayList<ArrayList<Integer> >(v);
        for (int i = 0; i < v; ++i)
            adj.add(new ArrayList<Integer>());
    }
    void addEdge(int v, int w) { adj.get(v).add(w); }

    // sorting detail fonksiyonu
    void AdjSortDetail(int v, boolean visited[],
                             Stack<Integer> stack)
    {
        visited[v] = true; Integer i;
        Iterator<Integer> it = adj.get(v).iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i])
                AdjSortDetail(i, visited, stack);
        }
        //result
        stack.push((v));
    }

    // Sıralama fonksiyonu
    void AdjSort()
    {
        Stack<Integer> stack = new Stack<Integer>();

        // Gezinme işaretlemesi
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                AdjSortDetail(i, visited, stack);

        while (stack.empty() == false)
            System.out.print(stack.pop() + " ");
        System.out.println("");
    }
}
