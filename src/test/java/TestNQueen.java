import org.junit.Test;

public class TestNQueen {
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
        } else {
            System.out.println("Not valid result for N=" + N);
            System.out.println();
        }
    }

    @Test
    public void tes8Queen() {
        int N = 8;
        NQueen q = new NQueen();
        NQueen.Result r = q.solve(N);

        if(r.isValid()) {
            System.out.println("Result for N=" + N);
            printBoard(r.board());
        } else {
            System.out.println("Not valid result for N=" + N);
            System.out.println();
        }
    }

    @Test
    public void tes10Queen() {
        int N = 10;
        NQueen q = new NQueen();
        NQueen.Result r = q.solve(N);

        if(r.isValid()) {
            System.out.println("Result for N=" + N);
            printBoard(r.board());
        } else {
            System.out.printf("Not valid result for N=" + N);
        }
    }
}
