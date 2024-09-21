import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for (int i = 0; i < arr.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}

		quadTree(0, 0, n);
		System.out.println(sb);
	}

	private static void quadTree(int x, int y, int size) {
		if (sameColor(x, y, size)) {
			sb.append(arr[x][y]);
		}
		else {
			int halfSize = size / 2;

			sb.append("(");
			quadTree(x, y, halfSize);
			quadTree(x, y + halfSize, halfSize);
			quadTree(x + halfSize, y, halfSize);
			quadTree(x + halfSize, y + halfSize, halfSize);
			sb.append(")");
		}
	}

	private static boolean sameColor(int x, int y, int size) {
		if (size == 1) {
			return true;
		} else {
			int color = arr[x][y];

			for (int i = x; i < x + size; i++) {
				for (int j = y; j < y + size; j++) {
					if (arr[i][j] != color) {
						return false;
					}
				}
			}
			return true;
		}
	}
}