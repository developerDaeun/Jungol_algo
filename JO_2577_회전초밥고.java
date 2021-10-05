import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JO_2577_회전초밥고 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		
		int N = Integer.parseInt(s[0]);	// 접시의 수
		int d = Integer.parseInt(s[1]);	// 초밥 가짓수
		int k = Integer.parseInt(s[2]);	// 연속해서 먹는 접시의 수
		int c = Integer.parseInt(s[3]);	// 쿠폰 번호
		
		int[] sushi = new int[N];	// 회전초밥 상태
		
		// 회전초밥 상태 저장
		for(int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		int[] cnt = new int[d+1];	// 초밥 번호에 따라 개수 저장하기
		
		int max = 0;	// 최대 초밥 가짓수 초기화
		int total = 0;	// 현재 선택한 초밥들의 가짓수
		
		// k 개만큼 초밥 개수 각각 세기
		for(int i = 0; i < k; i++) {
			cnt[sushi[i]]++;
		}
		
		// 현재 선택한 초밥이 1개 이상이면 total + 1
		for(int i = 1; i <= d; i++) {
			if(cnt[i] > 0) total++;
		}
		
		// 쿠폰을 사용하지 않았으면 total + 1
		if(cnt[c] == 0) {
			total++;
			cnt[c]++;
		}
		
		max = Math.max(max, total);	// 최대값 구하기
		
		// k개 이후부터 슬라이딩윈도우
		for(int i = k; i < N+k-1; i++) {
			
			cnt[sushi[(i-k)%N]]--;	// k개 이전의 초밥갯수 - 1
			if(cnt[sushi[(i-k)%N]] == 0) total--; // k개 이전의 초밥이 현재 선택한 초밥에 포함이 안되면 (전체 개수 - 1)
			
			if(cnt[sushi[i%N]] == 0) total++;	// 현재 초밥을 포함시키기위해 (전체 개수 + 1)
			cnt[sushi[i%N]]++;	// 현재 초밥 개수 + 1
			
			// 쿠폰을 사용하지 않았으면 (전체 개수 + 1)
			if(cnt[c]==0) max = Math.max(max, total+1);
			else max = Math.max(max, total);	// 최대 가지수 구하기
		}
		
		System.out.print(max);
	}
}