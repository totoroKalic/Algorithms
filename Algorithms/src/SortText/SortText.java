package SortText;
import edu.princeton.cs.algs4.*;
import java.lang.Runtime;
public class SortText {
    public static long time(String alg,Integer[] a) {
        Long start = System.currentTimeMillis();
        if (alg.equals("Insertion")) InsertionSort.sort(a);
        if (alg.equals("MergeDU")) BottomupMergesort.sort(a);
        if (alg.equals("Merge")) TopdownMergesort.sort(a);
        if (alg.equals("Quick")) RandomQuicksort.sort(a);
        if (alg.equals("Quick3")) QuicksortwithDijkstra3wayPartition.sort(a);
        Long end = System.currentTimeMillis();
        return (end - start);
    }
    public static long memory(String alg,Integer[] a){
        Runtime start = Runtime.getRuntime();
        long a1 =start.totalMemory()-start.freeMemory();
        if (alg.equals("Insertion")) InsertionSort.sort(a);
        if (alg.equals("MergeDU")) BottomupMergesort.sort(a);
        if (alg.equals("Merge")) TopdownMergesort.sort(a);
        if (alg.equals("Quick")) RandomQuicksort.sort(a);
        if (alg.equals("Quick3")) QuicksortwithDijkstra3wayPartition.sort(a);
        Runtime end = Runtime.getRuntime();
        long a2 = end.totalMemory()-end.freeMemory();
        return (a2-a1);
    }
    public static long average(long[] a){
        int len = a.length;
        int flag = 0;
        long all_number = 0;
        for(int i=0;i<len;i++){
            if(a[i] < 0){
                flag++;
                continue;
            }
            all_number += a[i];
        }
        return all_number/(len - flag);
    }
    public static void main(String[] args) {
        int[] times = { 100000, 200000, 300000, 400000, 1000000 };
        long time_cost = 0,time_text = 0;
        long save1[][] = new long[5][10];
        long save2[][] = new long[5][10];
        long save3[][] = new long[5][10];
        long save4[][] = new long[5][10];
        long save5[][] = new long[5][10];
        for (int k = 0; k < 1; k++) {
            Integer[] a = new Integer[times[k]];
            for (int j = 0; j < times[k]; j++)
                a[j] = StdRandom.uniform(100);
            StdOut.println("第"+(k+1)+"次运行：");
            StdOut.println("Times:" + times[k]);
            time_cost = 0;
            StdOut.println("Quick sort with Dijkstra 3-way Partition:");
            for (int i = 0; i < 10; i++) {
                StdRandom.shuffle(a);
                Integer[] a2 = a.clone();
                time_text = time("Quick3",a);
                save1[k][i] = memory("Quick3",a2);
                time_cost += time_text;
                StdOut.print("time" + (i + 1) + ":" + time_text + "ms" + "  ");
            }
            StdOut.println("Average:" + (time_cost / 10));
            //
            time_cost = 0;
            StdOut.println("Random Quick:");
            for (int i = 0; i < 10; i++) {
                StdRandom.shuffle(a);
                Integer[] a2 = a.clone();
                time_text = time("Quick",a);
                save2[k][i] = memory("Quick",a2);
                time_cost += time_text;
                StdOut.print("time" + (i + 1) + ":" + time_text + "ms" + "  ");
            }
            StdOut.println("Average:" + (time_cost / 10));
            //
            time_cost = 0;
            StdOut.println("Top-down Merge:");
            for (int i = 0; i < 10; i++) {
                StdRandom.shuffle(a);
                Integer[] a2 = a.clone();
                time_text = time("Merge",a);
                save3[k][i] = memory("Merge",a2);
                time_cost += time_text;

                StdOut.print("time" + (i + 1) + ":" + time_text + "ms" + "  ");
            }
            StdOut.println("Average:" + (time_cost / 10));
            //
            time_cost = 0;
            StdOut.println("Down-Up Merge:");
            for (int i = 0; i < 10; i++) {
                StdRandom.shuffle(a);
                Integer[] a2 = a.clone();
                time_text = time("MergeDU",a);
                save4[k][i] = memory("MergeDU",a2);
                time_cost += time_text;
                StdOut.print("time" + (i + 1) + ":" + time_text + "ms" + "  ");
            }
            StdOut.println("Average:" + (time_cost / 10));
            //
            time_cost = 0;
            StdOut.println("Insertion Sort:");
            for (int i = 0; i < 10; i++) {
                StdRandom.shuffle(a);
                Integer[] a2 = a.clone();
                time_text = time("Insertion",a);
                save5[k][i] = memory("MergeDU",a2);
                time_cost += time_text;
                StdOut.print("time" + (i + 1) + ":" + time_text + "ms" + "  ");
            }
            StdOut.println("Average time:" + (time_cost / 10));

            StdOut.println();
        }
        Runtime start = Runtime.getRuntime();
        long a1 =start.maxMemory();
        StdOut.println(a1);

        for(int k = 0;k<5;k++){
            StdOut.println("第"+(k+1)+"次运行：");
            StdOut.println("Times:" + times[k]);
            StdOut.println("Quick sort with Dijkstra 3-way Partition:");
            for(int i=0;i<10;i++)
                StdOut.print("time" + (i + 1) + ":" + save1[k][i]+ " ");
            StdOut.println("Average Memory:" + average(save1[k]));
            StdOut.println("Random Quick:");
            for(int i=0;i<10;i++)
                StdOut.print("time" + (i + 1) + ":" + save2[k][i]+ "  ");
            StdOut.println("Average Memory:" + average(save2[k]));
            StdOut.println("Top-down Merge:");
            for(int i=0;i<10;i++)
                StdOut.print("time" + (i + 1) + ":" + save3[k][i]+ "  ");
            StdOut.println("Average Memory:" + average(save3[k]));
            StdOut.println("Down-Up Merge:");
            for(int i=0;i<10;i++)
                StdOut.print("time" + (i + 1) + ":" + save4[k][i]+ "  ");
            StdOut.println("Average Memory:" + average(save4[k]));
            //
            StdOut.println("Insert Merge:");
            for(int i=0;i<10;i++)
                StdOut.print("time" + (i + 1) + ":" + save5[k][i]+ "  ");
            StdOut.println("Average Memory:" + average(save5[k]));
        }
    }
}

