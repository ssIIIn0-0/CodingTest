import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] board;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        game(0);
        System.out.println(max);
    }

    public static void game(int cnt) {
        if (cnt == 5) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    max = Math.max(max, board[i][j]);
                }
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            int[][] copy = new int[n][n];
            switch (d) {
                case 0:
                    for (int i = 0; i < n; i++) {
                        int idx = 0;
                        boolean visited = false;
                        for (int j = 0; j < n; j++) {
                            if (board[i][j] != 0) {
                                if (idx == 0) {
                                    copy[i][idx] = board[i][j];
                                    idx++;
                                } else {
                                    if (!visited && copy[i][idx - 1] == board[i][j]) {
                                        copy[i][idx - 1] *= 2;
                                        visited = true;
                                    } else {
                                        copy[i][idx] = board[i][j];
                                        visited = false;
                                        idx++;
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < n; i++) {
                        int idx = n - 1;
                        boolean visited = false;
                        for (int j = n - 1; j >= 0; j--) {
                            if (board[i][j] != 0) {
                                if (idx == n - 1) {
                                    copy[i][idx] = board[i][j];
                                    idx--;
                                } else {
                                    if (!visited && copy[i][idx + 1] == board[i][j]) {
                                        copy[i][idx + 1] *= 2;
                                        visited = true;
                                    } else {
                                        copy[i][idx] = board[i][j];
                                        visited = false;
                                        idx--;
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < n; i++) {
                        int idx = 0;
                        boolean visited = false;
                        for (int j = 0; j < n; j++) {
                            if (board[j][i] != 0) {
                                if (idx == 0) {
                                    copy[idx][i] = board[j][i];
                                    idx++;
                                } else {
                                    if (!visited && copy[idx - 1][i] == board[j][i]) {
                                        copy[idx - 1][i] *= 2;
                                        visited = true;
                                    } else {
                                        copy[idx][i] = board[j][i];
                                        visited = false;
                                        idx++;
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < n; i++) {
                        int idx = n - 1;
                        boolean visited = false;
                        for (int j = n - 1; j >= 0; j--) {
                            if (board[j][i] != 0) {
                                if (idx == n - 1) {
                                    copy[idx][i] = board[j][i];
                                    idx--;
                                } else {
                                    if (!visited && copy[idx + 1][i] == board[j][i]) {
                                        copy[idx + 1][i] *= 2;
                                        visited = true;
                                    } else {
                                        copy[idx][i] = board[j][i];
                                        visited = false;
                                        idx--;
                                    }
                                }
                            }
                        }
                    }
            }

            int[][] tmp = new int[n][n];
            for (int i = 0; i < n; i++) {
                tmp[i] = board[i].clone();
            }
            board = copy;
            game(cnt + 1);
            board = tmp;
        }
    }
}