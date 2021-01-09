import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DfsBfs1260 {
	int pointNum, lineNum, starts;
	boolean[][] graph = new boolean[1001][1001];
	boolean[] visited = new boolean[1001];
	Queue<Integer> queues = new LinkedList<Integer>();
	ArrayList<Integer> sisi = new ArrayList<>();

	public void bfs() {
		int i, j, nextPoint;

		while (!queues.isEmpty()) {
			nextPoint = queues.poll();
			for (i = 1; i <= pointNum; i++) {
				if (graph[nextPoint][i] && !visited[i]) {
					System.out.print(i + " ");
					queues.add(i);
					visited[i] = true;
				}
			}
		}
	}

	public void bfsStart(int starts) {
		queues.add(starts);
		System.out.print(starts + " ");
		visited[starts] = true;

		bfs();

		return;
	}

	public void dfs(int starts) {
		int i, j;

		if (visited[starts])
			return;

		System.out.print(starts + " ");
		visited[starts] = true;
		for (i = 1; i <= pointNum; i++) {
			if (graph[starts][i] && !visited[i])
				dfs(i);
		}

		return;
	}

	public void clear() {
		int i;
		for (i = 0; i <= pointNum; i++)
			visited[i] = false;
	}

	public void setNum() {
		int i;
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(bf.readLine());
			pointNum = Integer.parseInt(st.nextToken());
			lineNum = Integer.parseInt(st.nextToken());
			starts = Integer.parseInt(st.nextToken());

			for (i = 0; i < lineNum; i++) {
				StringTokenizer temp = new StringTokenizer(bf.readLine());
				int x = Integer.parseInt(temp.nextToken()), y = Integer.parseInt(temp.nextToken());
				graph[x][y] = true;
				graph[y][x] = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String args[]) {
		DfsBfs1260 dab = new DfsBfs1260();
		dab.setNum();
		
		dab.clear(); 
		dab.dfs(dab.starts); 
		dab.clear(); 
		System.out.println();
		dab.bfsStart(dab.starts);
		
	}
}