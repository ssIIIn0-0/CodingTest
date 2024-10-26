import java.io.*;
import java.util.*;

public class Main {
    static int N,M, pack = Integer.MAX_VALUE, piece = Integer.MAX_VALUE, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine()," ");

            pack = Math.min(pack, Integer.parseInt(st.nextToken()));
            piece = Math.min(piece, Integer.parseInt(st.nextToken()));
        }
        if(piece * 6 <= pack)
            answer = piece * N;
        else{
            answer = (N/6) * pack;
            if(N%6 * piece <= pack)
                answer += (N%6) * piece;
            else
                answer += pack;
        }
        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}