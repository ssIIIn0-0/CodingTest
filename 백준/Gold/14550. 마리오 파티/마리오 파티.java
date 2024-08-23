import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true){
            String firstLine = br.readLine();
            StringTokenizer st = new StringTokenizer(firstLine);
            int N = Integer.parseInt(st.nextToken());
            if (N ==0){
                break;
            }
            //주사위 눈 1~S까지
            int S = Integer.parseInt(st.nextToken());
            //T턴 이내에 도착
            int T = Integer.parseInt(st.nextToken());

            int[] arr = new int[N+2];
            arr[0] = 0;

            //입력받기
            int inputCount = 1;
            while (inputCount != N+1){
                st = new StringTokenizer(br.readLine());
                while(st.hasMoreTokens()){
                    int temp = Integer.parseInt( st.nextToken());
                    arr[inputCount] = temp;
                    inputCount++;
                }
            }


            //T번 시도했을때 N칸에서의 최대값
            //0 : 시작, N+1 : 도착
            int[][] dp = new int[T][N+2];
            for(int i = 0; i<T ;i++){
                dp[i][0] = 0;
                for(int j=1; j<N+2; j++){
                    dp[i][j] = Integer.MIN_VALUE;
                }
            }

            if (S >= 0) System.arraycopy(arr, 1, dp[0], 1, S);

            for(int i = 1; i<T; i++){
                for (int j = 1; j<N+2; j++){
                    for(int dice =1 ; dice<=S; dice++){
                        if(j-dice>=0 && dp[i-1][j-dice] != Integer.MIN_VALUE){
                            dp[i][j] = Integer.max(dp[i][j],dp[i-1][j-dice]+arr[j]);
                        }
                    }
                }
            }
            sb.append(dp[T-1][N+1]).append("\n");
        }
        System.out.println(sb);
    }
}