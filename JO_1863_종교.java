import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1863_종교 {

	static void make() {
		parents = new int[n+1];
		
		for(int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a==b) return;
		parents[b] = a;
	}
	
	static int n, m;
	static int[] parents;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());	// 학생수
		m = Integer.parseInt(st.nextToken());	// 쌍의 수
		
		make();	// 정점마다 집합 생성
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	// 합집합
		}
		
		int total = 0;
		for(int i = 1; i <= n; i++) {
			if(parents[i] == i) {	// 집합의 대표자 개수 구하기 (종교 개수)
				total++;
			}
		}
		
		System.out.println(total);
	}
}