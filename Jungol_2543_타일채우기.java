import java.util.Scanner;

public class Jungol_2543_타일채우기 {

	static int hr,hc;
	static int[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = sc.nextInt();	// 화장실 바닥 한 변 길이
		hr = sc.nextInt();	// 배수구 x 좌표
		hc = sc.nextInt();	// 배수구 y 좌표
		
		map = new int[N][N];	// 화장실 바닥
		
		// 배수구 및 빈공간(홀)의 중심에 타일 채우기
		fill(0,0,N,hr,hc);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	static void fill(int r, int c, int size, int hr, int hc) {
		
		if(size==1) {	// 타일은 한변의 크기가 2이므로 1이면 리턴
			return;
		}
		
		int sum = 0;
		for(int i = r; i < r+size; i++) {
			for(int j = c; j < c+size; j++) {
				if((i==hr&&j==hc) || map[i][j] != 0) {	// 채워진 타일 수 구하기
					sum++;
				}
			}
		}
		
		if(sum == size*size) { // 현재 공간을 모두 다 채웠다면 return
			return;
		}
		
		int half = size/2;
		
		// 처음에 hr,hc 는 4,4 => 왼쪽위 빈공간 3,3 / 오른쪽위 빈공간 3,4 / 왼쪽아래 빈공간 4,3 / 오른쪽아래 빈공간 4,4
		if(hr<r+half && hc<c+half) {	// 왼쪽위 공간에 빈공간이 있을때 => 중심을 1번 타일로 채우기
			map[r+half-1][c+half] = 1;
			map[r+half][c+half-1] = 1;
			map[r+half][c+half] = 1;
			
			fill(r,c,half,hr,hc);	// 왼쪽위 말고 타일이 채워져 있으므로 왼쪽위를 hr,hc로 설정(채우면 안되는 공간이므로)
			fill(r,c+half,half,r+half-1,c+half);
			fill(r+half,c,half,r+half,c+half-1);
			fill(r+half,c+half,half,r+half,c+half);
			
		}else if(hr<r+half && hc>=c+half) {	// 오른쪽위 공간에 빈공간이 있을때 => 중심을 2번 타일로 채우기
			map[r+half-1][c+half-1] = 2;
			map[r+half][c+half-1] = 2;
			map[r+half][c+half] = 2;
			
			fill(r,c,half,r+half-1,c+half-1);	// 오른쪽위 말고 타일이 채워져 있으므로 오른쪽위를 hr,hc로 설정(채우면 안되는 공간이므로)
			fill(r,c+half,half,hr,hc);
			fill(r+half,c,half,r+half,c+half-1);
			fill(r+half,c+half,half,r+half,c+half);
			
		}else if(hr>=r+half && hc<c+half) {	// 왼쪽아래 공간에 빈공간이 있을때 => 중심을 3번 타일로 채우기
			map[r+half-1][c+half-1] = 3;
			map[r+half-1][c+half] = 3;
			map[r+half][c+half] = 3;
			
			fill(r,c,half,r+half-1,c+half-1);	// 왼쪽아래 말고 타일이 채워져 있으므로 왼쪽아래를 hr,hc로 설정(채우면 안되는 공간이므로)
			fill(r,c+half,half,r+half-1,c+half);
			fill(r+half,c,half,hr,hc);
			fill(r+half,c+half,half,r+half,c+half);
			
		}else { // 오른쪽아래 공간에 빈공간이 있을때 => 중심을 4번 타일로 채우기
			map[r+half-1][c+half-1] = 4;
			map[r+half-1][c+half] = 4;
			map[r+half][c+half-1] = 4;
			
			fill(r,c,half,r+half-1,c+half-1);	// 오른쪽아래 말고 타일이 채워져 있으므로 오른쪽아래를 hr,hc로 설정(채우면 안되는 공간이므로)
			fill(r,c+half,half,r+half-1,c+half);
			fill(r+half,c,half,r+half,c+half-1);
			fill(r+half,c+half,half,hr,hc);
		}
	}
}