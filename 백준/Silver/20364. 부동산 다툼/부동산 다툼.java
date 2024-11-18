import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {
	public static int N, Q;
	public static int[] arr;
	public static boolean[] visited;
	public static int answer = 0;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine()); 	
    	
    	N = Integer.parseInt(st.nextToken());
    	Q = Integer.parseInt(st.nextToken());
    	visited = new boolean[N+1];
    	
    	for(int i=0;i<Q;i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		boolean flag = false;
    		int firstMeetPosition = 1;
    		for(int j=a; j>=2; j/=2) {
    			if(visited[j] == true) {
    				flag = true;
    				firstMeetPosition = j;
    			}
    		}
    		
    		if(flag == true) {
    			System.out.println(firstMeetPosition);
    		}else if(flag == false) {
    			visited[a] = true;
    			System.out.println("0");
    		}
    		
    	}
    } 
}