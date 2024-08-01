import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[] DX = {-1, 0, 1, 0};
    private static final int[] DY = {0, -1, 0, 1};
    private static int n, m, K;
    private static int[][] a;
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        a = new int[n][m];

        for (int no = 1; no <= K; no++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()) - 1;   // 출발하는 열
            int d = Integer.parseInt(st.nextToken());       // 골렘의 출구 방향

            // 골렘 이동
            int[] res = move(c, d, no);
            boolean inBound = res[0] == 1;  // 0:  보드 밖, 1: 제대로 배치
            int x = res[1];
            int y = res[2];

            // 골렘 몸 일부가 숲을 벗어나있는지 확인
            if (inBound) {  // 0:  보드 밖, 1: 제대로 배치
                // 정령 이동
                ans += bfs(x, y, no);
            } else {
                // 숲 초기화
                a = new int[n][m];
            }
        }

        System.out.println(ans);
    }

    // 출구 위치 (골렘 몸 내에서)
    private static int[] getExit(int x, int y, int d) {
        if (d == 0) {           // 북
            return new int[]{x - 1, y};
        } else if (d == 1) {    // 동
            return new int[]{x, y + 1};
        } else if (d == 2) {    // 남
            return new int[]{x + 1, y};
        } else {                // 서
            return new int[]{x, y - 1};
        }
    }

    // 주어진 좌표가 범위 내에 있는지
    private static boolean inBoard(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }

    // 골렘이 주어진 좌표로 이동 가능한 상태인지
    private static boolean check(int x, int y) {
        if (!inBoard(x, y)) { // 좌표가 보드 밖에 위치하면
            if (x < n && y >= 0 && y < m) {
                return true;
            }
        } else { // 좌표가 보드 안에 위치하면
            if (a[x][y] == 0) { // 다른 골렘이 없는지
                return true;
            }
        }
        return false;
    }

    // 골렘 이동, 최종 위치 반환
    private static int[] move(int c, int d, int no) {
        int x = -2;
        int y = c; // 골렘 내 중앙의 정령 위치. 보드 맨 위에서 두 칸 위인 x==-2 지점부터 내려온다.
        while (true) {
            // 골렘 수직 이동
            if (check(x + 2, y) && check(x + 1, y - 1) && check(x + 1, y + 1)) {
                x += 1;
            }
            // 골렘 왼쪽 이동
            else if (check(x + 1, y - 1) && check(x - 1, y - 1) && check(x, y - 2) && check(x + 1, y - 2) && check(x + 2, y - 1)) {
                x += 1;
                y -= 1;
                d = (d - 1 + 4) % 4;
            }
            // 골렘 오른쪽 이동
            else if (check(x + 1, y + 1) && check(x - 1, y + 1) && check(x, y + 2) && check(x + 1, y + 2) && check(x + 2, y + 1)) {
                x += 1;
                y += 1;
                d = (d + 1) % 4;
            } else {    // 골렘의 일부가 범위 밖으로 나오면 이동 중단
                break;
            }
        }

        // 골렘 지도에 표시
        if (!inBoard(x, y) || !inBoard(x + 1, y) || !inBoard(x - 1, y) || !inBoard(x, y + 1) || !inBoard(x, y - 1)) {
            return new int[]{0, -1, -1};    // 골렘이 범위를 벗어남
        } else {
            a[x][y] = a[x + 1][y] = a[x - 1][y] = a[x][y + 1] = a[x][y - 1] = no;   // 골렘 번호를 각 맵 위치에 넣어둗(다른 골렘이랑 구분)
            int[] exit = getExit(x, y, d); // 출구 위치 x,y : 정령의 위치, d : 출구 방향
            int ex = exit[0];
            int ey = exit[1];
            a[ex][ey] = -no;    // 출구를 -골렘번호 로 설정
            return new int[]{1, x, y};
        }
    }

    // 정령이 이동할 수 있는 최대위치 탐색 -> 점수 계산
    private static int bfs(int sx, int sy, int no) {
        List<Integer> cand = new ArrayList<>(); // 정령이 방문할 수 있는 y좌표
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy});
        boolean[][] visit = new boolean[n][m];
        visit[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            for (int k = 0; k < 4; k++) {   // 4방향 ㅌㅁ색
                int nx = x + DX[k];
                int ny = y + DY[k];
                if (!inBoard(nx, ny) || visit[nx][ny] || a[nx][ny] == 0) {  // 새로운 위치가 보드의 범위를 벗어남 or 이미 방문했거나 or 골렘이 없는 빈칸
                    continue;
                }
                // 같은 골렘의 부분이거나 출구인 경우  or 현재 위치가 출구이고 다음 위치가 출구가 아닌 경우(다른 골렘의 부분인 경우)
                if (Math.abs(a[x][y]) == Math.abs(a[nx][ny]) || (a[x][y] < 0 && Math.abs(a[nx][ny]) != Math.abs(a[x][y]))) {
                    q.add(new int[]{nx, ny});
                    visit[nx][ny] = true;
                    cand.add(nx);
                }
            }
        }
        
        // 정령이 도달할 수 있는 모든 y좌표를 수집 후 내림차순으로 정렬
        Collections.sort(cand, Collections.reverseOrder());
        return cand.get(0) + 1; // 그중에서 가장 큰 y좌표 가져오기 (+1을 하는 이유 : 인덱스때문에 -1된 상태의 y좌표를 가져왔으므로)
    }
}
