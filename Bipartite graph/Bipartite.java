
//  E/17/206

import java.util.Base64;
import java.util.LinkedList;

class Bipartite {

    //number of vertices
    final static int V = 4;

    //check whether the graph is bipartite
    boolean isBipartite(int G[][], int src) {
        //to store the colors of vertices
        int colorArr[] = new int[V];
        //assign no color for every node
        for (int i=0; i<V; ++i) {
            colorArr[i] = -1;
        }
        //assign the first color
        colorArr[src] = 1;

        //create a linked list to store the vertices
        LinkedList<Integer>q = new LinkedList<Integer>();
        //we add the source vertex
        q.add(src);

        //check whether the queue is not empty
        while (q.size() != 0) {

            //returns and removes the element at the front the container
            int u = q.poll();

            //return false if there is a self loop
            if (G[u][u] == 1) {
                return false;
            }

            //find all non colored vertices
            for (int v=0; v<V; ++v) {
                //check whether there is a edge and destination vertex is not colored
                if (G[u][v]==1 && colorArr[v]==-1) {
                    //assign the next color to the vertex
                    colorArr[v] = 1-colorArr[u];
                    //add to the queue
                    q.add(v);
                }
                //if there is an edge and the destination vertex with same color
                else if(G[u][v]==1 && colorArr[v]==colorArr[u]) {
                    return false;
                }
            }
        }
        //if all the vertices colored using 2 colors
        return true;
    }


    public static void main(String[] args) {

        //Adjacency Matrix
        int G[][] = {{0,1,0,1}, {1,0,1,0}, {0,1,0,1}, {1,0,1,0}};
        //create a Bipartite object
        Bipartite b = new Bipartite();

        //check whether the graph is bipartite
        if (b.isBipartite(G,0)) {
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }
    }
}
