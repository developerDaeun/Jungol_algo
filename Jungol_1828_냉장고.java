import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jungol_1828_냉장고 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());	// 화학물질의 수 N
		Item[] items = new Item[N]; // N개 화학물질의 최저보관온도, 최고보관온도

		for(int i = 0; i < N; i++) { // N개 화학물질의 최저보관온도, 최고보관온도 입력
			st = new StringTokenizer(br.readLine(), " ");
			items[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(items);	// 최저보관온도가 낮은 순으로 오름차순 정렬

		ArrayList<Item> list = new ArrayList<>();
		list.add(items[0]);	// 첫번째 화학물질 추가
		
		int total = 1;
		for(int i = 1; i < items.length; i++) {
			if(list.get(list.size()-1).max < items[i].min) {	// 범위 벗어나면(현재 들어갈 냉장고가 없으면) 냉장고 추가
				list.add(items[i]);	// 다음 냉장고의 최저, 최고온도 기준이 됨
				total++;	// 냉장고 개수 +1
			}else if(list.get(list.size()-1).max > items[i].max){	// 현재 냉장고의 범위 안에 있으면 list에 추가
				list.add(new Item(items[i].min, items[i].max));	// 냉장고의 최저, 최고온도 기준 새로 정함(현재 물질의 min, max)
			}else {	// 일부만 겹쳐진 상태라면 list에 추가
				list.add(new Item(items[i].min, list.get(list.size()-1).max)); // (현재 물질의 min, 이전 물질의 max)로 냉장고 기준 정함
			}
		}
		
		bw.write(String.valueOf(total));
		bw.close();
	}

	static class Item implements Comparable<Item>{	// 화학물질 클래스
		int min, max;

		public Item(int min, int max) {
			this.min = min;
			this.max = max;
		}

		@Override
		public int compareTo(Item o) {
			if(this.min == o.min) {	// 최저 보관온도가 같으면 최고 보관온도 순으로 오름차순
				return this.max - o.max;
			}
			return this.min - o.min;	// 최저 보관온도 오름차순
		}
	}
}