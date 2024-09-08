import java.io.*;
import java.util.*;
import java.math.*;


public class Main {
	static int map[][];
	static boolean visited[][];
	static int min=Integer.MAX_VALUE;
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();

		int N=Integer.parseInt(br.readLine());

		map=new int[N][N];
		visited=new boolean[N][N];

		for(int i=0;i<map.length;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<map.length;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int number = 2;
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length;j++) {
				if(map[i][j]==1) {
					dfs(i,j,number++);
				}
			}
		}
		
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length;j++) {
				if(map[i][j]>1) {
					bfs(i,j,map[i][j]);
				}
			}
		}
		
		System.out.println(min);




	}
	public static void bfs(int y,int x,int number) {
		Queue<Integer[]> queue=new LinkedList<>();
		visited=new boolean[map.length][map.length];
		visited[y][x] = true;
		queue.add(new Integer[] {y,x,0});

		while(!queue.isEmpty()) {
			Integer temp[] = queue.poll();
			int nowY=temp[0];
			int nowX=temp[1];
			int count=temp[2];
			
			if(map[nowY][nowX]!=0&&map[nowY][nowX]!=number) 
				min=Math.min(min,count-1);
			if(count>min)
				return;

			for(int i=0;i<4;i++) {

				int nextY=nowY+dy[i];
				int nextX=nowX+dx[i];
				if(nextY<0||nextX<0||nextY>=map.length||nextX>=map.length)
					continue;
				if(map[nextY][nextX]==number)
					continue;
				if(visited[nextY][nextX]) continue;
				
				queue.add(new Integer[] {nextY,nextX,count+1});
				visited[nextY][nextX]=true;
			}

		}



	}

	public static void dfs(int y,int x,int number) {
		visited[y][x]=true;
		map[y][x]=number;
		for(int i=0;i<4;i++) {
			int nextY=y+dy[i];
			int nextX=x+dx[i];
			if(nextY<0||nextX<0||nextX>=map.length||nextY>=map.length)
				continue;
			if(visited[nextY][nextX]==true||map[nextY][nextX]!=1)
				continue;

			dfs(nextY,nextX,number);
		}
	}

}