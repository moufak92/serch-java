
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Board board = new Board();
        Game game = new Game();
        int mode;
        int[][] board1 = board.level();
        point farmer = game.searchFarmer(board1);
        State state = new State(board1, farmer);
        Logic logic = new Logic(game);
        while (true) {
        System.out.println("1 for player mode");
        System.out.println("2 for BFS");
        System.out.println("3 for DFS");
        System.out.println("4 for UCS");
        System.out.println("0 for exit game");

            mode = input.nextInt();
            switch (mode) {
                case 0 -> System.exit(0);
                case 1 -> logic.player(board1);
                case 2 -> logic.bfs(state);
                case 3 -> logic.dfs(state);
                case 4 -> logic.ucs(state);
                default -> System.out.println("Wrong selection");
            }


        }
    }
}





