package com.erdemcamlioglu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CheckTopology {
    public static int t, n, m, a;
    public static Stack<Integer> s;

    public static ArrayList<Integer> tsort;

    public static ArrayList<ArrayList<Integer>> adj;
    public static int[] visited = new int[(int)1e5 + 1];


    static void checkSort(int u) {
        visited[u] = 1;
        for (Integer it : adj.get(u)) {
            if (visited[it] == 0)
                checkSort(it);
        }
        s.push(u);
    }
    static boolean cycleCalc() {
        Map<Integer, Integer> pos = new HashMap<>();
        int ind = 0;
        while (!s.isEmpty()) {
            pos.put(s.peek(), ind);
            tsort.add(s.peek());
            ind += 1;
            s.pop();
        }

        for (int i = 0; i < n; i++) {
            for (Integer it : adj.get(i)) {
                if (pos.get(i) > pos.get(it)) {
                    return true;
                }
            }
        }

        return false;
    }

    static void addEdge(int u, int v) {
        adj.get(u).add(v);
    }
}