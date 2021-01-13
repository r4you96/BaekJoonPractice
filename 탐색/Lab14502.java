import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Lab14502 {
	int width, height;
	int minimumInfested;
	int infestedCount;
	int startInfestedCount;
	boolean[][] startVisited;
	boolean[][] visited;
	int[][] startMap;
	int[][] copyMap;
	ArrayList<Points> startPointArrayList = new ArrayList<>();
	Queue<Points> pointQueue = new LinkedList<>();
	
	
	public void infestedStart() {
		Points nowPoint;
		int x,y, i,j;
		clear();
		
		while(!pointQueue.isEmpty()) {
			nowPoint = pointQueue.remove();
			x = nowPoint.x;
			y = nowPoint.y;
			
			if(y+1<height && copyMap[y+1][x] == 0) {

				infestedCount++;
				copyMap[y+1][x] = 2;
				pointQueue.add(new Points(y+1, x));
			}
			if(y - 1 >= 0 && copyMap[y-1][x] == 0) {

				infestedCount++;
				copyMap[y-1][x] = 2;
				pointQueue.add(new Points(y-1, x));
			}
			if(x + 1<width && copyMap[y][x+1] == 0) {

				infestedCount++;
				copyMap[y][x+1] = 2;
				pointQueue.add(new Points(y, x+1));
			}
			if(x - 1 >= 0 && copyMap[y][x-1] == 0) {
				infestedCount++;
				copyMap[y][x-1] = 2;
				pointQueue.add(new Points(y, x-1));
			}

		}
		
		if(infestedCount < minimumInfested) {
			minimumInfested = infestedCount;
		}
		
		return;
	}
	
	
	public void makeWall(int count, int y, int x) {
		int i, j;
		startMap[y][x] = 1;
		
		
		if(count == 3) {		
			infestedStart();
			
			startMap[y][x] = 0;
			return;
		}
		
		for(i = y; i<height; i++) {
			for(j = (i==y) ? x : 0;j<width; j++) {
				if(startMap[i][j] == 0) {
					makeWall(count + 1, i, j);
				}
			}
		}	
		startMap[y][x] = 0;

		return;
	}
	
	public class Points{
		int x, y;
		public Points(int y, int x) {
			this.x = x;
			this.y = y;
 		}
	}
	
	public void clear() {
		int i, j;
		for(i=0; i<height; i++) {
			for(j = 0; j<width; j++) {
				copyMap[i][j] = startMap[i][j];
			}
		}
		for(Points pt : startPointArrayList) {
			pointQueue.add(pt);
		}
		infestedCount = startInfestedCount + 3;
	}
	
	public void setNum() {
		int i, j;
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			height = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			
			copyMap = new int[height][width];
			startMap = new int[height][width];
			startVisited = new boolean[height][width];
			
			minimumInfested = height * width + 1;
			
			//Arrays.fill(startMap, 0);
			
			for(i=0; i<height; i++)
				Arrays.fill(startVisited[i], false);
			
			for(i =0; i<height; i++) {
				st = new StringTokenizer(bf.readLine());
				for(j=0; j<width; j++) {
					startMap[i][j] = Integer.parseInt(st.nextToken());
					if(startMap[i][j] == 2) {
						startInfestedCount++;
						startPointArrayList.add(new Points(i, j));
						startVisited[i][j] = true;
					}else if(startMap[i][j] == 1)
						startInfestedCount++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public static void main(String[] args) {
		int i, j;
		// TODO Auto-generated method stub
		Main mains = new Main();
		mains.setNum();
		for(i=0; i<mains.height; i++) {
			for(j=0; j<mains.width; j++) {
				if(mains.startMap[i][j] == 0)
					mains.makeWall(1, i, j);
			}
		}
		
		System.out.println(mains.height * mains.width - mains.minimumInfested);
	}*/

}
