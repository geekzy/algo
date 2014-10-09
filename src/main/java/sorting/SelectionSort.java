package sorting;

public class SelectionSort extends AbstractSorting {

    public void sort(Comparable[] a) {
        // Sort a[] into incresing order
        int N = a.length;
        for (int i = 0; i < N; i++) {
            // Exchange a[i] with smallest entry in a[i+1...N]
            int min = i; // index of smallest entry
            for (int j = i+1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            // Exchange it
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        new SelectionSort().execute(args);
    }
}