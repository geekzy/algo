package sorting;

public abstract AbstractSorting {

	public abstract void sort(Compareable[] a);

	public boolean less(Compareable v, Compareable w) {}

	public void exch(Compareable[] a, int i, int j) {}

	public void show(Compareable[] a) {}

	public boolean isSorted(Compareable[] a) {}

	public void execute(String[] args) {
		String[] a = new BufferedReader(new InputStreamReader(System.in)).readline().trim().split(" ");
		sort(a);
		assert isSorted(a);
		show(a);
	}
}