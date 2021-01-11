import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BuildingNum2667 {
	int n, buildingCount;
	int [][] map;
	boolean[][] visited;
	ArrayList<Integer> buildingNum = new ArrayList<>();
	Queue<point> pointQueue = new LinkedList<>();
	
	public class point{
		int x,y;
		public point(int y, int x) {
			this.x = x;
			this.y = y;
		}
	}
	
	public void searchBuilding() {
		int i, j;
		
		for(i =0; i<n; i++) {
			for(j=0; j<n; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					goBfs(i,j);
				}
				visited[i][j] = true;
			}
		}
		
	}
	
	public void goBfs(int i, int j) {
		int x, y, visitPoint;
		visitPoint = 0;
		point nowPoint;
		pointQueue.add(new point(i, j));
		visited[i][j] = true;
		visitPoint++;
		
		while(!pointQueue.isEmpty()) {
			nowPoint = pointQueue.remove();
			x = nowPoint.x;
			y = nowPoint.y;
			
			if(y+1 < n &&!visited[y+1][x] && map[y+1][x] == 1) {
				pointQueue.add(new point(y+1, x));
				visitPoint++;
				visited[y+1][x] = true;
			}
			if(y > 0 && !visited[y-1][x] && map[y-1][x] == 1) {
				pointQueue.add(new point(y-1, x));
				visitPoint++;
				visited[y-1][x] = true;
			}
			if(x+1 <n && !visited[y][x+1] && map[y][x+1] == 1) {
				pointQueue.add(new point(y, x+1));
				visitPoint++;
				visited[y][x+1] = true;
			}
			if(x > 0 && !visited[y][x-1] && map[y][x-1] == 1) {
				pointQueue.add(new point(y, x-1));
				visitPoint++;
				visited[y][x-1] = true;
			}
		}
		buildingCount++;
		buildingNum.add(visitPoint);
	}
	
	
	public void setNum() {
		buildingCount = 0;
		int i, j;
		String buildingLine;
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			n = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			visited = new boolean[n][n];
			//System.out.println(n);
			
			for(i = 0; i< n; i++) {
				st = new StringTokenizer(bf.readLine());
				buildingLine = st.nextToken();
				for(j=0; j<n; j++) {
					map[i][j] = Character.getNumericValue(buildingLine.charAt(j));
					visited[i][j] = false;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		for(i = 0; i< n; i++) {
			
			for(j=0; j<n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}*/
		
	}
	/*
	public static void main(String args[]) {
	
		BuildingNum2667 mains = new BuildingNum2667();
		mains.setNum();
		mains.searchBuilding();
		
		mains.buildingNum.sort(Comparator.naturalOrder());
		
		System.out.println(mains.buildingCount);
		for(int i : mains.buildingNum) {
			System.out.println(i);
		}
		
	}*/
}
