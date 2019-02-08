public class NQueen {
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

        //'Angle condition':
        //3 queens a, b, c will form some angle if they will same horizontal distance (x-coordinate) and vertical distance (y-coordinate) between them.
        // i.e. x-coordinate difference between a,b will be equal to x-coordinate difference between b,c
        //      AND y-coordinate difference between a,b will be equal to y-coordinate difference between b,c
        //
        //As stated above (i, j)th entry in distance array contains distance between queen i and j queens.
        //
        //so we will first find out each queen's y-axis distance from one queen below (across x-axis) at increasing distance.
        //(i.e. first we will find out y-distance between queen at one x-distance apart, then two x-distance apart (j variable does that) and so on.
        //- 'distance[i][j]' indicates that value.
        //We also find out second queens distance from third queen at the same time.
        //- 'distance[j][j+(j-i)] indicates that value.
        //here we chosen third queen which is at j+(j-i) x-distance, i.e. (j-i) distance from second queen.
        //- so we have first and second at x-distance apart
        //- and second and third at same x-distance apart.
        //- now if their y-distance matches (i.e. distance[i][j] ==  distance[j][j+(j-i)]) then we have three queen at some some angle
        //  as described by 'Angle condition' above. hence we reject this position and find out some other position for current queen.
        for(int i = 0; i < row + 1; i++) {
            for(int j = i + 1; (2*j - i) < row + 1; j++) {
                if(distance[i][j] == distance[j][2*j - i]) {
                    return false;
                }
            }
        }

        return true;
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
