import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();	
        st = new StringTokenizer(br.readLine()," ");
        
        for(int i=0;i<N;i++){
            int n = Integer.parseInt(st.nextToken());
            if(n == 1){	
                list.add(i);
            }
        }
        
        if(list.size()<K){
            bw.write("-1");
        }else{
            int result = Integer.MAX_VALUE;
            for(int i=0;i<=list.size()-K;i++){
                int start = list.get(i);
                int end = list.get(i+K-1);
                result = Math.min(result, end-start+1);
            }
            bw.write(String.valueOf(result));
        }
        
        bw.flush();
    }
}

