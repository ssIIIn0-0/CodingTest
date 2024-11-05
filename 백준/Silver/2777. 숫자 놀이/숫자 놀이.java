import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(bf.readLine());
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(bf.readLine());

			int idx = 9, answer = 0;
			if (n == 1) {
				bw.write(1 + "\n");
				continue;
			}
			while (idx > 1) {
				if (n % idx == 0) {
					n /= idx;
					answer += 1;
				} else {
					idx -= 1;
				}
			}
			if (n != 1) {
				bw.write(-1 + "\n");
			} else {
				bw.write(answer + "\n");
			}
		}
		bw.flush();
	}
}