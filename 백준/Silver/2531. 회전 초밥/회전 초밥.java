import java.util.*;
import java.io.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken()); 
        int k = Integer.parseInt(st.nextToken());   
        int c = Integer.parseInt(st.nextToken());  
        
        int[] sushi = new int[N];
        for(int i = 0 ; i < N ; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }
        
        int[] chk = new int[d+1];
        chk[c] = 3001;
        int cnt = 1; 
        
        for(int i = 0 ; i < k ; i++) {
            if(chk[sushi[i]] == 0)
                cnt++;
            
            chk[sushi[i]]++;
        }
        
        int max = cnt;
        
        for(int i = 0 ; i < N-1 ; i++) {
            int sIdx = sushi[i];
            int eIdx = sushi[((i+k) < N) ? (i+k) : (i+k) % N];
            
            if(--chk[sIdx] == 0)
                cnt--;
                
            if(++chk[eIdx] == 1)
                cnt++;
           
            max = Math.max(max, cnt);
        }
        
        System.out.println(max);
    }
}