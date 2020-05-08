package adjacencyMatrix;

import java.util.*;

public class adjacencyMatrix {
    private int[][] incomingEdge = new int[5][5];
    private int[][] outgoingEdge = new int[5][5];
    private int[][] dfsMatrix = new int[5][5]; //for DFS
    private Map<String,Integer> ref= new HashMap<String,Integer>();

    public void initialize(){
        ref.put("AU",0);
        ref.put("BE",1);
        ref.put("DK",2);
        ref.put("EG",3);
        ref.put("HK",4);

        for(int[] i: incomingEdge){Arrays.fill(i,0);}
        for(int[] i: outgoingEdge){Arrays.fill(i,0);}
        for(int[] i: dfsMatrix){Arrays.fill(i,0);}

        addEdge("AU","EG",12);
        addEdge("AU","HK",6);
        addEdge("DK","EG",4);
        addEdge("DK","BE",1);
        addEdge("HK","BE",9);
    }

    public void addEdge(String origin, String destination, int weight){
        outgoingEdge[ref.get(origin)][ref.get(destination)] = weight;
        incomingEdge[ref.get(destination)][ref.get(origin)] = weight;
    }

    public void removeEdge(String vertex1, String vertex2){
        int x = ref.get(vertex1);
        int y =ref.get(vertex2);

        if(isIncoming(vertex1,vertex2)){
            incomingEdge[x][y]=0;
            outgoingEdge[y][x]=0;
        }
        else if (isOutgoing(vertex1,vertex2)){
            outgoingEdge[x][y]=0;
            incomingEdge[y][x]=0;
        }
    }

    public void displayIncomingMatrix(){System.out.println(Arrays.deepToString(incomingEdge));}
    public void displayOutgoingMatrix(){System.out.println(Arrays.deepToString(outgoingEdge));}
    public boolean isOutgoing(String origin,String destination){return outgoingEdge[ref.get(origin)][ref.get(destination)] != 0;}
    public boolean isIncoming(String origin,String destination){return incomingEdge[ref.get(origin)][ref.get(destination)] != 0;}

    public String getKeyByValue(Map<String,Integer> map, int index){
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (Objects.equals(index, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public ArrayList<String> adjacentVertices(String vertex){
        int index = ref.get(vertex);
        ArrayList<String> adjacent = new ArrayList<String>();
        for(int i=0; i<5; ++i){
            if(outgoingEdge[index][i] != 0){
                adjacent.add(getKeyByValue(ref,i));
            }
        }
        return adjacent;
    }

    //Function 1: Determine if strongly connected. Will be using DFS
    public void stronglyConnected(){
        boolean stronglyConnect = false;
        do {
            depthFirstSearch();
            stronglyConnect = dfsMatrixChecker();
            if (stronglyConnect)
                System.out.println("\nGraph is Strongly Connected!");
            else {
                System.out.println("\nGraph is NOT Strongly Connected!");
                randomlyGenerateEdge();
            }
        }
        while (!stronglyConnect);
    }

    public void depthFirstSearch(){
        String[] vertices = new String[] {"AU","BE","DK","EG","HK"};
        dfsMatrixClearer();

        for(int x=0;x<5;++x){
            System.out.print("\n\nFor starting vertex " + vertices[x]);
            DFS(vertices[x],x);
        }
        System.out.print("\n" + Arrays.deepToString(dfsMatrix));
    }

    public void DFS(String vertex,int startingVertexArrayPosition){
        int index = ref.get(vertex);
        dfsMatrix[startingVertexArrayPosition][index] = 1;
        ArrayList<String> vertexIsAdjacentTo = adjacentVertices(vertex);
        System.out.print("\n" + vertex + " is adjacent to: " + vertexIsAdjacentTo);
        for(String i:vertexIsAdjacentTo){
            if (dfsMatrix[startingVertexArrayPosition][ref.get(i)]==0)
                DFS(i,startingVertexArrayPosition);
        }
    }

    public void randomlyGenerateEdge(){
        Random random = new Random();
        int source = random.nextInt(5);
        int destination = random.nextInt(5);
        System.out.println("\nSource: " + source);
        System.out.println("Destination: " + destination);

        while(source==destination||outgoingEdge[source][destination]!=0){
             source = random.nextInt(5);
             System.out.println("\nNew source: " + source);
             destination = random.nextInt(5);
            System.out.println("New destination: " + destination);
        }
        outgoingEdge[source][destination]=1;
        System.out.println("An edge is generated between " + getKeyByValue(ref,source) + " and " + getKeyByValue(ref,destination));
    }

    public void dfsMatrixClearer(){
        for (int[] i:dfsMatrix)
            Arrays.fill(i,0);
    }

    public boolean dfsMatrixChecker(){
        for (int[] matrix : dfsMatrix)
            for (int i : matrix) {
                if (i == 0)
                    return false;
            }
        return true;
    }
}
