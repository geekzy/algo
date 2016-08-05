#include <iostream>
using namespace std;

int T, N, M;
int map[20][20];
int distMap[20][20][4];
int goldMinePosX[4];
int goldMinePosY[4];

void floodFill(int x, int y, int distance, int mineIndex);

int main() {
	//cin >> T;
	//for (int testCase = 1; testCase <= T; testCase++) {
		cin >> N >> M; // N=mapSize M=goldMines

		// read the goldMines position
		for (int i = 0; i < M; i++) {
			cin >> goldMinePosY[i] >> goldMinePosX[i];
			goldMinePosY[i]--; // normalize to (0, 0)
			goldMinePosX[i]--;
		}

		// read the map + clear distMap
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				cin >> map[x][y];
				for (int i = 0; i < M; i++) distMap[x][y][i] = -1;
			}
		}
		
		// push goldMines position to the map
		for (int i = 0; i < M; i++) {
			map[goldMinePosX[i]][goldMinePosY[i]] = 2;
		}

		// do flood fill from each goldMines position to get distance map info
		for (int i = 0; i < M; i++) { 
			floodFill(goldMinePosX[i], goldMinePosY[i], 0, i);
		}

		// use distMap info to get smallest maxDistance to each goldMines
		int minDistances = N*N+1;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				// if is not road, skip its info
				if (map[x][y] == 0) continue;

				// if pos is goldMines, skip its info
				if (map[x][y] == 2) continue;

				int maxDistance = 0;
				for (int i = 0; i < M; i++) 
					if (distMap[x][y][i] > maxDistance) maxDistance = distMap[x][y][i];
				if (maxDistance < minDistances)	{ 
					minDistances = maxDistance;
					//// debug
					//cout << "--" << testCase;
					//for (int i = 0; i < M; i++) cout << " " << i << ":" <<  distMap[x][y][i];
					//cout << endl;
				}
			}
		}

		//cout << "#" << testCase << " " << minDistances << endl;
		cout << minDistances << endl;
	//}

	return 0;
}



void floodFill(int x, int y, int distance, int mineIndex) {
	// if pos is out of map boundary, skip!
	if ((x < 0) || (x >= N) || (y < 0) || (y >= N)) return; 

	// if pos is not a road, skip!
	if (map[x][y] == 0) return;
	
	// else, if this pos is unvisited or already visited but is of longer path
	// give/update the distance info then continue the recursive procedure
	if ((distMap[x][y][mineIndex] == -1) || (distMap[x][y][mineIndex] > distance))  {
		distMap[x][y][mineIndex] = distance;
		floodFill(x  , y-1, distance+1, mineIndex); // up
		floodFill(x  , y+1, distance+1, mineIndex); // down
		floodFill(x-1, y  , distance+1, mineIndex); // left
		floodFill(x+1, y  , distance+1, mineIndex); // right 
	}
}