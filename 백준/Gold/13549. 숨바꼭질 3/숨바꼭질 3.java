import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {
	
	public static int N,K;
	public static boolean[] visited;
	public static int result = Integer.MAX_VALUE;
	public static int max = 100000;
	public static Queue<Node> q = new LinkedList<>();
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	visited = new boolean[max + 1];
    	bfs();
    	System.out.println(result);
    }
    
    public static void bfs() {
    	q.offer(new Node(N,0));
    	while(!q.isEmpty()) {
    		Node node = q.poll();
    		int x = node.x;
    		int time = node.time;
    		visited[x]= true;
    		
    		if(x == K) result = Math.min(result,  time);
    		
    		if(x * 2 <= max && visited[x * 2] == false) q.offer(new Node(x * 2, time));
    		if(x + 1 <= max && visited[x + 1] == false) q.offer(new Node(x + 1, time + 1)); 
    		if(x - 1 >= 0 && visited[x - 1] == false) q.offer(new Node(x - 1, time + 1));
    	}
    }
    
    
    
}
 
class Node{
	int x;
	int time;
	public Node(int x , int time) {
		this.x=x;
		this.time = time;
	}
}