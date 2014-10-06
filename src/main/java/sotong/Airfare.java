package com.srin.sotong.cases;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Budi Oktaviyan Suryanto (b.suryanto@samsung.com)
 */
public class Airfare {
    static int N, destination, minimum, cost;
    static int[][] X;
    static int[] Y, Z;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < T; tc++) {
            destination = 0;
            N = Integer.parseInt(br.readLine().trim());
            X = new int[N][N];
            Y = new int[N + 1];
            Z = new int[N];

            for (int i = 0; i < N; i++) {
                String[] ith = br.readLine().trim().split(" ");

                for (int j = 0; j < ith.length; j++) {
                    X[i][j] = Integer.parseInt(ith[j]);
                }

                Z[i] = 0;
            }

            Y[0] = 0;
            cost = 0;
            calculateCost(1, 0);
            System.out.println(minimum);
        }

        br.close();
    }

    public static void calculateCost(int i, int e) {
        if (i == N) {
            if (X[Y[i - 1]][e] > 0) {
                Y[i] = e;
                destination++;

                if (destination < 2) {
                    minimum = airFare();
                } else {
                    int airFare = airFare();
                    if (airFare < minimum) {
                        minimum = airFare;
                    }
                }
            }
            return;
        }

        for (int j = 0; j < N; j++) {
            if (j != e && X[Y[i - 1]][j] > 0 && Z[j] == 0) {
                if (destination > 1 && cost + X[Y[i - 1]][j] > minimum) {
                    continue;
                }

                cost += X[Y[i - 1]][j];
                Y[i] = j;
                Z[j] = 1;
                calculateCost(i + 1, e);
                Z[j] = 0;
                cost -= X[Y[i - 1]][j];
            }
        }
    }

    public static int airFare() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            result += X[Y[i]][Y[i + 1]];
        }
        return result;
    }
}