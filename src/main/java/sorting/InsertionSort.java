package sorting;

public class InsertionSort extends AbstractSorting {

    public void sort(Comparable[] a) {
        // Sort a[] into incresing order
        int N = a.length;
        for (int i = 1; i < N; i++) {
            // insert a[i] among a[i-1], a[i-2], ...
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    public static void main(String[] args) {
        new InsertionSort().execute(args);
    }
}