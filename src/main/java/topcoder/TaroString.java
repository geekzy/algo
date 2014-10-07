package topcoder;

public class TaroString {

	public String getAnswer(String S) {
		boolean match = S.matches("[^C&&[A-Z]]+C[^A&&[A-Z]]+A[^T&&[A-Z]]+T[A-Z]*");
		return match ? "Possible" : "Impossible";
	}
}