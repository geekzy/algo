package topcoder;

public class DiagonalDisproportion {

	public int getDisproportion(String[] matrix) {
		int N = matrix.length;
		int main = 0;
		int collateral = 0;

		for (int i = 0, j = N-1; i < N; i++, j--) {
			main += Character.getNumericValue(matrix[i].charAt(i));
			collateral += Character.getNumericValue(matrix[i].charAt(j));
		}

		return main - collateral;
	}
}