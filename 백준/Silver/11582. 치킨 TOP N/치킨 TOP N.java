import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] chicken;
    static int N;
    static int[] tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        chicken = new int[N];
        for (int i = 0; i < N; i++) {
            chicken[i] = Integer.parseInt(st.nextToken());
        }
        int k = Integer.parseInt(br.readLine());
        // k명 남은 상태가 몇번째 턴인지 알아보기
        int t = 0;
        while (true) {
            if ((N >> t) == k) {
                break;
            }
            t++;
        }
        for (int n = 2; n < N; n *= 2) {
            if (t == 0) {
                break;
            }
            // 2개를 정렬
            sorting(n);
            t--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : chicken) {
            sb.append(i).append(" ");
        }
        System.out.print(sb);
    }

    private static void sorting(int n) {
        tmp = new int[n];
        for (int i = 0; i < N; i += n) {
            for (int j = i, idx = 0; j < i + n && idx < n; j++, idx++) {
                tmp[idx] = chicken[j];
            }
            Arrays.sort(tmp);
            for (int j = i, idx = 0; j < i + n && idx < n; j++, idx++) {
                chicken[j] = tmp[idx];
            }
        }
    }
}