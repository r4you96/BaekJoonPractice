import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Tetromino14500 {
	int m, n;
	int[][] map;
	Point[][] tetrominos;
	boolean[][] isNotVisited;
	int[] dirY = {-1,1,0,0};
	int[] dirX = {0,0,-1,1};
	int maxSum = Integer.MIN_VALUE;
	
	public void startTetrominos() {
		int i,j;
		
		for(i=0; i<4; i++)
			Arrays.fill(isNotVisited[i], true);
		
		for(i=0; i<m; i++) {
			for(j=0; j<n; j++) {
				isNotVisited[i][j] = false;
				setTetrominos(0, 0, i, j);
				blockFour(-1, -1 ,map[i][j], i, j);
				isNotVisited[i][j] = true;
			}
		}
		
		System.out.println(maxSum);
	}
	
	public void setTetrominos(int count, int sum ,int y, int x) {
		int i, j, nextX, nextY;
		
		
		if(count == 4) {
			/*for(i=0; i<m; i++) {
				for(j=0; j<n; j++)
					System.out.print(isNotVisited[i][j] + " ");
				System.out.println();
			}
			System.out.println();*/
			
			if(maxSum < sum)
				maxSum = sum;
			
			return;
		}
		
		for(i = 0; i<4; i++) {
			nextX = x + dirX[i];
			nextY = y + dirY[i];
			
			if(nextY >= 0 && nextY <m && nextX >= 0 && nextX <n && isNotVisited[nextY][nextX]) {
				isNotVisited[y][x] = false;
				setTetrominos(count+1, sum + map[nextY][nextX], nextY, nextX);
				isNotVisited[y][x] = true;
			}
		}
	}
	
	public void blockFour(int count, int num,int sum, int y, int x) {
		int i, j, nextX, nextY;
		
		if(count == 2) {
			if(maxSum < sum)
				maxSum = sum;
			
			return;
		}
					
		for(i = num+1; i<4; i++) {
			nextX = x + dirX[i];
			nextY = y + dirY[i];
			
			if(nextY >= 0 && nextY <m && nextX >= 0 && nextX <n) {
				blockFour(count+1, i ,sum + map[nextY][nextX], y, x);
			}
		}
	}
	
	public void setNum() throws IOException{
		int i, j;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		isNotVisited = new boolean[m][n];
		
		for(i = 0; i< m; i++) {
			st  = new StringTokenizer(bf.readLine());
			for(j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Tetromino14500 main = new Tetromino14500();
		main.setNum();
		main.startTetrominos();
	}

}
