import java.io.*;
import java.util.*;

public class Main {
    static int N = 0;                    // 노드의 개수
    static ArrayList<Integer>[] Node;    // 노드의 부모
    static boolean[] visited;            // 방문 여부
    static int[] parentNode;             // 부모 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        Node = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        parentNode = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            Node[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            Node[node1].add(node2);
            Node[node2].add(node1);
        }

        // 루트 노드를 1로 설정함
        DFS(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(parentNode[i]).append("\n");
        }
        
        System.out.print(sb.toString());
    }

    static void DFS(int num) {
        visited[num] = true;
        for (int next : Node[num]) {
            if (!visited[next]) {
                parentNode[next] = num;
                DFS(next);
            }
        }
    }
}