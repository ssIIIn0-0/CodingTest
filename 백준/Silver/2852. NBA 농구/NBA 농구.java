import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
 
public class Main {
	static int N, M, S, P, A, B, X, L, R, C, n, k, m, l, K, D, T;
	static int answer = 0;
	static ArrayList<int[]> arr = new ArrayList<int[]>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		arr.add(new int[] {99999, timeConvertToInt("48:00")});
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int team = Integer.parseInt(st.nextToken());
			String time = st.nextToken();
			arr.add(new int[] {team, timeConvertToInt(time)});
		}
		Collections.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return Integer.compare(a[1], b[1]);
			}
		});
		int[] teamWinTime = new int[2];
		int teamScore = 0;
		for(int i=0; i<N; i++) {
			int[] now = arr.get(i);
			int[] next = arr.get(i+1);
			
			teamScore += (now[0] == 1) ? 1 : -1;
			if(teamScore > 0) {
				teamWinTime[0] += next[1] - now[1];
			}else if(teamScore < 0){
				teamWinTime[1] += next[1] - now[1];
			}
		}
		
		System.out.println(intConvertToTime(teamWinTime[0]));
		System.out.println(intConvertToTime(teamWinTime[1]));
	}
	
	static int timeConvertToInt(String s) {
		String[] split = s.split("[:]");
		int hour = Integer.parseInt(split[0]) * 60;
		int minute = Integer.parseInt(split[1]);
		return hour + minute;
	}
	
	static String intConvertToTime(int time){
		int hour = time / 60;
		int minute = time % 60;
		String ret = String.format("%02d:%02d", hour,minute);
		return ret;
	}
		
	
}