import java.util.Scanner;

public class Board {
    int[][] board = {
            {8, 8, 8, 8, 8, 8},
            {8, 0, 0, 0, 0, 8},
            {8, 0, 2, 2, 0, 8},
            {8, 9, 9, 3, 0, 8},
            {8, 9, 1, 3, 0, 8},
            {8, 9, 9, 9, 9, 8},
            {8, 8, 8, 8, 8, 8}
    };
    int[][] board2 = {
            {8, 8, 8, 8, 8, 8, 8, 8},
            {8, 9, 9, 9, 9, 9, 0, 8},
            {8, 9, 0, 0, 3, 9, 0, 8},
            {8, 9, 0, 2, 0, 9, 9, 8},
            {8, 9, 0, 2, 1, 3, 9, 8},
            {8, 8, 8, 8, 8, 8, 8, 8}
    };
    int[][] board3 = {
            {8, 8, 8, 8, 8, 8, 8, 8},
            {8, 0, 9, 9, 9, 9, 9, 8},
            {8, 9, 9, 3, 0, 9, 9, 8},
            {8, 9, 0, 3, 0, 0, 9, 8},
            {8, 9, 0, 2, 0, 9, 9, 8},
            {8, 9, 0, 2, 0, 9, 0, 8},
            {8, 9, 9, 0, 1, 9, 0, 8},
            {8, 0, 9, 9, 9, 9, 0, 8},
            {8, 8, 8, 8, 8, 8, 8, 8}
    };


   public int[][] level() {
       int level;
       Scanner input = new Scanner(System.in);

       while (true) {
           System.out.println(" choose level 1  ->  3");
           System.out.println(" 0 for exit game ");
           level = input.nextInt();
           switch (level) {
               case 0 -> System.exit(0);

               case 1 -> {
                   return board;
               }
               case 2 -> {
                   return board2;
               }
               case 3 -> {
                   return board3;
               }
               default -> System.out.println("Wrong selection");
           }

       }
   }
}
