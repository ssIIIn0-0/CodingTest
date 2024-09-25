import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static int[] box, cube;
	static int n;
	static boolean f = true;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		box = new int[3];
		for(int i=0; i<3; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}
        
		n = Integer.parseInt(br.readLine());
		cube = new int[n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			cube[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		int res = divide(box[0], box[1], box[2]);
		if(f) {
			System.out.println(res);
		}else {
			System.out.println(-1);
		}
	}
	
	static int divide(int l, int w, int h) {
		if(l == 0 || w == 0 || h == 0 ) return 0;
		int k = Math.min(l, Math.min(w, h));
		
		int t = (int)(Math.log(k)/Math.log(2))+1;
		while(t--> 0) {
			if(n <= t || cube[t] <= 0)continue;
			cube[t]--;
			int size = (int)Math.pow(2, t);
			return divide(l-size, w, h) + divide(size, w-size, h) + divide(size, size, h-size)+1;
		}
		f = false;
		return -1;
	}
}