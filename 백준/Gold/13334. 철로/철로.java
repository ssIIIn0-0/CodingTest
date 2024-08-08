import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static class Line implements Comparable<Line>{
        int start;
        int end;
        int length;

        public Line(int start, int end){
            this.start = start;
            this.end = end;
            this.length = end - start;
        }

        @Override
        public int compareTo(Line l) {
            return this.end - l.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Line[] lines = new Line[n];

        StringTokenizer st;

        for(int i=0;i<n;i++){

            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int o  = Integer.parseInt(st.nextToken());
            
            // 집과 오피스의 위치 중 작은 값을 시작점으로 하기
            if(h<o) lines[i] = new Line(h,o);
            else lines[i] = new Line(o,h);

        }
        
        // 철로의 길이
        int d = Integer.parseInt(br.readLine());
        
        // 끝점을 기준으로 오름차순 정렬
        Arrays.sort(lines);

        // 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;

        for(int i=0;i<n;i++){
            // 오름차순대로 Line 하나 가져오기 
            pq.add(lines[i].start); // Line의 시작점을 PriorityQueue에 넣기 
            
            // 철로의 시작점보다 시작점이 앞에 있는 Line 쳐내기
            while(!pq.isEmpty() && pq.peek() < lines[i].end - d) pq.remove();
            
            // PriorityQueue에 남아있는 Line은 철로에 포함되는 Line
            // 최댓값 갱신하기
            ans = Math.max(ans,pq.size());
        }

        // 출력
        System.out.println(ans);


    }
}