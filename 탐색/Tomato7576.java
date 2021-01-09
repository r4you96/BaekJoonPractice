import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tomato7576 {
	int m, n;
	Queue<point> pointQueue = new LinkedList<>();
	boolean visited[][];
	int[][] box;
	int visitedCount, dayCount, nowDayCount;
	int groundNum;
	public class point{
		int x, y;
		public point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	public void startTomato() {
		int i, j;
		
		for(i=0; i<n; i++) {
			for(j=0; j<m; j++) {
				if(box[i][j] != -1) {
					groundNum++;
					if(box[i][j] == 1) {
						visitCheck(i, j);
					}
				}
			}
		}
	}
	public void printTomato() {
		int i,j;
		for(i=0; i<n; i++) {
			for(j=0; j<m; j++) {
				System.out.print(box[i][j] + " ");
			}
			System.out.println();
		}
	}
	public int tomato() {
		int queueLen, i,j;
		point tmpPoint;
		int x, y;
		//첫날에 다 찼으면 0반환
		if(visitedCount == groundNum)
			return 0;
		
		while(!pointQueue.isEmpty()) {
			nowDayCount = 0;
			if(visitedCount == groundNum)
				return dayCount;
	
			dayCount++;
			//큐 길이만큼만 너비 탐색
			queueLen = pointQueue.size();
			for(i=0; i<queueLen; i++) {			
				tmpPoint = pointQueue.remove();
				x = tmpPoint.x;
				y = tmpPoint.y;
				
				if(y + 1 < n && !visited[y + 1][x] && box[y+1][x] == 0) {
					visitCheck(y+1, x);
				}
				if(y - 1 >= 0 && !visited[y - 1][x] && box[y-1][x] == 0) {
					visitCheck(y-1, x);
				}
				if(x + 1 < m && !visited[y][x + 1] && box[y][x +1] == 0) {
					visitCheck(y, x+1);
				}
				if(x - 1 >= 0 && !visited[y][x - 1] && box[y][x-1] == 0) {
					visitCheck(y, x-1);
				}				
			}
			
			if(nowDayCount == 0)
				return -1;
		}
		
		return dayCount;
	}
	public void visitCheck(int y, int x) {
		box[y][x] = 1;
		visited[y][x] = true;
		pointQueue.add(new point(y, x));
		visitedCount++;
		nowDayCount++;
	}
	
	public void setNum() {
		int i, j;
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			
			visited = new boolean[n][m];
			box = new int[n][m];
			
			for(i = 0; i<n; i++) {
				st = new StringTokenizer(bf.readLine());
				for(j=0; j<m; j++) {
					box[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			
			for(i = 0; i<n; i++) {
				for(j=0; j<m; j++) {
					visited[i][j] = false;
				} 
			}
			dayCount = 0;
			groundNum = 0;	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		Tomato7576 tomato = new Tomato7576();
		tomato.setNum();
		tomato.startTomato();
		System.out.println(tomato.tomato());
	}
}
