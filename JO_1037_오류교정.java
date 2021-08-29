import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1037_오류교정 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());	// 행렬 크기

		int[][] arr = new int[n+1][n+1];
		// 행,열 1 개수 저장
		int[] rSum = new int[n+1];
		int[] cSum = new int[n+1];
		
		int temp;
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				temp = Integer.parseInt(st.nextToken());
				arr[i][j] = temp;
				if(temp == 1) {
					rSum[i]++;
					cSum[j]++;
				}
			}
		}
		
		boolean check = false;
		int changedI = 0, changedJ = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(rSum[i]%2==1 && cSum[j]%2==1) {	// 행,열 둘다 1이 홀수개일때 Change bit (i,j)
					if(arr[i][j] == 0) {
						rSum[i]++;
						cSum[j]++;
						arr[i][j] = 1;
					}else {
						rSum[i]--;
						cSum[j]--;
						arr[i][j] = 0;
					}
					changedI = i;
					changedJ = j;
					check = false;
				}else if(rSum[i]%2==1 || cSum[j]%2==1) {	// 행,열 하나만 1이 홀수개일때 Corrupt
					check = true;
				}
			}
		}
		
		if(check) System.out.println("Corrupt");
		else if(changedI==0) System.out.println("OK");
		else System.out.println("Change bit ("+changedI+","+changedJ+")");
	}
}