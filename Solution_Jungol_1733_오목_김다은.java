import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_Jungol_1733_오목_김다은 {

	static int[][] map = new int[19+2][19+2];
	static int[] dr = {-1,0,1,1};	// 오른쪽위,오른쪽,오른쪽아래,아래
	static int[] dc = {1,1,1,0};
	static int start;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
	
		for (int i = 1; i <= 19; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 1; i <= 19; i++) {
			for (int j = 1; j <= 19; j++) {
				if(map[i][j]!=0) {
					start = map[i][j];
					if(dfs(i,j)) {
						bw.write(String.valueOf(map[i][j]) + "\n" + i + " " + j);
						bw.close();
						return;
					}
				}
			}
		}
		bw.write("0");
		bw.close();
	}

	static boolean dfs(int r, int c) {		// 검: 1, 흰: 2, 승부결정X: 0 리턴

		for(int d = 0; d < 4; d++) {
			if(map[r-dr[d]][c-dc[d]] == start) {	// 이전칸이 나랑 다른색이어야함
				continue;
			}
			if(map[r+dr[d]][c+dc[d]] != start
					|| map[r+dr[d]*2][c+dc[d]*2] != start
					|| map[r+dr[d]*3][c+dc[d]*3] != start
					|| map[r+dr[d]*4][c+dc[d]*4] != start) {	// 5칸 연속 같은 색인지, 5칸까지 볼수있는 이유는 어차피 마지막으로 탐색하는것이 r-5,c-5이므로
				continue;
			}
			if(map[r+dr[d]*5][c+dc[d]*5] == start) { // 5칸 이후칸이 다른색이어야함
				continue;
			}
			return true;
		}

		return false;
	}
}