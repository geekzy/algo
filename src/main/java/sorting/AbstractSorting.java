package sorting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public abstract class AbstractSorting {

    public abstract void sort(Comparable[] a);

    public boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    public void show(Comparable[] a, long startTime, long endTime) {

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println();
        System.out.println("Execution time: " + (endTime - startTime) + "s");
    }

    public boolean isSorted(Comparable[] a) {

        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) { return false; }
        }

        return true;
    }

    public void execute(String[] args) {
        try {
            String[] a = new BufferedReader(new InputStreamReader(System.in)).readLine().trim().split(" ");

            final long startTime = System.currentTimeMillis();
            sort(a);
            final long endTime = System.currentTimeMillis();

            if (isSorted(a)) {
                show(a, startTime, endTime);
            }
        }
        catch (IllegalArgumentException e) {}
        catch (IOException e) {}
    }
}