package abb;

public class Fibonacci {
	public static void main(String args[]) {
		Fibonacci app = new Fibonacci();
		long start = System.currentTimeMillis();
		//String result = app.generateFib(45); // 45 sequence in 12316ms
		String result = app.fasterFib(10000); // 10000 sequence in 1721ms
		long end = System.currentTimeMillis();
		System.out.println(result + "\n\t->[" + (end - start) + "ms]");
	}
	
	private String generateFib(int count) {
		String result = "";
		for (int i = 0; i < count; i++) {
			result += "," + fib(i);
		}
		return result.substring(1);
	}
	
	private long fib(long n) {
		if (n <= 1)  return 1;
		return fib(n-1) + fib(n-2);
	}
	
	private String fasterFib(int count) {
		long[] f = new long[count];
		// 1, 1, 2, 3, 5, 8, 13, 21
		long fib = 1;
		String result = "";
		for (int i = 0; i < count; i++) {
			if (i > 1) {
				fib = f[i-1] + f[i-2];
			}
			f[i] = fib;
			result += "," + f[i];
		}
		return result.substring(1);
	}
}