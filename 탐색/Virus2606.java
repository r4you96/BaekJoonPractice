import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class Virus2606 {
	int n, lineNum, infestedCount;
	boolean[] visited;
	boolean[][] connected;
	
	
	public void spreadVirus(int start) {
		int i;
		if(visited[start])
			return;
		//System.out.println(start);
		visited[start] = true;
		infestedCount++;
		
		for(i=1; i<=n; i++) {
			if(!visited[i] && connected[start][i])
				spreadVirus(i);
		}
	}
	
	public void connect(int x, int y) {
		connected[x][y] = true;
		connected[y][x] = true;
	}
	
	public void setNum() {
		int i,j, started, ended;
		infestedCount = -1;
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			n = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(bf.readLine());
			lineNum = Integer.parseInt(st.nextToken());
			
			visited = new boolean[n+1];
			connected = new boolean[n+1][n+1];
			
			for(i=0; i<n; i++) {
				visited[i] = false;
				for(j=0; j<n; j++) {
					connected[i][j] = false;
				}
			}
			
			for(i=0; i<lineNum; i++) {
				st = new StringTokenizer(bf.readLine());
				started = Integer.parseInt(st.nextToken());
				ended = Integer.parseInt(st.nextToken());
				
				connect(started, ended);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Virus2606 mains = new Virus2606();
		mains.setNum();
		mains.spreadVirus(1);
		
		System.out.println(mains.infestedCount);
	}

}
