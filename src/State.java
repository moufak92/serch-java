import java.util.Arrays;

import java.lang.*;

public class State implements Comparable<State>{
    int cost=0;
    int [][]board;
    point farmer;
    State parent;

    State(int [][]board, point farmer)
    {
        this.board=board;
        this.farmer=farmer;

    }

     State(int [][]board, point farmer,int cost)
    {
        this.board=board;
        this.farmer=farmer;
        this.cost=cost;

    }
    State(int [][]board, point farmer,int cost,State parent)
    {
        this.board=board;
        this.farmer=farmer;
        this.cost=cost;
        this.parent=parent;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State otherState = (State) obj;
        return Arrays.deepEquals(this.board, otherState.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);

    }

    @Override
    public int compareTo(State o) {
            return Integer.compare(this.cost,o.cost);
        }



    }


