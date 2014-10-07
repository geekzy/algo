package topcoder;

public class DiagonalDisproportion {

	public int getDisproportion(String[] matrix) {
		int N = matrix[0].length();
		int main = 0;
		int collateral = 0;

		for (int i = 0; i < N; i++) {
			main += Character.getNumericValue(matrix[i].charAt(i));
			collateral += Character.getNumericValue(matrix[i].charAt(N-i-1));
		}

		return main - collateral;
	}
}