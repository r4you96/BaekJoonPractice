import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.print.attribute.standard.Finishings;

//백준 아기상어 16236
public class BabyShark16236 {
	int n , time = 0, fishNum = 0, eatCount = 0 ,sharkVal = 2;
	int[][] map;
	boolean[][] visited;
	boolean canEat = true;
	Points sharkPoint;
	ArrayList<Points> fishs = new ArrayList<>();
	Queue<Points> loadQueue = new LinkedList<>();
	
	//숫자 입력
	public void setNum() {
		int i, j;
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			n = Integer.parseInt(st.nextToken());
			
			map = new int[n][n];
			visited = new boolean[n][n];
			
			for(i = 0; i<n; i++) {
				st = new StringTokenizer(bf.readLine());
				
				for(j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(map[i][j] == 9) {
						sharkPoint = new Points(i, j);
						map[i][j] = 0;
					}else if(map[i][j] != 0) {
						fishNum++;
					}	
				}
			}
			
			/*for(i = 0; i<n; i++) {
				for(j=0; j<n; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.out.println();*/
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//다음 물고기 찾기
	public void searchFish() {
		int i,j, queueSize, x, y;
		Points nowPoint;
		loadQueue.add(sharkPoint);
		visited[sharkPoint.y][sharkPoint.x] = true;
		int distance = 0;
		

		while(!loadQueue.isEmpty()) {
			distance++;
			queueSize = loadQueue.size();
			
			for(i=0; i<queueSize; i++) {
				nowPoint = loadQueue.remove();
				
				x = nowPoint.x;
				y = nowPoint.y;
				
				if(y+1 < n && !visited[y+1][x] && map[y+1][x] <= sharkVal) {
					checkFish(y+1, x);
				}if(y -1 >= 0 && !visited[y-1][x] && map[y-1][x] <= sharkVal) {
					checkFish(y-1, x);
				}if(x + 1 < n &&!visited[y][x+1] && map[y][x+1] <= sharkVal) {
					checkFish(y, x+1);
				}if(x-1 >= 0 && !visited[y][x-1] && map[y][x-1] <= sharkVal) {
					checkFish(y, x-1);
				}
			}
			if(!fishs.isEmpty()) {
				break;
			}
		}
		loadQueue.clear();
		if(!fishs.isEmpty()) {
			time += distance;
		}else {
			canEat = false;
		}
		
	}
	
	public void eatFish() {
		int i, j;
		fishs.sort(Comparator.naturalOrder());
		Points eatFishPoint = fishs.get(0);
		
		sharkPoint = eatFishPoint;
		map[sharkPoint.y][sharkPoint.x] = 9;
		
		/*for(i = 0; i<n; i++) {
			for(j=0; j<n; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("시간"+time);*/
		
		map[sharkPoint.y][sharkPoint.x] = 0;
		
		
		eatCount++;
		fishNum--;
		if(eatCount%sharkVal == 0) {
			eatCount = 0;
			sharkVal++;
		}
		
		fishs.clear();
		for(i=0; i<n; i++)
			Arrays.fill(visited[i], false);
	}
	
	public void checkFish(int y, int x) {
		visited[y][x] = true;
		if(sharkVal > map[y][x] && map[y][x] != 0) {
			fishs.add(new Points(y, x));
		}else {
			loadQueue.add(new Points(y, x));
		}
	}
	
	public class Points implements Comparable<Points>{
		int x,y, distance;
		public Points(int y, int x) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Points o) {
			// TODO Auto-generated method stub
			
	
		if(this.y > o.y) {
			return 1;
		}else if(this.y < o.y) {
			return -1;
		}else if(this.y == o.y) {
			if(this.x > o.x) {
				return 1;
			}else{
				return -1;
			}
		}
		
		return 0;
		}		
	}
	
	
	
	public static void main(String args[]) {
		BabyShark16236 main = new BabyShark16236();
		main.setNum();
		boolean canEat;
		//System.out.println(main.fishNum);
		while(main.fishNum != 0) {
			main.searchFish();
			
			if(!main.canEat)
				break;
			else {
				main.eatFish();
			}
			/*System.out.println("남은 물고기 수 :" + main.fishNum);
			System.out.println("물고기 섭취 가능? : "+main.canEat);*/
		}
		System.out.println(main.time);
	
	}
}
