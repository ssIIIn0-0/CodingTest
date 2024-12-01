import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            long[] dp = new long[102];

            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 1;
            dp[4] = 2;
            dp[5] = 2;
            for (int j = 6; j <= n; j++) {
                dp[j] = dp[j - 1] + dp[j - 5];
            }
            System.out.println(dp[n]);
        }
        // dp[n] = dp[n-1] + dp[n-5]
    }
}