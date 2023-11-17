import java.util.*;
import java.util.Scanner;

public class Logic {

    Game game;


    Logic(Game game){
        this.game = game;
    }

    public void player(int [][]board){

       point farmer= game.searchFarmer(board);
       State state=new State(board, farmer);
        game.printBoard(state);
        Scanner input=new Scanner(System.in);
        int move_player;
        while (!game.endGame(board)) {
             farmer= game.searchFarmer(board);

            game.moveOne(board);
            System.out.println("Enter_move");

            move_player=input.nextInt();
            switch (move_player) {
                case 6 -> game.moveRight(board, farmer);
                case 4 -> game.moveLeft(board, farmer);
                case 8 -> game.moveUp(board, farmer);
                case 2 -> game.moveDown(board, farmer);
                default -> System.out.println("Wrong selection");
            }
        }
    }

    public void bfs(State node){

    Queue<State> q = new LinkedList<>();
    HashSet<State> visited = new HashSet<>();
        q.add(node);
        visited.add(node);
        int numerofnodesvisited = 0;
        while (!q.isEmpty()){
            System.out.println(numerofnodesvisited++);
            State s=q.poll();
            assert s != null;
            game.printBoard(s);
            if(game.endGame(s.board)){
                System.out.println("end of the game");
                System.out.println("Cost : "+s.cost);
                return ;
            }
            ArrayList<State> states=game.getNextState(s.board,++s.cost);
            for (State state : states) {
                if (!visited.contains(state)) {
                    q.add(state);
                    visited.add(state);
                }

            }

        }

    }
    public void dfs(State node){

        Stack<State> stack = new Stack<>();

        HashSet<State> visited = new HashSet<>();

        stack.push(node);

        visited.add(node);

        int numerofnodesvisited = 0;
        while (!stack.isEmpty()){
            System.out.println(numerofnodesvisited++);
            State s=stack.pop();
            game.printBoard(s);
            if(game.endGame(s.board)){
                System.out.println("end of the game");
                System.out.println("Cost : "+s.cost);


                return ;
            }
            ArrayList<State> states=game.getNextState(s.board,++s.cost);
            for (State state : states) {
                if (!visited.contains(state)) {
                    stack.push(state);
                    visited.add(state);

                }
            }

        }
    }

    public State getv(State state,HashSet<State> p) {
        for (State s : p) {
            if (s.equals(state)) {
                return s;
            }
        }
        return null;
    }

    public void path(State state){
            while(state.parent!=null){
                System.out.println();
                game.printBoard(state);
                state = state.parent;
            }
    }

        public void ucs(State node){

        PriorityQueue<State> pq =new PriorityQueue<>();

        HashSet<State> visited = new HashSet<>();

        node.parent=null;
        pq.add(node);

        int numerofnodesvisited = 0;
        while (!pq.isEmpty()){
            numerofnodesvisited++;
            State s=pq.poll();
            assert s != null;
            //game.printBoard(s);
            if(game.endGame(s.board)){
                path(s);
                System.out.println(numerofnodesvisited);
                System.out.println("end of the game");
                System.out.println("Cost : "+s.cost);
                return ;
            }

            ArrayList<State> states=game.getNextState(s.board,++s.cost);
            visited.add(s);
            for (State state : states) {
                state.parent=s;
                if (!visited.contains(state)) {
                    pq.add(state);
                    visited.add(state);

                }else{
                    State oldState = getv(state, visited);

                    if (oldState.cost > state.cost) {
                        oldState.cost=state.cost;
                        oldState.parent=state.parent;
                        pq.add(oldState);

                    }
                }
            }

        }
    }
}
