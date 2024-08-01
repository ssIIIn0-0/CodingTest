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
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            // 골렘 이동
            int[] res = move(c, d, no);
            boolean inBound = res[0] == 1;
            int x = res[1];
            int y = res[2];

            // 골렘 몸 일부가 숲을 벗어나있는지 확인
            if (inBound) {
                // 정령 이동
                ans += bfs(x, y, no);
            } else {
                // 숲 초기화
                a = new int[n][m];
            }
        }

        System.out.println(ans);
    }

    // 출구 위치
    private static int[] getExit(int x, int y, int d) {
        if (d == 0) {
            return new int[]{x - 1, y};
        } else if (d == 1) {
            return new int[]{x, y + 1};
        } else if (d == 2) {
            return new int[]{x + 1, y};
        } else {
            return new int[]{x, y - 1};
        }
    }

    private static boolean inBoard(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }

    // 골렘이 어떤 좌표로 이동 가능한 상태인지 확인
    private static boolean check(int x, int y) {
        if (!inBoard(x, y)) { // 좌표가 보드 밖에 위치하면
            if (x < n && y >= 0 && y < m) { // 좌표가 위쪽이 뚫린 바구니 같은 공간에 있는지
                return true;
            }
        } else { // 좌표가 보드 안에 위치하면
            if (a[x][y] == 0) { // 다른 골렘이 없는지
                return true;
            }
        }
        return false;
    }

    // 골렘 이동
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
            } else {
                break;
            }
        }

        // 골렘 지도에 표시
        if (!inBoard(x, y) || !inBoard(x + 1, y) || !inBoard(x - 1, y) || !inBoard(x, y + 1) || !inBoard(x, y - 1)) {
            return new int[]{0, -1, -1};
        } else {
            a[x][y] = a[x + 1][y] = a[x - 1][y] = a[x][y + 1] = a[x][y - 1] = no;
            int[] exit = getExit(x, y, d); // 출구 위치
            int ex = exit[0];
            int ey = exit[1];
            a[ex][ey] = -no;
            return new int[]{1, x, y};
        }
    }

    // 정령 이동
    private static int bfs(int sx, int sy, int no) {
        List<Integer> cand = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy});
        boolean[][] visit = new boolean[n][m];
        visit[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + DX[k];
                int ny = y + DY[k];
                if (!inBoard(nx, ny) || visit[nx][ny] || a[nx][ny] == 0) {
                    continue;
                }
                // 절댓값이 같은 칸으로 움직이거나, 출구 칸에서 다른 칸으로 이동 가능
                if (Math.abs(a[x][y]) == Math.abs(a[nx][ny]) || (a[x][y] < 0 && Math.abs(a[nx][ny]) != Math.abs(a[x][y]))) {
                    q.add(new int[]{nx, ny});
                    visit[nx][ny] = true;
                    cand.add(nx);
                }
            }
        }

        Collections.sort(cand, Collections.reverseOrder());
        return cand.get(0) + 1;
    }
}
