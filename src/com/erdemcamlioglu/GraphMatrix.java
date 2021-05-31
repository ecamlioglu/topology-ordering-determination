package com.erdemcamlioglu;

//adj matrix
class Vertex {
    public int label;

    public Vertex(int lab) {
        label = lab;
    }
}

public class GraphMatrix {

    private final int MAX_SIZE = 10;
    private Vertex vertexList[];
    private int adjList[][];
    private int nVerts;
    private int sortedArray[];

    public GraphMatrix() {
        vertexList = new Vertex[MAX_SIZE];
        adjList = new int[MAX_SIZE][MAX_SIZE];
        nVerts = 0;
        sortedArray = new int[MAX_SIZE];
    }

    public void addVertex(int lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end) {
        adjList[start][end] = 1;
    }

    public void displayVertex(int v) {
        System.out.println(vertexList[v].label);
    }

    public void topo() {
        int orig_nVerts = nVerts;
        while (nVerts > 0) {
            int currentVertex = matrixKontrol();
            sortedArray[nVerts - 1] = vertexList[currentVertex].label;
            deleteVertex(currentVertex);
        }
        for (int j = 0; j < orig_nVerts; j++) {
            System.out.print(sortedArray[j] + " ");
        }
        System.out.println("");
    }

    public int matrixKontrol() {
        boolean isEdge;
        for (int row = 0; row < nVerts; row++) {
            isEdge = false;
            for (int col = 0; col < nVerts; col++) {
                if (adjList[row][col] > 0) {
                    isEdge = true;
                    break;
                }
            }
            if (!isEdge)
                return row;
        }
        return -1;
    }

    public void deleteVertex(int delVert) {
        if (delVert != nVerts - 1) {
            for (int j = delVert; j < nVerts - 1; j++)
                vertexList[j] = vertexList[j + 1];

            for (int row = delVert; row < nVerts - 1; row++)
                moveRowUp(row, nVerts);

            for (int col = delVert; col < nVerts - 1; col++)
                moveColLeft(col, nVerts - 1);
        }
        nVerts--;
    }

    private void moveRowUp(int row, int length) {
        for (int col = 0; col < length; col++)
            adjList[row][col] = adjList[row + 1][col];
    }

    private void moveColLeft(int col, int length) {
        for (int row = 0; row < length; row++)
            adjList[row][col] = adjList[row][col + 1];

    }
}
