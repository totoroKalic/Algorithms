package MapRouting;

import edu.princeton.cs.algs4.*;

import edu.princeton.cs.algs4.Point2D;

public class EdgeWeightedDigraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;
    //private ArrayList<Node> nodes;
    private int[] indegree;
    Point2D[] points;
    private  double flag;

    public EdgeWeightedDigraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }

    public EdgeWeightedDigraph(int V, int E) {
        this(V);
        if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = 0.01 * StdRandom.uniform(100);
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        points = new Point2D[V];
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for(int i = 0; i < V; i++) {
            int no = in.readInt();
            int x = in.readInt();
            int y = in.readInt();
            //System.out.println(no+" "+x+" "+y);
            points[i] = new Point2D(x,y);
        }
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            validateVertex(v);
            validateVertex(w);
            //System.out.println(v+" "+w);
            double x = Math.abs(points[v].x() - points[w].x());
            double y = Math.abs(points[v].y() - points[w].y());
            double weight = Math.sqrt(x*x+y*y);
            //Edge e = new Edge(v,w,weight);
            addEdge(new DirectedEdge(v, w, weight));
        }
        StdOut.println("地图读取完毕.");
    }

    public EdgeWeightedDigraph(In in,int end) {
        this(in.readInt());
        int E = in.readInt();
        points = new Point2D[V];
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for(int i = 0; i < V; i++) {
            int no = in.readInt();
            int x = in.readInt();
            int y = in.readInt();
            //System.out.println(no+" "+x+" "+y);
            points[i] = new Point2D(x,y);
        }
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            validateVertex(v);
            validateVertex(w);
            //System.out.println(v+" "+w);
            double x = Math.abs(points[v].x() - points[w].x());
            double y = Math.abs(points[v].y() - points[w].y());
            double weight = Math.sqrt(x*x+y*y);

            double x2 = Math.abs(points[v].x() - points[end].x());
            double y2 = Math.abs(points[v].y() - points[end].y());
            double weight2 = Math.sqrt(x2*x2+y2*y2);

            double x3 = Math.abs(points[w].x() - points[end].x());
            double y3 = Math.abs(points[w].y() - points[end].y());
            double weight3 = Math.sqrt(x3*x3+y3*y3);
            flag = weight3 - weight2 + weight;
            //Edge e = new Edge(v,w,weight);
            addEdge(new DirectedEdge(v, w, weight));
        }
        StdOut.println("地图读取完毕.");
    }
    public EdgeWeightedDigraph(EdgeWeightedDigraph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++)
            this.indegree[v] = G.indegree(v);
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<DirectedEdge> reverse = new Stack<DirectedEdge>();
            for (DirectedEdge e : G.adj[v]) {
                reverse.push(e);
            }
            for (DirectedEdge e : reverse) {
                adj[v].add(e);
            }
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        indegree[w]++;
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
