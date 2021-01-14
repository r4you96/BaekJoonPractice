import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class GetDomain2583 {
	int height, width, rectNum;
	Rectangle[] rectArray;
	ArrayList<Integer> rectSize = new ArrayList<>();
	int[][] map;
	boolean[][] isNotVisited;
	Queue<Point> pointQueue = new LinkedList<>();
	Point[] bfsPoint = {new Point(-1, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1)};  
	
	public class Point{
		int x, y;
		public Point(int y, int x) {
			// TODO Auto-generated constructor stub
			this.y = y;
			this.x = x;
		}
	}
	
	public class Rectangle{
		int a, b, c, d;
		public Rectangle(int a, int b, int c, int d) {
			// TODO Auto-generated constructor stub
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
		}
	}
	
	public void setNum() {
		int i, a, b, c, d;
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			height = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			rectNum = Integer.parseInt(st.nextToken());
			
			rectArray = new Rectangle[rectNum];
			map = new int[height][width];
			isNotVisited = new boolean[height][width];

			for(i=0; i<height; i++)
				Arrays.fill(isNotVisited[i], true);
			
			for(i = 0; i<rectNum; i++) {
				st = new StringTokenizer(bf.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				
				rectArray[i] = new Rectangle(a, b, c, d);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void drawRect() {
		int i, j, k;
		Rectangle rect;
		
		for(k =0 ;k <rectArray.length; k++) {
			rect = rectArray[k];
			for(i = rect.a; i< rect.c; i++) {
				for(j = rect.b; j<rect.d; j++) {
					map[j][i] = 1;
				}
			}
		}
		
		/*for(i=0; i<height; i++) {
			for(j=0; j<width; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();*/
	}
	
	public void bfsRect(int y, int x) {
		int i, size = 1, nowX, nowY;
		Point nowPoint;
		isNotVisited[y][x] = false;
		pointQueue.add(new Point(y, x));
		
		while(!pointQueue.isEmpty()) {
			nowPoint = pointQueue.remove();
			
			for(i = 0; i< 4; i++) {
				nowX = nowPoint.x + bfsPoint[i].x;
				nowY = nowPoint.y + bfsPoint[i].y;
				
				if(nowX >= 0 && nowX < width) {
					if(nowY >= 0 && nowY < height) {
						if(map[nowY][nowX] == 0 && isNotVisited[nowY][nowX]) {
							isNotVisited[nowY][nowX] = false;
							pointQueue.add(new Point(nowY, nowX));
							size++;
						}
					}
				}
			}
		}
		
		rectSize.add(size);
	}
	
	
	/*public static void main(String[] args) {
		int i, j;
		GetDomain2583 main =new GetDomain2583();
		main.setNum();
		main.drawRect();
		
		for(i= 0; i< main.height; i++) {
			for(j=0; j<main.width; j++) {
				if(main.map[i][j] == 0 && main.isNotVisited[i][j])
					main.bfsRect(i, j);
			}
		}
		main.rectSize.sort(Comparator.naturalOrder());
		
		System.out.println(main.rectSize.size());
		for(int sizes : main.rectSize)
			System.out.print(sizes + " ");	
	}*/
}
