import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Cheeze2638 {
	int height, width, cheezeNum = 0, deleteCount, time;
	ArrayList<Point> cheezeList = new ArrayList<>();
	Queue<Point> surfaceQueue = new LinkedList<>();
	int[][] map;
	boolean[][] isNotVisted;
	boolean canDelete;
	int[] xDir = { 0, 0, -1, 1 };
	int[] yDir = { -1, 1, 0, 0 };

	public class Point {
		int x, y;

		public Point(int y, int x) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	public void bfsCheeze() {
		int i, j, x, y, queueSize;
		Point nowPoint;
		for (i = 0; i < height; i++)
			Arrays.fill(isNotVisted[i], true);

		surfaceQueue.add(new Point(0, 0));
		isNotVisted[0][0] = false;
		deleteCount = 0;
		time++;
		
		while (!surfaceQueue.isEmpty()) {
			nowPoint = surfaceQueue.remove();

			for (i = 0; i < 4; i++) {
				x = nowPoint.x + xDir[i];
				y = nowPoint.y + yDir[i];

				if (x >= 0 && x < width && y >= 0 && y < height && map[y][x] == 0 && isNotVisted[y][x]) {
					surfaceQueue.add(new Point(y, x));
					isNotVisted[y][x] = false;
				} else if (x >= 0 && x < width && y >= 0 && y < height && map[y][x] == 1 && isNotVisted[y][x]) {
					cheezeList.add(new Point(y, x));
					isNotVisted[y][x] = false;
				}
			}
		}
		
		int checkCount;
		for(Point tmp : cheezeList) {
			checkCount = 0;
			for (i = 0; i < 4; i++) {
				x = tmp.x + xDir[i];
				y = tmp.y + yDir[i];
				
				if(x >= 0 && x < width && y >= 0 && y < height && map[y][x] == 0 && !isNotVisted[y][x]) {
					checkCount++;
				}
			}
			
			if(checkCount >= 2) {
				isNotVisted[tmp.y][tmp.x] = true;
				map[tmp.y][tmp.x] = 0;
				deleteCount++;
			}
		}
		cheezeList.clear();
		
		cheezeNum -= deleteCount;
		if(cheezeNum == 0)
			canDelete = false;
	}

	public void setNum() throws IOException {
		int i, j;

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());

		map = new int[height][width];
		isNotVisted = new boolean[height][width];
		canDelete = true;
		time = 0;
		
		for (i = 0; i < height; i++) {
			st = new StringTokenizer(bf.readLine());
			for (j = 0; j < width; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					cheezeNum++;
			}
		}
	}

	/*public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Cheeze2638 main = new Cheeze2638();
		main.setNum();
		
		while(main.canDelete) {
			main.bfsCheeze();
		}
		System.out.println(main.time);
		//System.out.println(main.deleteCount);
	}*/

}
