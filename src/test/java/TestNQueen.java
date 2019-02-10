import org.junit.Assert;
import org.junit.Test;

public class TestNQueen {
    private void printDistance(int distance[][]) {
        for(int i = 0; i < distance.length; i++) {
            System.out.printf("%02d -> ", i);
            for(int k = 0; k < distance.length; k++) {
                if(k > i) {
                    if(distance[i][k] >= 0) {
                        System.out.print("|0" + distance[i][k]);
                    } else {
                        System.out.print("|" + distance[i][k]);
                    }
                } else {
                    System.out.print("|**");
                }
            }
            System.out.println("|");
        }
        System.out.println();
    }

    private int[][] calcDistance(int queens[]) {
        int distance[][] = new int[queens.length][queens.length];

        for(int i = 0; i < distance.length; i++) {
            for(int j = 0; j < i; j++) {
                distance[j][i] = queens[i] - queens[j];
            }
        }

        return distance;
    }

    private int[][] calcDistance(int board[][]) {
        int queens[] = new int[board.length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if(board[i][j] > 0) {
                    queens[i] = j;
                }
            }
        }

        return calcDistance(queens);
    }

    @Test
    public void checkAngleConditions() {
        NQueen q = new NQueen();
        {
            int board[][] = {
                    {1, 0, 0, 0, 0, 0, 0, 0}, //<- 0
                    {0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0}, //<- 2
                    {0, 0, 0, 0, 0, 0, 1, 0}, //<- 3
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            };
            int distance[][] = calcDistance(board);

            Assert.assertFalse("Incorrect result in angle condition", q.checkAngleCondition(distance, 3));
        }
        {
            int board[][] = {
                    {0, 1, 0, 0, 0, 0, 0, 0}, //<- 0
                    {0, 0, 0, 1, 0, 0, 0, 0}, //<- 1
                    {1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 1}, //<- 3
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            };
            int distance[][] = calcDistance(board);

            Assert.assertFalse("Incorrect result in angle condition", q.checkAngleCondition(distance, 3));
        }
        {

            int board[][] = {
                    {0, 0, 0, 1, 0, 0, 0, 0}, //<- 0
                    {0, 0, 0, 0, 0, 0, 1, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0}, //<- 4
                    {0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 1, 0}, //<- 6
                    {0, 1, 0, 0, 0, 0, 0, 0},
            };
            int distance[][] = calcDistance(board);

            Assert.assertFalse("Incorrect result in angle condition", q.checkAngleCondition(distance, 7));
        }
        {
            int board[][] = {
                    {0, 0, 0, 0, 0, 0, 1, 0}, //<- 0
                    {0, 0, 0, 0, 1, 0, 0, 0}, //<- 1
                    {0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0}, //<- 3
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            };
            int distance[][] = calcDistance(board);

            Assert.assertFalse("Incorrect result in angle condition", q.checkAngleCondition(distance, 3));
        }
        {
            int board[][] = {
                    {0, 0, 0, 0, 0, 0, 0, 1}, //<- 0
                    {1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0}, //<- 2
                    {0, 1, 0, 0, 0, 0, 0, 0}, //<- 3
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
            };
            int distance[][] = calcDistance(board);

            Assert.assertFalse("Incorrect result in angle condition", q.checkAngleCondition(distance, 3));
        }
        {
            //IGNORE: violation of classic n-queen conditions, we are only checking angle-conditions
            int board[][] = {
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //<- 0
                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, //<- 6
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, //<- 9
            };
            int distance[][] = calcDistance(board);

            Assert.assertFalse("Incorrect result in angle condition", q.checkAngleCondition(distance, 9));

            int reverseBoard[][] = new int[board.length][board.length];
            for (int i = 0; i < board.length; i++) {
                System.arraycopy(board[i], 0, reverseBoard[board.length - i - 1], 0, board.length);
            }

            Assert.assertFalse("Incorrect result in angle condition", q.checkAngleCondition(reverseBoard, 9));
        }

        {
            //IGNORE: violation of classic n-queen conditions, we are only checking angle-conditions
            int board[][] = {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //<- 0
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, //<- 6
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, //<- 9
            };
            int distance[][] = calcDistance(board);

            Assert.assertFalse("Incorrect result in angle condition", q.checkAngleCondition(distance, 9));

            int reverseBoard[][] = new int[board.length][board.length];
            for (int i = 0; i < board.length; i++) {
                System.arraycopy(board[i], 0, reverseBoard[board.length - i - 1], 0, board.length);
            }

            Assert.assertFalse("Incorrect result in angle condition", q.checkAngleCondition(reverseBoard, 9));
        }
        {
            int board[][] = {
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0}, //<- 6
                {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0}, //<- 8 (2, 3)
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //<- 16 (8, 12)
                {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
                {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0}
            };
            int distance[][] = calcDistance(board);

            Assert.assertFalse("Incorrect result in angle condition", q.checkAngleCondition(distance, 24));
        }
    }


    public void printBoard(int board[]) {
        for(int i = 0; i < board.length; i++) {
            for(int k = 0; k < board.length; k++) {
                if(board[i] == k) {
                    System.out.print("|#");
                } else {
                    System.out.print("|_");
                }
            }
            System.out.println("|");
        }
        System.out.println();
    }


    @Test
    public void test3Queen() {
        int N = 3;
        NQueen q = new NQueen();
        NQueen.Result r = q.solve(N);

        if(r.isValid()) {
            System.out.println("Result for N=" + N);
            printBoard(r.board());

            int[][] distance = calcDistance(r.board());
            Assert.assertTrue("Incorrect result in angle condition",
                    q.checkAngleConditionBrute(distance, N - 1));
        } else {
            System.out.println("Not valid result for N=" + N);
            System.out.println();
        }
    }

    @Test
    public void test4Queen() {
        int N = 4;
        NQueen q = new NQueen();
        NQueen.Result r = q.solve(N);

        if(r.isValid()) {
            System.out.println("Result for N=" + N);
            printBoard(r.board());

            int[][] distance = calcDistance(r.board());
            Assert.assertTrue("Incorrect result in angle condition",
                    q.checkAngleConditionBrute(distance, N - 1));
        } else {
            System.out.println("Not valid result for N=" + N);
            System.out.println();
        }
    }

    @Test
    public void test5Queen() {
        int N = 5;
        NQueen q = new NQueen();
        NQueen.Result r = q.solve(N);

        if(r.isValid()) {
            System.out.println("Result for N=" + N);
            printBoard(r.board());

            int[][] distance = calcDistance(r.board());
            Assert.assertTrue("Incorrect result in angle condition",
                    q.checkAngleConditionBrute(distance, N - 1));
        } else {
            System.out.println("Not valid result for N=" + N);
            System.out.println();
        }
    }

    @Test
    public void test8Queen() {
        int N = 8;
        NQueen q = new NQueen();
        NQueen.Result r = q.solve(N);

        if(r.isValid()) {
            System.out.println("Result for N=" + N);
            printBoard(r.board());

            int[][] distance = calcDistance(r.board());
            Assert.assertTrue("Incorrect result in angle condition",
                    q.checkAngleConditionBrute(distance, N - 1));
        } else {
            System.out.println("Not valid result for N=" + N);
            System.out.println();
        }
    }

    @Test
    public void test9Queen() {
        int N = 9;
        NQueen q = new NQueen();
        NQueen.Result r = q.solve(N);

        if(r.isValid()) {
            System.out.println("Result for N=" + N);
            printBoard(r.board());

            int[][] distance = calcDistance(r.board());
            Assert.assertTrue("Incorrect result in angle condition",
                    q.checkAngleConditionBrute(distance, N - 1));
        } else {
            System.out.printf("Not valid result for N=" + N);
        }
    }

    @Test
    public void test10Queen() {
        int N = 10;
        NQueen q = new NQueen();
        NQueen.Result r = q.solve(N);

        if(r.isValid()) {
            System.out.println("Result for N=" + N);
            printBoard(r.board());

            int[][] distance = calcDistance(r.board());
            Assert.assertTrue("Incorrect result in angle condition",
                    q.checkAngleConditionBrute(distance, N - 1));
        } else {
            System.out.printf("Not valid result for N=" + N);
        }
    }

    @Test
    public void test25Queen() {
        int N = 25;
        NQueen q = new NQueen();
        NQueen.Result r = q.solve(N);

        if(r.isValid()) {
            System.out.println("Result for N=" + N);
            printBoard(r.board());

            int[][] distance = calcDistance(r.board());
            Assert.assertTrue("Incorrect result in angle condition",
                    q.checkAngleConditionBrute(distance, N - 1));
        } else {
            System.out.printf("Not valid result for N=" + N);
        }
    }
}
