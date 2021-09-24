
import java.util.ArrayList;
import java.util.Arrays;

class TC_DFC {

    private int vertices;   //number of vertices in the graph
    private ArrayList<Integer>[] adjList; //adjacency list
    private int[][] tc; //transitive closure matrix

    //constructor
    public TC_DFC(int vertices) {
        this.vertices = vertices;
        this.tc = new int[vertices][vertices];

        initAdjList();
    }

    //to create the adjacency list
    @SuppressWarnings("unchecked")
    private void initAdjList() {
        //create the adjacency matrix
        this.adjList = new ArrayList[this.vertices];
        //for every index create new array list
        for (int i=0; i<this.vertices;i++) {
            this.adjList[i] = new ArrayList<>();
        }
    }

    //to add an edge from u to v
    public void addEdge(int u, int v) {
        this.adjList[u].add(v);
    }

    //to find all reachable vertices for s
    private void dfsUtil(int s, int v) {
        if (s==v) {
            //check whether the vertex is in it's array itself
            if (adjList[v].contains(v)) {
                tc[s][v] = 1;   //Mark reachability from s to v as true.
            }
        }else {
                tc[s][v] = 1;
        }

        //iterate through the matrix where the index is v
        for (int adj: adjList[v]) {
            //if there is no edge from s to current vertex
            if (tc[s][adj]==0) {
                dfsUtil(s, adj);
            }
        }
    }

    //function to find transitive closure
    public void transitiveClosure() {
        for (int i=0; i<this.vertices; i++) {
            dfsUtil(i,i);   //call the function
        }

        //to print the reachability matrix
        for (int i=0; i<this.vertices; i++) {
            System.out.println(Arrays.toString(this.tc[i]));
        }
    }


    public static void main(String[] args) {

        //create an object
        TC_DFC g = new TC_DFC(4);

        //adding edges
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,2);
        g.addEdge(2,0);
        g.addEdge(2,3);
        g.addEdge(3,3);

        System.out.println("Transitive closure Matrix is");
        g.transitiveClosure();

    }
}
