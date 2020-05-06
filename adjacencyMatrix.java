package adjacencyMatrix;

import java.util.*;

public class adjacencyMatrix {
    private int[][] incomingEdge = new int[5][5];
    private int[][] outgoingEdge = new int[5][5];
    private Map<String,Integer> ref= new HashMap<String,Integer>();

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

    public void displayIncomingMatrix(){
        System.out.println(Arrays.deepToString(incomingEdge));
    }

    public void displayOutgoingMatrix(){
        System.out.println(Arrays.deepToString(outgoingEdge));
    }

    public boolean isOutgoing(String origin,String destination){
        return outgoingEdge[ref.get(origin)][ref.get(destination)] != 0;
    }

    public boolean isIncoming(String origin,String destination){
        return incomingEdge[ref.get(origin)][ref.get(destination)] != 0;
    }

    public String getKeyByValue(Map<String,Integer> map, int index){
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (Objects.equals(index, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public ArrayList<String> adjacent(String vertex){
        int index = ref.get(vertex);
        ArrayList<String> adjacent = new ArrayList<String>();
        for(int i=0; i<5; ++i){
            if(outgoingEdge[index][i] != 0){
                adjacent.add(getKeyByValue(ref,i));
            }
            if(incomingEdge[index][i] != 0){
                adjacent.add(getKeyByValue(ref,i));
            }
        }
        return adjacent;
    }

    public void initialize(){
        ref.put("AU",0);
        ref.put("BE",1);
        ref.put("DK",2);
        ref.put("EG",3);
        ref.put("HK",4);

        for(int[] i: incomingEdge){Arrays.fill(i,0);}
        for(int[] i: outgoingEdge){Arrays.fill(i,0);}

        addEdge("AU","EG",12);
        addEdge("AU","HK",6);
        addEdge("DK","EG",4);
        addEdge("DK","BE",1);
        addEdge("HK","BE",9);
    }
}
