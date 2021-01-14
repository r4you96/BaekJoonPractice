import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SafetyDomain2468 {
	int[][] map;
	boolean[][] isNotWet;
	boolean[][] isNotVisited;
	Queue<Point> checkQueue = new LinkedList<>();
	
	int[] dirX = {0,0,-1,1};
	int[] dirY = {-1,1,0,0};
	
	int n, domainNum, maxDomain;
	int rain = -1;
	
	public void startRain() {
		int i,j, rainCount = 0;
		maxDomain = 0;
		
		for(i=0; i<n; i++)
			Arrays.fill(isNotWet[i], true);
		while(true) {
			//System.out.println(rainCount);
			//System.out.println(rain);
			rain++;
			domainNum = 0;
			for(i=0; i<n; i++)
				Arrays.fill(isNotVisited[i], true);
			//비 높이 상승 영역 반영
			for(i=0; i<n; i++) {
				for(j=0; j<n; j++) {
					if(isNotWet[i][j] && map[i][j] <= rain) {
						isNotWet[i][j] = false;
						rainCount++;
					}
				}
			}
			/*for(i=0; i<n; i++) {
				for(j=0; j<n; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			
			for(i=0; i<n; i++) {
				for(j=0; j<n; j++) {
					System.out.print(isNotWet[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();*/
			
			
			//만약 영역이 없으면 멈춤
			if(rainCount == n * n)
				break;
			
			for(i=0; i<n; i++) {
				for(j=0; j<n; j++) {
					if(isNotWet[i][j] && isNotVisited[i][j]) {
						bfsRain(i, j);
						domainNum++;
					}
				}
			}
			
			if(domainNum > maxDomain)
				maxDomain = domainNum;
		}
		
		System.out.println(maxDomain);
		
	}
	
	public void bfsRain(int y, int x) {
		int i, nowX, nowY;
		Point nowPoint;
		isNotVisited[y][x] = false;
		checkQueue.add(new Point(x, y));
		
		while(!checkQueue.isEmpty()) {
			nowPoint = checkQueue.remove();
			for(i=0; i<4; i++) {
				nowX = nowPoint.x + dirX[i];
				nowY = nowPoint.y + dirY[i];
				
				if(nowX>=0 && nowX<n && nowY>=0 && nowY<n && map[nowY][nowX] > rain && isNotVisited[nowY][nowX]){
					isNotVisited[nowY][nowX] = false;
					checkQueue.add(new Point(nowX,nowY));
				}
			}
		}
	}
	
	public void setNum() throws IOException {
		int i,j;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf. readLine());
		
		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		isNotVisited = new boolean[n][n];
		isNotWet  = new boolean[n][n];
		maxDomain = 30;
		
		//Arrays.fill(isNotVisited[i], true);
		for(i = 0; i< n; i++) {
			st = new StringTokenizer(bf. readLine());
			for(j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}	
	}
	/*public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SafetyDomain2468 main = new SafetyDomain2468();
		main.setNum();
		main.startRain();
	}*/

}
