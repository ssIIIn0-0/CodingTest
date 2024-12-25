import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        //인접리스트
        ArrayList<Node>[] grape = new ArrayList[V+1];
        //최단거리배열
        int[] D = new int[V + 1];
        //방문배열
        boolean[] visited = new boolean[V + 1];

        for (int i = 0; i <= V; i++) {
        	//인접리스트 배열 초기화
            grape[i] = new ArrayList<>();
            //인덱스 값 무한대로 초기화
            D[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            grape[u].add(new Node(v, w));
        }

        //다익스트라 -> 우선순위 큐
        PriorityQueue<Node> que = new PriorityQueue<>();
        //시작 정점(출발 노드)의 인덱스 값은 0
        D[K] = 0;
        que.offer(new Node(K, 0));

        while (!que.isEmpty()) {
            Node now = que.poll();
            visited[now.toNode] = true;
            for (Node node : grape[now.toNode]) {
                if (!visited[node.toNode]) {
                    if (D[now.toNode] + node.e < D[node.toNode]) {
                        D[node.toNode] = D[now.toNode] + node.e;
                        que.offer(new Node(node.toNode, D[node.toNode]));
                    }
                }
            }
         }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (D[i] == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
            } else {
                sb.append(D[i]).append("\n");
            }
        }
        System.out.println(sb);
    }

    static class Node implements Comparable<Node>{
        int toNode;
        int e;

        @Override
        public int compareTo(Node o){
            return this.e - o.e;
        }

        public Node(int toNode, int e) {
            this.toNode = toNode;
            this.e = e;
        }
    }
}