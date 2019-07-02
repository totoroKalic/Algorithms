package SortText;

import edu.princeton.cs.algs4.StdOut;
public class RandomQuicksort {
    private static int partition(Integer[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Integer v = a[lo];
        while (true) {
            while (less(a[++i], v))
                if (i == hi)
                    break;
            while (less(v, a[--j]))
                if (j == lo)
                    break;
            if (i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void sort(Integer[] a) {
        //StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Integer[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static boolean less(Integer v, Integer w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Integer[] a, int i, int j) {
        Integer t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Integer[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Integer[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1]))
                return false;
        return true;
    }
}