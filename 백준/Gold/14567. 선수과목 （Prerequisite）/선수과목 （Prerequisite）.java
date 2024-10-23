import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dp;
    static ArrayList<Integer>[] prerequisite;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        prerequisite = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            prerequisite[i] = new ArrayList<>();
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            prerequisite[A].add(B);
        }
        Arrays.fill(dp, 1);
        for (int i = 1; i <= N; i++) {
            if(prerequisite[i].isEmpty())
                continue;
            for (int num : prerequisite[i]) {
                dp[num] = Math.max(dp[num], dp[i] + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dp[i]).append(" ");
        }
        System.out.println(sb);
    }
}