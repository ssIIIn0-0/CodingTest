import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int[][] board;

    static int[] dx = {0,0,1,-1,1,1,-1,-1};
    static int[] dy = {1,-1,0,0,1,-1,1,-1}; // 8방 탐색

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            String line = bf.readLine();

            for(int j=1;j<=n;j++){
                char tmp = line.charAt(j-1);

                if(tmp == '#')
                    board[i][j] = 9; // 가능한 위치는 9로 설정

                else
                    board[i][j] = tmp - '0';
            }
        }

        int count = 0;

        for(int i=2;i<=n-1;i++){
             for(int j=2;j<=n-1;j++){

                boolean can = true;

                for(int k=0;k<8;k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(board[nx][ny] == 0) {
                        can = false; // 지뢰가 존재할 수 없는 위치
                        break;
                    }
                }

                if(can){
                    for(int k=0;k<8;k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        board[nx][ny] -= 1; // 해당 위치에 지뢰를 박을것이기 때문에 주변의 정보 -1
                    } 

                    count++; // 지뢰 개수 증가
                }
            }
        }

        System.out.println(count);

        bf.close();
    }
}