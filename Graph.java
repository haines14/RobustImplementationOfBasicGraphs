import java.util.ArrayList;
import java.util.Arrays;


public class Graph {
    /**
     * Node class
     **/

    public class Node {
        //Double distances = (double) Integer.MAX_VALUE;
        int vertex;
        //boolean visitedMe;
        double weight;


        Node(int vertex, double weight) {
            this.vertex = vertex;
            this.weight = weight;




        }



    }


    private ArrayList<ArrayList<Node>> adj;
    public String printingVals = "";


    private int size;   //represents the number of vertices in the graph
    private int edges;  //represents the number of edges in the graph
    boolean isDirected; //true when the graph is directed otherwise false
    public String[] connnectedString;
    boolean visited[];

    //TODO add other instance variables that might be needed

    /***
     * initialize an undirected graph with n vertices and no edges
     * @param vertices
     */
    public void ugraph(int vertices) {
        isDirected = false;
        adj = new ArrayList<ArrayList<Node>>();
        for (int i= 0; i < vertices;i++){
            adj.add(i, new ArrayList<Node>());
            for (int j = 0; j < vertices;j++){
                adj.get(i).add(j,null);
                edges = 0;
            }
        }


    }

    /***
     * initialize a directed graph with n vertices and no edges
     * @param vertices
     */
    public void dgraph(int vertices) {
        isDirected = true;
        adj = new ArrayList<ArrayList<Node>>();
        for (int i= 0; i < vertices;i++){
            adj.add(i, new ArrayList<Node>());
            for (int j = 0; j < vertices;j++){
                adj.get(i).add(j,null);
                edges = 0;

            }
        }
    }

    /***
     * add an edge to a graph that goes from vertex u to vertex v and has weight w;
     * if u or v are not vertices in the graph return false otherwise return true
     *
     * Make sure that you add the edge correctly for both directed and undirected graph
     *
     * @param fromV
     * @param toV
     * @param weight
     */
    public boolean addEdge(int fromV, int toV, double weight) {
        if (fromV < 0 || fromV >= adj.size()){
            return false;
        }
        else if (toV < 0 || toV >= adj.size()){
            return false;
        }
        edges++;
        if (isDirected){
            Node n = new Node(toV,weight);
            adj.get(fromV).set(toV,n);
            return true;
        }
        else{
            Node n = new Node(toV,weight);
            Node n2 = new Node(fromV,weight);
            adj.get(fromV).set(toV,n);
            adj.get(toV).set(fromV,n2);
            return true;
        }

    }

    /***
     * print the number of vertices in the graph
     */
    public int vertexSize() {
        return adj.size();
    }

    /***
     * print the number of edges in the graph
     */
    public int edgeSize() {
        //TODO implement edgeSize
        int checkSize = 0;
        if (isDirected == false){
            for (int i = 0; i < adj.size();i++){
                for (int j = 0; j < adj.get(i).size();j++){
                    if (adj.get(i).get(j) != null){
                        checkSize++;
                    }
                }
            }
            int f = checkSize/2;
            edges = f;
            return f;
        }
        int second = 0;
        for (int i = 0; i < adj.size();i++){
            for (int j = 0; j < adj.get(i).size();j++){
                 if (adj.get(i).get(j) != null){
                     second++;
                 }
            }
        }
        edges = second;
        return second;
    }

    /***
     * print the weight of the edge from vertex u to vertex v
     * if u or v are not vertices in the graph return false otherwise return -1.0
     * @param u
     * @param v
     */
    public double weight(int u, int v) {
        if (u < 0 || u >= adj.size() || v < 0 || v >= adj.size()){
            return -1.0;
        }
        if (adj.get(u).size() == 0){
            return -1.0;
        }
        if (!isDirected) {
            if (adj.get(u).get(v) == null) {
                return -1.0;
            }

            return adj.get(u).get(v).weight;
        }
        else{
            if (adj.get(u).get(v)==null){

                    return -1.0;
                }

            return adj.get(u).get(v).weight;
        }

    }

    /***
     * print a list of vertices that are adjacent to vertex v
     * return a String containing the list of adjacent vertices in ascending order
     * return "none" if there are no adjacent vertices
     * @param v
     */
    public String adjList(int v) {
        String s = "";
        if (adj == null){
            return "none";
        }
        if (v > adj.size()){
            return "none";
        }
        if (v < 0){
            return "none";
        }
        if (adj.get(v).size() == 0){
            return "none";
        }
        for (int i = 0; i < adj.get(v).size();i++){
            if (adj.get(v).get(i) != null){
                s += i + " ";
            }
        }
        if (s == ""){
            return "none";
        }
        return s;
    }

    /***
     * return the adjacency matrix representation of the graph
     * return a string double dimentional array containing the matrix representation
     * Example:
     * X 1.0 X 1.0 X
     * 1.0 X 1.0 1.0 X
     * X 1.0 X X 1.0
     * 1.0 1.0 X X 1.0
     * X X 1.0 1.0 X
     * X represents the vertex combinations where there are no edges
     */
    public String[][] matrix() {
        String[][] s = new String[adj.size()][adj.size()];
        for (int i = 0; i < adj.size();i++){
            for (int j = 0; j < adj.get(i).size();j++){
                if (adj.get(i).get(j) != null){
                    s[i][j] = adj.get(i).get(j).weight + "";
                }
                else{
                    s[i][j] = "X";
                }
            }
        }
        return s;
    }

    /***
     * return the adjacency matrix representation of the transitive closure of the graph
     * Example:
     * 1 1 1 1
     * 1 1 1 1
     * 1 1 1 1
     * X X X 1
     * This will only be tested on directed graphs where edge weights are all one so the
     * matrix should use "1" to signify reachability or "X" otherwise
     */
    public String[][] tclosure() {
        if (adj == null){
            return null;
        }
        double starter[][] = new double[adj.size()][adj.size()];
        for (int i = 0 ; i < adj.size();i++){
            for (int j =0 ; j < adj.size();j++){
                if (adj.get(i).get(j) != null) {
                    starter[i][j] = adj.get(i).get(j).weight;
                }
                else{
                    starter[i][j] = 0.0;
                }
            }
        }
        String s[][] = new String[adj.size()][adj.size()];
        for (int k = 0; k < adj.size();k++){
            for (int i = 0; i < adj.size();i++){
                for (int j = 0; j < adj.size();j++){
                    starter[i][j] = (starter[i][j] != 0.0) || ((starter[i][k] != 0) && (starter[k][j] != 0))?1.0:0.0;
                }


            }
        }
        for (int i = 0; i < adj.size();i++){
            for (int j = 0; j < adj.size();j++){
                if (starter[i][j] == 1.0){
                    s[i][j] = "1";
                }
                else{
                    s[i][j] = "X";
                }
                if (i == j){
                    s[i][j] = "1";
                }
            }
        }

        return s;
    }




    /***
     * print the shortest path from vertex u to vertex v
     * use any of the algorithms taught in the class
     * You need to return the shortest path in the following format
     * "4 6 2 0, 5.0"
     * Here 4 6 2 0 are vertices and 5.0 is the maximum edge weight
     * return "path does not exist" if there is no path"
     * You can assume that only one shortest path will exist for each pair of vertices in a given graph.
     * In this section, no negative weight edges will be used in the test cases,
     * @param u
     * @param v
     */
    public String spath (int u, int v) {
        if (u < 0 || v < 0 || u > adj.size() || v > adj.size()) {
            return "path does not exist";
        }
        double[] minDistance = new double[adj.size()];
        int[] prev = new int[adj.size()];
        for (int i =0 ;i < adj.size();i++){
            if (u != i){
                minDistance[i] = Double.MAX_VALUE;
            }
            prev[i] = -1;
        }
        prev[u] = -1;
        minDistance[u] = 0.0;
        double storedDistance =0.0;
        int previousNodeVertex = -1;
        for (int i = 0; i < adj.size();i++){
            for (int j= 0; j < adj.get(i).size();j++){
                if (adj.get(i).get(j) != null){
                    if ((minDistance[i] + adj.get(i).get(j).weight) < minDistance[j]){
                        minDistance[j] = minDistance[i]+adj.get(i).get(j).weight;
                        prev[j] = i;
                    }
                }
            }
        }
        //Adjacent time
        for (int i=0; i < adj.size();i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                if (adj.get(i).get(j) != null) {
                    if (minDistance[i] + adj.get(i).get(j).weight < minDistance[j]) {
                        minDistance[j] = minDistance[i] + adj.get(i).get(j).weight;
                        prev[j] = i;
                    }
                }
            }
        }
        for (int i =0 ; i < adj.size();i++){
            for (int j =0; j < adj.get(i).size();j++){
                if (adj.get(i).get(j) != null){
                    if (minDistance[i] + adj.get(i).get(j).weight < minDistance[j]){
                        minDistance[j] = minDistance[i] + adj.get(i).get(j).weight;
                        prev[j] =i;
                    }
                }
            }
        }
        for (int i =0 ; i < adj.size();i++){
            for (int j =0; j < adj.get(i).size();j++){
                if (adj.get(i).get(j) != null){
                    if (minDistance[i] + adj.get(i).get(j).weight < minDistance[j]){
                        minDistance[j] = minDistance[i] + adj.get(i).get(j).weight;
                        prev[j] =i;
                    }
                }
            }
        }
        for (int i =0 ; i < adj.size();i++){
            for (int j =0; j < adj.get(i).size();j++){
                if (adj.get(i).get(j) != null){
                    if (minDistance[i] + adj.get(i).get(j).weight < minDistance[j]){
                        minDistance[j] = minDistance[i] + adj.get(i).get(j).weight;
                        prev[j] =i;
                    }
                }
            }
        }
        for (int i =0 ; i < adj.size();i++){
            for (int j =0; j < adj.get(i).size();j++){
                if (adj.get(i).get(j) != null){
                    if (minDistance[i] + adj.get(i).get(j).weight < minDistance[j]){
                        minDistance[j] = minDistance[i] + adj.get(i).get(j).weight;
                        prev[j] =i;
                    }
                }
            }
        }
        for (int i =0 ; i < adj.size();i++){
            for (int j =0; j < adj.get(i).size();j++){
                if (adj.get(i).get(j) != null){
                    if (minDistance[i] + adj.get(i).get(j).weight < minDistance[j]){
                        minDistance[j] = minDistance[i] + adj.get(i).get(j).weight;
                        prev[j] =i;
                    }
                }
            }
        }
        String start = "";
        printingVals = "";
        for (int i =0 ; i < adj.size();i++) {
            if (adj.get(i) != null) {
                if (i == v) {
                    start += printPath(prev, v);
                }
            }
        }
        //start = start.substring(0,start.length()-1);
        start+=", ";
        start += minDistance[v];
        if (minDistance[v] == Double.MAX_VALUE){
            return "path does not exist";
        }
        return start;


    }
    public String printPath(int parent[], int vertex){
        if (vertex < 0){
            return "";
        }

        printPath(parent,parent[vertex]);
        printingVals+= vertex + " ";
        return printingVals;


    }


    /***
     * return any topological sorting of the graph
     * You need to return a string in the following format
     * "9 6 7 4 3 5 2 1 8 0"
     * Where each number is a vertex
     */
    public String tsort(){
        boolean[] visit = new boolean[adj.size()];
        for (int i =0; i< adj.size();i++){
            visit[i] = false;
        }
        int[] depart = new int[2*adj.size()];
        for (int i = 0; i < 2*adj.size();i++){
            depart[i] = -1;
        }
        int time =0 ;
        for (int i=0; i< adj.size();i++){
            if (!visit[i]){
                time = DFS(i,visit,depart,time);
            }
        }
        String sorted = "";
        for (int i = 2 * adj.size()-1; i >= 0;i--){
            if (depart[i] != -1){
                sorted+= depart[i] + " ";
                System.out.println(depart[i] + " ");
            }
        }
        return sorted;
    }
    public int DFS(int i, boolean[] visit, int[] depart, int t){
        visit[i] = true;
        t++;
        for (int ti =0; ti < adj.get(i).size();ti++) {
            if (adj.get(i).get(ti) != null) {
                if (!visit[ti]) {
                   t = DFS(ti,visit,depart,t);
                }
            }
        }
        depart[t] = i;
        t++;
        return t;
    }


    /***
     * return true if the graph is strongly connected
     * otherwise false
     */
    public void dfs(int start, boolean[] visited){
        visited[start]= true;
        connnectedString[start] += start+ " ";
        for (int i =0 ; i< adj.get(start).size();i++){
            if (adj.get(start).get(i) != null){
                if (!visited[i]){
                    dfs(i,visited);
                }
            }
        }
    }
    public void dfs(int start, boolean[] visited, int lastIndex){
        visited[start]= true;
        connnectedString[lastIndex] += start+ " ";
        for (int i =0 ; i< adj.get(start).size();i++){
            if (adj.get(start).get(i) != null){
                if (!visited[i]){
                    dfs(i,visited,lastIndex);
                }
            }
        }
    }
    public boolean sconn() {
        connnectedString = new String[adj.size()]; //Just for other test case '(:-/)
        for (int i = 0; i < adj.size(); i++) {
            boolean[] visited = new boolean[adj.size()];
            dfs(i,visited);
            for (int s =0; s < visited.length;s++){
                if(visited[s] == false){
                    return false;
                }
            }
        }
        return true;
    }

    /***
     * return the connected components of the graph
     * Each connected component is stored in a string separated by spaces
     * Return an array of strings in a sorted ordere
     */
    public String[] components() {
       connnectedString = new String[adj.size()];
       for (int i =0 ; i < connnectedString.length;i++){
           connnectedString[i] = "";
       }
        boolean[] visited = new boolean[adj.size()];
       int counter = 0;
       for (int i =0; i< adj.size();i++){
           if (!visited[i]){
              // connnectedString[i] += i +" ";
               dfs(i,visited,counter);
               counter++;
           }

       }
       int cout = 0;
       for (int i =0; i < connnectedString.length;i++){
           if (connnectedString[i].equalsIgnoreCase("")){
               String[] vals = new String[cout];
               for (int j =0;j < cout;j++){
                   vals[j] = connnectedString[j];
               }
              //System.out.println("HERE");
               return vals;
           }
           cout++;
       }
       Arrays.sort(connnectedString);
        return connnectedString;
    }

    /***
     * return true if the graph is a simple graph
     * otherwise false
     * @return
     */
    public boolean simple() {
        if (!isDirected) {
            boolean visit[] = new boolean[adj.size()];
            for (int i=0;i<adj.size();i++){
                visit[i] = false;

            }
            for (int i =0; i < adj.size();i++){
                if (visit[i] == false){
                   if (isCyclicUnd(i,visit,-1)){
                       return false;
                   }
                }
            }
            for (int i =0;i < adj.size();i++){
                int c = 0;
                for (int j =0; j < adj.get(i).size();j++){
                    if (adj.get(i).get(j) != null){
                        for (int ck =0; ck < adj.get(j).size();ck++){
                            if (adj.get(j).get(ck) != null){
                                if (ck != i){
                                    for (int recursion=0;recursion < adj.get(ck).size();recursion++){
                                        if (adj.get(ck).get(recursion)!= null){
                                            if (recursion == i){
                                                return false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
            return true;
        }
        boolean [] visited = new boolean[adj.size()];
        boolean [] isCycleStack = new boolean[adj.size()];
        for (int i =0; i < adj.size();i++){
            if (isCyclic(i,visited,isCycleStack)){
                return false;
            }
        }
        return true;
    }
    public boolean isCyclicUnd(int v, boolean[] visit, int parent){
        visit[v] = true;
        int i;
        for (i=0;i< adj.get(v).size();i++) {
            if (adj.get(v).get(i) != null) {
                int k = adj.get(v).get(i).vertex;
                if (k != parent){
                    if (visit[k]){
                        return true; //Cycle found
                    }
                    else{
                        //Not visited
                        if (isCyclicUnd(k,visit,v)){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
    public boolean isCyclic(int i, boolean[] visited, boolean[] stacks){
        if (stacks[i]){
            return true;
        }
        if (visited[i]){
            return false;
        }
        visited[i] = true;
        stacks[i] = true;
        ArrayList<Node> arr = adj.get(i);
        for (int child = 0; child < arr.size();child++) {
            if (isCyclic(child, visited, stacks)) {
                return true;
            }
        }
        stacks[i] = false;
        return false;
    }
}

