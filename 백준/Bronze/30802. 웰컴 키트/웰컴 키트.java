import java.util.*;
import java.io.*;

public class Main {
    private static int N, T, P; 
    private static int[] sizes;
    private static int minBundleOfT;
    private static int maxBundleOfP, remainP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        sizes = new int[6];
        for(int i=0;i<6;i++){
            sizes[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
       
        calculateT();
        
        calculateP();
        
        StringBuilder sb = new StringBuilder();
        sb.append(minBundleOfT);
        sb.append("\n");
        sb.append(maxBundleOfP).append(" ").append(remainP);
        System.out.println(sb);
    }
    
    static void calculateT(){
        for(int i=0;i<6;i++){
            int num = sizes[i];
            if(num==0){
                continue;
            }
            minBundleOfT += num / T;
            if(num%T>0){
                minBundleOfT++;
            }
        }
    }
    
    static void calculateP(){
        maxBundleOfP = N / P;
        remainP = N % P; 
    }
}