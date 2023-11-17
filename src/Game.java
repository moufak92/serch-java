
import java.util.ArrayList;



public class Game {

    public ArrayList<point> searchSeed(int [][]board){
        ArrayList<point> seed=new ArrayList<>();
        for (int x = 1; x < board.length; x++) {
            for (int y = 1; y < board[0].length; y++) {
                if (board[x][y] == 2) {
                    seed.add(new point(x,y));
                }
            }
        }
        return seed;
    }

    public ArrayList<point> searchHole(int [][]board){
        ArrayList<point> hole=new ArrayList<>();
        for (int x = 1; x < board.length; x++) {
            for (int y = 1; y < board[0].length; y++) {

                if (board[x][y] == 3 ||board[x][y]==5 ) {
                    hole.add(new point(x,y));
                }
            }
        }
        if(hole.isEmpty())
        {
            return null;
        }else {
            return hole;
        }
    }

    public int heuristic(int [][]board){
        ArrayList<point> seed=searchSeed(board);
        ArrayList<point> hole=searchHole(board);
        point farmer=searchFarmer(board);

        int xoffamerseed= Math.abs(farmer.x- seed.get(0).x);
        int yoffamerseed= Math.abs(farmer.y- seed.get(0).y);
        int xoffamerseed1= Math.abs(farmer.x- seed.get(1).x);
        int yoffamerseed1= Math.abs(farmer.y- seed.get(1).y);
       int xofssedhoel0 = Math.abs(seed.get(0).x-hole.get(0).x);
        int yofssedhoel0 = Math.abs(seed.get(0).y-hole.get(0).y);
        int xofssedhoel1 = Math.abs(seed.get(1).x-hole.get(1).x);
        int yofssedhoel1 = Math.abs(seed.get(1).y-hole.get(1).y);

        return xoffamerseed+yoffamerseed+xoffamerseed1+yoffamerseed1+xofssedhoel0+yofssedhoel0+xofssedhoel1+yofssedhoel1;
    }

    public void printBoard(State state){
        for (int[] ints : state.board) {
            for (int anInt : ints) {
                switch (anInt) {
                    case 0 -> System.out.print("land  " + " ");
                    case 1, 5 -> System.out.print("farmer" + " ");
                    case 2 -> System.out.print("seed  " + " ");
                    case 3 -> System.out.print("hole  " + " ");
                    case 6 -> System.out.print("plant" + " ");
                    case 8 -> System.out.print("  " );
                    case 9 -> System.out.print("block " + " ");
                }
            }
            System.out.println();
        }
    }

    public point searchFarmer(int[][] board) {
        for (int x = 1; x < board.length; x++) {
            for (int y = 1; y < board[0].length; y++) {
                if (board[x][y] == 1) {
                    return new point(x, y);
                }
                if (board[x][y] == 5) {
                    return new point(x, y);
                }

            }
        }
        return null;
    }

    public boolean moveOnRight(int[][] board, point farmer) {
        if (board[farmer.x][farmer.y + 1] != 9 && board[farmer.x][farmer.y + 1] != 8) {
            if (board[farmer.x][farmer.y + 1] == 2) {
                if (board[farmer.x][farmer.y + 2] == 2 || board[farmer.x][farmer.y + 2] == 9 || board[farmer.x][farmer.y + 2] == 8) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean moveOnLeft(int[][] board, point farmer) {
    if (board[farmer.x][farmer.y - 1] != 9 && board[farmer.x][farmer.y - 1] != 8) {
            if (board[farmer.x][farmer.y - 1] == 2) {
                if (board[farmer.x][farmer.y - 2] == 2 || board[farmer.x][farmer.y - 2] == 9 || board[farmer.x][farmer.y - 2] == 8) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean moveOnUp(int[][] board, point farmer) {
        if (board[farmer.x - 1][farmer.y] != 9 && board[farmer.x - 1][farmer.y] != 8) {
            if (board[farmer.x - 1][farmer.y] == 2) {
                if (board[farmer.x - 2][farmer.y] == 2 || board[farmer.x - 2][farmer.y] == 9 || board[farmer.x - 2][farmer.y] == 8) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean moveOnDown(int[][] board, point farmer) {
        if (board[farmer.x + 1][farmer.y] != 9 && board[farmer.x + 1][farmer.y] != 8) {
            if (board[farmer.x + 1][farmer.y] == 2) {
                if (board[farmer.x + 2][farmer.y] == 2 || board[farmer.x + 2][farmer.y] == 9 || board[farmer.x + 2][farmer.y] == 8) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public ArrayList<point> moveOne(int[][] board) {
        ArrayList<point> points = new ArrayList<>();

        point farmer=searchFarmer(board);
        if (moveOnRight(board, farmer)) {
            points.add(new point(farmer.x,farmer.y+1, farmer.x, farmer.y+2));
           // System.out.println("Can move right");
        }
        if (moveOnLeft(board, farmer)) {
            points.add(new point(farmer.x,farmer.y-1,farmer.x,farmer.y-2));
          //  System.out.println("Can move lift");
        }
        if (moveOnUp(board, farmer)) {
            points.add(new point(farmer.x-1,farmer.y,farmer.x-2,farmer.y));
           // System.out.println("Can move Up");
        }
        if (moveOnDown(board, farmer)) {
            points.add(new point(farmer.x+1,farmer.y,farmer.x+2,farmer.y));
          //  System.out.println("Can move Down");
        }
        return points;
    }

    public int[][] copyBord(int[][] board) {
        int[][] new_board = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                new_board[i][j] = board[i][j];
            }
        }
        return new_board;
    }

    public void moveRight(int[][] board, point farmer) {
        State state=new State(board,farmer);
        if (moveOnRight(board, farmer)) {
            point p1, p2, p3;
            p1 = new point(farmer.x, farmer.y);
            p2 = new point(farmer.x, farmer.y + 1);
            p3 = new point(farmer.x, farmer.y + 2);
            state= new State(move(board, p1, p2, p3),farmer);

            printBoard(state);
        }
        else {
            printBoard(state);
        }
    }


    public void moveLeft(int[][] board, point farmer) {
        State state=new State(board,farmer);
        if (moveOnLeft(board, farmer)) {

            point p1, p2,p3;
            p1 = new point(farmer.x, farmer.y);
            p2 = new point(farmer.x, farmer.y - 1);
            p3 = new point(farmer.x, farmer.y - 2);
            move(board, p1, p2, p3);
            state= new State(move(board, p1, p2, p3),farmer);
            printBoard(state);
        }
        else {
            printBoard(state);
        }
    }

    public void moveUp(int[][] board, point farmer) {
        State state=new State(board,farmer);
        if (moveOnUp(board, farmer)) {
            point p1, p2, p3;
            p1 = new point(farmer.x, farmer.y);
            p2 = new point(farmer.x-1, farmer.y);
            p3 = new point(farmer.x - 2, farmer.y);
            move(board, p1, p2, p3);
            state= new State(move(board, p1, p2, p3),farmer);
            printBoard(state);
        }
        else {
            printBoard(state);
        }
    }
    public void moveDown(int[][] board, point farmer) {
        State state=new State(board,farmer);
        if (moveOnDown(board, farmer)) {

            point p1, p2,p3;
            p1 = new point(farmer.x, farmer.y);
            p2 = new point(farmer.x+1, farmer.y);
            p3 = new point(farmer.x + 2, farmer.y);
            move(board, p1, p2, p3);
            state= new State(move(board, p1, p2, p3),farmer);
            printBoard(state);
        }
        else {
            printBoard(state);
        }

    }

    public boolean endGame(int[][] board) {
       ArrayList<point>hole= searchHole(board);
         if (hole==null) {
            System.out.print("YOU WIN \n End Game");
            return true;
        }
        return false;
    }

    public int [][] move(int[][] board, point first, point second, point third) {


        int source = board[first.x][first.y];
        int dest = board[second.x][second.y];
        if (source == 1) {
            if (dest == 0) {
                board[second.x][second.y] = source;
                board[first.x][first.y] = dest;
            } else if (dest == 3) {
                board[second.x][second.y] = 5;
                board[first.x][first.y] = 0;
            } else if (dest == 2) {
                if (board[third.x][third.y] == 0) {
                    board[second.x][second.y] = 1;
                    board[third.x][third.y] = 2;
                    board[first.x][first.y] = 0;
                } else if (board[third.x][third.y] == 3) {
                    board[second.x][second.y] = 1;
                    board[third.x][third.y] = 6;
                    board[first.x][first.y] = 0;
                }

            } else if (dest == 6) {
                if (board[third.x][third.y] == 0) {
                    board[second.x][second.y] = 5;
                    board[second.x][second.y + 1] = 2;
                    board[first.x][first.y] = 0;
                } else if (board[third.x][third.y] == 3) {
                    board[second.x][second.y] = 5;
                    board[third.x][third.y] = 6;
                    board[first.x][first.y] = 0;
                }

            }

        }
        else if (source == 5) {
            if (dest == 0) {
                board[second.x][second.y] = 1;
                board[first.x][first.y] = 3;
            } else if (dest == 3) {
                board[second.x][second.y] = 5;
                board[first.x][first.y] = 3;
            } else if (dest == 2) {

                if (board[third.x][third.y] == 0) {

                    board[second.x][second.y] = 1;
                    board[third.x][third.y] = 2;
                    board[first.x][first.y] = 3;

                } else if (board[third.x][third.y] == 3) {
                    board[second.x][second.y] = 1;
                    board[third.x][third.y] = 6;
                    board[first.x][first.y] = 3;
                }

            } else if (dest == 6) {
                if (board[third.x][third.y] == 0) {
                    board[second.x][second.y] = 5;
                    board[third.x][third.y] = 2;
                    board[first.x][first.y] = 3;

                } else if (board[third.x][third.y] == 3) {
                    board[second.x][second.y] = 5;
                    board[third.x][third.y] = 6;
                    board[first.x][first.y] = 3;
                }

            }

        }
        return board;
    }

    public ArrayList<State> getNextState(int [][]originalBoard,int cost) {
        point farmer=searchFarmer(originalBoard);
        ArrayList<State>states=new ArrayList<>();
        ArrayList<point> points=moveOne(originalBoard);
        for (point point : points) {
            point p1, p2;
            p1 = new point(point.x, point.y);
            p2 = new point(point.z, point.v);
            int[][] board = copyBord(originalBoard);
            board = move(board, farmer, p1, p2);
            states.add(new State(board,farmer,cost));
        }

        return states;
    }
}

