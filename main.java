package adjacencyMatrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class main {
    public static void main(String[] args) {
        adjacencyMatrix obj = new adjacencyMatrix();
        obj.initialize();
        System.out.println(obj.isIncoming("HK","BE"));
        System.out.println(obj.adjacent("HK"));
        obj.displayIncomingMatrix();
    }
}

/*
    there are two matrix:
    1. incoming
    2. outgoing

    each edge is associated with both incoming and outgoing

    problem 1: how to link a vertex to a certain row? (hashmap)

    method to implement:
    1. add edge (parameters:origin, destination, weight)
    2. remove edge (origin, destination)
    3.
 */
