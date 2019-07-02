package Percolation;
import java.util.Scanner;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats{
    private double[] averages;
    private int x_site;
    private int y_site;
    private int time;
    public PercolationStats(int n, int t){
        time = t;
        if(n <= 0&&t <= 0)
            throw new IllegalArgumentException("N and T is wrong");
        averages = new double[t];
        for(int i=0;i<time;i++){
            int flag = 0;
            Percolation demo = new Percolation(n);
            while(demo.percolates()){
                x_site = StdRandom.uniform(n)+1;
                y_site = StdRandom.uniform(n)+1;
                if(!demo.isOpen(x_site,y_site)){
                    demo.open(x_site,y_site);
                    flag++;
                }
            }
            averages[i] = (double)flag / (n*n);
        }
    }
    public double mean(){
        return StdStats.mean(averages);
    }
    public double stddev(){
        return StdStats.stddev(averages);
    }
    public double confidenceLo(){
        return (mean() -1.96*stddev()/Math.sqrt(time));
    }
    public double confidenceHi(){
        return (mean() + 1.96*stddev()/Math.sqrt(time));
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        StdOut.println("please input matrix;");
        int size = in.nextInt();
        StdOut.println("please input time;");
        int time = in.nextInt();
        long start=System.currentTimeMillis();
        PercolationStats text = new PercolationStats(size,time);
        StdOut.println("mean:"+text.mean());
        StdOut.println("stddev:"+text.stddev());
        StdOut.println(text.confidenceLo()+"~"+text.confidenceHi());
        long end=System.currentTimeMillis();
        StdOut.println("time:"+(end-start)+"ms");
        in.close();
    }
}