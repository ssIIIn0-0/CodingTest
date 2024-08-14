import java.io.*;

// Bottom-up 방식
public class Main{
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        // 양수
        if(n > 0){
            System.out.println(1);
        }
        
        // 음수
        else if (n < 0){
            n = -n;
            if(n % 2 == 0)
                System.out.println(-1);
            else
                System.out.println(1);
        }
        
        // 0
        else{
            System.out.println(0);
        }
        
        dp = new int[1_000_001];
        dp[1] = 1;
        dp[2] = 1;
        for(int i = 3; i < n+1; i++){
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1_000_000_000;
        }
        
        System.out.println(dp[n]);
    }
}