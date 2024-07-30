import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[][] d = new int[n + 1][n + 1];
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				if (i == j) d[i][j] = 0;  // 자기 자신은 0으로 초기화
				else d[i][j] = 1000000000;  // 초기화 값을 Integer.MAX_VALUE로 잡으면 overflow로 인해 값이 넘침
			}
		}
		
		for (int i = 0; i < m; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			int c = sc.nextInt();
			
			d[s][e] = Math.min(d[s][e], c);
		}
		
		for (int i = 1; i < n + 1; i++) {  // 플로이드 워셜 알고리즘
			for (int j = 1; j < n + 1; j++) {
				for (int k = 1; k < n + 1; k++) {
					d[j][k] = Math.min(d[j][k], d[j][i] + d[i][k]);
				}
			}
		}
		
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (d[i][j] == 1000000000) System.out.print(0 + " ");  // i에서 j로 가지 못한다면 0출력
				else System.out.print(d[i][j] + " " );
			}
			System.out.println();
		}
	}
}