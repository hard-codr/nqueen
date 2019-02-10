import java.util.stream.Stream;

public class NQueen {
    /**
     * Check for angle condition
     * @param distance distance array (populate until row elements)
     * @param row how much of distances are populate
     * @return true if the NONE of the three queen are at some angle, false otherwise
     */
    boolean checkAngleCondition(int distance[][], int row) {
        int N = distance.length;
        //'Angle condition':
        //3 queens a, b, c will form some angle if they have same slope between them.
        // i.e. x-coordinate difference between a,b will be equal to (or fraction/multiple of) x-coordinate difference between b,c
        //      AND y-coordinate difference between a,b will be equal to (or SAME fraction/multiple of) y-coordinate difference between b,c
        //
        //As stated above (i, j)th entry in distance array contains distance between queen i and j queens.
        //
        //so we will first find out each queen's y-axis distance from one queen below (across x-axis) at increasing distance.
        //(i.e. first we will find out y-distance between queen at one x-distance apart, then two x-distance apart (j variable does that) and so on.
        //- 'distance[i][j]' indicates that value.
        //We also find out second queens some (j-i)*k distance from third queen at the same time. (k can be multiple or fraction)
        //- 'distance[j][j+(j-i)*k]' indicates that value.
        //- so we have first and second at x-distance apart
        //- and second and third at same k*x-distance apart. where (k = ... 1/4, 1/3, 1/2, 1, 2, 3, 4... etc)
        //- now if their y-distance matches by equation distance[i][j] ==  distance[j][j+(j-i)]*(1/k) then we have three queen at some some angle
        //  as described by 'Angle condition' above. hence we reject this position and find out some other position for current queen.
        for(int i = 0; i < row; i++) {
            for(int j = i + 1; j < row; j++) {

                int baseXDistance = j - i;
                if(j + baseXDistance < row + 1 && distance[i][j] == distance[j][j + baseXDistance]) {
                    //x-distance between a, b is equal to x-distance between b, c AND
                    //y-distance between a, b is equal to y-distance between b, c
                    return false;
                }

                int MAXFK = Math.min(baseXDistance, (row+1)/2);
                for(int k = 2; k <= MAXFK; k++) {
                    int fractionXDistance = baseXDistance / k;
                    //check whether j + fractions of baseXDistance are within range (row + 1)
                    //if j-i=8 then j + fractions will be j+4, j+2, etc
                    if (baseXDistance % k == 0 && j + fractionXDistance < row + 1) { //k which can evenly divides baseXDistance e.g. if j-i is 6 then, k will be 2, 3
                        int fractionYDistance = distance[j][j + fractionXDistance];
                        if (distance[i][j] == fractionYDistance * k) {
                            return false;
                        }
                    }
                }

                for(int k = 2; k <= (N-j)/(j-i); k++) {
                    int multipleXDistance = baseXDistance*k;
                    //check whether j + multiples of baseXDistance are within range (row + 1)
                    //if j-i=2 then j + multiples will be j+4, j+8, etc
                    if(j + multipleXDistance < row + 1) {
                        int multipleYDistance = distance[j][j + multipleXDistance];
                        if(distance[i][j]*k == multipleYDistance) {
                            return false;
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Brute force way to search whether any three queen form some angle. Always performs worse than checkAngleCondition above (for N > 20).
     * Used for checking the result of the checkAngleCondition.
     * @param distance distance array (populate until row elements)
     * @param row how much of distances are populate
     * @return true if the NONE of the three queen are at some angle, false otherwise
     */
    boolean checkAngleConditionBrute(int[][] distance, int row) {
        for(int i = 0; i < row + 1; i++) {
            for (int j = i + 1; j < row + 1; j++) {
                for (int k = j + 1; k < row + 1; k++) {
                    int distXij = j - i;
                    int distXjk = k - j;
                    int distYij = distance[i][j];
                    int distYjk = distance[j][k];

                    if(distXij == distXjk) {
                        if (distYij == distYjk) {
                            return false;
                        }
                    } else if(distXij > distXjk && distXij % distXjk == 0) {
                        if(distYij % distYjk == 0 && distXij/distXjk == distYij/distYjk) {
                            return  false;
                        }
                    } else if(distXij < distXjk && distXjk % distXij == 0) {
                        if(distYjk % distYij == 0 && distXjk/distXij == distYjk/distYij) {
                            return  false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * Check whether it is ok to place queen in 'row'th row at 'col'th column on chessboard.
     * Assumption: All the previous queens are in correct position as indicated by the board array.
     * ith value of the 'board' indicate the ith queen at (i+1, board[i]+1) coordinate on chessboard.
     *
     * Aux. data - distance is 2d-array whose distance[i][j] indicating the distance of ith queen from jth queen.
     * - Distance could be negative, which indicates queen is at the left side.
     * - All diagonal values will be 0
     * - Bottom-left area below diagonal will not be used.
     */
    private boolean isValid(int board[], int N_, int row, int col, int distance[][]) {
        for(int i = 0; i < row; i++) {
            //current queen is in same column as the ith-queen hence not valid
            if(board[i] == col) return false;

            //find the angle between the current queen and i-th queen
            int vertical = row - i;
            int horizontal = Math.abs(board[i] - col);
            //if vertical==horizontal then both are at 45 (or 135) degree and hence not valid
            if(vertical == horizontal) return false;
        }

        for(int i = 0; i < row; i++) {
            //since we have tentative y-coordinate of x'th queen (where location = col)
            //populate the distance of this queen for each previous queen
            distance[i][row] = col - board[i];
        }

        return checkAngleCondition(distance, row);
    }

    private boolean solveInner(int board[], int row, int distance[][]) {
        int N = board.length;
        for(int j = 0; j < N; j++) {
            //if valid then we found place for row-th queen
            if (isValid(board, N, row, j, distance)) {
                board[row] = j;

                if (row == N - 1) {
                    //reached at the end of the board. termination condition of the recursion.
                    return true;
                } else if (solveInner(board,row + 1, distance)) {
                    //propagate termination condition up to top stack
                    return true;
                } //else row-1th queen didn't find the valid position with current queen's position,
                  // try different position for current queen.
            }
        }
        //didn't find the valid position in this row, backtrack to row-1
        return false;
    }

    public Result solve(int N) {
        int board[] = new int[N];
        int distance[][] = new int[N][N];

        if(!solveInner(board,0, distance)) {
            for(int i=0; i<N; i++) {
                board[i] = -1;
            }
            return new Result(false, board);
        }
        return new Result(true, board);
    }

    public static class Result {
        private boolean isValid;
        private int board[];

        public Result(boolean isValid, int board[]) {
            this.isValid = isValid;
            this.board = board;
        }

        public boolean isValid() {
            return isValid;
        }

        public int[] board() {
            return board;
        }
    }
}
