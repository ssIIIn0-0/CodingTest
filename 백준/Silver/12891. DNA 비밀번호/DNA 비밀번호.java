import java.util.*;
import java.io.*;

public class Main {


    static int N,M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static String password;
    static int [] arr;


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        password = br.readLine();
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int count = 0;
        arr = new int[4];
        for(int j=0; j<M; j++){
            if(password.charAt(j) == 'A'){
                arr[0] ++;
            }else if(password.charAt(j) == 'C'){
                arr[1] ++;
            }else if(password.charAt(j) == 'G'){
                arr[2] ++;
            } else if (password.charAt(j) == 'T') {
                arr[3] ++;
            }
        }
        if(arr[0] >= A && arr[1] >= C && arr[2] >= G && arr[3] >=T ){
            count++;
        }
        for(int i=M; i<password.length(); i++){
            if(password.charAt(i-M) == 'A'){
                arr[0] --;
            }else if(password.charAt(i-M) == 'C'){
                arr[1] --;
            }else if(password.charAt(i-M) == 'G'){
                arr[2] --;
            } else if (password.charAt(i-M) == 'T') {
                arr[3] --;
            }
            if(password.charAt(i) == 'A'){
                arr[0] ++;
            }else if(password.charAt(i) == 'C'){
                arr[1] ++;
            }else if(password.charAt(i) == 'G'){
                arr[2] ++;
            } else if (password.charAt(i) == 'T') {
                arr[3] ++;
            }
            if(arr[0] >= A && arr[1] >= C && arr[2] >= G && arr[3] >=T ){
                count++;
            }

        }
        System.out.println(count);
    }
}