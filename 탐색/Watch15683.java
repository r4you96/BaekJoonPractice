import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Watch15683 {
	int n, m;
	int[][] map;
	int[][] copyMap;
	
	ArrayList<Cctv> cctvList = new ArrayList<>();
	int[] cctvState;
	int maxSafeDomain = Integer.MAX_VALUE;
	
	public class Cctv{
		int y, x, state, num;
		
		public Cctv(int y, int x, int num) {
			this.y = y;
			this.x = x;
			this.num = num;
		}
		
		public void setState(int state) {
			this.state = state;
		}
		public int getState() {
			return this.state;
		}
	}
	
	public void forceSearch(int count) {
		int i;
		
		if(count == cctvList.size()) {
			checkCctv();
			return;
		}
		
		
		Cctv nowCctv = cctvList.get(count);
		if(nowCctv.num == 2) {
			for(i=0; i<2; i++) {
				nowCctv.state = i;
				forceSearch(count + 1);
			}
		}else {
			for(i=0; i<4; i++) {
				nowCctv.state = i;
				forceSearch(count + 1);
			}
		}
	}
	
	public void checkCctv() {
		int j, k;
		
		for(j = 0; j<n; j++) {
			for(k = 0; k<m; k++)
				copyMap[j][k] = map[j][k];
		}
		
		Cctv tmp;
		for(j=0; j<n; j++) {
			for(k=0; k<m; k++) {
				if(map[j][k] == 5) {
					tmp = new Cctv(j, k, 5);
					launchLaser(tmp, 1);
					launchLaser(tmp, 2);
					launchLaser(tmp, 3);
					launchLaser(tmp, 4);
				}
			}
		}
		
		for(Cctv i : cctvList) {
			if(i.num == 1) {
				if(i.state == 0) {
					launchLaser(i, 1);
				}else if(i.state == 1) {
					launchLaser(i, 2);
				}else if(i.state == 2) {
					launchLaser(i, 3);
				}else if(i.state == 3) {
					launchLaser(i, 4);
				}	
			}else if(i.num == 2) {
				if(i.state == 0) {
					launchLaser(i, 1);
					launchLaser(i, 3);
				}else if(i.state == 1) {
					launchLaser(i, 2);
					launchLaser(i, 4);
				}
			}else if(i.num == 3) {
				if(i.state == 0) {
					launchLaser(i, 1);
					launchLaser(i, 2);
				}else if(i.state == 1) {
					launchLaser(i, 2);
					launchLaser(i, 3);
				}else if(i.state == 2) {
					launchLaser(i, 3);
					launchLaser(i, 4);
				}else if(i.state == 3) {
					launchLaser(i, 1);
					launchLaser(i, 4);
				}	
			}else if(i.num == 4) {
				if(i.state == 0) {
					launchLaser(i, 1);
					launchLaser(i, 2);
					launchLaser(i, 3);
				}else if(i.state == 1) {
					launchLaser(i, 1);
					launchLaser(i, 2);
					launchLaser(i, 4);
				}else if(i.state == 2) {
					launchLaser(i, 1);
					launchLaser(i, 3);
					launchLaser(i, 4);
				}else if(i.state == 3) {
					launchLaser(i, 4);
					launchLaser(i, 2);
					launchLaser(i, 3);
				}	
			}
		}
		
		int countingSafety = 0;
		
		/*for(j = 0; j<n; j++) {
			for(k = 0; k<m; k++) {
				System.out.printf("%3d ", copyMap[j][k]);
			}
			System.out.println();
		}
		System.out.println();*/
		
		for(j = 0; j<n; j++) {
			for(k = 0; k<m; k++) {
				if(copyMap[j][k] == 0)
					countingSafety++;
			}
		}
		
		if(countingSafety<maxSafeDomain)
			maxSafeDomain = countingSafety;
		
		return;
	}
	
	public void launchLaser(Cctv nowCctv, int direction) {
		int i;
		if(direction == 1) { //위
			for(i=nowCctv.y-1; i>=0; i--) {
				if(map[i][nowCctv.x] == 6)
					break;
				else if(map[i][nowCctv.x] == 0)
					copyMap[i][nowCctv.x] = -1;
			}
		}else if(direction == 2) { //오른
			for(i=nowCctv.x+1; i<m; i++) {
				if(map[nowCctv.y][i] == 6)
					break;
				else if(map[nowCctv.y][i] == 0)
					copyMap[nowCctv.y][i] = -1;
			}
		}else if(direction == 3) { //아래
			for(i=nowCctv.y+1; i<n; i++) {
				if(map[i][nowCctv.x] == 6)
					break;
				else if(map[i][nowCctv.x] == 0)
					copyMap[i][nowCctv.x] = -1;
			}
		}else if(direction == 4) { //왼
			for(i=nowCctv.x-1; i>=0; i--) {
				if(map[nowCctv.y][i] == 6)
					break;
				else if(map[nowCctv.y][i] == 0)
					copyMap[nowCctv.y][i] = -1;
			}
		}
	}
	
	
	public void setNum() throws IOException {
		int i,j, count = 0 ;
		int num;
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		copyMap = new int[n][m];
		
		for(i=0; i<n; i++) {
			st  = new StringTokenizer(bf.readLine());
			for(j=0; j<m; j++) {
				//시시티비면 어레이리스트 넣음
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] < 5 && map[i][j] > 0) {
					cctvList.add(new Cctv(i, j, map[i][j]));
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Watch15683 main = new Watch15683();
		main.setNum();
		main.forceSearch(0);
		System.out.println(main.maxSafeDomain);
		
	}

}
