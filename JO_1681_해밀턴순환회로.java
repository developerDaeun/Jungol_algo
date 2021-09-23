import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1681_해밀턴순환회로 {

	static int N;
	static int[][] map;
	static boolean[] v;
	static int res;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());	// 배달해야 하는 장소의 수 N
		map = new int[N][N];	// 인접행렬
		v = new boolean[N];	// 방문배열
		
		// 인접행렬 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		res = Integer.MAX_VALUE;	// 최소 비용 결과
		
		v[0] = true;	// 첫번째 정점 방문 체크
		dfs(0,0,0);	// 모든 경우를 탐색
		
		System.out.println(res);
	}

	static void dfs(int idx, int cnt, int total) {	// idx: 현재 인덱스, cnt: 고른 간선수, total: 현재까지 비용 합
		// 정점-1 개수만큼 간선을 골랐을때
		// 마지막 인덱스에서 0번 정점까지 가는 비용을 더한 값으로 최소 비용 구하기
		if(cnt == N-1) {
			if(map[idx][0] != 0)	// 인접 정점일 때만 계산
				res = Math.min(res, total + map[idx][0]);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(v[i] || map[idx][i]==0) continue;	// 방문하거나 인접 정점이 아니면 continue
			if(res <= total+map[idx][i]) continue;	// 결과보다 큰 값이 나오면 가지치기
			
			v[i] = true;	// 방문체크
			dfs(i, cnt+1, total+map[idx][i]);	// 다음 정점으로 이동
			v[i] = false;	// 방문체크 해제
		}
	}

}