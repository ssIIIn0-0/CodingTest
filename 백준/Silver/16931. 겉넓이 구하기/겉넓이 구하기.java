import java.util.*;
import java.io.*;

public class Main {
    
	static int N, M;
	static int[][] map;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	map = new int[N][M];
    	
    	int sum = 0; // 겉넓이
    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		for(int j=0; j<M; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			if(map[i][j] != 0) {
    				sum++;
    			}
    		}
    	}
    	
    	sum *= 2; // 아래에서 바라본 방향, 위에서 바라본 방향은 그냥 더해주면 됨
    	
    	//동 쪽에서 바라봄
    	for(int i=0; i<N; i++) {
    		for(int j=M-1; j>=1; j--) {
    			int space = map[i][j-1] - map[i][j]; // 바로 뒷 칸의 높이에서 앞 칸의 높이를 빼면 겉넓이가 된다.
    			if(space >= 0) { // 겉넓이가 양수일 때만 총 겉넓이에 더해주면 된다.
    				sum += space;
    			}
    		}
    		sum += map[i][M-1]; // 가장 앞쪽에 있는 면은 그냥 더해주면 된다.
    	}
    	
    	//서 쪽에서 바라봄
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<=M-2; j++) {
    			int space = map[i][j+1] - map[i][j];
    			if(space >= 0) {
    				sum += space;
    			}
    		}
    		sum += map[i][0];
    	}
    	
    	//남 쪽에서 바라봄
    	for(int j=0; j<M; j++) {
    		for(int i=N-1; i>=1; i--) {
    			int space = map[i-1][j] - map[i][j];
    			if(space >= 0) {
    				sum += space;
    			}
    		}
    		sum += map[N-1][j];
    	}
    	
    	//북 쪽에서 바라봄
    	for(int j=0; j<M; j++) {
    		for(int i=0; i<=N-2; i++) {
    			int space = map[i+1][j] - map[i][j];
    			if(space >= 0) {
    				sum += space;
    			}
    		}
    		sum += map[0][j];
    	}
    	
    	System.out.println(sum);
    }
	
}