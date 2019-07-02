package MapRouting;

import java.util.Scanner;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class MapText {
    public static void main(String[] args){
        Point2D[] points;
        In text_two = new In("usa.txt");

        StdDraw.setCanvasSize(900,400);
        StdDraw.setXscale(-2000,13000);
        StdDraw.setYscale(0,5000);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.GRAY);
        int V = text_two.readInt();
        int E = text_two.readInt();
        points = new Point2D[V];

        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for(int i = 0; i < V; i++) {
            int no = text_two.readInt();
            int x = text_two.readInt();
            int y = text_two.readInt();
            points[i] = new Point2D(x,y);
        }
        for (int i = 0; i < E; i++) {
            int v = text_two.readInt();
            int w = text_two.readInt();
            points[v].drawTo(points[w]);
        }
        StdDraw.setPenColor(StdDraw.RED);

        In text_in = new In("usa.txt");
        Scanner in = new Scanner(System.in);
        StdOut.println("请输入源点（输入-1结束查找）：");
        int S_name = in.nextInt();
        if(S_name < 0)
            return;
        StdOut.println("请输入终点（输入-1结束查找）：");
        int E_end = in.nextInt();
        if(E_end<0)
            return;
        //one
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(text_in);
        //two
        //EdgeWeightedDigraph G = new EdgeWeightedDigraph(text_in,E_end);
        while(S_name>0 && E_end>0){
            long startTime = System.currentTimeMillis();
            //ThinkTwo as = new ThinkTwo(G,S_name,E_end);
            ThinkOne as = new ThinkOne(G,S_name,E_end);
            long endTime = System.currentTimeMillis();

            if (as.hasPathTo(E_end)) {
                StdOut.printf("%d to %d (%.2f)  ", S_name, E_end, as.distTo(E_end));
                StdOut.println();
                for (DirectedEdge e : as.pathTo(E_end)) {
                    StdOut.println(e + "   ");
                    points[e.from()].drawTo(points[e.to()]);
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         no path\n", S_name, E_end);
            }
            StdOut.print("time = ");
            StdOut.print(endTime-startTime);
            StdOut.println("ms");

            StdOut.println("请输入源点（输入-1结束查找）：");
            S_name = in.nextInt();
            if(S_name == -1)
                return;
            StdOut.println("请输入终点（输入-1结束查找）：");
            E_end = in.nextInt();
            if(E_end == -1)
                return;
        }
        in.close();
    }
}

